package com.srccodes.example;

import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.QueryParam;


public class DatabaseWrapper {
	

    public static Connection connection;
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    //static final String DB_URL = "jdbc:mysql://localhost:3306/";
    
    static final String DB_URL = "jdbc:mysql://mymoviebuddy.c2snbmfpd8nh.us-west-2.rds.amazonaws.com:3306/";
    		
    		
    static final String DB_NAME = "moviebuddy";
    
    static final String USER = "awsuser";
    static final String PASS = "mymoviepwd";
    
    //  Database credentials
//    static final String USER = "mymoviebuddy1";
//    static final String PASS = "mymoviebuddy_123";
//    
    
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//createDatabase(DB_NAME);

    	//createMovieQueueTable();
    	
		//createConnectRequestsTable();
		
		//deleteTable("moviequeue");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf.parse("21/12/2014");
		String dt="2014-12-21" ;
		//Date date = Date.valueOf( dt);
		
		UserMovieSelection userMovie = new UserMovieSelection(1, "MV006178670000", "InterStellar", 10025, dt);
		
//		UserMovieSelection userMovie2 = new UserMovieSelection(2, "MV006178670000", "InterStellar", 10025, dt);
//		UserMovieSelection userMovie3 = new UserMovieSelection(3, "MV006178670000", "InterStellar", 10025, dt);
//		UserMovieSelection userMovie4 = new UserMovieSelection(4, "2", "InterStellar", 10025, dt);
//		
//		insertMovieQueue(userMovie2);
//		insertMovieQueue(userMovie3);
//		insertMovieQueue(userMovie4);
		
		/*List<UserMovieSelection> filteredUsers = getMatchingProfiles(userMovie);
		
		for (UserMovieSelection filteredUser : filteredUsers)
		{
			System.out.println(filteredUser.getMovieId() + " " + filteredUser.getMovieName() + " " + filteredUser.getUserId() + " " + filteredUser.getZipCode());
		}*/
		
		//ConnectRequest connectRequest = new ConnectRequest(1, 2, "MV006178670000", "InterStellar", 10025, dt);
		//insertConnectRequest(connectRequest);
		
		/*List<ConnectRequest> connectRequests = getOpenReceivedRequests(2);
		
		for (ConnectRequest connectRequest : connectRequests)
		{
			System.out.println("User Id1 : " + connectRequest.getUserId1() + " " + "User Id2 : " + connectRequest.getUserId2() + " " + connectRequest.getMovieName() + " " + connectRequest.getMovieId() + " " + connectRequest.getDate() ) ;
		}*/
		
		/*List<ConnectRequest> sentRequests = getOpenSentRequests(1);
		
		for (ConnectRequest sentRequest : sentRequests)
		{
			System.out.println("User Id1 : " + sentRequest.getUserId1() + " " + "User Id2 : " + sentRequest.getUserId2() + " " + sentRequest.getMovieName() + " " + sentRequest.getMovieId() + " " + sentRequest.getDate() ) ;
		}*/
		
		List<ConnectRequest> closedRequests = getClosedMatchedAndUnmatchedRequests(1, true);
		List<ConnectRequest> closedDeniedRequests = getClosedMatchedAndUnmatchedRequests(1, false);
		for (ConnectRequest closedRequest : closedRequests)
		{
			System.out.println("User Id1 : " + closedRequest.getUserId1() + " " + "User Id2 : " + closedRequest.getUserId2() + " " + closedRequest.getMovieName() + " " + closedRequest.getMovieId() + " " + closedRequest.getDate() ) ;
		}

    }
	
	 public static void createDatabase(String databaseName) {
  	   Connection conn = null;
  	   Statement stmt = null;
  	   try{
  	      //STEP 2: Register JDBC driver
  	      Class.forName("com.mysql.jdbc.Driver");

  	      //STEP 3: Open a connection
  	      System.out.println("Creating database..." + databaseName);
  	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
  	      
  	      stmt = conn.createStatement();
  	      String sql = "CREATE DATABASE " + databaseName; 

  	      stmt.executeUpdate(sql);
  	      System.out.println("Created database successfully...");

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
	 
	 
	 public static void createConnectRequestsTable() {
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
	  	      
	  	      String sql = "CREATE TABLE connectrequests "+
	                    "(requestid BIGINT not NULL AUTO_INCREMENT,  " +
	                    " queueid1 BIGINT not NULL, " +
	                    " queueid2 BIGINT not NULL, " + 
	                    " movieid VARCHAR(255) not NULL, " +
	                    " moviename VARCHAR(255), " + 
	                    " user1accept BOOL , " + 
	                    " user2accept BOOL , " +
	                    " PRIMARY KEY ( requestid ))"; 

	  	      stmt.executeUpdate(sql);
	  	      System.out.println("Created table request matching in given database...");

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
	 
	 
	 
	 public static void createUsersTable() {
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
	  	      
	  	      String sql = " CREATE TABLE customers " +
						     " (userid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,  " +
						     " userName VARCHAR(255) NOT NULL,   " +
						     "  firstName VARCHAR(255),   " +
						     "  lastName VARCHAR(255),  " +
						     "  emailID VARCHAR(255),   " +
						     "  sex VARCHAR(255),   " +
						     " password VARCHAR(255),  " +
						     "  UNIQUE KEY ( userName ))    ENGINE = InnoDB " +
						     " DEFAULT CHARACTER SET = utf8;"; 

	  	      stmt.executeUpdate(sql);
	  	      System.out.println("Created table request matching in given database...");

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


	 
	 
	 public static List<ConnectRequest> searchUser(String userName, UserMovieSelection userMovie){
	  	   Connection conn = null;
	  	   Statement stmt = null;
	  	   List<ConnectRequest> connectRequests = new ArrayList<ConnectRequest>();
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

	  	      String sql = "SELECT userid , c.userName ,firstName, lastName, emailID   from customers c  where c.userName like '%"+ userName +"%' and c.userid != " + userMovie.getUserId();
	  	      System.out.println(sql);
	  	      
	  	      //System.out.println(sql);
	  	      ResultSet rs = stmt.executeQuery(sql);
	  	      HashMap<Long, ConnectRequest> hm = new HashMap<Long, ConnectRequest>();
	  	      //STEP 5: Extract data from result set
	  	      while(rs.next()){
	  	         //Retrieve by column name
	  	         long userId_returned  = rs.getLong("userid");
	  	         String userNameFull = rs.getString("userName");
	 	         //String email = rs.getString("emailID");
	 	         String firstName = rs.getString("firstName");   
	 	         String lastName = rs.getString("lastName");
	  	         ConnectRequest connectRequest = new ConnectRequest( userMovie.getUserId(),userId_returned , userMovie.getMovieId(), userMovie.getMovieName(),userMovie.getZipCode(), userMovie.getDate());
	  	         //userFirstName2, lastname2, emailid2, userName2 
	  	         	//connectRequest.setEmailId(email);
	  	       		connectRequest.setFirstName(firstName);
	  	       		connectRequest.setLastName(lastName);
	  	   		connectRequest.setUserName2(userNameFull);
		         connectRequest.setAjaxButton("<button onclick=\"inviteFriend(" +userMovie.getUserId() +","+userId_returned+ ",'"+ userMovie.getMovieId() +"','"+ userMovie.getMovieName() + "','"+ userMovie.getZipCode() + "','"+ userMovie.getDate() + "')\">Invite User</button>");        		 
	  	         connectRequest.setRequestCount(1);
	  	       hm.put(connectRequest.getUserId2(), connectRequest);
	  	   /*    //UserMovieSelection filteredUserMovie = new UserMovieSelection(userId, movieId, movieName, zipCode, date);
	  	       if(requestCount==0){
	  	    	   filteredUserMovie.setAjaxButton("<button onclick=\"connectWithUser('"+ userMovie.getUserId() + "','"+  filteredUserMovie.getUserId() + "','"+  userMovie.getMovieId()   + "','"+  userMovie.getMovieName() + "','"+  dateToStr + "','"+  userMovie.zipCode + "')\">Connect Now</button>");
	  	       }
	  	       else if (requestStatus==null ){
	  	  	    	   filteredUserMovie.setAjaxButton("<button disabled onclick=\"connectWithUser('"+ userMovie.getUserId() + "','"+  filteredUserMovie.getUserId() + "','"+  userMovie.getMovieId()   + "','"+  userMovie.getMovieName() + "','"+  dateToStr + "','"+  userMovie.zipCode + "')\">Sent</button>");
	  	  	   }
	  	       else if ( requestStatus.equals("1") ){
	  	    	   filteredUserMovie.setAjaxButton("<button disabled onclick=\"connectWithUser('"+ userMovie.getUserId() + "','"+  filteredUserMovie.getUserId() + "','"+  userMovie.getMovieId()   + "','"+  userMovie.getMovieName() + "','"+  dateToStr + "','"+  userMovie.zipCode + "')\">Accepted</button>");
	    	     
	  	       }else if ( requestStatus.equalsIgnoreCase("0") ){
	  	    	   filteredUserMovie.setAjaxButton("<button disabled onclick=\"connectWithUser('"+ userMovie.getUserId() + "','"+  filteredUserMovie.getUserId() + "','"+  userMovie.getMovieId()   + "','"+  userMovie.getMovieName() + "','"+  dateToStr + "','"+  userMovie.zipCode + "')\">Denied</button>");
	    	     
	  	       }
	  	       */
	  	       
	  	       	connectRequests.add(connectRequest);
	  	  	    System.out.println(userMovie.getUserId()+","+userId_returned +","+ userMovie.getMovieId()+","+userMovie.getMovieName()+","+userMovie.getZipCode()+","+ userMovie.getDate());
	  	      }
	  	      
	  	    List<ConnectRequest> cr= getDashBoard(userMovie.getUserId());
	  	    for(ConnectRequest c :cr){
	  	    	if(c.getMovieId().equals(userMovie.getMovieId()) && c.getZipCode()== userMovie.getZipCode() &&  
	  	    			c.getDate().equals(userMovie.getDate()) && hm.containsKey(c.getUserId2())){
	  	    		ConnectRequest temp= hm.get(c.getUserId2());
	  	    		temp.setAjaxButton(c.getAjaxButton());
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
	  	   return connectRequests;
	 }
	 
	 
	 public static List<ConnectRequest> getDashBoard(long userid) {
	 
	     	List<ConnectRequest> openReq=DatabaseWrapper.getOpenReceivedRequests( userid);
	    	List<ConnectRequest> openSent= DatabaseWrapper.getOpenSentRequests( userid);
	    	List<ConnectRequest> closedAccepted=DatabaseWrapper.getClosedMatchedAndUnmatchedRequests(userid,true);
	    	List<ConnectRequest> closedDenied=DatabaseWrapper.getClosedMatchedAndUnmatchedRequests(userid,false);
	    	
	    	openReq.addAll(openSent);
	    	openReq.addAll(closedAccepted);
	    	openReq.addAll(closedDenied);
	    	return openReq;
	 }
	 public static List<ConnectRequest> getOpenReceivedRequests(long userId) {
		 List<ConnectRequest> connectRequests = new ArrayList<ConnectRequest>();
		 
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

	  	      String sql = "SELECT distinct m1.queueid, m1.userid, m1.movieid, m1.moviename, m1.zipcode, m1.date,  "+
	  	      "	(select max(username) from customers where userid= m1.userid) as username	"  
	  	      		       + "FROM " 
	  	      		       + " moviequeue m1, "
	  	      		       + " connectrequests c, "
	  	      		       + " moviequeue m2 "
	  	      		       + " WHERE m2.userid = "+ userId 
	  	    		       + "   AND m1.queueid = c.queueid1 "
	  	    		       + "   AND m2.queueid = c.queueid2  "
	  	    		       + "   AND c.user1accept = TRUE"
	  	      			   + "   AND (c.user2accept is NULL) ";
	  	      		       
	  	      System.out.println("SQL for getOpenReceivedRequests : ");
	  	      System.out.println(sql);
	  	      
	  	      //System.out.println(sql);
	  	      ResultSet rs = stmt.executeQuery(sql);
		  		 Map<String, String>  imgList=Images.getMovieImages();
	  	      //STEP 5: Extract data from result set
	  	      while(rs.next()){
	  	         //Retrieve by column name
	  	         long userId_returned  = rs.getLong("userid");
	  	         String movieId  = rs.getString("movieid");
	  	         long zipcode  = rs.getLong("zipcode");
	  	         String movieName  = rs.getString("moviename");
	  	         Long zipCode  = rs.getLong("zipcode");
	  	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 			 String dateToStr = sdf.format(rs.getDate("date"));
	  	         String date = dateToStr;
	  	         String username2=rs.getString("username");
	  	         
	  	         ConnectRequest connectRequest = new ConnectRequest( userId,userId_returned , movieId, movieName, zipCode, date);
		         connectRequest.setAjaxButton("<button onclick=\"acceptRequest("+userId_returned+ ","+userId + ",'"+ movieId +"','"+ movieName + "','"+ dateToStr + "','"+ zipCode + "')\">Accept Request</button><br><br>" 
		        		 + "<button onclick=\"denyRequest("+userId_returned+ ","+userId + ",'"+ movieId +"','"+ movieName + "','"+ dateToStr + "','"+ zipCode + "')\">Deny Request</button>");
	  	         connectRequest.setRequestCount(1);
	  	         connectRequest.setRequestStatus("NULL");
	        	if(imgList.containsKey(movieId)){
	          		String src=imgList.get(movieId);
	          		connectRequest.setMovieImage("<img src=\""+ src +"\" style=\"width:100;height:150;\" >");
	          	}else{
	          		connectRequest.setMovieImage("<img src=\"http://comm.soc.northwestern.edu/mscblog/files/2013/03/movie_night.jpg\" style=\"width:100;height:150;\" >");
	          	}
	 
	        	 connectRequest.setUserName2(username2);
	        	
	  	         connectRequests.add(connectRequest);
	  	          
	  	  	     //System.out.println(userId +","+ movieId + "," + zipCode + "," + date);
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
	  	 return connectRequests;
	 }
	 
	 public static List<ConnectRequest> getOpenSentRequests(long userId) {
		 List<ConnectRequest> connectRequests = new ArrayList<ConnectRequest>();
		 
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

	  	      String sql = "SELECT distinct m2.queueid, m2.userid, m2.movieid, m2.moviename, m2.zipcode, m2.date, " +
	  	    		 "	(select max(username) from customers where userid= m2.userid) as username	"  
	  	      		       + "FROM " 
	  	      		       + " moviequeue m1, "
	  	      		       + " connectrequests c, "
	  	      		       + " moviequeue m2 "
	  	      		       + " WHERE m1.userid = "+ userId 
	  	    		       + "   AND m1.queueid = c.queueid1 "
	  	    		       + "   AND m2.queueid = c.queueid2  "
	  	    		       + "   AND c.user1accept = TRUE"
	  	   				   + "   AND (c.user2accept is NULL) ";
	  	      		       
	  	      System.out.println("SQL for getOpenReceivedRequests : ");
	  	      System.out.println(sql);
	  	      
	  	      //System.out.println(sql);
	  	      ResultSet rs = stmt.executeQuery(sql);
	  		
		  		 Map<String, String>  imgList=Images.getMovieImages();
	  	      //STEP 5: Extract data from result set
	  	      while(rs.next()){
	  	         //Retrieve by column name
	  	         long userId_returned  = rs.getLong("userid");
	  	         String movieId  = rs.getString("movieid");
	  	         long zipcode  = rs.getLong("zipcode");
	  	         String movieName  = rs.getString("moviename");
	  	         Long zipCode  = rs.getLong("zipcode");
	  	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 			 String dateToStr = sdf.format(rs.getDate("date"));
	  	         String date = dateToStr;
	  	         String userName= rs.getString("username");
	  	         
	  	         ConnectRequest connectRequest = new ConnectRequest(userId, userId_returned,  movieId, movieName, zipCode, date);
	  	         connectRequest.setAjaxButton("<button disabled onclick=''>Sent</button>");
	  	         connectRequest.setRequestCount(1);
	  	         connectRequest.setRequestStatus("NULL");
		        	if(imgList.containsKey(movieId)){
		          		String src=imgList.get(movieId);
		          		connectRequest.setMovieImage("<img src=\""+ src +"\" style=\"width:100;height:150;\" >");
		          	}else{
		          		connectRequest.setMovieImage("<img src=\"http://comm.soc.northwestern.edu/mscblog/files/2013/03/movie_night.jpg\" style=\"width:100;height:150;\" >");
		          	}
		        	 connectRequest.setUserName2(userName);
	  	         connectRequests.add(connectRequest);
	  	          
	  	  	     System.out.println(userId +","+ movieId + "," + zipCode + "," + date);
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
	  	 return connectRequests;
	 }
	 
	 
	 public static List<ConnectRequest> getClosedMatchedAndUnmatchedRequests(long userId , boolean matched) {
		 List<ConnectRequest> connectRequests = new ArrayList<ConnectRequest>();
		 
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

	  	      String sql = "SELECT distinct m2.queueid, m2.userid, m2.movieid, m2.moviename, m2.zipcode, m2.date , " 
	  	    		  + "	(select max(c.username) from customers c  where c.userid= m2.userid) as username	"  
	  	      		       + "FROM " 
	  	      		       + " moviequeue m1, "
	  	      		       + " connectrequests c, "
	  	      		       + " moviequeue m2 "
	  	      		       + " WHERE m1.userid = "+ userId 
	  	    		       + "   AND m1.queueid = c.queueid1 "
	  	    		       + "   AND m2.queueid = c.queueid2  "
	  	    		       + "   AND c.user1accept = TRUE"
	  	      			   + "   AND c.user2accept = " + (matched==true?"TRUE":"FALSE")  ;
	  	      		       
	  	      System.out.println("SQL for getClosedMatchedRequests : ");
	  	      System.out.println(sql);
	  	      
	  	      //System.out.println(sql);
	  	      ResultSet rs = stmt.executeQuery(sql);
	  		
		  		 Map<String, String>  imgList=Images.getMovieImages();
	  	      //STEP 5: Extract data from result set
	  	      while(rs.next()){
	  	         //Retrieve by column name
	  	         long userId_returned  = rs.getLong("userid");
	  	         String movieId  = rs.getString("movieid");
	  	       String userName  = rs.getString("userName");
	  	         long zipcode  = rs.getLong("zipcode");
	  	         String movieName  = rs.getString("moviename");
	  	         Long zipCode  = rs.getLong("zipcode");
	  	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 			 String dateToStr = sdf.format(rs.getDate("date"));
	  	         String date = dateToStr;
	  	         
	  	         
	  	         ConnectRequest connectRequest = new ConnectRequest(userId , userId_returned , movieId, movieName, zipCode, date);
	  	         connectRequest.setAjaxButton("<button disabled onclick=''>"+ (matched==true?"Accepted":"Denied") +" </button>");
	  	         connectRequest.setRequestCount(1);
	  	         connectRequest.setRequestStatus("1");
		        	if(imgList.containsKey(movieId)){
		          		String src=imgList.get(movieId);
		          		connectRequest.setMovieImage("<img src=\""+ src +"\" style=\"width:100;height:150;\" >");
		          	}else{
		          		connectRequest.setMovieImage("<img src=\"http://comm.soc.northwestern.edu/mscblog/files/2013/03/movie_night.jpg\" style=\"width:100;height:150;\" >");
		          	}
		       connectRequest.setUserName2(userName);
	  	         
	  	         connectRequests.add(connectRequest);
	  	          
	  	  	     System.out.println(userId +","+ movieId + "," + zipCode + "," + date);
	  	      }
	  	      
	  	      
	  	    sql = "SELECT distinct m1.queueid, m1.userid, m1.movieid, m1.moviename, m1.zipcode, m1.date, " +
	  	    		 "	(select max(c.username) from customers c where c.userid= m1.userid) as username	"  
	      		       + "FROM " 
	      		       + " moviequeue m1, "
	      		       + " connectrequests c, "
	      		       + " moviequeue m2 "
	      		       + " WHERE m2.userid = "+ userId 
	    		       + "   AND m1.queueid = c.queueid1 "
	    		       + "   AND m2.queueid = c.queueid2  "
	    		       + "   AND c.user2accept = TRUE"
	      			   + "   AND c.user1accept = " + (matched==true?"TRUE":"FALSE")  ;
	      		       
	      System.out.println("SQL for getOpenReceivedRequests : ");
	      System.out.println(sql);
	      
	      //System.out.println(sql);
	       rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         long userId_returned  = rs.getLong("userid");
	         String movieId  = rs.getString("movieid");
	         long zipcode  = rs.getLong("zipcode");
	         String movieName  = rs.getString("moviename");
	         String userName  = rs.getString("username");
	         Long zipCode  = rs.getLong("zipcode");
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 String dateToStr = sdf.format(rs.getDate("date"));
	         String date = dateToStr;
	         
	         
	         ConnectRequest connectRequest = new ConnectRequest(userId ,userId_returned ,  movieId, movieName, zipCode, date);
	         connectRequest.setAjaxButton("<button disabled onclick=''>"+ (matched==true?"Accepted":"Denied") +"</button>");
	         connectRequest.setRequestCount(1);
	         connectRequest.setRequestStatus("1");
	         connectRequest.setUserName2(userName);
	         
        	if(imgList.containsKey(movieId)){
          		String src=imgList.get(movieId);
          		connectRequest.setMovieImage("<img src=\""+ src +"\" style=\"width:100;height:150;\" >");
          	}else{
          		connectRequest.setMovieImage("<img src=\"http://comm.soc.northwestern.edu/mscblog/files/2013/03/movie_night.jpg\" style=\"width:100;height:150;\" >");
          	}
            connectRequest.setUserName2(userName);
	        connectRequests.add(connectRequest);
	          
	  	     System.out.println(userId +","+ movieId + "," + zipCode + "," + date);
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
	  	 return connectRequests;
	 }
	
	 
	 
	 
	 public static void createMovieQueueTable() {
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
	  	      
	  	      String sql = "CREATE TABLE moviequeue "+
	                    "(queueid BIGINT not NULL AUTO_INCREMENT, " +
	                    " userid BIGINT not NULL, " + 
	                    " movieid VARCHAR(255) not NULL, " +
	                    " moviename VARCHAR(255), " + 
	                    " date DATE, " + 
	                    " zipcode BIGINT, " +
	                    " PRIMARY KEY ( queueid ))"; 

	  	      stmt.executeUpdate(sql);
	  	      System.out.println("Created table movie queue in given database...");

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
	 
	  
	 
	 
	 public static List<UserMovieSelection> getMatchingProfiles(UserMovieSelection userMovie) {
  	   Connection conn = null;
  	   Statement stmt = null;
  	   List<UserMovieSelection> filteredUsers = new ArrayList<UserMovieSelection>();
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
  	    ConnectRequest connectRequest = new ConnectRequest(userMovie.getUserId(),0 , userMovie.getMovieId(), userMovie.getMovieName(), userMovie.getZipCode(),userMovie.getDate());
  	    long queueId = fetchQueueId1( connectRequest);
  	      String sql = "SELECT userid, movieid, moviename, zipcode, date, " +
  	      		  "   (select count(*)   FROM moviebuddy.connectrequests cr " +
  	      		  " 		where cr.queueid1= " + queueId +" and cr.queueid2=mq.queueid and cr.movieid=mq.movieid ) as cnt,  " +
				  "         (select  max(user2accept)    FROM moviebuddy.connectrequests cr " +
				  " 		where cr.queueid1= "+ queueId +" and cr.queueid2=mq.queueid and cr.movieid=mq.movieid ) as status, " +
				  "         (select  max(username)    FROM moviebuddy.customers cr " +
				  " 		where cr.userid= mq.userid ) as username " +
  	      		 " FROM moviequeue mq WHERE userid != "+ userMovie.getUserId() +
  	    		       " AND movieid = '" + userMovie.getMovieId() + "' AND zipcode  = " + userMovie.getZipCode() + " AND date LIKE " + "'"+userMovie.getDate()+"'";
  	     
  	      System.out.println(sql);
  	      
  	      //System.out.println(sql);
  	      ResultSet rs = stmt.executeQuery(sql);

  	      //STEP 5: Extract data from result set
  		 Map<String, String>  imgList=Images.getMovieImages();
  		 Map<String, UserMovieSelection> userMovieSelectionMap=new HashMap<String, UserMovieSelection>();
  		 
  		 List<String> userNameslist = new ArrayList<String>();
  	      while(rs.next()){
  	         //Retrieve by column name
  	         long userId  = rs.getLong("userid");
  	         String movieId  = rs.getString("movieid");
  	         String username=rs.getString("username");
  	         String movieName  = rs.getString("moviename");
  	         
  	         Long zipCode  = rs.getLong("zipcode");
 			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 			String dateToStr = sdf.format(rs.getDate("date"));
  	        String date = dateToStr;
  	        long requestCount= rs.getLong("cnt");
  	        String requestStatus=rs.getString("status");
  	       UserMovieSelection filteredUserMovie = new UserMovieSelection(userId, movieId, movieName, zipCode, date);
  	       if(requestCount==0){
  	    	   filteredUserMovie.setAjaxButton("<button onclick=\"connectWithUser('"+ userMovie.getUserId() + "','"+  filteredUserMovie.getUserId() + "','"+  userMovie.getMovieId()   + "','"+  userMovie.getMovieName() + "','"+  dateToStr + "','"+  userMovie.zipCode + "')\">Connect Now</button>");
  	       }
  	       else if (requestStatus==null ){
  	  	    	   filteredUserMovie.setAjaxButton("<button disabled onclick=\"connectWithUser('"+ userMovie.getUserId() + "','"+  filteredUserMovie.getUserId() + "','"+  userMovie.getMovieId()   + "','"+  userMovie.getMovieName() + "','"+  dateToStr + "','"+  userMovie.zipCode + "')\">Sent</button>");
  	  	   }
  	       else if ( requestStatus.equals("1") ){
  	    	   filteredUserMovie.setAjaxButton("<button disabled onclick=\"connectWithUser('"+ userMovie.getUserId() + "','"+  filteredUserMovie.getUserId() + "','"+  userMovie.getMovieId()   + "','"+  userMovie.getMovieName() + "','"+  dateToStr + "','"+  userMovie.zipCode + "')\">Accepted</button>");
    	     
  	       }else if ( requestStatus.equalsIgnoreCase("0") ){
  	    	   filteredUserMovie.setAjaxButton("<button disabled onclick=\"connectWithUser('"+ userMovie.getUserId() + "','"+  filteredUserMovie.getUserId() + "','"+  userMovie.getMovieId()   + "','"+  userMovie.getMovieName() + "','"+  dateToStr + "','"+  userMovie.zipCode + "')\">Denied</button>");
    	     
  	       }
        	if(imgList.containsKey(movieId)){
          		String src=imgList.get(movieId);
          		filteredUserMovie.setMovieImage("<img src=\""+ src +"\" style=\"width:100;height:150;\" >");
          	}else{
          		filteredUserMovie.setMovieImage("<img src=\"http://comm.soc.northwestern.edu/mscblog/files/2013/03/movie_night.jpg\" style=\"width:100;height:150;\" >");
          	}
            filteredUserMovie.setUserName(username);
  	        userNameslist.add(username);
  	        filteredUsers.add(filteredUserMovie);
  	        userMovieSelectionMap.put(username, filteredUserMovie);
  	  	    System.out.println(userId +","+ movieId + "," + zipCode + "," + date);
  	      }
  	      //get current user
  	    String currentUser=DatabaseWrapper.getUserIdToName( userMovie.getUserId());
  	      	
  	     int[] ranks= com.mkyong.db.DatabaseWrapper.getCompatibility(currentUser, userNameslist.toArray(new String[0]));
  	     for(int i=0; i<ranks.length;i++){
  	    	String userName=userNameslist.get(i);
  	    	UserMovieSelection u= userMovieSelectionMap.get(userName);
  	    	u.setRank(ranks[i]);
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
  	   return filteredUsers;
  	}
  
	 public static String  getUserIdToName(long userId){
		 String currentUser="";
 	     try{
 	  	      //STEP 2: Register JDBC driver
 	  	      Class.forName("com.mysql.jdbc.Driver");
 	    	   Connection conn = null;
 	     	   Statement stmt = null;
 	  	      //STEP 3: Open a connection
 	  	      System.out.println("Connecting to a selected database...");
 	  	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
 	  	      System.out.println("Connected database successfully...");
 	  	      
 	  	      //STEP 4: Execute a query
 	  	      System.out.println("Creating statement...");
 	  	      stmt = conn.createStatement();
 	 
		 
 	  	      String sql = "SELECT max(userid) as userid, max(username) as username from customers where userid= "+  userId;  
 	  	      System.out.println(sql);
 	      
 	  	      //System.out.println(sql);
 	  	      ResultSet rs = stmt.executeQuery(sql);
 	  	      while(rs.next()){ 
 	  	    	  currentUser=rs.getString("username");   
 	  	      }
 	     
 	    }catch(Exception e){
 	    	e.printStackTrace();
 	    	  return "";
 	    }
 	     return currentUser;
	 }
	 
	 public static String  getUserIdToEmailID(long userId){
		 String currentUser="";
 	     try{
 	  	      //STEP 2: Register JDBC driver
 	  	      Class.forName("com.mysql.jdbc.Driver");
 	    	   Connection conn = null;
 	     	   Statement stmt = null;
 	  	      //STEP 3: Open a connection
 	  	      System.out.println("Connecting to a selected database...");
 	  	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
 	  	      System.out.println("Connected database successfully...");
 	  	      
 	  	      //STEP 4: Execute a query
 	  	      System.out.println("Creating statement...");
 	  	      stmt = conn.createStatement();
 	 
		 
 	  	      String sql = "SELECT max(userid) as userid, max(emailID) as emailID from customers where userid= "+  userId;  
 	  	      System.out.println(sql);
 	      
 	  	      //System.out.println(sql);
 	  	      ResultSet rs = stmt.executeQuery(sql);
 	  	      while(rs.next()){ 
 	  	    	  currentUser=rs.getString("emailID");   
 	  	      }
 	     
 	    }catch(Exception e){
 	    	e.printStackTrace();
 	    	  return "";
 	    }
 	     return currentUser;
	 }
	 
	 
	 public static int  getUserNameToId(String userName){
		 String currentUser="";
		 int currentuserid=0;
 	     try{
 	  	      //STEP 2: Register JDBC driver
 	  	      Class.forName("com.mysql.jdbc.Driver");
 	    	   Connection conn = null;
 	     	   Statement stmt = null;
 	  	      //STEP 3: Open a connection
 	  	      System.out.println("Connecting to a selected database...");
 	  	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
 	  	      System.out.println("Connected database successfully...");
 	  	      
 	  	      //STEP 4: Execute a query
 	  	      System.out.println("Creating statement...");
 	  	      stmt = conn.createStatement();
 	 
		 
 	  	      String sql = "SELECT max(userid) as userid, max(username) as username from customers where userName= '"+  userName + "'";  
 	  	      System.out.println(sql);
 	      
 	  	      //System.out.println(sql);
 	  	      ResultSet rs = stmt.executeQuery(sql);
 	  	      
 	  	      while(rs.next()){ 
 	  	    	  currentuserid=Integer.parseInt(rs.getString("userid")==null?"0":rs.getString("userid"));   
 	  	      }
 	     
 	    }catch(Exception e){
 	    	e.printStackTrace();
 	    	  return -1;
 	    }
 	     return currentuserid;
	 }
	 
	 
	 public static void deleteTable(String tableName) {
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
	 
	 
    
    public static void insertMovieQueue(UserMovieSelection userMovie) {
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

  	      st = conn.prepareStatement("insert into moviequeue (userid, movieid, moviename, date, zipcode) values(?,?,?,?,?)");
  	      //now you bind the data to your parameters
  	      st.setLong(1, userMovie.getUserId());
  	      st.setString(2, userMovie.getMovieId());
  	      st.setNString(3, userMovie.getMovieName());

			 
  	      st.setDate(4,java.sql.Date.valueOf(userMovie.getDate()));
  	      st.setLong(5, userMovie.getZipCode());
  	    
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
  	   System.out.println("User Movie selection inserted...");
  	   System.out.println("Goodbye!");
  	}
    
    
    public static void insertConnectRequest(ConnectRequest connectRequest) {
    	
       long queueId1 = fetchQueueId1(connectRequest);
       
       long queueId2 = fetchQueueId2(connectRequest);
       
       insertConnectRequest(queueId1, queueId2, connectRequest.getMovieId(), connectRequest.getMovieName(), new Boolean(true), null);
    	
    }
    
    
    
    public static void acceptConnectRequest(ConnectRequest connectRequest) {
    	
       long queueId1 = fetchQueueId1(connectRequest);
       
       long queueId2 = fetchQueueId2(connectRequest);
       
       acceptRejectConnectRequest(queueId1, queueId2, connectRequest.getMovieId(), connectRequest.getMovieName(), true);
    	
    }
    
    public static void rejectConnectRequest(ConnectRequest connectRequest) {
    	
        long queueId1 = fetchQueueId1(connectRequest);
        
        long queueId2 = fetchQueueId2(connectRequest);
        
        acceptRejectConnectRequest(queueId1, queueId2, connectRequest.getMovieId(), connectRequest.getMovieName(),false);
     	
     }
    
    public static long fetchQueueId1(ConnectRequest connectRequest)
    {
       long queueid = 0;	
   	   Connection conn = null;
   	   Statement st = null;
   	   try{
   	      //STEP 2: Register JDBC driver
   	      Class.forName("com.mysql.jdbc.Driver");

   	      //STEP 3: Open a connection
   	      //System.out.println("Connecting to a selected database...");
   	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
   	      //System.out.println("Connected database successfully...");
   	      
   	      //STEP 4: Execute a query
   	      System.out.println("Selecting queueid 1...");
   	      
	   	  String sql = "SELECT queueid, movieid, moviename, zipcode, date FROM moviequeue WHERE userid = "+ connectRequest.getUserId1() +
	  		       " AND movieid = '" + connectRequest.getMovieId() + "' AND zipcode  = " + connectRequest.getZipCode() + " AND date LIKE " + "'"+connectRequest.getDate()+"'";
	   	  System.out.println(sql);
   	      st = conn.createStatement();
  	      
  	      //System.out.println(sql);
  	      ResultSet rs = st.executeQuery(sql);
		
		    //STEP 5: Extract data from result set
		    while(rs.next()){
		       //Retrieve by column name
		       queueid = rs.getLong("queueid");
		       System.out.println("Queue id filtered is " + queueid);
		       break;
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
		       if(st!=null)
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
		 return queueid;
		}//end main
    
    
    public static long fetchQueueId2(ConnectRequest connectRequest)
    {
       long queueid = 0;	
   	   Connection conn = null;
   	   Statement st = null;
   	   try{
   	      //STEP 2: Register JDBC driver
   	      Class.forName("com.mysql.jdbc.Driver");

   	      //STEP 3: Open a connection
   	      //System.out.println("Connecting to a selected database...");
   	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
   	      //System.out.println("Connected database successfully...");
   	      
   	      //STEP 4: Execute a query
   	      System.out.println("Selecting queueid 2...");
   	      
   	      
	   	  String sql = "SELECT queueid, movieid, moviename, zipcode, date FROM moviequeue WHERE userid = "+ connectRequest.getUserId2() +
	  		       " AND movieid = '" + connectRequest.getMovieId() + "' AND zipcode  = " + connectRequest.getZipCode() + " AND date LIKE " + "'"+connectRequest.getDate()+"'";
	   	  System.out.println(sql);
   	      st = conn.createStatement();
  	      
  	      //System.out.println(sql);
  	      ResultSet rs = st.executeQuery(sql);
		
		    //STEP 5: Extract data from result set
		    while(rs.next()){
		       //Retrieve by column name
		       queueid = rs.getLong("queueid");
		       System.out.println("Queue id filtered is " + queueid);
		       break;
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
		       if(st!=null)
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
		 return queueid;
		}//end main
    
    public static void acceptRejectConnectRequest(long queueId1, long queueId2, String movieId, String movieName, Boolean accept)
    {
    	
    	 
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
        	      String sql="update connectrequests set user2accept= " + (accept==true?"TRUE":"FALSE")+ "  where  queueid1= "+ queueId1 + " and  queueid2= "+ queueId2+ " and movieid= '"+movieId +"'";
    	      st = conn.prepareStatement(sql);
    	      //now you bind the data to your parameters
    	    	      //st.setNString(4, movieName);
    	      //st.setBoolean(5, user1accept);
    	      System.out.println("update query: "+sql);
    	     /* if (user2accept != null)
    	      {
    	    	  st.setBoolean(6, user2accept);  
    	      }*/
    	      
    	      //and then you can execute it
    	      st.executeUpdate();
    	      //System.out.println("Inserted records into the table...");
    	      
    	      System.out.println("Connect request inserted...");

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
    
    public static void insertConnectRequest(long queueId1, long queueId2, String movieId, String movieName, Boolean user1accept, Boolean user2accept)
    {
    	
    	 
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
    	      int alreadyExists=isRowAlreadyPresent(queueId1,queueId2 );
    	      if (alreadyExists>0)  return; //dont insert dup row
    	      
    	      st = conn.prepareStatement("insert into connectrequests (queueid1, queueid2, movieid, moviename, user1accept) values(?,?,?,?,?)");
    	      //now you bind the data to your parameters
    	      st.setLong(1, queueId1);
    	      st.setLong(2, queueId2);
    	      st.setNString(3, movieId);
    	      st.setNString(4, movieName);
    	      st.setBoolean(5, user1accept);
    	      
    	     /* if (user2accept != null)
    	      {
    	    	  st.setBoolean(6, user2accept);  
    	      }*/
    	      
    	      //and then you can execute it
    	      st.executeUpdate();
    	      //System.out.println("Inserted records into the table...");
    	      
    	      System.out.println("Connect request inserted...");

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
    
    public static int isRowAlreadyPresent(long q1,long q2){
    	Connection conn = null;
   	    Statement stmt = null; 	      long cnt=0;
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
 
	      String sql = "select count(*) as cnt FROM connectrequests where queueid1 ="+ q1 +" and queueid2 =" + q2;
	      //System.out.println(sql);
	  	      ResultSet rs = stmt.executeQuery(sql);
	 
	  	      while(rs.next()){ 
	  	    	  cnt= rs.getLong("cnt") ;   
	  	      }
	  	      return cnt>0?1:0;
   	   }
   	   catch(Exception e){
   		   e.printStackTrace();
   		   return -1;
   	   } 
    }
    public static void deleteRecord(String tableName, long id) {
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
    	                   "WHERE id = " + id;
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
    
    public static void deleteAllRecords(String tableName) {
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
    
    
    
    
   /* public static List<customer> filterUsers(String userName) {
		 List<ConnectRequest> connectRequests = new ArrayList<ConnectRequest>();
		 
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

	  	      String sql = "SELECT m2.queueid, m2.userid, m2.movieid, m2.moviename, m2.zipcode, m2.date " 
	  	      		       + "FROM " 
	  	      		       + " moviequeue m1, "
	  	      		       + " connectrequests c, "
	  	      		       + " moviequeue m2 "
	  	      		       + " WHERE m1.userid = "+ userId 
	  	    		       + "   AND m1.queueid = c.queueid1 "
	  	    		       + "   AND m2.queueid = c.queueid2  "
	  	    		       + "   AND c.user1accept = TRUE"
	  	   				   + "   AND (c.user2accept is NULL OR c.user2accept = FALSE) ";
	  	      		       
	  	      System.out.println("SQL for getOpenReceivedRequests : ");
	  	      System.out.println(sql);
	  	      
	  	      //System.out.println(sql);
	  	      ResultSet rs = stmt.executeQuery(sql);

	  	      //STEP 5: Extract data from result set
	  	      while(rs.next()){
	  	         //Retrieve by column name
	  	         long userId_returned  = rs.getLong("userid");
	  	         String movieId  = rs.getString("movieid");
	  	         long zipcode  = rs.getLong("zipcode");
	  	         String movieName  = rs.getString("moviename");
	  	         Long zipCode  = rs.getLong("zipcode");
	  	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 			 String dateToStr = sdf.format(rs.getDate("date"));
	  	         String date = dateToStr;
	  	         
	  	         
	  	         ConnectRequest connectRequest = new ConnectRequest(userId, userId_returned , movieId, movieName, zipCode, date);
	  	         connectRequests.add(connectRequest);
	  	          
	  	  	     System.out.println(userId +","+ movieId + "," + zipCode + "," + date);
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
	  	 return connectRequests;
	 }
	 */
   
   
 }
