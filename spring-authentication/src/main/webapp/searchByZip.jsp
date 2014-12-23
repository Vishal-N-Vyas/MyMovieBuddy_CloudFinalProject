<html><head>

<link rel="stylesheet" href="./resources/css/jquery.dynatable.css">
<link rel="stylesheet" href="./resources/css/dynaTableInlined.css">

    <link rel="stylesheet" media="all" href="./resources/css/bootstrap-2.3.2.min.css">
    <link rel="stylesheet" media="all" href="./resources/css/application.css">
    <link rel="stylesheet" media="all" href="./resources/css/project.css">
    <link rel="stylesheet" media="all" href="./resources/css/pygments.css">
    <link rel="stylesheet" media="all" href="./resources/css/share.css">
	
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
<body  class="backstretch  ">
 <div id='cssmenu'> 
<ul>

   <li><a href='./connectionDashBoard.jsp'><span>Movie Buddy DashBoard</span></a></li>
  <li   class='active'><a href='./searchByZip.jsp'>
 
   Search Movies and Invite
 
</span></a></li>
   <li><a href='/spring-authentication/buildProfile'><span>Profile</span></a></li>
   <li><a href='/spring-authentication/fileupload'><span>Upload Picture</span></a></li>
   <!--
   <li  ><a href='./aboutUs.html'><span>About Us</span></a></li>
   <li><a href='./techStack.html'><span>Technology Stack</span></a></li> -->
  <li><a href='/spring-authentication/logoutuser'><span>Log out</span></a></li>
</ul>
</div>
 
    <script src="resources/js/lib/backstretch.js"></script>

    <script>
      $(".backstretch").backstretch([
        "resources/backgrounds/wg_blurred_backgrounds_8.jpg"
      ]);
    </script>

<div id="pageFrame" style="color:white"><br>
<h2 class=" input-glass">Step 1 of 3 : Search Movies Playing at theatres Around You</h2><br>
<input type="text" id="zip" value='10025' style="width: 206px;height: 30px;letter-spacing: normal;line-height: 30px;"/>
<button onclick="getMoviesGrid()">Search Movies by ZipCode</button>
<br> <br><br>
<div style="">
 
<table id="my-ajax-table" class="table table-bordered input-glass"  style="width:100%; text-align:left;">
  <thead  style="font-size:+20">
     
     <th data-dynatable-column="title" class="dynatable-head"  data-dynatable-sorts="title"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Title</a></th>
     <th data-dynatable-column="movieImage" class="dynatable-head"  data-dynatable-sorts="movieImage"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Movie Image</a></th>
     <th data-dynatable-column="castList" class="dynatable-head"  data-dynatable-sorts="castList"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Cast</a></th>
 
     <th data-dynatable-column="genresList" class="dynatable-head"  data-dynatable-sorts="genresList"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Genres</a></th>
     <th data-dynatable-column="releaseYear" class="dynatable-head"  data-dynatable-sorts="releaseYear"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Year</a></th>
 
     <th data-dynatable-column="subType" class="dynatable-head"  data-dynatable-sorts="subType"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Type</a></th>
     <th data-dynatable-column="theatresList" class="dynatable-head"  data-dynatable-sorts="theatresList"  data-dynatable-default-sort="true" tyle="width:100px"><a class="dynatable-sort-header" href="#">In Theatres</a></th>
 
     <th data-dynatable-column="ajaxButton" class="dynatable-head"  data-dynatable-sorts="ajaxButton"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Queue</a></th>
     <th data-dynatable-column="tmsId" class="dynatable-head"  data-dynatable-sorts="tmsId"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Id</a></th>
 
     <th data-dynatable-column="youTubeLink" class="dynatable-head"  data-dynatable-sorts="youTubeLink"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Trailer</a></th>
 
  </thead>
  <tbody>
  </tbody>
</table>
</div>

 
</div>








<!-- 
<table  id="my-table" class="table table-bordered">
    <thead>
        <tr>
              <th data-dynatable-column="line" class="dynatable-head"><a class="dynatable-sort-header" href="#">line</a></th>
            <th data-dynatable-column="value1" class="dynatable-head"><a class="dynatable-sort-header" href="#">value1</a></th>
            <th data-dynatable-column="value2" class="dynatable-head"><a class="dynatable-sort-header" href="#">value2</a></th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td rowspan="2">1</td>
            <td>1.1</td>
            <td>1.2</td>
        </tr>
        <tr>
            <td>1.3</td>
            <td>1.4</td>
        </tr>
        <tr>
            <td rowspan="2">2</td>
            <td>2.1</td>
            <td>2.2</td>
        </tr>
        <tr>
            <td>2.3</td>
            <td>2.4</td>
        </tr>
    </tbody>
</table> 

<select id="keyword" onchange="getMoviesGrid()">
  <option value="love" selected="selected">love</option>
  <option value="halloween" >halloween</option>
  <option value="girl">girl</option>
  <option value="beiber">beiber</option>
  <option value="ebola">ebola</option>
  <option value="modi">modi</option>
  <option value="nyc">nyc</option>
  <option value="india">india</option>
  <option value="us">us</option>
  <option value="columbia">columbia</option>
  <option value="microsoft">microsoft</option>
</select>
 <br><br><br><br><br><br>
-->
</body>
</html>