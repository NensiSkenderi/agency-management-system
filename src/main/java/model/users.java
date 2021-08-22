package model;

public class users {

	private int user_id;
	private String password,full_name,group_access,user_status;
	
	public users(int user_id, String password, String full_name, String group_access, String user_status) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.full_name = full_name;
		this.group_access = group_access;
		this.user_status = user_status;
	}
	
	public users() {
		
	}

	public users(String password, String full_name, String group_access, String user_status) {
		super();
		this.password = password;
		this.full_name = full_name;
		this.group_access = group_access;
		this.user_status = user_status;
	}



	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getGroup_access() {
		return group_access;
	}

	public void setGroup_access(String group_access) {
		this.group_access = group_access;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}
	
	
}
