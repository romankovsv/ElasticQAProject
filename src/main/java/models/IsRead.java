package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class IsRead{

	@JsonProperty("type")
	private String type;

	public IsRead(String type) {
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
			"IsRead{" + 
			"type = '" + type + '\'' + 
			"}";
		}
}