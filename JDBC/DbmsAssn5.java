package com.metacube.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbmsAssn5 {
	
	/*
	 * Used to establish the single connection between this program and database
	 * @return Connection object
	 */
	public static Connection getConnection(){
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/storefront", "root", "1234");
			
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
		return con;
	}
	
	/*
	 * Used to get the orders of particular user
	 * @param userId as int whose orders are to be fetched
	 * @return as int the number of orders of a user
	 */
	
	public int getOrders(int userId){
		String query="select o.o_id,o.time,o.amt from orders o join users u where u.u_id=o.u_id and o.status='shipped' order by o.time ";
	    List<Order> orders=new ArrayList<Order>();
	    try{
	    	Connection con=getConnection();
	    	PreparedStatement pstmt=con.prepareStatement(query);
	    	pstmt.setInt(1,userId);
	    	ResultSet set=pstmt.executeQuery();
	    	while(set.next())
	    	{
	    		orders.add(new Order(set.getInt("o_id"),set.getInt("amt"),set.getString("time")));
	    		
	    	}
	    	System.out.println("Total Orders : "+orders.size());
	    	for(Order order:orders)
	    		System.out.println(order);
	    	con.close();	
	    	
	    }catch(SQLException e){
	    	e.printStackTrace();
	    }
	    return orders.size();
	}
	
	/*
	 * Used to show all categories of store front
	*/
	public void showCategories()
	{
		String query="select c.cat_name,count(c.p_id) from category c left join produt p on c.p_id=p.p_id group by c.p_id";
		try{
			Connection con=getConnection();
			PreparedStatement pmtst=con.prepareStatement(query);
			System.out.println("Query - "+query);
			ResultSet set=pmtst.executeQuery();
			List<Category> parentList=new ArrayList<>();
			while(set.next()){
				parentList.add(new Category(set.getInt(2), set.getString(1)));
			}
			for(Category category:parentList)
				System.out.println("Category : "+category.getId()+"Name : "+category.getName());
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Used to set such products to inactive mode which are not ordered in last year
	 * @return number of products which are set to inactive mode
	 */
	
	public int setInactive(){
		String query="update product set active=0 where p_id not in(select o.p_id from orders o join product p where o.p_id=p.p_id	"+"and datediff(now(),o.time)<365";
		int inactive=0;
		try{
			Connection con=getConnection();
			PreparedStatement pmtst=con.prepareStatement(query);
			inactive=pmtst.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return inactive;
	
	}
	
	/*
	 * Used to add images of a product
	 * @param productId id of product as int whose images to be added
	 * @param images list of images for a particular product
	 * @return number of rows inserted
	 */
	public int addImage(int image_id,int p_id,List<String> images){
		String query="insert into Image(image_id,p_id,image_file) values(?,?,?)";
		int []result=null;
		try{
			Connection con=getConnection();
			PreparedStatement pmtst=con.prepareStatement(query);
			con.setAutoCommit(false);
			for(int count=0;count<images.size();count++){
				pmtst.setInt(1,image_id);
				pmtst.setInt(1, p_id);
				pmtst.setString(2, images.get(count));
				pmtst.addBatch();
			}
			result=pmtst.executeBatch();
			System.out.println("Number of Rows Inserted : "+result.length);
			con.commit();
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result.length;
	}
}
