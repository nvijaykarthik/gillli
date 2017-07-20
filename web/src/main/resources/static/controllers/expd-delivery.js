app.controller('newDeliveryController', function($scope,$http,$log,$httpParamSerializerJQLike,
		$routeParams,$location,authService) {
	
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
   
	
});