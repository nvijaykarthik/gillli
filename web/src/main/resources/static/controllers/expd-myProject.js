app.controller('myProjectController', function($scope,$http,$log,$httpParamSerializerJQLike,
		$routeParams,$location,authService) {

	$scope.getMyTeam = function() {
		authService.getTeamListForUser().success(function(data, status) {
			$scope.myteam = data;
	    });
	}
	$scope.getMyTeam();
	$scope.loadProjects=function(){
		var restUrl=namespace+"/resource/project/myProject?teamId="+ $scope.selectedTeam 
		
		if(!$scope.selectedTeam ){
			alert("Please select the team");
			return;
		}
		
		if($scope.status)
			restUrl=restUrl+"&status="+$scope.status
			
		$http({
			method : "GET",
			url :restUrl 
		})
		.then(
			function success(response) {
				$scope.projectList = response.data;
				
			},
			function failure(response) {
				$log.error(response.status)
				$scope.showerror = true;
				$scope.error = response.data.message;
			}
		);
	}
	
	$scope.getHelp = function() {
		
	}
	
	$scope.getStatus = function() {
		
		$http({
			method : "GET",
			url : namespace+"/resource/project/projectStatus"
		})
		.then(
			function success(response) {
				$scope.projectStatus = response.data;
			},
			function failure(response) {
				$log.error(response.status)
				$scope.showerror = true;
				$scope.error = response.data.message;
			}
		);
	}
	$scope.getStatus();
});