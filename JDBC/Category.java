package com.metacube.JDBC;

public class Category {
String Name;
int id;
public Category(int id,String Name){
	this.id=id;
	this.Name=Name;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
}
