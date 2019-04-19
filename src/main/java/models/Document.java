package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import java.util.Objects;

@Generated("com.robohorse.robopojogenerator")
public class Document{

	@JsonProperty("is_read")
	private boolean isRead;

	@JsonProperty("author")
	private String author;

	@JsonProperty("name")
	private String name;

	public Document(boolean isRead, String author, String name) {
		this.isRead = isRead;
		this.author = author;
		this.name = name;
	}

    public Document() {

    }

	public void setIsRead(boolean isRead){
		this.isRead = isRead;
	}

	public boolean isIsRead(){
		return isRead;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"Document{" + 
			"is_read = '" + isRead + '\'' + 
			",author = '" + author + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Document document = (Document) o;
		return isRead == document.isRead &&
					   Objects.equals(author, document.author) &&
					   Objects.equals(name, document.name);
	}

	@Override
	public int hashCode() {

		return Objects.hash(isRead, author, name);
	}
}