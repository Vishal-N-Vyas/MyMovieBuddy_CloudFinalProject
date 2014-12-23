
package com.mkyong.db;
/*
 * Copyright 2010-2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.mkyong.customer.model.Customer;
import com.mkyong.customer.model.User;


/**
 * Welcome to your new AWS Java SDK based project!
 *
 * This class is meant as a starting point for your console-based application that
 * makes one or more calls to the AWS services supported by the Java SDK, such as EC2,
 * SimpleDB, and S3.
 *
 * In order to use the services in this sample, you need:
 *
 *  - A valid Amazon Web Services account. You can register for AWS at:
 *       https://aws-portal.amazon.com/gp/aws/developer/registration/index.html
 *
 *  - Your account's Access Key ID and Secret Access Key:
 *       http://aws.amazon.com/security-credentials
 *
 *  - A subscription to Amazon EC2. You can sign up for EC2 at:
 *       http://aws.amazon.com/ec2/
 *
 *  - A subscription to Amazon SimpleDB. You can sign up for Simple DB at:
 *       http://aws.amazon.com/simpledb/
 *
 *  - A subscription to Amazon S3. You can sign up for S3 at:
 *       http://aws.amazon.com/s3/
 */
public class DatabaseWrapper {
   
    public static Connection connection;
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   // static final String DB_URL = "jdbc:mysql://localhost:3306/";
   // static final String DB_NAME = "moviebuddyusers";
    //  Database credentials
    //static final String USER = "root";
    //static final String PASS = "playuser";
    
    static final String tableName = "customers";
    static final String permissionTableName = "customer_roles";
    static final String profilePicTableName = "customer_pics";
    static final String prefTableName = "customer_prefs";
 
 
    
    static final String DB_URL = "jdbc:mysql://XXXX.XXXX.usXXXX.rds.amazonaws.com:3306/";
    		
    		
    static final String DB_NAME = "moviebuddy";
    
    static final String USER = "XXXX";
    static final String PASS = "XXXX";
    /**
     * RDS Database Functions
     * @param args
     */
    public static void main(String[] args) {
    	/* Assignment 1 Dead code
    	createTable("TwitData");
    	ArrayList<Twit> tList = TweetGet.readJSON("Obama");
    	insertRecord("TwitData", tList);
    	tList = TweetGet.readJSON("Bieber");
    	insertRecord("TwitData", tList);
    	tList = TweetGet.readJSON("America");
    	insertRecord("TwitData", tList);
    	tList = TweetGet.readJSON("India");
    	insertRecord("TwitData", tList);
    	tList = TweetGet.readJSON("Narendra Modi");
    	insertRecord("TwitData", tList);
    	tList = TweetGet.readJSON("Ebola");
    	insertRecord("TwitData", tList);
    	tList = TweetGet.readJSON("Columbia");
    	insertRecord("TwitData", tList);
    	tList = TweetGet.readJSON("New York");
    	insertRecord("TwitData", tList);
    	selectRecord("TwitData", "Obama");
    	//deleteTable("TwitData");
    	 */
    	//createTable("customers");
    	//deleteTable("customers");
    	String[] s = {"Ram"};
    	getCompatibility("Abhyuday", s);
    }
    
    public static int[] getCompatibility(String currentUser, String[] matchingUsers) {
    	   Connection conn = null;
    	   Statement stmt = null;
    	   int[] compatibility = new int[matchingUsers.length];
    	   int totalCompatibility = 0;
    	   for(int i=0;i<matchingUsers.length;i++) {
    		   compatibility[i] = 0;
    	   }
    	   try{
    	      //STEP 2: Register JDBC driver
    	      Class.forName("com.mysql.jdbc.Driver");

    	      //STEP 3: Open a connection
    	      //System.out.println("Connecting to a selected database...");
    	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
    	      //System.out.println("Connected database successfully...");
    	      
    	      //STEP 4: Execute a query
    	      //System.out.println("Inserting records into the table...");
    	      stmt = conn.createStatement();
    	      
    	      //Get movie prefs
    	      String sql = "SELECT prefValue FROM customer_prefs where userName = '" + currentUser + "'";
    	      ResultSet rsCurrentUser = stmt.executeQuery(sql);
    	      while(rsCurrentUser.next()) {
    	    	String prefValue = rsCurrentUser.getString("prefValue");
    	    	for(int i=0;i<matchingUsers.length; i++) {
    	    		sql = "SELECT prefValue FROM customer_prefs where userName = '" + matchingUsers[i] + "'";
    	    		ResultSet rsMatchingUser = conn.createStatement().executeQuery(sql);
    	    		System.out.println("i = " + i);
    	    		while(rsMatchingUser.next()) {
    	    		    String matchPrefValue = rsMatchingUser.getString("prefValue");
    	    		    /*
    	    			sql = "SELECT COUNT(*) As total FROM" +
    	   	    		  		"(" +
    	   	    		  		" SELECT pref_id FROM customer_prefs where userName = '" + currentUser + "' AND prefValue = '" + prefValue + "'" +
    	   	    		  		" intersect" +
    	   	    		  		" SELECT pref_id FROM customer_prefs where userName = '" + matchingUsers[i] + "' AND prefValue = '" + matchPrefValue + "'" +
    	   	    	  			" ) I";
    	   	    	  	*/
    	    		    /*
    	    		    sql = "SELECT COUNT(*) As total FROM" +
    	   	    		  		"(" +
    	   	    		  		" (SELECT pref_id FROM customer_prefs where userName = '" + currentUser + "' AND prefValue = '" + prefValue + "') a" +
    	   	    		  		" INNER JOIN " +
    	   	    		  		" (SELECT pref_id FROM customer_prefs where userName = '" + matchingUsers[i] + "' AND prefValue = '" + matchPrefValue + "') b" +
    	   	    	  			" ON (a.pref_id = b.pref_id)" +
    	   	    		  		" )";
    	    			ResultSet rs = conn.createStatement().executeQuery(sql);
    	    			while(rs.next()) {
    	    				compatibility[i] += rs.getInt("total");
    	    				totalCompatibility += rs.getInt("total");
    	    			}
    	    			*/
    	    		    System.out.println("pref1 = " + prefValue + " pref2 = " + matchPrefValue);
    	    		    if(matchPrefValue.equals(prefValue)) {
    	    		    	compatibility[i]++;
    	    				totalCompatibility++;
    	    		    }
    	    		}
    	    	}
    	      }

    	   }catch(SQLException se){
    	      //Handle errors for JDBC
    	      se.printStackTrace();
    	   }catch(Exception e){
    	      //Handle errors for Class.forName
    	      e.printStackTrace();
    	   }finally{
    	      //finally block used to close resources
    	      try{
    	         if(stmt!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	      }// do nothing
    	      try{
    	         if(conn!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	         se.printStackTrace();
    	      }//end finally try
    	   }//end try
    	   System.out.println("Tweet inserted...");
    	   System.out.println("Goodbye!");
    	   System.out.println("Total Compatibility = " + totalCompatibility);
    	   for(int i=0;i<matchingUsers.length; i++) {
    		   if(totalCompatibility == 0) {
    			   break;
    		   }
    		   compatibility[i] = (compatibility[i]/totalCompatibility)*100;
    		   System.out.println("Matching user = " + matchingUsers[i] + " Compatibility = " + compatibility[i]);
    	   }
    	   return compatibility;
    	}
    
    public static void createTable(String tableName) {
 	   Connection conn = null;
 	   Statement stmt = null;
 	   try{
 	      //STEP 2: Register JDBC driver
 	      Class.forName("com.mysql.jdbc.Driver");

 	      //STEP 3: Open a connection
 	      System.out.println("Connecting to a selected database...");
 	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
 	      System.out.println("Connected database successfully...");
 	      stmt = conn.createStatement();
 	      
 	      String sql = "CREATE TABLE " + tableName + " " +
                   "(userName VARCHAR(255) NOT NULL, " +
                   " firstName VARCHAR(255), " + 
                   " lastName VARCHAR(255), " +
                   " emailID VARCHAR(255), " +
                   " sex VARCHAR(255), " + 
                   " password VARCHAR(255), " +
                   //" content BLOB, " +  
                   " PRIMARY KEY ( userName ))"; 

 	      stmt.executeUpdate(sql);
 	      System.out.println("Created table in given database...");

 	   }catch(SQLException se){
 	      //Handle errors for JDBC
 	      se.printStackTrace();
 	   }catch(Exception e){
 	      //Handle errors for Class.forName
 	      e.printStackTrace();
 	   }finally{
 	      //finally block used to close resources
 	      try{
 	         if(stmt!=null)
 	            conn.close();
 	      }catch(SQLException se){
 	      }// do nothing
 	      try{
 	         if(conn!=null)
 	            conn.close();
 	      }catch(SQLException se){
 	         se.printStackTrace();
 	      }//end finally try
 	   }//end try
 	   System.out.println("Goodbye!");
 	}//end main
    
    public static void createTable() {
    	   Connection conn = null;
    	   Statement stmt = null;
    	   try{
    	      //STEP 2: Register JDBC driver
    	      Class.forName("com.mysql.jdbc.Driver");

    	      //STEP 3: Open a connection
    	      System.out.println("Connecting to a selected database...");
    	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
    	      System.out.println("Connected database successfully...");
    	      stmt = conn.createStatement();
    	      
    	      String sql = "CREATE TABLE " + tableName + " " +
                      "(userName VARCHAR(255) NOT NULL, " +
                      " firstName VARCHAR(255), " + 
                      " lastName VARCHAR(255), " +
                      " emailID VARCHAR(255), " +
                      " sex VARCHAR(255), " + 
                      " password VARCHAR(255), " +
                      //" content BLOB, " +  
                      " PRIMARY KEY ( userName ))"; 

    	      stmt.executeUpdate(sql);
    	      System.out.println("Created table in given database...");
    	      
    	      sql = "CREATE TABLE " + permissionTableName + " "
    	      		+ "(user_role_id int(11) NOT NULL AUTO_INCREMENT,"
    	      		+ " userName varchar(255) NOT NULL, "
    	    		+ " role varchar(45) NOT NULL, "
    	    		+ " PRIMARY KEY (user_role_id), "
    	    		+ " UNIQUE KEY uni_userName_role (role,userName), "
    	    		+ " KEY fk_userName_idx (userName), "
    	    		+ " CONSTRAINT fk_userName FOREIGN KEY (userName) REFERENCES customers (userName))";
    	      stmt.executeUpdate(sql);
    	      System.out.println("Created table in given database...");
    	      
    	      sql = "CREATE  TABLE " + profilePicTableName + "(userName VARCHAR(255) NOT NULL , content BINARY, PRIMARY KEY ( userName ))";
    	      stmt.executeUpdate(sql);
    	      
    	      sql = "CREATE TABLE " + prefTableName + " (pref_id int(11) NOT NULL AUTO_INCREMENT, userName varchar(255) NOT NULL,   prefType varchar(255),  prefValue varchar(255), PRIMARY KEY (pref_id))";
    	      System.out.println("Created table in given database...");
    	      
    	   }catch(SQLException se){
    	      //Handle errors for JDBC
    	      se.printStackTrace();
    	   }catch(Exception e){
    	      //Handle errors for Class.forName
    	      e.printStackTrace();
    	   }finally{
    	      //finally block used to close resources
    	      try{
    	         if(stmt!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	      }// do nothing
    	      try{
    	         if(conn!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	         se.printStackTrace();
    	      }//end finally try
    	   }//end try
    	   System.out.println("Goodbye!");
    	}//end main
    
    public static void insertPic(String userName, MultipartFile file) {
   	   Connection conn = null;
   	   Statement stmt = null;
   	   try{
   	      //STEP 2: Register JDBC driver
   	      Class.forName("com.mysql.jdbc.Driver");

   	      //STEP 3: Open a connection
   	      //System.out.println("Connecting to a selected database...");
   	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
   	      //System.out.println("Connected database successfully...");
   	      
   	      //STEP 4: Execute a query
   	      stmt = conn.createStatement();
   	      Blob blob = new javax.sql.rowset.serial.SerialBlob(file.getBytes());
   	      PreparedStatement st;
   	      st = conn.prepareStatement("insert into " + profilePicTableName + " (userName,content) values (?,?);");
   	      //now you bind the data to your parameters
   	      
   	      st.setNString(1, userName);
   	      st.setBlob(2, blob);
   	      System.out.println("Picture inserted...");
   	      //st.setNString(8, twit.content);
   	      //and then you can execute it
   	      st.executeUpdate();
   	      //System.out.println("Inserted records into the table...");

   	   }catch(SQLException se){
   	      //Handle errors for JDBC
   	      se.printStackTrace();
   	   }catch(Exception e){
   	      //Handle errors for Class.forName
   	      e.printStackTrace();
   	   }finally{
   	      //finally block used to close resources
   	      try{
   	         if(stmt!=null)
   	            conn.close();
   	      }catch(SQLException se){
   	      }// do nothing
   	      try{
   	         if(conn!=null)
   	            conn.close();
   	      }catch(SQLException se){
   	         se.printStackTrace();
   	      }//end finally try
   	   }//end try
   	   System.out.println("Goodbye!");
   	}
     
    
    
    public static void insertCustomer(Customer twit) {
  	   Connection conn = null;
  	   Statement stmt = null;
  	   try{
  	      //STEP 2: Register JDBC driver
  	      Class.forName("com.mysql.jdbc.Driver");

  	      //STEP 3: Open a connection
  	      //System.out.println("Connecting to a selected database...");
  	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
  	      //System.out.println("Connected database successfully...");
  	      
  	      //STEP 4: Execute a query
  	      //System.out.println("Inserting records into the table...");
  	      stmt = conn.createStatement();
  	      PreparedStatement st;
  	      st = conn.prepareStatement("insert into " + tableName + " (userName,firstName,lastName,emailID, sex, password) values (?,?,?,?,?,?);");
  	      //now you bind the data to your parameters
  	      st.setNString(1, twit.getUserName());
  	      st.setNString(2, twit.getFirstName());
  	      st.setNString(3, twit.getLastName());
  	      st.setNString(4, twit.getEmailID());
  	      st.setNString(5, twit.getSex());
  	      st.setNString(6, twit.getPassword());
  	      //st.setNString(8, twit.content);
  	      //and then you can execute it
  	      st.executeUpdate();
  	      //System.out.println("Inserted records into the table...");

  	   }catch(SQLException se){
  	      //Handle errors for JDBC
  	      se.printStackTrace();
  	   }catch(Exception e){
  	      //Handle errors for Class.forName
  	      e.printStackTrace();
  	   }finally{
  	      //finally block used to close resources
  	      try{
  	         if(stmt!=null)
  	            conn.close();
  	      }catch(SQLException se){
  	      }// do nothing
  	      try{
  	         if(conn!=null)
  	            conn.close();
  	      }catch(SQLException se){
  	         se.printStackTrace();
  	      }//end finally try
  	   }//end try
  	   System.out.println("Tweet inserted...");
  	   System.out.println("Goodbye!");
  	}
    
    public static void insertCustomerProfile(String userName, String prefType, String[] prefValues) {
    	   Connection conn = null;
    	   Statement stmt = null;
    	   try{
    	      //STEP 2: Register JDBC driver
    	      Class.forName("com.mysql.jdbc.Driver");

    	      //STEP 3: Open a connection
    	      //System.out.println("Connecting to a selected database...");
    	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
    	      //System.out.println("Connected database successfully...");
    	      
    	      //STEP 4: Execute a query
    	      //System.out.println("Inserting records into the table...");
    	      stmt = conn.createStatement();
    	      /*
    	      String sql = "DELETE FROM " + prefTableName + " " + 
	                   "(WHERE pref_id > 0 AND userName = '" + userName + "' AND prefType = '" + prefType + "')";
	          */
    	      String sql = "DELETE FROM customer_prefs WHERE pref_id > 0 AND userName = '" + userName + "' AND prefType = '" + prefType + "'";
    	      System.out.println(sql);
    	      stmt.executeUpdate(sql);
    	      //delete existing records first
    	      for(int i=0;i<prefValues.length;i++) {
    	    	  PreparedStatement st;
    	    	  st = conn.prepareStatement("insert into " + prefTableName + " (userName,prefType, prefValue) values (?,?,?);");
    	    	  //now you bind the data to your parameters
    	    	  st.setNString(1, userName);
    	    	  st.setNString(2, prefType);
    	    	  st.setNString(3, prefValues[i]);
    	    	  st.executeUpdate();
    	      }
    	      //st.setNString(8, twit.content);
    	      //and then you can execute it
    	      //System.out.println("Inserted records into the table...");

    	   }catch(SQLException se){
    	      //Handle errors for JDBC
    	      se.printStackTrace();
    	   }catch(Exception e){
    	      //Handle errors for Class.forName
    	      e.printStackTrace();
    	   }finally{
    	      //finally block used to close resources
    	      try{
    	         if(stmt!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	      }// do nothing
    	      try{
    	         if(conn!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	         se.printStackTrace();
    	      }//end finally try
    	   }//end try
    	   System.out.println("Tweet inserted...");
    	   System.out.println("Goodbye!");
    	}
    
    public static void insertCustomerPermission(String userName, String role) {
   	   Connection conn = null;
   	   Statement stmt = null;
   	   try{
   	      //STEP 2: Register JDBC driver
   	      Class.forName("com.mysql.jdbc.Driver");

   	      //STEP 3: Open a connection
   	      //System.out.println("Connecting to a selected database...");
   	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
   	      //System.out.println("Connected database successfully...");
   	      
   	      //STEP 4: Execute a query
   	      //System.out.println("Inserting records into the table...");
   	      stmt = conn.createStatement();
   	      PreparedStatement st;
   	      st = conn.prepareStatement("insert into " + permissionTableName + " (userName,role) values (?,?);");
   	      //now you bind the data to your parameters
   	      st.setNString(1, userName);
   	      st.setNString(2, role);
   	      //st.setNString(8, twit.content);
   	      //and then you can execute it
   	      st.executeUpdate();
   	      //System.out.println("Inserted records into the table...");

   	   }catch(SQLException se){
   	      //Handle errors for JDBC
   	      se.printStackTrace();
   	   }catch(Exception e){
   	      //Handle errors for Class.forName
   	      e.printStackTrace();
   	   }finally{
   	      //finally block used to close resources
   	      try{
   	         if(stmt!=null)
   	            conn.close();
   	      }catch(SQLException se){
   	      }// do nothing
   	      try{
   	         if(conn!=null)
   	            conn.close();
   	      }catch(SQLException se){
   	         se.printStackTrace();
   	      }//end finally try
   	   }//end try
   	   System.out.println("Tweet inserted...");
   	   System.out.println("Goodbye!");
   	}
    
    public static boolean authenticateUser(User user) {
       Connection conn = null;
 	   Statement stmt = null;
 	   try{
 	      //STEP 2: Register JDBC driver
 	      Class.forName("com.mysql.jdbc.Driver");

 	      //STEP 3: Open a connection
 	      System.out.println("Connecting to a selected database...");
 	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
 	      System.out.println("Connected database successfully...");
 	      
 	      //STEP 4: Execute a query
 	      System.out.println("Creating statement...");
 	      stmt = conn.createStatement();
 	      String sql = "SELECT userName, password FROM " + tableName;
	      //System.out.println(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      System.out.println(user.getUserName() + user.getPassword());
	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         String userName  = rs.getNString("userName");
	         String password  = rs.getNString("password");
	         if(user !=null && userName !=null && password !=null &&  user.getUserName().equals(userName) && user.getPassword().equals(password)) {
	        	 rs.close();
	        	 return true;
	         }
	      }
	      rs.close();
 	   }catch(SQLException se){
 	      //Handle errors for JDBC
 	      se.printStackTrace();
 	   }catch(Exception e){
 	      //Handle errors for Class.forName
 	      e.printStackTrace();
 	   }finally{
 	      //finally block used to close resources
 	      try{
 	         if(stmt!=null)
 	            conn.close();
 	      }catch(SQLException se){
 	      }// do nothing
 	      try{
 	         if(conn!=null)
 	            conn.close();
 	      }catch(SQLException se){
 	         se.printStackTrace();
 	      }//end finally try
 	   }//end try
 	   System.out.println("Goodbye!");
 	   return false;
    }
    public static void deleteRecord(String userName) {
    	   Connection conn = null;
    	   Statement stmt = null;
    	   try{
    	      //STEP 2: Register JDBC driver
    	      Class.forName("com.mysql.jdbc.Driver");

    	      //STEP 3: Open a connection
    	      System.out.println("Connecting to a selected database...");
    	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
    	      System.out.println("Connected database successfully...");
    	      
    	      //STEP 4: Execute a query
    	      System.out.println("Creating statement...");
    	      stmt = conn.createStatement();
    	      String sql = "DELETE FROM " + tableName + " " + 
    	                   "WHERE userName = " + userName;
    	      stmt.executeUpdate(sql);
    	      
    	   }catch(SQLException se){
    	      //Handle errors for JDBC
    	      se.printStackTrace();
    	   }catch(Exception e){
    	      //Handle errors for Class.forName
    	      e.printStackTrace();
    	   }finally{
    	      //finally block used to close resources
    	      try{
    	         if(stmt!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	      }// do nothing
    	      try{
    	         if(conn!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	         se.printStackTrace();
    	      }//end finally try
    	   }//end try
    	   System.out.println("Goodbye!");
    	}//end main
    
    public static void deleteAllRecords() {
 	   Connection conn = null;
 	   Statement stmt = null;
 	   try{
 	      //STEP 2: Register JDBC driver
 	      Class.forName("com.mysql.jdbc.Driver");

 	      //STEP 3: Open a connection
 	      System.out.println("Connecting to a selected database...");
 	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
 	      System.out.println("Connected database successfully...");
 	      
 	      //STEP 4: Execute a query
 	      System.out.println("Creating statement...");
 	      stmt = conn.createStatement();
 	      String sql = "DELETE FROM " + tableName;
 	      stmt.executeUpdate(sql);
 	      
 	   }catch(SQLException se){
 	      //Handle errors for JDBC
 	      se.printStackTrace();
 	   }catch(Exception e){
 	      //Handle errors for Class.forName
 	      e.printStackTrace();
 	   }finally{
 	      //finally block used to close resources
 	      try{
 	         if(stmt!=null)
 	            conn.close();
 	      }catch(SQLException se){
 	      }// do nothing
 	      try{
 	         if(conn!=null)
 	            conn.close();
 	      }catch(SQLException se){
 	         se.printStackTrace();
 	      }//end finally try
 	   }//end try
 	   System.out.println("Goodbye!");
 	}//end main
 
    /*
    public static ArrayList<Customer> selectRecord(String tableName, String filterKeyword) {
    	   Connection conn = null;
    	   Statement stmt = null;
    	   ArrayList<Customer> tList = new ArrayList<Customer>();
    	   try{
    	      //STEP 2: Register JDBC driver
    	      Class.forName("com.mysql.jdbc.Driver");

    	      //STEP 3: Open a connection
    	      System.out.println("Connecting to a selected database...");
    	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
    	      System.out.println("Connected database successfully...");
    	      
    	      //STEP 4: Execute a query
    	      System.out.println("Creating statement...");
    	      stmt = conn.createStatement();

    	      String sql = "SELECT id, latitude, longitude, keyword, screenName, time, sentiment, content FROM " + tableName;
    	      if(!filterKeyword.equalsIgnoreCase("All")) {
    	    	  sql = sql + " WHERE keyword" + " = '" + filterKeyword +"'";
    	      }
    	      //System.out.println(sql);
    	      ResultSet rs = stmt.executeQuery(sql);
 
    	      //STEP 5: Extract data from result set
    	      while(rs.next()){
    	         //Retrieve by column name
    	         long id  = rs.getLong("id");
    	         String keyword = rs.getString("keyword");
    	         String screenName = rs.getString("screenName");
    	         String content = rs.getString("content");
    	         double latitude = rs.getDouble("latitude");
    	         double longitude = rs.getDouble("longitude");
    	         String time = rs.getString("time");
    	         String sentiment = rs.getString("sentiment");
    	         Customer t = new Customer(screenName, content, latitude, longitude, keyword, id, time);
    	         t.sentiment = sentiment;
    	         tList.add(t);
    	         //Display values
    	         System.out.print("keyword: " + keyword);
    	         System.out.print(", screenName: " + screenName);
    	         System.out.print(", content: " + content);
    	         System.out.println(", latitude: " + latitude);
    	         System.out.print(", longitude: " + longitude);
    	         System.out.print(", time: " + longitude);
    	         System.out.println(", id: " + id);
    	         System.out.println(", sentiment: " + sentiment);
    	      }
    	      rs.close();
    	   }catch(SQLException se){
    	      //Handle errors for JDBC
    	      se.printStackTrace();
    	   }catch(Exception e){
    	      //Handle errors for Class.forName
    	      e.printStackTrace();
    	   }finally{
    	      //finally block used to close resources
    	      try{
    	         if(stmt!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	      }// do nothing
    	      try{
    	         if(conn!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	         se.printStackTrace();
    	      }//end finally try
    	   }//end try
    	   System.out.println("Goodbye!");
    	   return tList;
    	}//end main
    */
    public static void deleteTable() {
    	   Connection conn = null;
    	   Statement stmt = null;
    	   try{
    	      //STEP 2: Register JDBC driver
    	      Class.forName("com.mysql.jdbc.Driver");

    	      //STEP 3: Open a connection
    	      System.out.println("Connecting to a selected database...");
    	      conn = DriverManager.getConnection(DB_URL + DB_NAME, USER, PASS);
    	      System.out.println("Connected database successfully...");
    	      
    	      //STEP 4: Execute a query
    	      System.out.println("Deleting table in given database...");
    	      stmt = conn.createStatement();
    	      
    	      String sql = "DROP TABLE " + tableName;
    	 
    	      stmt.executeUpdate(sql);
    	      System.out.println("Table  deleted in given database...");
    	   }catch(SQLException se){
    	      //Handle errors for JDBC
    	      se.printStackTrace();
    	   }catch(Exception e){
    	      //Handle errors for Class.forName
    	      e.printStackTrace();
    	   }finally{
    	      //finally block used to close resources
    	      try{
    	         if(stmt!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	      }// do nothing
    	      try{
    	         if(conn!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	         se.printStackTrace();
    	      }//end finally try
    	   }//end try
    	   System.out.println("Goodbye!");
    	}//end main
}
