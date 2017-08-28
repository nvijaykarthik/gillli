app.controller('resourcePlanningController', function($scope,$http,$log,$httpParamSerializerJQLike,$routeParams,$location,authService) {

	$scope.resourceForm={};
	$scope.monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
		  "Jly", "Aug", "Sep", "Oct", "Nov", "Dec"];
	
	$scope.days = ['Sun','Mon','Tue','Wed','Thu','Fri','Sat'];
	
	$scope.mmdd = function(date) {
		  var mm = date.getMonth(); // getMonth() is zero-based
		  var dd = date.getDate();
		  var yy = date.getFullYear().toString().substr(-2);
		  return [yy," ",$scope.monthNames[mm]," ",
			      (dd>9 ? '' : '0') + dd
		         ].join('');
		};

	$scope.day = function(date) {
		  var day = date.getDay();
		  return $scope.days[day];
		};

		
	$scope.range = [];
	$scope.currentPage=0;
	
	$scope.getData=function(teamId,pgNo){
		$scope.data={};
		$scope.range=[];
	$http({
        method : "GET",
        url : namespace+'/resource/resourcePlan?teamId='+teamId+'&pgNo='+pgNo
        //url:'/resource-plan.json'
    }).then(function success(response) {
        $scope.data=response.data;
        
        $scope.currentPage=$scope.data.currentPage;
        
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
	}
	
	
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
		
	    $("body").append("<p id='screenshot'><b>Resource: </b>"+displayData.resourceName+"<br/><b>Project: </b>"+
	    		displayData.projectName+"<br/><b>Phase: </b>"+displayData.phase+"<br/><b>StartDate: </b>"+
	    		displayData.startDate+"<br/><b>EndDate: </b>"+displayData.endDate+"<br/><b>Effort Total: </b>"+
	    		displayData.totalEffort+"<br/><b>Eff Per Day: </b>"+displayData.effortPerDay+"<br/><b>Comments: </b>"+
	    		displayData.comments+"</p>");                                 
	    
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
	
   
   $scope.getMyTeam = function() {
		authService.getTeamListForUser().success(function(data, status) {
			$scope.myteam = data;
	    });
	}
	$scope.getMyTeam();
	
	$scope.loadTeam=function(){
		$scope.getData($scope.selectedTeam,0);
	}
	
	$scope.selectThisProject=function(selProj){
		$scope.selectedProject=selProj;
		$scope.showSearchSelect=false;
	}
	
	$scope.toggleSearchSelect=function(){
		if($scope.showSearchSelect===false){
			$scope.showSearchSelect=true;
		}else{
			$scope.showSearchSelect=false;
		}
			
	}
   $scope.getProjects=function(){
	   
	   
	   $http({
	        method : "GET",
	        url : namespace+'/resource/project/availProject'
	    }).then(function success(response) {
	    	$scope.projectList=response.data;	    	
	    }, function failure(response) {
	        $log.error(response.status) 
	    });
   }
   $scope.getProjects();
   
   $scope.getUsersForSelectedTeam=function(){
	   $http({
	        method : "GET",
	        url : namespace+'/resource/users/members?teamId='+$scope.selectedTeam
	    }).then(function success(response) {
	    	$scope.teamMembers=response.data;	    	
	    }, function failure(response) {
	        $log.error(response.status) 
	    });
   }
   
   $scope.saveResourceData=function(){
	   $scope.resourceForm['projectId']=$scope.selectedProject.id;
	   $scope.resourceForm['teamId']=$scope.selectedTeam;

	   $scope.resourceForm['startDate'] =  $scope.convertDate($scope.resourceForm.startDate);
	   $scope.resourceForm['endDate'] =  $scope.convertDate($scope.resourceForm.endDate);
	   
	   $http({
           method  : 'POST',
           url     : namespace+'/resource/resourcePlan',
           data    : $scope.resourceForm,
           headers : {'Content-Type': 'application/json'}
          }).then(
	          function success(resp){
	              $scope.loadTeam();
	           },
	          function failure(resp){
	           alert("Error Creating Project :"+resp.data.message);
	           $log.error(resp.data)
	          });
      }
   
   $scope.convertDate=function(inputFormat) {
	   function pad(s) { return (s < 10) ? '0' + s : s; }
	   var d = new Date(inputFormat);
	   return [d.getFullYear(), pad(d.getMonth()+1),pad(d.getDate()) ].join('-');
	 }
   
   
   $scope.deleteAlloc=function(id){   
	   if (confirm("Do you want to delete ?") == true) {
		   $http({
		        method : "DELETE",
		        url : namespace+'/resource/resourcePlan?planId='+id
		    }).then(function success(response) {
		    	$scope.loadTeam();
		    }, function failure(response) {
		        $log.error(response.status)
		    });
	    } else {
	        
	    }	
	}
   
   $scope.editAlloc=function(rd){   
	   $scope.resourceForm=rd;
	   $scope.selectedProject={};
	   $scope.selectedProject.id=rd.projectId;
	   $scope.selectedProject.name=rd.projectName;
	}
   
   $scope.previousPage=function(){
	   var prevPage=$scope.currentPage-1;
	   $scope.getData($scope.selectedTeam,prevPage);
   }
   
   $scope.nextPage=function(){
	   var nxtPage=$scope.currentPage+1;
	   $scope.getData($scope.selectedTeam,nxtPage);
   }
   
});

