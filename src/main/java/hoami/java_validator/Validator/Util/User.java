package hoami.java_validator.Validator.Util;

import java.util.Date;

public class User {
	private String name;
	private int age;
	private Date birthday;
	private String email;
	private String password;


	public User(String name, int age, Date birthday, String email, String password) {
		this.setName(name);
		this.setAge(age);
		this.setBirthday(birthday);
		this.setEmail(email);
		this.setPassword(password);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
}
