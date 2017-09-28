app.controller('deploymentController', function($scope,$http,$log,$httpParamSerializerJQLike,
		$routeParams,$location,authService) {

	$scope.getMyTeam = function() {
		authService.getTeamListForUser().success(function(data, status) {
			$scope.myteam = data;
	    });
	}
	
	$scope.formData={};
	
	$scope.search=function(){	
		var restUrl=namespace+"/resource/deployment/search?"+$httpParamSerializerJQLike($scope.formData) 		
		$http({
			method : "GET",
			url :restUrl 
		})
		.then(
			function success(response) {
				$scope.depList = response.data;
			},
			function failure(response) {
				$log.error(response.data)
				alert("Error while retrieving the deployment search result: "+response.data.message);
			}
		);
	}
});

app.controller('deploymentDetailController', function($scope,$http,$log,$httpParamSerializerJQLike,
		$routeParams,$location,authService) {

	$scope.formData={};
	
	 authService.getPrincipal().success(function(data, status) {
	        $scope.user = data;
	        console.log($scope.user);
	    	$scope.formData['createdBy']=$scope.user.username;
	    });
	    
	

	
	$scope.getDeployment=function(deploymentId){	
		var restUrl=namespace+"/resource/deployment?id="+deploymentId		
		$http({
			method : "GET",
			url :restUrl 
		})
		.then(
			function success(response) {
				$scope.formData = response.data;
				$scope.getTasksForDeployment(deploymentId);
				if($routeParams.copy==='true'){
					$scope.formData['id']="";
				}
			},
			function failure(response) {
				$log.error(response.data)
				alert("Error while retrieving the deployment details: "+response.data.message);
			}
		);
	}
	
	if($routeParams.deploymentId!=0){
		$scope.getDeployment($routeParams.deploymentId)
	}
	
	$scope.getTasksForDeployment=function(deploymentId){	
		var restUrl=namespace+"/resource/deployment/tasksForDeployment?deploymentId="+deploymentId		
		$http({
			method : "GET",
			url :restUrl 
		})
		.then(
			function success(response) {
				$scope.taskList = response.data;
			},
			function failure(response) {
				$log.error(response.data)
				alert("Error while retrieving the Task Details details: "+response.data.message);
			}
		);
	}
	
	$scope.saveDeploymentDetails=function(){
		$http({
            method  : 'POST',
            url     : namespace+'/resource/deployment',
            data    : $scope.formData,
            headers : {'Content-Type': 'application/json'}
           }).then(
           function success(resp){
               console.log(resp.data);
               $scope.formData=resp.data;
            },
           function failure(resp){
            alert("Error Saving Deployment Details");
            $log.error(resp.data)
           });
	}
	
});

