app.controller('projectController', function($scope,$http,$log,$httpParamSerializerJQLike,$routeParams,$location) {

	$scope.newProjForm={};
	
	$scope.createNewProject=function(){
		$http({
            method  : 'POST',
            url     : namespace+'/resource/project/newProject',
            data    : $scope.newProjForm.projectName,
            headers : {'Content-Type': 'application/json'}
           }).then(
                   function success(resp){
                       $log.info(resp.data)
                       $scope.newProjForm={};
                       $location.path( "/projectView/"+resp.data.id );
                    },
                   function failure(resp){
                    alert("Error Creating Project");
                    $log.error(resp.data)
                   });
	}
});

app.controller('projectViewController', function($scope,$http,$log,$httpParamSerializerJQLike,$routeParams,$location) {

	$http({
        method : "GET",
        url : namespace+'/resource/project?id='+$routeParams.projectId
    }).then(function success(response) {
         $scope.project = response.data;
    }, function failure(response) {
        $log.error(response.status)
         $scope.showerror=true;
         $scope.error=response.data.message;
    });
	
});