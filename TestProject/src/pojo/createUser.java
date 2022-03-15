package pojo;

public class createUser {

	private String name;
	private String username;
	private String email;
	private address address;
	private int id;

	public createUser() {}
	public createUser(String name, String username, String email, pojo.address address, int id) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.address = address;
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public address getAddress() {
		return address;
	}
	public void setAddress(address address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
}
