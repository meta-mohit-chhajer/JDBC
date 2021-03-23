package com.metacube.JDBC;

public class Order {
int id;
int amount;
String Date;
public Order(int id,int amount,String Date){
	this.id=id;
	this.amount=amount;
	this.Date=Date;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public String getDate() {
	return Date;
}
public void setDate(String date) {
	Date = date;
}

@Override
public String toString()
{
	return "Order [id = "+id+" amount = "+amount+" Date = "+Date+ " ]"; 
			
}

}
