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

	
	$scope.projectInfoData={};
	$scope.loadProj=function(projectId){
	$http({
        method : "GET",
        url : namespace+'/resource/project?id='+projectId
    }).then(function success(response) {
         $scope.project = response.data;
         $http({
 	        method : "POST",
 	        url : namespace+'/resource/common/parseToHtml',
 	        data:$scope.project.description,
 	        headers : {'Content-Type': 'application/json'},
 	        transformResponse: [function (data, headers) {
 	        return data;
 	        }],
 	    }).then(function success(response) {
 	    	 $("#project_description").html(response.data);
 	    }, function failure(response) {
 	        $log.error(response.status)
 	         $scope.showerror=true;
 	         $scope.error="Error while processing html";
 	    });
         
    }, function failure(response) {
        $log.error(response.status)
         $scope.showerror=true;
         $scope.error=response.data.message;
    });
	}
	$scope.loadProj($routeParams.projectId);
	$scope.getHelp=function(){
	$http({
        method : "GET",
        url : namespace+'/markdown.json'
    }).then(function success(response) {
         $("#markdownHelp").html( response.data.help);
    }, function failure(response) {
        $log.error(response.status)
         $scope.showerror=true;
         $scope.error=response.data.message;
    });
	}
	$http({
        method : "GET",
        url : namespace+'/resource/project/projectType'
    }).then(function success(response) {
         $scope.projectTypes = response.data;
    }, function failure(response) {
        $log.error(response.status)
         $scope.showerror=true;
         $scope.error=response.data.message;
    });
	
	$http({
        method : "GET",
        url : namespace+'/resource/project/projectStatus'
    }).then(function success(response) {
         $scope.status = response.data;
    }, function failure(response) {
        $log.error(response.status)
         $scope.showerror=true;
         $scope.error=response.data.message;
    });
	
	
	$scope.populateInfo=function(projectInfo){
		$scope.projectInfoData=projectInfo;
	}
	
	$scope.saveProjectInfo=function(project){
		$http({
            method  : 'POST',
            url     : namespace+'/resource/project/updateProject',
            data    : project,
            headers : {'Content-Type': 'application/json'}
           }).then(
           function success(resp){
               $log.info(resp.data);
               $scope.project=project;
               $scope.loadProj(project.id)
            },
           function failure(resp){
            alert("Error Updating Project");
            $log.error(resp.data)
            $scope.project=$scope.projectInfoData;
           });
	}
	$scope.preview=function(){
		$http({
	        method : "POST",
	        url : namespace+'/resource/common/parseToHtml',
	        data:$scope.projectInfoData.description,
	        headers : {'Content-Type': 'application/json'},
	        transformResponse: [function (data, headers) {
	        return data;
	        }],
	    }).then(function success(response) {
	    	   $("#previewOnDesc").html(response.data);
	    }, function failure(response) {
	        $log.error(response.status)
	         $scope.showerror=true;
	         $scope.error="Error while processing html";
	    });
	}
	
});