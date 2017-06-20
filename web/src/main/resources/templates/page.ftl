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
	<#if contextPath??>
      	    <form action="/${contextPath}/wiki/search" method="post">
      	 <#else>
      	   <form action="/wiki/search" method="GET">
      	 </#if>
	 <div class="header clearfix thisHeader">
      <!-- <nav>
          <ul class="nav nav-pills pull-right">
            <li role="presentation" class="active"><a href="/wiki/edit?title=${page.title}">Edit</a></li>
          </ul>
        </nav> -->
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
      <div class="col-md-10">
      
       <h1>${page.title}</h1>
       <#if contextPath??>
      	    <a href="/${contextPath}/wiki/edit?title=${page.title}">Edit</a>
      	 <#else>
      	   <a href="/wiki/edit?title=${page.title}">Edit</a>
      	 </#if>
       
       <hr/>
       <div class="wiki">${page.htmlContent}</div>
    
      </div>
      </div>
      <hr/>
  	<span class="label label-default pull-right">This page is lasted Modified on ${page.modifiedDate}</span>
  </div>
</body>
</html>