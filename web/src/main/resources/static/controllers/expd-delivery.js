app.controller('newDeliveryController', function($scope,$http,$log,$httpParamSerializerJQLike,
		$routeParams,$location,authService,$q) {
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
	  $scope.artifactFiles=[];
	  $('#fileList').empty();
	  $scope.delFrm={};
	  $scope.selectedTeam="";
	  $scope.selectedApp="";
	  $scope.show_del_frm=0;
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
  $scope.delFrm={};
  
	$scope.populateDelivery=function(){
		$scope.selectedAppJson = JSON.parse($scope.selectedApp);
		console.log($scope.selectedAppJson.appReleaseTag);
		$scope.delFrm['releaseTag']=$scope.selectedAppJson.appReleaseTag;
		
		$http({
			method : "GET",
			url : namespace+"/resource/delivery/getVersion?appId="+ $scope.selectedAppJson.id
		  	})
		  	.then(
				function success(response) {
					$scope.delFrm['version'] = response.data;
				},
				function failure(response) {
					$log.error(response.status)
					alert("error while getting version");
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
		//console.log($scope.artifactFiles);
		var fileList=$('#fileList');
		fileList.empty();
		$.each($scope.artifactFiles, function(key, value){
			var li = $('<li/>')
		        .addClass('list-group-item')
		        .text(value.name)
		        .appendTo(fileList);
		});
	}
	

	$scope.showFileUpload=function(){
	  $('#artifact-fileupload').trigger('click'); 
	}
	
	$scope.showEditFileUpload=function(){
		  $('#edit-artifact-fileupload').trigger('click'); 
		}
	
	$scope.saveDelivery=function(){
		$scope.delFrm['teamId']=$scope.selectedTeam;
		$scope.delFrm['projectId']=$scope.selectedProject.id;
		$scope.delFrm['applicationId']=$scope.selectedAppJson.id;
		
		$http({
            method  : 'POST',
            url     : namespace+'/resource/delivery',
            data    : $scope.delFrm,
            headers : {'Content-Type': 'application/json'}
           }).then(
           function success(resp){
               $log.info(resp.data);
               if($scope.artifactFiles.length>0){
               
       		$q.all([$.each($scope.artifactFiles, function(key, value){
	       			var frmdata = new FormData();
	                frmdata.append("deliveryId",resp.data.id);
		       		frmdata.append("applicationId",$scope.selectedAppJson.id);
		       		frmdata.append("version",$scope.delFrm.version);
	       			frmdata.append("file", value);
	       			
	       			$.ajax({
	    			    url: namespace+'/resource/delivery/uploadArtifacts',
	    			    data: frmdata,
	    			    cache: false,
	    			    contentType: false,
	    			    processData: false,
	    			    type: 'POST',
	    			    success: function(data){
	    			        console.log(data);
	    			    },
	    			    error:function(data){
	    			    	console.log(data);
	    			    }
	    			});
	       		})]).then(function(){
	       			alert("Successfully Uploaded");
	       			$('#addDeliveryModal').modal('hide');
	       			$scope.artifactFiles={};
	       			$('#fileList').empty();
	       		});
               }
               
            },
           function failure(resp){
            alert("Error saving delivery");
           
           });
		
		
		
	}
	
	  $scope.editDelivery=function(delivery){
		  $scope.editDel=delivery;
		  
		  if(!$scope.selectedProject.name){
			  alert("please select the project");
			  return;
		  }
		  $('#editDeliveryModal').modal('show');
		  $scope.loadArtifact(delivery.id);
	  }
	  $scope.loadArtifact=function(deliveryId){
		  $http({
				method : "GET",
				url : namespace+"/resource/delivery/getArtifacts?deliveryId="+ deliveryId
			  	})
			  	.then(
					function success(response) {
						$scope.artificatList = response.data;
					},
					function failure(response) {
						$log.error(response.status)
						alert("error while retrieving artifacts")
					});
	  }
	  $scope.deleteArtifact=function(artifact){
		  $http({
				method : "DELETE",
				url : namespace+"/resource/delivery/deleteArtifacts?id="+ artifact.id
			  	})
			  	.then(
					function success(response) {
						console.log(response.data);
						alert("successfully deleted")
						$scope.loadArtifact(artifact.deliveryId);
					},
					function failure(response) {
						$log.error(response.status)
						alert("Error while deleting the artifact")
					});
	  }
	  
	  $('#edit-artifact-fileupload').on('change', prepareUploadEdit);
		function prepareUploadEdit(event){
			$scope.artifactEditFiles = event.target.files;
			//console.log($scope.artifactFiles);
			var editfileList=$('#editfileList');
			editfileList.empty();
			$.each($scope.artifactEditFiles, function(key, value){
				var li = $('<li/>')
			        .addClass('list-group-item')
			        .text(value.name)
			        .appendTo(editfileList);
			});
		}
		
		$scope.saveEditedDelivery=function(){
			$http({
	            method  : 'POST',
	            url     : namespace+'/resource/delivery',
	            data    : $scope.editDel,
	            headers : {'Content-Type': 'application/json'}
	           }).then(
	           function success(resp){
	               $log.info(resp.data);
	               if($scope.artifactEditFiles.length>0){
	               
	       		$q.all([$.each($scope.artifactEditFiles, function(key, value){
		       			var frmdata = new FormData();
		                frmdata.append("deliveryId",$scope.editDel.id);
			       		frmdata.append("applicationId",$scope.editDel.applicationId);
			       		frmdata.append("version",$scope.editDel.version);
		       			frmdata.append("file", value);
		       			
		       			$.ajax({
		    			    url: namespace+'/resource/delivery/uploadArtifacts',
		    			    data: frmdata,
		    			    cache: false,
		    			    contentType: false,
		    			    processData: false,
		    			    type: 'POST',
		    			    success: function(data){
		    			        console.log(data);
		    			    },
		    			    error:function(data){
		    			    	console.log(data);
		    			    }
		    			});
		       		})]).then(function(){
		       			alert("Successfully Uploaded");
		       			$('#editDeliveryModal').modal('hide');
		       			$scope.artifactEditFiles={};
		       			$('#editfileList').empty();
		       		});
	               }
	               
	            },
	           function failure(resp){
	            alert("Error saving delivery");
	           
	           });
		}
	
});