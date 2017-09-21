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

	$scope.getDeployment=function(deploymentId){	
		var restUrl=namespace+"/resource/deployment?id="+deploymentId		
		$http({
			method : "GET",
			url :restUrl 
		})
		.then(
			function success(response) {
				$scope.formData = response.data;
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
});

