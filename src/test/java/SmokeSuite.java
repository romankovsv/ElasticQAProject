import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import config.TestConfig;
import io.restassured.response.Response;
import models.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SmokeSuite extends TestConfig {

    public static final String INDEX = "book";
    public static final String DOC_PATH = "/_doc/1";
    public static final String SEARCH = "/_search";
    public static final String FORMAT_YAML = "?format=yaml";

    @BeforeMethod
    public void putMappings() {

        delete(INDEX);

        Properties properties = new Properties(new IsRead("boolean"), new Author("text"), new Name("text"));
        Mapping mapping = new Mapping(new Mappings(properties));

        given()
                .body(mapping)
                .when()
                .put(INDEX)
                .then()
                .statusCode(200).log().all();

    }



    @Test(groups = "smoke")
    public void verifyStoreDocument() {

        final String name = Utils.genRandomString();
        final String author = Utils.genRandomString();
        final Boolean is_read = new Random().nextBoolean();

        Document document = new Document(is_read, author, name);

        given()
                .body(document)
                .when()
                .put(INDEX + DOC_PATH)
                .then()
                .assertThat()
                .statusCode(201)
                .log()
                .all();

        Response response = get(INDEX + DOC_PATH);

        final String actualIndex = response.path("_index").toString();
        assertEquals(actualIndex, INDEX, String.format("Wrong index! Expected: %s , actual: %s", actualIndex,
                INDEX));

        final String actualName = response.path("_source.name");
        assertEquals(actualName, name, String.format("Wrong name! Expected: %s, actual: %s", name, actualName));

        final String actualAuthor = response.path("_source.author");
        assertEquals(actualAuthor, author, String.format("Wrong author! Expected: %s, actual: %s", author,
                actualAuthor));

        final Boolean actualIsRead = response.path("_source.is_read");
        assertEquals(actualIsRead, is_read, String.format("Wrong value! Expected: %s, actual: %s", is_read,
                actualIsRead));
    }


    @Test(groups = "smoke")
    public void verifyMatchQuery() {

        final String name = Utils.genRandomString();
        final String author = Utils.genRandomString();
        final Boolean is_read = new Random().nextBoolean();

        Document document = new Document(is_read, author, name);

        given()
                .body(document)
                .when()
                .put(INDEX + DOC_PATH)
                .then()
                .assertThat()
                .statusCode(201)
                .log()
                .all();

        SearchQuery query = new SearchQuery(new Query(new Match(name)));

        Response response;
        int counter = 0;
        String result;

        do {
            response = given().body(query)
                               .when()
                               .get(INDEX + SEARCH);

            result = response.path("hits.hits._index").toString();

            if (result.contains(INDEX))
                break;
            counter++;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (counter <= 10);

        assertTrue(response.jsonPath().getList("hits.hits").size() == 1);

    }

    @Test(groups = "smoke")
    public void validateStoredDoc() {

        final String name = Utils.genRandomString();
        final String author = Utils.genRandomString();
        final Boolean is_read = new Random().nextBoolean();

        Document document = new Document(is_read, author, name);

        given()
                .body(document)
                .when()
                .put(INDEX + DOC_PATH)
                .then()
                .assertThat()
                .statusCode(201)
                .log()
                .all();

        Document actualDocument = get(INDEX + DOC_PATH).jsonPath()
                                          .getObject("_source", Document.class);

        assertEquals(actualDocument, document);
    }


    @Test(groups = "smoke")
    public void checkYAMLFormat() throws IOException, ProcessingException {

        final String name = Utils.genRandomString();
        final String author = Utils.genRandomString();
        final Boolean is_read = new Random().nextBoolean();

        Document document = new Document(is_read, author, name);

        given()
                .body(document)
                .when()
                .put(INDEX + DOC_PATH)
                .then()
                .assertThat()
                .statusCode(201)
                .log()
                .all();

        SearchQuery query = new SearchQuery(new Query(new Match(name)));
        String yaml;

        int counter = 0;

        do {
            yaml = given().body(query)
                           .when()
                           .get(INDEX + SEARCH + FORMAT_YAML)
                           .getBody()
                           .asString();

            if (yaml != null)
                break;
            counter++;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (counter <= 10);

        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
        Object obj = yamlReader.readValue(yaml, Object.class);
        ObjectMapper jsonWriter = new ObjectMapper();
        String jsonSting = jsonWriter.writeValueAsString(obj);

        JsonNode schemaNode = JsonLoader.fromResource("/jsonschema.json");
        JsonNode data = JsonLoader.fromString(jsonSting);
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema schema = factory.getJsonSchema(schemaNode);
        ProcessingReport report = schema.validate(data);

        Assert.assertTrue(report.isSuccess(), "Invalid json");

    }


    @AfterMethod
    public void cleanup() {
        delete(INDEX);
    }
}
