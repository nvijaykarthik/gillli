app.controller('resourcePlanningController', function($scope,$http,$log,$httpParamSerializerJQLike,$routeParams,$location) {

	$scope.monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
		  "Jly", "Aug", "Sep", "Oct", "Nov", "Dec"];
	
	$scope.days = ['Sun','Mon','Tue','Wed','Thu','Fri','Sat'];
	
	$scope.mmdd = function(date) {
		  var mm = date.getMonth(); // getMonth() is zero-based
		  var dd = date.getDate();

		  return [$scope.monthNames[mm]," ",
			      (dd>9 ? '' : '0') + dd
		         ].join('');
		};

	$scope.day = function(date) {
		  var day = date.getDay();
		  return $scope.days[day];
		};

		
	$scope.range = [];

	$http({
        method : "GET",
        url : namespace+'/resource-plan.json'
    }).then(function success(response) {
        $scope.data=response.data;
 
        var date = new Date($scope.data.currentDate);
    	
    	for(var i=0;i<$scope.data.totalCell;i++) {
    	  $scope.resDate={};
    	  date.setDate(date.getDate() + 1); 
    	  $scope.resDate['MonDD']=$scope.mmdd(date);
    	  $scope.resDate['day']=$scope.day(date);
    	  $scope.range.push($scope.resDate);
    	}
    	
    	
    }, function failure(response) {
        $log.error(response.status) 
    });
	
	$scope.rowClass=function(rd,index,color){	
		var start=Math.round((new Date(rd.startDate)- new Date($scope.data.currentDate))/(1000*60*60*24));
		var end = Math.round((new Date(rd.endDate)- new Date($scope.data.currentDate))/(1000*60*60*24));
		if(index>=start-1 && index<=end-1){
			console.log("ng-style");
			return {"background-color":color,"border":"none"};
		}
		return "";
	}
	
	
	$scope.weekEnd=function(day){	
		if(day==='Sun' || day ==='Sat')
		   return {"background-color":"#e6e6e6"};
		else
		   return ""
	}
	
	$scope.getRandomColor=function() {
		  var hex = Math.floor(Math.random() * 0xFFFFFF);
		  return "#" + ("000000" + hex.toString(16)).substr(-6);
	}
	$scope.xOffset = 10;
    $scope.yOffset = 30;
    
	$scope.resMouseEnter=function(displayData,e){
		
	    $("body").append("<p id='screenshot'><b>Resource: </b>"+displayData.resourceName+"<br/><b>Project: </b>"+displayData.projectName+"<br/><b>Phase: </b>"+displayData.phase+"<br/><b>StartDate: </b>"+displayData.startDate+"<br/><b>EndDate: </b>"+displayData.endDate+"<br/><b>Effort Total: </b>"+displayData.totalEffort+"<br/><b>Eff Per Day: </b>"+displayData.efforPerDay+"</p>");                                 
	    
	    $("#screenshot")
	           .css("top",(e.pageY - $scope.xOffset) + "px")
	           .css("left",(e.pageX + $scope.yOffset) + "px")
	           .css("z-index","1050")
	           .css("position","absolute")
	           .css("border","solid 1px #e6e6e6")
	           .css("background","#fafafa")
	           .css("padding","2px")
	           .css("font-size","12px")
	           .css("box-shadow"," 5px 5px 5px #888")
	           .fadeIn("fast");   
	}
	
	$scope.resMouseleave= function(){    
       $("#screenshot").remove();
   }
	
   $scope.resMousemove=function(e){
       $("#screenshot")
           .css("top",(e.pageY - $scope.xOffset) + "px")
           .css("left",(e.pageX + $scope.yOffset) + "px")
           .css("position","absolute")
           .css("border","solid 1px #e6e6e6")
	       .css("background","#fafafa")
	       .css("padding","2px")
	       .css("font-size","12px")
	       .css("box-shadow"," 5px 5px 5px #888")
           .css("z-index","1050");
   }    
	
});

