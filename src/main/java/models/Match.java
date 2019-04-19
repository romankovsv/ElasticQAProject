package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Match{

	@JsonProperty("name")
	private String name;

	public Match(String name) {
		this.name = name;
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
			"Match{" + 
			"name = '" + name + '\'' + 
			"}";
		}
}