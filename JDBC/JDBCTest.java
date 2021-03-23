package com.metacube.JDBC;
import java.util.*;
import org.junit.*;
public class JDBCTest {
	/*
	 * JDBCTest is used to test the JDBC class functionality
	 */
	@Test
	public void getOrders()
	{
		DbmsAssn5 db=new DbmsAssn5();
		Assert.assertEquals(1,db.getOrders(1));
	}
	
	/*
	 * Tests adding images to products
	 */
	@Test
	public void addImages() {
		DbmsAssn5 sf = new DbmsAssn5();
		List<String> imageList = new ArrayList<String>();
		imageList.add("shirt1.jpg");
		imageList.add("shirt2.jpg");
		imageList.add("shirt3.jpg");
		imageList.add("shirt4.jpg");
		Assert.assertEquals(4, sf.addImage(1, 1, imageList));
	}
}
