<!DOCTYPE HTML>
<html class="gt-ie8 gt-ie9 not-ie" ng-app="myApp"> <!--<![endif]-->
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>Wiki</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

	<!-- Open Sans font from Google CDN -->
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300&subset=latin" rel="stylesheet" type="text/css">

	<!-- Expedite's stylesheets -->
	<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	
	<style>
		.thisHeader{
			background:#3471d0;
			
			margin-bottom: 10px;
			padding-left: 15px;
			height:38px;
		}
		.thisHeader span{
			font-size:24px;
			font-weight:bold;
			color:white;
		}
		
		.thisHeader .searchBx{
			margin:5px;
			margin-right:0px;
			float:right;
		}
		.thisHeader .searchBtn{
			margin:3px;
			margin-right:40px;
			float:right;
		}
		body{
			background:#f2f2f2;
			background-image:url(/images/backGrnd.png);
		}
		.container{
			background:white;
		}
	</style>
	
</head>
<body>
	<#if contextPath!="">
      	    <form action="/${contextPath}/wiki/search" method="post">
      	 <#else>
      	   <form action="/wiki/search" method="GET">
      	 </#if>
	<div class="header clearfix thisHeader">
        <span>Wiki</span>
        <input type="submit" class="searchBtn btn btn-default btn-sm" value="search"/>
        	<input type="text" name="searchtext" class="searchBx"/>
       
      </div>
      </form>
<div class="container">
  
      <div class="row">
      <div class="col-md-2" style="min-height:500px;border-right:solid 1px #e5e5e5;">
      <h3><u>Menu</u></h3>
      ${sidebar}
      </div>
      <div class="col-md-10" >
       <p> Page you are looking for is not available</p>
		   <ul>
		   <li>${title}</li>
		   </ul>
		  	Please 
		  	 <#if contextPath!="">
		  	<a href="/${contextPath}/wiki/addPage?title=${title}" class="btn btn-success">click here</a> 
		  	<#else>
      	  	 <a href="/wiki/addPage?title=${title}" class="btn btn-success">click here</a> 
      	 	</#if>
		  	to create this page.
	  </div>
      </div>
      <hr/>
   
      </div>
      
  
</body>
</html>