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
	$scope.formTaskData={};
	$scope.viewOnly=false;
	$scope.addTaskEnablement=true;
	
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
				if($routeParams.view==='true'){
					$scope.viewOnly=true;
				}else{
					$scope.viewOnly=false;
				}

				if($scope.user.username===$scope.formData.createdBy){
					$scope.viewOnly=false;
				}else{
					$scope.viewOnly=true;
				}
				
				$scope.addTaskEnablement=false;
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
		$scope.formData['startDateTime']=$('#startDateTime').val();
		$scope.formData['endDateTime']=$('#endDateTime').val();
		$http({
            method  : 'POST',
            url     : namespace+'/resource/deployment',
            data    : $scope.formData,
            headers : {'Content-Type': 'application/json'}
           }).then(
           function success(resp){
               console.log(resp.data);
               $scope.formData=resp.data;
               $scope.addTaskEnablement=false;
            },
           function failure(resp){
            alert("Error Saving Deployment Details");
            $log.error(resp.data)
           });
	}
	
	
	$scope.editTsk=function(task){
		$scope.formTaskData=task;
	}
	
	$scope.newTask=function(){
		$scope.formTaskData={};
		$scope.formTaskData['createdBy']=$scope.user.username;
	}
	
	$scope.saveTaskDetails=function(){
		$scope.formTaskData['depolymentId']=$scope.formData.id
		$scope.formTaskData['startDateTime']=$('#tskStDt').val();
		$scope.formTaskData['endDateTime']=$('#tskEdDt').val();
		
		$http({
            method  : 'POST',
            url     : namespace+'/resource/deployment/saveTask',
            data    : $scope.formTaskData,
            headers : {'Content-Type': 'application/json'}
           }).then(
           function success(resp){
               console.log(resp.data);
               $scope.formTaskData=resp.data;
               $scope.getTasksForDeployment($scope.formData.id);
            },
           function failure(resp){
            alert("Error Saving Task");
            $log.error(resp.data)
           });
	}
	
	
	$scope.getAvailteam=function(){
		
		$http({
			method : "GET",
			url :namespace+ "/resource/team",
		}).then(function success(response) {
			$log.log(response.data.content)
			$scope.availTeams = response.data;
		}, function failure(response) {
			$log.error(response.status)
			alert("Error Retrieving teams");
		});

	}
	$scope.getAvailteam();
	
	$scope.getTeamsForApproval=function(deploymentId){	
		var restUrl=namespace+"/resource/teamApproval?deploymentId="+deploymentId		
		$http({
			method : "GET",
			url :restUrl 
		})
		.then(
			function success(response) {
				$scope.teamList = response.data;
				$scope.refreshDeploymentStatusOnApproval();
			},
			function failure(response) {
				$log.error(response.data)
				alert("Error while retrieving the Approval Team List : "+response.data.message);
			}
		);
	}
	
	$scope.getTeamsForApproval($routeParams.deploymentId);
	
	$scope.addTeamForApproval=function(){
		$scope.teamApproval={};
		var team=angular.fromJson($scope.selectedTeam);
		$scope.teamApproval['teamId']=team.id;
		$scope.teamApproval['teamName']=team.teamName;
		$scope.teamApproval['deploymentId']=$routeParams.deploymentId;
		
		$http({
            method  : 'POST',
            url     : namespace+'/resource/teamApproval',
            data    : $scope.teamApproval,
            headers : {'Content-Type': 'application/json'}
           }).then(
           function success(resp){
               console.log(resp.data);
               $scope.getTeamsForApproval($routeParams.deploymentId);
            },
           function failure(resp){
            alert("Error adding team approval");
            $log.error(resp.data)
           });
		
	}
	
	$scope.removeTeamFromApproval=function(team){
		$http({
			method : "DELETE",
			url : namespace+'/resource/teamApproval?appId='+ team.id
		  	})
		  	.then(
				function success(response) {
					console.log(response.data);
					alert("successfully deleted")
					$scope.getTeamsForApproval($routeParams.deploymentId);
				},
				function failure(response) {
					$log.error(response.status)
					alert("Error while deleting the Team from approval");
				});
	}
	
	$scope.cast=function(approval){
		var teamApproval={};
		teamApproval['id']=$scope.cast_team.id;
		teamApproval['approved']=approval;
		teamApproval['teamId']=$scope.cast_team.teamId;
		teamApproval['teamName']=$scope.cast_team.teamName;
		teamApproval['deploymentId']=$scope.cast_team.deploymentId;
		
		$http({
            method  : 'POST',
            url     : namespace+'/resource/teamApproval/ApproveOrReject',
            data    : teamApproval,
            headers : {'Content-Type': 'application/json'}
           }).then(
           function success(resp){
               console.log(resp.data);
               $scope.getTeamsForApproval($routeParams.deploymentId);
               
            },
           function failure(resp){
            alert("Error While Approving ");
            $log.error(resp.data)
           });
	}
	
	$scope.showCast=function(team){
		$scope.cast_team=team;
	}
	
	$scope.refreshDeploymentStatusOnApproval=function(){
		var refresh = true;
		angular.forEach($scope.teamList, function(team){
		  if(refresh) {
		    if(!team.approved){
		    	refresh = false;
		    }
		  }
		});
		$scope.getDeployment($routeParams.deploymentId)
	}
});

