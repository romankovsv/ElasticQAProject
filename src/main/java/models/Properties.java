package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Properties{

	@JsonProperty("is_read")
	private IsRead isRead;

	@JsonProperty("author")
	private Author author;

	@JsonProperty("name")
	private Name name;

	public Properties(IsRead isRead, Author author, Name name) {
		this.isRead = isRead;
		this.author = author;
		this.name = name;
	}

	public void setIsRead(IsRead isRead){
		this.isRead = isRead;
	}

	public IsRead getIsRead(){
		return isRead;
	}

	public void setAuthor(Author author){
		this.author = author;
	}

	public Author getAuthor(){
		return author;
	}

	public void setName(Name name){
		this.name = name;
	}

	public Name getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"Properties{" + 
			"is_read = '" + isRead + '\'' + 
			",author = '" + author + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}