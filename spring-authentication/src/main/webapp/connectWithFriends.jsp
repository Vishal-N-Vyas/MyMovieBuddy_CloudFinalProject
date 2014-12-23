<html><head>

<link rel="stylesheet" href="./resources/css/jquery.dynatable.css">
<link rel="stylesheet" href="./resources/css/dynaTableInlined.css">
    <link rel="stylesheet" media="all" href="./resources/css/reset.css">
    <link rel="stylesheet" media="all" href="./resources/css/bootstrap-2.3.2.min.css">
    <link rel="stylesheet" media="all" href="./resources/css/application.css">
    <link rel="stylesheet" media="all" href="./resources/css/project.css">
    <link rel="stylesheet" media="all" href="./resources/css/pygments.css">
    <link rel="stylesheet" media="all" href="./resources/css/share.css">	
	
<script src="./resources/js/jquery-1.7.2.min.js"></script>
<script src="./resources/js/jquery.dynatable.js"></script>
<script src="./resources/js/mainCode.js"></script>

<style>
 
button {
    color: black;

} 
</style>
</head>
<body>
<br>
<h2 class=" input-glass">Step 2 of 3 : Select Your Movie Buddy from waiting users</h2>
<div  class=" input-glass">Status:
 <div id="pageFramePage2">

 </div>
   <div id="refreshPanel">
	<button  onclick="refreshConnectPanel()">Refresh Connnection Panel</button>"
 </div>
 </div>

<div id="pageFramePage3">
<br> <br><br>

 
<div style="color:white" class="">
 
<table id="connections-table" class="table table-bordered input-glass"  style="width:100%; text-align:left;">
  <thead  style="font-size:+20">
     
    <!-- <th data-dynatable-column="queueId" class="dynatable-head"  data-dynatable-sorts="queueId"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">QueueId</a></th> --> 
     <th data-dynatable-column="userId" class="dynatable-head"  data-dynatable-sorts="userId"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">User Id</a></th>
     <th data-dynatable-column="userName" class="dynatable-head"  data-dynatable-sorts="userName"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">User Name</a></th>
    
     <th data-dynatable-column="movieId" class="dynatable-head"  data-dynatable-sorts="movieId"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Movie Id</a></th>
 
     <th data-dynatable-column="movieName" class="dynatable-head"  data-dynatable-sorts="movieName"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Movie Name</a></th>
     <th data-dynatable-column="movieImage" class="dynatable-head"  data-dynatable-sorts="movieImage"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Movie Image</a></th>
     
     <th data-dynatable-column="date" class="dynatable-head"  data-dynatable-sorts="date"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Show Date</a></th>
 
     <th data-dynatable-column="zipCode" class="dynatable-head"  data-dynatable-sorts="zipCode"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Zip</a></th>
     <th data-dynatable-column="rank" class="dynatable-head"  data-dynatable-sorts="rank"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">rank</a></th>
    <th data-dynatable-column="ajaxButton" class="dynatable-head"  data-dynatable-sorts="ajaxButton"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Connect</a></th> 

 
  </thead>
  <tbody>
  </tbody>
</table>
</div>
<br><br>
 
<h2 class=" input-glass">Step 3 of 3 : Invite Your Friends </h2>

 
<input type="text" id="userName" value='' style="width: 206px;height: 30px;letter-spacing: normal;line-height: 30px;"/>
<button onclick="searchFriend()">Search Friends by UserName and Invite to Event</button>

<div style="color:white">
<table id="connections-invite" class="table table-bordered input-glass"  style="width:100%; text-align:left;">
  <thead  style="font-size:+20">
 
    <!-- <th data-dynatable-column="queueId" class="dynatable-head"  data-dynatable-sorts="queueId"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">QueueId</a></th> --> 
  <th data-dynatable-column="userId2" class="dynatable-head"  data-dynatable-sorts="userId2"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Id</a></th>
   <th data-dynatable-column="userName2" class="dynatable-head"  data-dynatable-sorts="userName2"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">User Name</a></th>
     <th data-dynatable-column="firstName" class="dynatable-head"  data-dynatable-sorts="firstName"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">First Name</a></th>
     <th data-dynatable-column="lastName" class="dynatable-head"  data-dynatable-sorts="lastName"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Last Name</a></th>
     <th data-dynatable-column="ajaxButton" class="dynatable-head"  data-dynatable-sorts="ajaxButton"  data-dynatable-default-sort="true"><a class="dynatable-sort-header" href="#">Invite</a></th> 
 
 
  </thead>
  <tbody>
  </tbody>
</table>
</div>
</div>
 
</div>








</body>
</html>