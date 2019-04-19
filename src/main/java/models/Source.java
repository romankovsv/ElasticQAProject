package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Source{

	@JsonProperty("is_read")
	private boolean isRead;

	@JsonProperty("author")
	private String author;

	@JsonProperty("name")
	private String name;

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
			"Source{" + 
			"is_read = '" + isRead + '\'' + 
			",author = '" + author + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}