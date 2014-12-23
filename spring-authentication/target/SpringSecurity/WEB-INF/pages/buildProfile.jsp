<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Login Page</title>
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/select2.css">
    <link rel="stylesheet" href="resources/css/admin.css">
    <script src="resources/js/lib/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="resources/js/lib/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/lib/select2.js"></script>
			<link rel="stylesheet" href="resources/css/menu_styles.css">
  </head>
  <body class="backstretch">
   <div id='cssmenu'> 
<ul>

   <li ><a href='./connectionDashBoard.jsp'><span>Movie Buddy DashBoard</span></a></li>
  <li  ><a href='./searchByZip.jsp'>Search Movies and Invite</a></li>
   <li  class='active'><a href='/spring-authentication/buildProfile'><span>Profile</span></a></li>
      <li><a href='/spring-authentication/fileupload'><span>Upload Picture</span></a></li>
   <li  ><a href='./aboutUs.html'><span>About Us</span></a></li>
   <li><a href='./techStack.html'><span>Technology Stack</span></a></li>
  <li><a href='/spring-authentication/logoutuser'><span>Log out</span></a></li>
</ul>
</div><br><br>
		<div class="container block-absolute-center">
			<div class="text-center m-b-10 input-glass"  >
        		<h2 class="text-white font-w-100 "><br><br>Build your profile</h2>
			</div>
			<form id="buildProfileForm" method="post" action="spring-authentication/buildProfile">
				<div class="panel panel-default">
				  <div class="panel-heading">
				    <h3 class="panel-title">Favorite Movies</h3>
				  </div>
				  <div class="container panel-body">
					<div class="form-group">
						<input type="text" style="width:350px" class="select2-input" id="searchMovie"/>
						<input class="btn btn-primary" type="button" value="Add to Favorites" onClick="addFavMovie()"/>
					</div>
					<div class="row">
						<div class="col-sm-2">
							<strong>Selected Favorites</strong>
						</div>
						<div class="col-sm-8">
							<select style="width:700px;" class="" multiple id="favMovies" name="favMovies">
								<c:forEach var="movie" items="${results}">
									<option value="${movie.title}">${movie.title}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<input class="btn btn-info" type="button" value="Save Favorite Movies" onClick="saveFavoriteMovies();"/> 
				  </div>
				</div>
				<div class="panel panel-default">
				  <div class="panel-heading">
				    <h3 class="panel-title">Favorite Personalities</h3>
				  </div>
				  <div class="container panel-body">
					<div class="form-group">
						<input type="text" style="width: 350px" class="select2-input" id="searchPersonality"/>
						<input class="btn btn-primary" type="button" value="Add to Favorites" onClick="addFavPerson()"/>
					</div>
					<div class="">
						<select style="width:700px;" class="" multiple id="favPersonalities" name="favPersonalities">
							<c:forEach var="person" items="${personalities}">
								<option value="${person.name}">${person.name}</option>
							</c:forEach>
						</select>
					</div>
					<input class="btn btn-info" type="button" value="Save Favorite Personalities" onClick="saveFavoritePersonalities();"/>
				  </div>
				</div>
				<div class="panel panel-default">
				  <div class="panel-heading">
				    <h3 class="panel-title">Favorite Genre</h3>
				  </div>
				  <div class="panel-body">
					<div class="form-group row">
						<label> Genre </label>
						<select style="width:700px;" class="" multiple id="favGenres" name="favGenres" onchange="addGenre()">
							<option value="Comedy">Comedy</option>
							<option value="Mystery">Mystery</option>
							<option value="Drama">Drama</option>
							<option value="Sci-fi">Sci-fi</option>
							<option value="Thriller">Thriller</option>
							<option value="Horror">Horror</option>
							<option value="Action">Action</option>
							<option value="Romance">Romance</option>
							<option value="Family">Family</option>
							<option value="Crime">Crime</option>
							<option value="Animation">Animation</option>
						</select>
					</div>
					<input class="btn btn-info" type="button" value="Save Favorite Genres" onClick="saveFavoriteGenres();"/>
				  </div>
				</div>
			</form>
			<div>
			<form method="post" action="doneBuildProfile">
				<br/>
				<input type="submit" class="btn btn-success" value="Done" />
			</form>
			</div>
		</div>
    <script src="resources/js/lib/backstretch.js"></script>

    <script>
      $(".backstretch").backstretch([
        "resources/backgrounds/wg_blurred_backgrounds_8.jpg"
      ]);
      var favMovies = [];
      var favPersonalities = [];
      var favGenres = [];
      
      var addGenre = function(){
    	  var selectBox = document.getElementById("favGenre");
    	  var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    	  favGenres.push({id:selectedValue});
      }
      
      var addFavMovie = function(){
    	  var mov = $("#searchMovie").select2("val");
    	  favMovies.push({id:mov, text:mov});
    	  $("#favMovies").select2("data", favMovies);
      }

      var addFavPerson = function(){
    	  var person = $("#searchPersonality").select2("val");
    	  favPersonalities.push({id:person, text:person});
    	  $("#favPersonalities").select2("data", favPersonalities);
      }
      
      $("#favMovies").select2();
      $("#favPersonalities").select2();
      $("#searchMovie").select2({
	  	  	placeholder: "Search for a movie",
		    minimumInputLength: 3,
		    ajax: {
		        url: "searchMovie",
		        dataType: 'json',
		        quietMillis: 250,
		        data: function (term, page) {
		            return {
		                q: term, // search term
		            };
		        },
		        results: function (data, page) { 
		            return { results: data };
		        },
		        cache: true
		    },
		    initSelection: function(element, callback) {
		    }  
	  });
      $("#searchPersonality").select2({
    	  	placeholder: "Search for a personality",
    	    minimumInputLength: 3,
    	    ajax: { 
    	        url: "searchPersonality",
    	        dataType: 'json',
    	        quietMillis: 250,
    	        data: function (term, page) {
    	            return {
    	                q: term, // search term
    	            };
    	        },
    	        results: function (data, page) { 
    	            return { results: data };
    	        },
    	        cache: true
    	    },
    	    initSelection: function(element, callback) {
    	    }  
      });
      $("#favGenres").select2();
      
      var saveFavoriteMovies = function(){
    	var string = "";
    	for(var i=0; i<favMovies.length; i++) {
    		if(i==0) {
    			string = favMovies[i].id
    		}
    		else {
    			string = string + "^^*^^" + favMovies[i].id;
    		}
    	}
    	//alert(string);
    	//JSON.stringify(){movies:'',personalities:'',genres:''}
    	$.ajax({
    		type: 'Post',
    		url: 'saveFavMovies',
    		data: string,
    		contentType: 'application/json',
    		dataType: 'json',
    		success: function(data){
    			//alert(data.successMsg);
    			//alert(data.listSaved);
    		}
    	});
    	return false;
      };
      var saveFavoritePersonalities = function(){
      	//This entire block needs to be modified for sending data to server and saving to db.
      	//alert($("#favMovies").select2("val"));
      	//alert(favMovies);
      	var string = "";
      	for(var i=0; i<favPersonalities.length; i++) {
      		if(i==0) {
      			string = favPersonalities[i].id
      		}
      		else {
      			string = string + "^^*^^" + favPersonalities[i].id;
      		}
      	}
      	//JSON.stringify(){movies:'',personalities:'',genres:''}
      	$.ajax({
      		type: 'Post',
      		url: 'saveFavPersonalities',
      		data: string,
      		contentType: 'application/json',
      		dataType: 'json',
      		success: function(data){
      			//alert(data.successMsg);
      			//alert(data.listSaved);
      		}
      	});
      	return false;
        };
        var saveFavoriteGenres = function(){
        	//This entire block needs to be modified for sending data to server and saving to db.
        	//alert($("#favMovies").select2("val"));
        	//alert(favMovies);
        	var string = "";
        	for(var i=0; i<favGenres.length; i++) {
        		if(i==0) {
        			string = favGenres[i].id
        		}
        		else {
        			string = string + "^^*^^" + favGenres[i].id;
        		}
        	}
        	//JSON.stringify(){movies:'',personalities:'',genres:''}
        	$.ajax({
        		type: 'Post',
        		url: 'saveFavGenres',
        		data: string,
        		contentType: 'application/json',
        		dataType: 'json',
        		success: function(data){
        			//alert(data.successMsg);
        			//alert(data.listSaved);
        		}
        	});
        	return false;
          };
    </script>
  </body>
</html>