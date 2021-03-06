<html>
<body>
	<head>
      <style type="text/css">
         .tile {display: inline-block; border: 1px solid grey; background: silver; padding: 4px; text-align: center; font-size: 15px;width:250px; }	
      </style>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
      <script>
         
         // construct the url with parameter values
         var apikey = "XXXX";
         var baseUrl = "http://data.tmsapi.com/v1";
         var showtimesUrl = baseUrl + '/movies/showings';
         var zipCode = "78701";
         var d = new Date();
         var today = d.getFullYear() + '-' + (d.getMonth()+1) + '-' + d.getDate();
         
         $(document).ready(function() {
         
           // send off the query
           $.ajax({
         	url: showtimesUrl,
                data: {	startDate: today,
         	        zip: zipCode,
         	        jsonp: "dataHandler",
         	        api_key: apikey
                   },			
         	dataType: "jsonp",
           });
         });
         
         // callback to handle the results
         function dataHandler(data) {
          $(document.body).append('<p>Found ' + data.length + ' movies showing within 5 miles of ' + zipCode+':</p>');
          var movies = data.hits;
          $.each(data, function(index, movie) {
         	//var movieData = '<div class="tile"><img src="http://developer.tmsimg.com/' + movie.preferredImage.uri + '?api_key='+apikey+'"><br/>';
         	var movieData = '<div class="tile"><br/>'; 
         	movieData += movie.title;
         	if (movie.ratings) { movieData += ' (' + movie.ratings[0].code + ') </div>' };
         	$(document.body).append(movieData);
          });
         }
           
      </script>
   </head>
	<h1>Spring 3 MVC REST web service</h1>
	
	<h3>Movie Name : ${movie}</h3>	
</body>
</html>