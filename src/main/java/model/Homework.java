package model;

import org.springframework.web.multipart.MultipartFile;

public class Homework {
	private int id;
	private String name;
	private String telephone;
	private String email;
	private String major;
	private String post;
	private String address;
	private MultipartFile homework_file;
	private String homework;
	private int user_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public MultipartFile getHomework_file() {
		return homework_file;
	}
	public void setHomework_file(MultipartFile homework_file) {
		this.homework_file = homework_file;
	}
	public String getHomework() {
		return homework;
	}
	public void setHomework(String homework) {
		this.homework = homework;
	}
	
}
