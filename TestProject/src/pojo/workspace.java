package pojo;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = "id",allowSetters = true)
public class workspace {
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private int i;
	private String name;
	private String type;
	private String description;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String id;
	@JsonIgnore
	private HashMap<String,String> myHashMap;
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public HashMap<String, String> getMyHashMap() {
		return myHashMap;
	}

	public void setMyHashMap(HashMap<String, String> myHashMap) {
		this.myHashMap = myHashMap;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public workspace() {}
	
	public workspace(String name, String type, String description) {

		this.name = name;
		this.type = type;
		this.description = description;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
}
