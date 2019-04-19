package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Mapping{

	@JsonProperty("mappings")
	private Mappings mappings;

	public Mapping(Mappings mappings) {
		this.mappings = mappings;
	}

	public void setMappings(Mappings mappings){
		this.mappings = mappings;
	}

	public Mappings getMappings(){
		return mappings;
	}

	@Override
 	public String toString(){
		return 
			"Mapping{" + 
			"mappings = '" + mappings + '\'' + 
			"}";
		}
}