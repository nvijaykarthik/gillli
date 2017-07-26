app.controller('newDeliveryController', function($scope,$http,$log,$httpParamSerializerJQLike,
		$routeParams,$location,authService) {
	$scope.selectedProject={};
	
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
   
   $scope.loadDelivery=function(){
	   if($scope.selectedProject){
	   $http({
	        method : "GET",
	        url : namespace+'/resource/delivery?projectId='+$scope.selectedProject.id
	    }).then(function success(response) {
	    	$scope.deliveryList=response.data;	    	
	    }, function failure(response) {
	        $log.error(response.status) 
	    });
   }
   }
	
  $scope.addDelivery=function(){
	  console.log($scope.selectedProject.name);
	  
	  if(!$scope.selectedProject.name){
		  alert("please select the project");
		  return;
	  }
	  $('#addDeliveryModal').modal('show');
  }
  
  $scope.loadApplication=function(){
	  $http({
		method : "GET",
		url : namespace+"/resource/application?teamId="+ $scope.selectedTeam
	  	})
	  	.then(
			function success(response) {
				$scope.applicationList = response.data;
			},
			function failure(response) {
				$log.error(response.status)
				$scope.showerror = true;
				$scope.error = response.data.message;
			});
  }
  
   $scope.getMyTeam = function() {
		authService.getTeamListForUser().success(function(data, status) {
			$scope.myteam = data;
	    });
	}
	$scope.getMyTeam();
		// Add events
	$('#artifact-fileupload').on('change', prepareUpload);
	function prepareUpload(event){
		$scope.artifactFiles = event.target.files;
		console.log($scope.artifactFiles);
	}
	
	$scope.showFileUpload=function(){
	  $('#artifact-fileupload').trigger('click'); 
	}

	
	
});