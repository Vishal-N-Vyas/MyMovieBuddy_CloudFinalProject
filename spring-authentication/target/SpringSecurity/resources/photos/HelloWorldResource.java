package com.srccodes.example;


import com.columbia.cloud.techifinity.pojo.Dummy;
import com.columbia.cloud.techifinity.pojo.DynaTable;
import com.columbia.cloud.techifinity.pojo.DynaTableMovieList;
import com.columbia.cloud.techifinity.pojo.ImageProps;
import com.columbia.cloud.techifinity.pojo.TMSMovie;
import com.columbia.cloud.techifinity.pojo.TMSMovieNormalised;
import com.example.helloworld.core.Tweet;
import com.example.helloworld.core.TweetGroup;
import com.example.helloworld.core.TweetsDB;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
 
@Path("/hello-world")
@Produces( MediaType.APPLICATION_JSON )
public class HelloWorldResource {
	private static final String tmskey="XXX";
 
    private final TweetsDB db;
    
    public HelloWorldResource() {
       
        this.db=new  TweetsDB();
    }

 
    @GET
    @Path("/hello")
    public  String hello()  {
     return "Hello";
    }
    @GET
    @Path("/getAllTweets")
    public  List<Tweet> getAllTweets(@QueryParam("count") Integer count)  {
    	try{
    		  int value=count.intValue();
    		 return  db.GetAllTweetsFromDB(value);
    	}catch(Exception e){
    		 return  db.GetAllTweetsFromDB(100);
    	}
       
    }
    
    @GET
    @Path("/getTweetsByKeyword")
    public  List<TweetGroup> getTweetsByKeyword(@QueryParam("hashTag") String hashTag)  {
    	try{
    		 return  db.GetGroupedTweetsByKeyword(hashTag);
    	}catch(Exception e){
    		return null;
    	}		      
    }
    
    @GET
    @Path("/getGroupedTweets")
    public  List<TweetGroup> getGroupedTweets(@QueryParam("round") Integer round,@QueryParam("count") Integer count) throws Exception  {
    	try{
    		  int roundInt=round.intValue();
    		  int countInt=count.intValue();
    		 return  db.GetGroupedTweets(roundInt,countInt);
    	}catch(Exception e){
    		return  db.GetGroupedTweets(4, 100);
    	}

    }

    
    @GET
	@Path("/getMoviesByZipCodeDate")
	public DynaTableMovieList getMoviesByZipCodeDate(@QueryParam("zip") Integer zip){
		

		try {
		if(zip == null)
			zip=10025;
		
			Date curDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateToStr = sdf.format(curDate);
			 
			URL url = new URL(
					"http://data.tmsapi.com/v1/movies/showings?startDate="+ dateToStr +"&api_key="+  tmskey +"&zip="+zip);
			//http://localhost:8080/RESTfulExample/json/product/post");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(false);
			conn.setRequestMethod("GET");//POST
			conn.setRequestProperty("Content-Type", "application/json");

			String input = "{\"qty\":100,\"name\":\"iPad 4\"}";

			//OutputStream os = conn.getOutputStream();
			//os.write(input.getBytes());
			//os.flush();

			/*if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode()
						+ conn.getResponseMessage()
						);
			}*/

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);
				  // We read the json string now and recreate the AlbumsWithInnerClass class
		        Gson gson3 = new Gson();
         
		        TMSMovie[] movies = gson3.fromJson(output, TMSMovie[].class);
		        List<TMSMovieNormalised> normList=new ArrayList<TMSMovieNormalised>();
		    	TMSMovie tms;TMSMovieNormalised t ;
		    	 Map<String, String>  imgList=Images.getMovieImages();
		        for(int i=0;i<movies.length;i++){
		        	 tms=movies[i];
		        	 t = new TMSMovieNormalised();
		        	t.setTmsId(tms.getTmsId());
		        	t.setTitle(tms.getTitle());
		        	
		        	String youTubeURL = ""; 
		        	
		        	if (i < 4){
		        		youTubeURL = getYouTubeURL(t.getTitle() + " trailer");
		        		t.setYouTubeLink(youTubeURL);
		        	}
		        	else{
		        		t.setYouTubeLink("https://www.youtube.com/v/zSWdZVtXT7E");
		        	}
		        	
		        	t.setSubType(tms.getSubType());
		           	t.setReleaseYear(tms.getReleaseYear());
		          	t.setEntityType(tms.getEntityType());
		          	if(imgList.containsKey(tms.getTmsId())){
		          		String src=imgList.get(tms.getTmsId());
		          		t.setMovieImage("<img src=\""+ src +"\" style=\"width:100;height:150;\" >");
		          	}else{
		          		t.setMovieImage("<img src=\"http://comm.soc.northwestern.edu/mscblog/files/2013/03/movie_night.jpg\" style=\"width:100;height:150;\" >");
		          	}
		        	t.setTheatresList("");
		            
		        	
		        	
	 
		        	Map<String, Integer> theatreMap=new HashMap<String, Integer>();
		         	if( tms.getShowtimes()!=null){
		               	for(int j=0; j < tms.getShowtimes().size();j++){
		               		String name=tms.getShowtimes().get(j).getTheatre().getName();
		               		if(!theatreMap.containsKey(name)){
		               			t.setTheatresList(t.getTheatresList() + ",<br>" + name);
		               			theatreMap.put(name,1);
		               		}
			         	}
			           	t.setTheatresList(t.getTheatresList().substring(5, (t.getTheatresList().length()) ));
		         	}
		  
		         	
		         
		         		t.setGenresList("");
		         	if( tms.getGenres()!=null){
			         	for(String g: tms.getGenres()){
			         		t.setGenresList(t.getGenresList() + ",<br>" +  g );
			         	}
			         	t.setGenresList(t.getGenresList().substring(5, t.getGenresList().length()));
		         	}
		         	
		         	
		        
		         		t.setCastList("");
		            if(  tms.getTopCast()!=null){
			        	for(String g: tms.getTopCast()){
			         		t.setCastList(t.getCastList() + ",<br>" + g );
			         	}
			         	t.setCastList(t.getCastList().substring(5, t.getCastList().length()));
		         	}
		         	
	
		         	t.setAjaxButton("<button onclick=\"submitToQueue('"+t.getTmsId() + "','"+t.getTitle() + "','"+  dateToStr + "','"+ zip + "')\">Add</button>");
		         	normList.add(t);
		        
		        }
		        System.out.println("Length = " + movies.length);
		        DynaTableMovieList d = new DynaTableMovieList();
		    	d.setRecords(normList);
		    	d.setQueryRecordCount(normList.size());
		    	d.setTotalRecordCount(normList.size());
		        
				conn.disconnect();
		        return  d ;
			}



		} catch (MalformedURLException e) {

			e.printStackTrace();
			return null;
		} catch (IOException e) {

			e.printStackTrace();
			return null;

		}
		return null;

		
	}
    
    public String getYouTubeURL(String movie) {
    	
		try {
			
			
			String url_string = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + URLEncoder.encode(movie, "UTF-8")+"&order=date&videoEmbeddable=true&type=video&key=AIzaSyCqe-f-Vz4uLPXzgD14kJUeXMxU-6Cj0nk";
			
		
			URL url = new URL(url_string);
			//http://localhost:8080/RESTfulExample/json/product/post");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(false);
			conn.setRequestMethod("GET");//POST
			conn.setRequestProperty("Content-Type", "application/json");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("Response " + conn.getResponseCode() + "Message " + conn.getResponseMessage());
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode()
						+ conn.getResponseMessage()
						);
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			String videoId = "";
			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);
				
				// Select the first result
				
				if (output.contains("videoId"))
				{
					// Extract the video id
					
					String output_mod = output.replace("\"", "");
					
					StringTokenizer st = new StringTokenizer(output_mod, ":");
					
					boolean movieFound = false;
					while(st.hasMoreTokens()) { 
						String val = st.nextToken(); 
						String valTrim = val.trim();
						System.out.println(valTrim);
						if (!valTrim.contains("videoId"))
						{
							movieFound = true;
						    videoId = val.trim();
							break;
						}
					} 
					if (movieFound == true)
					{
						System.out.println("videoId " + videoId);
						break;
					}
					 
				}
				
			}
			
			conn.disconnect();
			
			String youTubeURL = "http://www.youtube.com/v/"+videoId;
				
				  // We read the json string now and recreate the AlbumsWithInnerClass class
		        //Gson gson3 = new Gson();
         
		        //TMSMovie[] movies = gson3.fromJson(output, TMSMovie[].class);
		        //System.out.println("Length = " + movies.length);
		        
		        //System.out.println(movies[0].getTitle());
				
		        //return movies;
				
				return youTubeURL;
			



		} catch (MalformedURLException e) {

			e.printStackTrace();
			return null;
		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}

	}
     
    @GET
	@Path("/getDummy")
	public DynaTable getDummy(){
    	DynaTable d = new DynaTable();
    	Dummy d1= new Dummy();
    	d1.setFav("fav1"); d1.setHobby("h1"); d1.setName("<button>");
    	Dummy d2= new Dummy();
    	d2.setFav("fav1"); d2.setHobby("h2"); d2.setName("<button>");
    	Dummy d3= new Dummy();
    	d3.setFav("fav1"); d3.setHobby("h3"); d3.setName("<button>");
    	
    	List<Dummy> ld=new ArrayList<Dummy>();
    	ld.add(d1);    	ld.add(d2);    	ld.add(d3);
    	
    	d.setRecords(ld);
    	d.setQueryRecordCount(3);
    	d.setTotalRecordCount(3);
    	return d;
    }
    	
    
    
    @GET
	@Path("/insertIntoQueue")
    public String insertIntoQueue(@QueryParam("userid") Integer userid ,
    		@QueryParam("movieid") String movieid , 
    		@QueryParam("movieName") String movieName,
    		@QueryParam("datetime") String datetime , 
    		 @QueryParam("zip") Integer zip   ){
     
    	java.sql.Date date = java.sql.Date.valueOf( datetime );
		
		UserMovieSelection userMovie = new UserMovieSelection(userid, movieid,movieName, zip, datetime);
		DatabaseWrapper.insertMovieQueue(userMovie);
		return "Success";
   }
    
    @GET
	@Path("/insertConnection")
    public String insertConnection(@QueryParam("userId1") Integer userId1 ,
    		@QueryParam("userId2") Integer userId2 ,
    		@QueryParam("movieId") String movieId , 
    		@QueryParam("movieName") String movieName,
    		@QueryParam("dateTime") String datetime , 
    		 @QueryParam("zip") Integer zipCode   ){
    	ConnectRequest connectRequest= new  ConnectRequest( userId1,  userId2,  movieId,  movieName,  zipCode,  datetime);
    	DatabaseWrapper.insertConnectRequest(connectRequest) ;
    	String name = DatabaseWrapper.getUserIdToName(userId1);
    	
    	//send email :-- to user 2 stating user 1 has sent invites
    	String subject="New Movie Invite Received";
    	String body="Hello, <br> You have received a Movie Buff invite for watching movie : " + movieName + " , on " + datetime + ", from User: "+ name+". Login to My MovieBuddy and goto Dashboard to Accept/ Deny the request." ;
		String email= DatabaseWrapper.getUserIdToEmailID(userId2);
		com.mkyong.common.App.sendMail(email, subject, body);
    	return "Success";
    }
    @GET
	@Path("/insertConnectionAndAddtoQueue")
    public String insertConnectionAndAddtoQueue(@QueryParam("userId1") Integer userId1 ,
    		@QueryParam("userId2") Integer userId2 ,
    		@QueryParam("movieId") String movieId , 
    		@QueryParam("movieName") String movieName,
    		@QueryParam("dateTime") String datetime , 
    		 @QueryParam("zip") Integer zipCode   ){
		UserMovieSelection userMovie = new UserMovieSelection(userId2, movieId,movieName, zipCode, datetime);
		DatabaseWrapper.insertMovieQueue(userMovie);
    	ConnectRequest connectRequest= new  ConnectRequest( userId1,  userId2,  movieId,  movieName,  zipCode,  datetime);
    	DatabaseWrapper.insertConnectRequest(connectRequest) ;
    	String name = DatabaseWrapper.getUserIdToName(userId1);
      	//send email :-- to user 2 stating user 1 has sent invites
    	String subject="New Movie Invite Received";
    	String body="Hello, <br> You have received a Movie Buff invite for watching movie : " + movieName + " , on " + datetime + ", from User: "+ name+". Login to My MovieBuddy and goto Dashboard to Accept/ Deny the request." ;
		String email= DatabaseWrapper.getUserIdToEmailID(userId2);
		com.mkyong.common.App.sendMail(email, subject, body);
    	return "Success";
	 
    }
    
    @GET
	@Path("/getMatchingProfiles")
    public List<UserMovieSelection> getMatchingProfiles(@QueryParam("userid") Integer userid ,
    		@QueryParam("movieid") String movieid , 
    		@QueryParam("movieName") String movieName,
    		@QueryParam("datetime") String datetime , 
    		 @QueryParam("zip") Integer zip   ){
     
		UserMovieSelection userMovie = new UserMovieSelection(userid, movieid,movieName, zip, datetime);
		return DatabaseWrapper.getMatchingProfiles(userMovie);		
   }
    @GET
	@Path("/searchUser")
    public  List<ConnectRequest> searchUser(@QueryParam("userName") String userName ,
    		@QueryParam("userid") Integer userid ,
    		@QueryParam("movieid") String movieid , 
    		@QueryParam("movieName") String movieName,
    		@QueryParam("datetime") String datetime , 
    		 @QueryParam("zip") Integer zip   ){
     
		UserMovieSelection userMovie = new UserMovieSelection(userid, movieid,movieName, zip, datetime);
	    List<ConnectRequest> crs = DatabaseWrapper.searchUser( userName,  userMovie);
		return crs;		
   }

    
    @GET
	@Path("/getDashBoard")
    public  List<ConnectRequest> getDashBoard(@QueryParam("userid") Integer userid  ){
  	
		return  DatabaseWrapper.getDashBoard(userid);		
   }
    
    
}