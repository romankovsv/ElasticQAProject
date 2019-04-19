package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Mappings{

	@JsonProperty("properties")
	private Properties properties;

	public Mappings(Properties properties) {
		this.properties = properties;
	}

	public void setProperties(Properties properties){
		this.properties = properties;
	}

	public Properties getProperties(){
		return properties;
	}

	@Override
 	public String toString(){
		return 
			"Mappings{" + 
			"properties = '" + properties + '\'' + 
			"}";
		}
}