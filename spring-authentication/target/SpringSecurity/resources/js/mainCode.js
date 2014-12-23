
/**
 * 
 */

//-------------------------------------
// get Tweets by Keyword
//-----------------------------------
function getTweetsByKeyword2(){
$.getJSON("./rest/hello-world/getDummy",function(result){
		
	  var response =result;    
	
	$('#my-ajax-table').dynatable({
		  dataset: {
		    records: response.records,
		    perPageDefault:5,
		    perPageOptions: [5,10,20,50]
		  }
		});
	//getting JSON from a remote source fails: #my-table2

	  });//end of ajax call
 
}//---------get Tweets by Keyword ------

//-----------------------------------
// search By zip
//-----------------------------------
function getMoviesGrid(){
	$.getJSON("./rest/hello-world/getMoviesByZipCodeDate?zip="+ document.getElementById("zip").value,function(result){
			
	 var response =result;    
		if(result ==null){
			$('#my-ajax-table').dynatable({
				  dataset: {
				    records: [] ,
				    perPageDefault:5,
				    perPageOptions: [5,10,20,50]
				  }
				});
		}else{
		$('#my-ajax-table').dynatable({
			  dataset: {
			    records: response.records,
			    perPageDefault:5,
			    perPageOptions: [5,10,20,50]
			  }
			});
		}
		//getting JSON from a remote source fails: #my-table2

		  });//end of ajax call
	 
	}//---------get Tweets by Keyword ------

	//-----------------------------------
	// submit Queue request
	//-----------------------------------
	function submitToQueue(movieid, title, datetime,zip){
		var userid=getSiteWideValue('userId');
		alert(movieid + " - " + title + " - " + datetime + " - " + zip);
		setSiteWideValue('movieid',movieid);
		setSiteWideValue('userId',userid);
		
		setSiteWideValue('userId1',userid);
 
		
		setSiteWideValue('title',title);
		setSiteWideValue('datetime',datetime);
		setSiteWideValue('zip',zip);
		
		
		
		$.getJSON("./rest/hello-world/insertIntoQueue?userid="+userid+"&movieid="+movieid +"&movieName="+ title+"&datetime="+ datetime +"&zip="+ zip,function(result){
	 		var len = result.length;
		    var i, j, entry;

		    if(result !="Success"){
		    	return;
		    }
		    $.get("connectWithFriends.jsp",function(data,status){
			    //alert("Data: " + data + "\nStatus: " + status);
			    document.getElementById('pageFrame').innerHTML=data;
			  
			    document.getElementById('pageFramePage2').innerHTML="<h4>Refresh Done successfully </h4>";
			    document.getElementById('refreshPanel').innerHTML="	<button  onclick=\"refreshConnectPanel("+ userid + ",'"+  movieid + "','"+   title + "','"+  datetime + "','"+  zip + "')\">Refresh Available Connections Profile</button>";
			    refreshConnectPanel(userid, movieid, title, datetime,zip);
			    
			  });//end of ajax call for connectWithFriends
 
	   	  });//end of ajax call for insertIntoQueue

		
		
		
		
		
			
	}
	//-----------------------------------
	// connect with user 2
	//-----------------------------------
	function connectWithUser(userId1,userId2,movieId,movieName,dateTime,zip) { 
 
		var userid1=userId1;
		//alert('userId1' + userId1 + ", userId2: "+ userId2+ ", movieId:" + movieId + " - " + movieName + " - " + dateTime + " - " + zip);
		setSiteWideValue('movieid',movieId);
		setSiteWideValue('userId1',userId1);
		setSiteWideValue('userId',userId1);
		setSiteWideValue('userId2',userId2);
		setSiteWideValue('title',movieName);
		setSiteWideValue('dateTime',dateTime);
		setSiteWideValue('zip',zip);
		
		$.getJSON("./rest/hello-world/insertConnection?userId1="+userId1+"&userId2="+userId2+"&movieId="+movieId +"&movieName="+ movieName+"&dateTime="+ dateTime +"&zip="+zip,function(result){
	 		var len = result.length;
		    var i, j, entry;

		    if(result =="Success"){
		    	document.getElementById('pageFramePage2').innerHTML="<h3>Connect Request Sent to User: "+ userId2 + ", for Movie :"+ movieName+", for Show: " + dateTime +"</h3>";
		    	refreshConnectPanel(userId1,movieId,movieName,dateTime,zip);
		    	return;
		    }else{
		    	document.getElementById('pageFramePage2').innerHTML="<h3>Connect Request Failed, Retry. Target User: "+ userId2 + ", for Movie :"+ movieName+", for Show: " + dateTime +"</h3>";
		    	return;
		    }
	   	  });//end of ajax call for getMatchingProfiles
	}
	
	//-----------------------------------
	// accept user
	//-----------------------------------
	function acceptRequest(userId1,userId2,movieId,movieName,dateTime,zip) { 
 
		var userid1=userId1;
		//alert('userId1' + userId1 + ", userId2: "+ userId2+ ", movieId:" + movieId + " - " + movieName + " - " + dateTime + " - " + zip);
		setSiteWideValue('movieid',movieId);
		setSiteWideValue('userId1',userId1);
		setSiteWideValue('userId',userId1);
		setSiteWideValue('userId2',userId2);
		setSiteWideValue('title',movieName);
		setSiteWideValue('dateTime',dateTime);
		setSiteWideValue('zip',zip);
		
		$.getJSON("./rest/hello-world/acceptInvite?userId1="+userId1+"&userId2="+userId2+"&movieId="+movieId +"&movieName="+ movieName+"&dateTime="+ dateTime +"&zip="+zip,function(result){
	 		var len = result.length;
		    var i, j, entry;

		    if(result =="Success"){
		    	document.getElementById('pageFramePage2').innerHTML="<h3>Invite Accepted for User: "+ userId1 + ", for Movie :"+ movieName+", for Show: " + dateTime +"</h3>";
		    	refreshDashBoardWithUserId(userId2);
		    	return;
		    }else{
		    	document.getElementById('pageFramePage2').innerHTML="<h3> Request Failed, Retry. Target User: "+ userId1 + ", for Movie :"+ movieName+", for Show: " + dateTime +"</h3>";
		    	return;
		    }
	   	  });//end of ajax call for getMatchingProfiles
	}
	
	
	//-----------------------------------
	// accept user
	//-----------------------------------
	function denyRequest(userId1,userId2,movieId,movieName,dateTime,zip) { 
 
		var userid1=userId1;
		//alert('userId1' + userId1 + ", userId2: "+ userId2+ ", movieId:" + movieId + " - " + movieName + " - " + dateTime + " - " + zip);
		setSiteWideValue('movieid',movieId);
		setSiteWideValue('userId1',userId1);
		setSiteWideValue('userId',userId1);
		setSiteWideValue('userId2',userId2);
		setSiteWideValue('title',movieName);
		setSiteWideValue('dateTime',dateTime);
		setSiteWideValue('zip',zip);
		
		$.getJSON("./rest/hello-world/rejectInvite?userId1="+userId1+"&userId2="+userId2+"&movieId="+movieId +"&movieName="+ movieName+"&dateTime="+ dateTime +"&zip="+zip,function(result){
	 		var len = result.length;
		    var i, j, entry;

		    if(result =="Success"){
		    	document.getElementById('pageFramePage2').innerHTML="<h3>Invite Accepted for User: "+ userId1 + ",  Movie :"+ movieName+",  Show: " + dateTime +"</h3>";
		    	refreshDashBoardWithUserId(userId2);
		    	return;
		    }else{
		    	document.getElementById('pageFramePage2').innerHTML="<h3> Request Failed, Retry. for User: "+ userId1 + ",  Movie :"+ movieName+",  Show: " + dateTime +"</h3>";
		    	return;
		    }
	   	  });//end of ajax call for getMatchingProfiles
	}
	
	
	//-----------------------------------
	// connect with user 2 via invite
	//-----------------------------------
 	function inviteFriend(userId1,userId2,movieId,movieName,zip,dateTime) { 
 
		var userid1=userId1;
		alert('userId1' + userId1 + ", userId2: "+ userId2+ ", movieId:" + movieId + " - " + movieName + " - " + dateTime + " - " + zip);
		setSiteWideValue('movieid',movieId);
		setSiteWideValue('userId1',userId1);
		setSiteWideValue('userId',userId1);
		setSiteWideValue('userId2',userId2);
		setSiteWideValue('title',movieName);
		setSiteWideValue('dateTime',dateTime);
		setSiteWideValue('zip',zip);
		
		$.getJSON("./rest/hello-world/insertConnectionAndAddtoQueue?userId1="+userId1+"&userId2="+userId2+"&movieId="+movieId +"&movieName="+ movieName+"&dateTime="+ dateTime +"&zip="+zip,function(result){
	 		var len = result.length;
		    var i, j, entry;

		    if(result =="Success"){
		    	document.getElementById('pageFramePage2').innerHTML="<h3>Connect Request Sent to User: "+ userId2 + ", for Movie :"+ movieName+", for Show: " + dateTime +"</h3>";
		    	searchFriend();
		    	return;
		    }else{
		    	document.getElementById('pageFramePage2').innerHTML="<h3>Connect Request Failed, Retry. Target User: "+ userId2 + ", for Movie :"+ movieName+", for Show: " + dateTime +"</h3>";
		    	return;
		    }
	   	  });//end of ajax call for getMatchingProfiles
	}
	
	

	function refreshConnectPanel(userId, movieid, title, datetime,zip){
 
		 
		setSiteWideValue('movieid',movieid);
		setSiteWideValue('userId',userId);
		setSiteWideValue('userId1',userId);
		setSiteWideValue('title',title);
		setSiteWideValue('datetime',datetime);
		setSiteWideValue('zip',zip);
		
	    
	    //------------------update the profiles list
	    $.getJSON("./rest/hello-world/getMatchingProfiles?userid="+userId+"&movieid="+movieid +"&movieName="+ title+"&datetime="+ datetime +"&zip="+ zip,function(result){

	    	var response =result;    
	 		if(result ==null){
	 			var dynatable = $('#connections-table').dynatable({
	 				  dataset: {
	 				    records: [] ,
	 				    perPageDefault:5,
	 				    perPageOptions: [5,10,20,50]
	 				  }
	 				}).data('dynatable');;
	 		}else{
	 			var dynatable = $('#connections-table').dynatable({
	 			  dataset: {
	 			    records:response ,
	 			    perPageDefault:5,
	 			    perPageOptions: [5,10,20,50]
	 			  }
	 			}).data('dynatable');;
	 		}
	 	 
	 		  dynatable.settings.dataset.originalRecords = response;
	 		  dynatable.process();
	   	  });//end of ajax call for getMatchingProfiles
	    
		
	}
	
	
	function searchFriend(){
		 
		movieid =	getSiteWideValue('movieid');
		userId =getSiteWideValue('userId');
		title=getSiteWideValue('title');
		 datetime=getSiteWideValue('datetime');
		 zip=getSiteWideValue('zip'); 
		 setSiteWideValue('userName',document.getElementById("userName").value);
		 name=getSiteWideValue('userName');
		
		 //alert(userId +" , "+ movieid +" , "+   title +" , "+  datetime   +" , "+ zip   +" , "+ name)
 
	    
	    //------------------update the profiles list
	    $.getJSON("./rest/hello-world/searchUser?userid="+userId+"&movieid="+movieid +"&movieName="+ title+"&datetime="+ datetime +"&zip="+ zip + "&userName="+document.getElementById("userName").value,function(result){

	    	var response =result;    
	 		if(result ==null){
	 			var dynatable = $('#connections-invite').dynatable({
	 				  dataset: {
	 				    records: [] ,
	 				    perPageDefault:5,
	 				    perPageOptions: [5,10,20,50]
	 				  }
	 				}).data('dynatable');;
	 		}else{
	 			var dynatable = $('#connections-invite').dynatable({
	 			  dataset: {
	 			    records:result ,
	 			    perPageDefault:5,
	 			    perPageOptions: [5,10,20,50]
	 			  }
	 			}).data('dynatable');;
	 		}
	 			 	 
	 		  dynatable.settings.dataset.originalRecords = response;
	 		  dynatable.process();
	   	  });//end of ajax call for getMatchingProfiles
	    
		
	}


	function refreshDashBoardWithUserId(userId ){
		//setSiteWideValue('userId',userId);
	    //------------------update the profiles list
	    $.getJSON("./rest/hello-world/getDashBoard?userid="+userId,function(result){
	    	var response =result;    
	 		if(result ==null){
	 			var dynatable = $('#connections-table').dynatable({
	 				  dataset: {
	 				    records: [] ,
	 				    perPageDefault:5,
	 				    perPageOptions: [5,10,20,50]
	 				  }
	 				}).data('dynatable');;
	 		}else{
	 			var dynatable = $('#connections-table').dynatable({
	 			  dataset: {
	 			    records:response ,
	 			    perPageDefault:5,
	 			    perPageOptions: [5,10,20,50]
	 			  }
	 			}).data('dynatable');;
	 		}
	 		  dynatable.settings.dataset.originalRecords = response;
	 		  dynatable.process();
	   	  });//end of ajax call for getMatchingProfiles
	}
	
	function refreshDashBoard(){
		var userId=getSiteWideValue('userId');
	    //------------------update the profiles list
	    $.getJSON("./rest/hello-world/getDashBoard?userid="+userId,function(result){
	    	var response =result;    
	 		if(result ==null){
	 			var dynatable = $('#connections-table').dynatable({
	 				  dataset: {
	 				    records: [] ,
	 				    perPageDefault:5,
	 				    perPageOptions: [5,10,20,50]
	 				  }
	 				}).data('dynatable');;
	 		}else{
	 			var dynatable = $('#connections-table').dynatable({
	 			  dataset: {
	 			    records:response ,
	 			    perPageDefault:5,
	 			    perPageOptions: [5,10,20,50]
	 			  }
	 			}).data('dynatable');;
	 		}
	 		  dynatable.settings.dataset.originalRecords = response;
	 		  dynatable.process();
	   	  });//end of ajax call for getMatchingProfiles
	}
	//-----------------------------------
	// has storage ? check if its a new browser
	//-----------------------------------
	var hasStorage = (function() {
	      try {
	        localStorage.setItem(mod, mod);
	        localStorage.removeItem(mod);
	        return true;
	      } catch(e) {
	        return false;
	      }
	    }());

	//-----------------------------------
	// set storage
	//-----------------------------------
	function setSiteWideValue(_key, _value) {
	    if(hasStorage) {
	        localStorage[_key] = _value;
	    }
	    else {
	    	setCookie(_key, _value);
	    }
	}

	
	//-----------------------------------
	// get storage
	//-----------------------------------
	function getSiteWideValue(_key) {
	    if(hasStorage) {
	        return localStorage[_key];
	    }
	    else {
	        if(checkCookie(_key) == true) {
	            return getCookie(_key);
	        }else{
	        	return "";
	        }
	    }
	}
	
	
	function setCookie(cname, cvalue, exdays) {
	    var d = new Date();
	    d.setTime(d.getTime() + (exdays*24*60*60*1000));
	    var expires = "expires="+d.toUTCString();
	    document.cookie = cname + "=" + cvalue + "; " + expires;
	}

	function getCookie(cname) {
	    var name = cname + "=";
	    var ca = document.cookie.split(';');
	    for(var i=0; i<ca.length; i++) {
	        var c = ca[i];
	        while (c.charAt(0)==' ') c = c.substring(1);
	        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
	    }
	    return "";
	}

	function checkCookie(cname) {
	    var user = getCookie(cname);
	    if (user != "") {
	        return true;
	    } else {
	        return false;
	    }
	}