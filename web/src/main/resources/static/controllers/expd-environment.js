app.controller('environmentController', function($scope,$http,$log,$httpParamSerializerJQLike,
		$routeParams,$location,authService) {

	$scope.formData={};
	
	$scope.getEnvironments = function() {
		$http({
			method : "GET",
			url : namespace+"/resource/environment"
		})
		.then(
			function success(response) {
				$scope.envList = response.data;
			},
			function failure(response) {
				$log.error(response.data)
				alert("Error getting environments");
			}
		);
	}
	$scope.getEnvironments();
	
	$scope.edit=function(env){
		$scope.formData=env;
	}
	
	$scope.clearData=function(env){
		$scope.formData={};
	}
	
	$scope.save=function(){
		$http({
            method  : 'POST',
            url     : namespace+'/resource/environment',
            data    : $scope.formData,
            headers : {'Content-Type': 'application/json'}
           }).then(
	           function success(resp){
	               $log.info(resp.data);
	               $scope.getEnvironments();
	            },
	           function failure(resp){
	            alert("Error Saving Environment :"+resp.data.message);
	            $log.error(resp.data)
	           });
	}
	
});