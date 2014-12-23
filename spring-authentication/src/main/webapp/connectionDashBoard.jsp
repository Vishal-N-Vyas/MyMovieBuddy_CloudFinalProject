<html><head>

<link rel="stylesheet" href="./resources/css/jquery.dynatable.css">
<link rel="stylesheet" href="./resources/css/dynaTableInlined.css">
    <link rel="stylesheet" media="all" href="./resources/css/reset.css">
    <link rel="stylesheet" media="all" href="./resources/css/bootstrap-2.3.2.min.css">
    <link rel="stylesheet" media="all" href="./resources/css/application.css">
    <link rel="stylesheet" media="all" href="./resources/css/project.css">
    <link rel="stylesheet" media="all" href="./resources/css/pygments.css">
    <link rel="stylesheet" media="all" href="./resources/css/share.css">	
		<link rel="stylesheet" href="resources/css/menu_styles.css">
		
<script src="./resources/js/jquery-1.7.2.min.js"></script>
<script src="./resources/js/jquery.dynatable.js"></script>
<script src="./resources/js/mainCode.js"></script>

 	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="resources/css/bootstrap.min.css">
			<link rel="stylesheet" href="resources/css/menu_styles.css">
		<link rel="stylesheet" href="resources/css/select2.css">
	    <link rel="stylesheet" href="resources/css/admin.css">
 		<script type="text/javascript" src="resources/js/lib/select2.js"></script>
<style>
 
button {
    color: black;

} 
select{
color:black;
}
</style>
</head>
<body  class="backstretch  " onload=" refreshDashBoard( )">
 <div id='cssmenu'> 
<ul>

   <li  class='active'><a href='./connectionDashBoard.jsp'><span>Movie Buddy DashBoard</span></a></li>
  <li  ><a href='./searchByZip.jsp'>Search Movies and Invite</a></li>
   <li><a href='/spring-authentication/buildProfile'><span>Profile</span></a></li>
      <li><a href='/spring-authentication/fileupload'><span>Upload Picture</span></a></li>
   <li  ><a href='./aboutUs.html'><span>About Us</span></a></li>
   <li><a href='./techStack.html'><span>Technology Stack</span></a></li>
  <li><a href='/spring-authentication/logoutuser'><span>Log out</span></a></li>
</ul>
</div>
 
    <script src="resources/js/lib/backstretch.js"></script>

    <script>
      $(".backstretch").backstretch([
        "resources/backgrounds/wg_blurred_backgrounds_8.jpg"
      ]);
      
    </script>
    
<br>
<h2 class=" input-glass">My Movie Buddy DashBoard</h2>
 <div id="pageFramePage2">

 </div>
  <div id="refreshPanel" class=" input-glass"> 
<button  onclick="refreshDashBoard()">Refresh DashBoard</button> 
 </div>
<div id="pageFramePage3">
<br> <br><br>

 
<div   style="color:white">
 
<table id="connections-table" class="table table-bordered input-glass"  style="width:100%; text-align:left;">
  <thead  style="font-size:+20">
     
    <!-- <th data-dynatable-column="queueId" class="dynatable-head"  data-dynatable-sorts="queueId"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">QueueId</a></th> --> 
     <th data-dynatable-column="userId2" class="dynatable-head"  data-dynatable-sorts="userId2"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">User Id</a></th>
     <th data-dynatable-column="userName2" class="dynatable-head"  data-dynatable-sorts="userName2"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">User Name</a></th>
     
     <th data-dynatable-column="movieId" class="dynatable-head"  data-dynatable-sorts="movieId"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Movie Id</a></th>
     <th data-dynatable-column="movieName" class="dynatable-head"  data-dynatable-sorts="movieName"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Movie Name</a></th>
     <th data-dynatable-column="movieImage" class="dynatable-head"  data-dynatable-sorts="movieImage"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Movie Image</a></th>
    
     <th data-dynatable-column="date" class="dynatable-head"  data-dynatable-sorts="date"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Show Date</a></th>
     <th data-dynatable-column="zipCode" class="dynatable-head"  data-dynatable-sorts="zipCode"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Zip</a></th>
     <th data-dynatable-column="ajaxButton" class="dynatable-head"  data-dynatable-sorts="ajaxButton"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Connect</a></th> 

 
  </thead>
  <tbody>
  </tbody>
</table>
</div>
 
 
 
 
</div>








</body>
</html>