package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SearchQuery{

	@JsonProperty("query")
	private Query query;

	public SearchQuery(Query query) {
		this.query = query;
	}

	public void setQuery(Query query){
		this.query = query;
	}

	public Query getQuery(){
		return query;
	}

	@Override
 	public String toString(){
		return 
			"SearchQuery{" + 
			"query = '" + query + '\'' + 
			"}";
		}
}