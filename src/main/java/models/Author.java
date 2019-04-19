package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Author{

	@JsonProperty("type")
	private String type;

	public Author(String type) {
		this.type = type;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"Author{" + 
			"type = '" + type + '\'' + 
			"}";
		}
}