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
        <input type="text" class="searchBx"/>
       
      </div>
      </form>
<div class="container">
  
      <div class="row">
      <div class="col-md-2" style="min-height:500px;border-right:solid 1px #e5e5e5;">
      <h3><u>Menu</u></h3>
      ${sidebar}
      </div>
      <div class="col-md-10" >
      
      
      	<#if contextPath??>
      	    <form action="/${contextPath}/wiki/editPage" method="post">
      	 <#else>
      	   <form action="/wiki/editPage" method="post">
      	 </#if>
    	<div>
    	<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
    	<input type="hidden" value="${page.id}" name="id"/>
    	</div>
    	
    	<div class="form-group">
  		  <label for="title">Title</label>
    	  <input type="text" class="form-control" id="title" placeholder="title" value="${page.title}" name="title" readonly>
  		</div>

    	<div class="form-group">
  		  <label for="content">Content</label>
    	<#if page.content??>
    		<textarea name="content" class="form-control" id="content" placeholder="content" rows="10">${page.content}</textarea>
    	<#else>
    		<textarea name="content" class="form-control" id="content" placeholder="content" rows="10"></textarea>
    	</#if>
    	</div>

    	<div>
    		<input type="submit" class="btn btn-primary" value="save"/>
    	</div>
    </form>
      
      </div>
      </div>

    </div>
</body>
</html>