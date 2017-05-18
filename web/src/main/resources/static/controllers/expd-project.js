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
                    alert("Error Creating Project :"+resp.data.message);
                    $log.error(resp.data)
                   });
	}
});

app.controller('projectViewController', function($scope,$http,$log,$httpParamSerializerJQLike,$routeParams,$location) {

	$scope.projectNotFound=false;
	
	$scope.projectInfoData={};
	$scope.loadProj=function(projectId){
	$http({
        method : "GET",
        url : namespace+'/resource/project?id='+projectId
    }).then(function success(response) {
    	
         $scope.project = response.data;
         $scope.projectNotFound=false;
         if($scope.project.description){
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
 	        $log.error(response.status);
 	        $log.error(response.data);
 	    });
        }
         
    }, function failure(response) {
        $log.error(response.status);
         $log.error(response.data);
         $scope.projectNotFound=true;
    });
	}
	
	$scope.loadProj($routeParams.projectId);

	
	$scope.getHelp=function(){
	$http({
        method : "GET",
        url : namespace+'/markdown.json'
    }).then(function success(response) {
         $("#markdownHelp").html( response.data.help);
         $("#markdownHelpComment").html( response.data.help);s
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
	
	$scope.projectComments={};
	
	$scope.loadComment=function(id){
		$http({
	        method : "GET",
	        url : namespace+'/resource/project/comments?projectId='+id
	    }).then(function success(response) {
	         $scope.comments = response.data;
	    }, function failure(response) {
	        $log.error(response.status)
	         $scope.showerror=true;
	         $scope.error=response.data.message;
	    });
		
	}
	
	$scope.loadComment($routeParams.projectId);
	
	$scope.addComment=function(project){
		$scope.projectComments['projectId']=project.id;
	}
	
	$scope.saveComment=function(){
		$http({
            method  : 'POST',
            url     : namespace+'/resource/project/comments',
            data    : $scope.projectComments,
            headers : {'Content-Type': 'application/json'}
           }).then(
           function success(resp){
               $log.info(resp.data);
               $scope.loadComment($scope.projectComments.projectId);
            },
           function failure(resp){
            alert("Error Updating Project");
            $log.error(resp.data)
            $scope.project=$scope.projectInfoData;
           });
	}
	
	
	$scope.previewComment=function(){
		$http({
	        method : "POST",
	        url : namespace+'/resource/common/parseToHtml',
	        data:$scope.projectComments.comment,
	        headers : {'Content-Type': 'application/json'},
	        transformResponse: [function (data, headers) {
	        return data;
	        }],
	    }).then(function success(response) {
	    	   $("#previewOnComment").html(response.data);
	    }, function failure(response) {
	        $log.error(response.status)
	         $scope.showerror=true;
	         $scope.error="Error while processing html";
	    });
	}
	$scope.uploadFormData={};
	
	// Add events
	$('#uploadFormData_file').on('change', prepareUpload);
	
	var projectfiles;
	
	function prepareUpload(event){
		projectfiles = event.target.files;
		
	}
	$scope.uploadDoc={};
	$scope.upload=function(){
		
		var frmdata = new FormData();
		console.log(projectfiles)
		if(projectfiles.length>0){
			
			$.each(projectfiles, function(key, value){
				frmdata.append("file", value);
			});
			
			frmdata.append("projectId",$("#uploadFormData_projectId").val());
			
			$.ajax({
			    url: namespace+'/resource/common/uploadDoc',
			    data: frmdata,
			    cache: false,
			    contentType: false,
			    processData: false,
			    type: 'POST',
			    success: function(data){
			        console.log(data.message);
			        $scope.uploadDoc['title']=data.fileName
					$scope.uploadDoc['url']="/resource/common/downloadDoc?projectId="+$("#uploadFormData_projectId").val()+"&fileName="+data.fileName
					$scope.uploadDoc['projectId']=$("#uploadFormData_projectId").val();
			        $scope.updateDocForProj();
			        
			    },
			    error:function(data){
			    	console.log(data.message);
			    }
			});
			
		}else if(projectfiles.length===0){
			$scope.uploadDoc['title']=$("#uploadFormData_title").val();
			$scope.uploadDoc['url']=$("#uploadFormData_url").val();
			$scope.uploadDoc['projectId']=$("#uploadFormData_projectId").val();
			$scope.updateDocForProj();
		}

	}
	
	$scope.updateDocForProj=function(){
		$http({
            method  : 'POST',
            url     : namespace+'/resource/project/updateDocument',
            data    : $scope.uploadDoc,
            headers : {'Content-Type': 'application/json'}
           }).then(
           function success(resp){
               $log.info(resp.data);
               $scope.getDocForProj($scope.uploadDoc.projectId);
            },
           function failure(resp){
            alert("Error Updating Project");
            $log.error(resp.data)
            $scope.project=$scope.projectInfoData;
           });
		
	}
	
	$scope.getDocForProj=function(id){
		$http({
	        method : "GET",
	        url : namespace+'/resource/project/documentsList?projectId='+id
	    }).then(function success(response) {
	    	 $scope.noDocs=false;
	         $scope.documentsList = response.data;
	    }, function failure(response) {
	        $log.error(response.status)
	         $scope.noDocs=true;
	    });
	}
	
	$scope.getDocForProj($routeParams.projectId);
	
});