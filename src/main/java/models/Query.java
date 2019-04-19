package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Query{

	@JsonProperty("match")
	private Match match;

	public void setMatch(Match match){
		this.match = match;
	}

	public Query(Match match) {
		this.match = match;
	}

	public Match getMatch(){
		return match;
	}

	@Override
 	public String toString(){
		return 
			"Query{" + 
			"match = '" + match + '\'' + 
			"}";
		}
}