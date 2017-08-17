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

app.controller('envDetailsController', function($scope,$http,$log,$httpParamSerializerJQLike,
		$routeParams,$location,authService) {
	
	$scope.search={};
	$scope.formData={};
	$scope.toggleAppSelect=function(){
		   if($scope.showAppSelect===false){
				$scope.showAppSelect=true;
			}else{
				$scope.showAppSelect=false;
			}
	}

	$scope.selectApp=function(app){
		$scope.appSearchText=app.appName;
		$scope.search.applicationId=app.id;
		$scope.showAppSelect=false;
		$scope.getEnv();
	  }

	$scope.autoSearchApp=function(){
		   $scope.search.applicationId="";
		   if($scope.appSearchText.length>2){
		   $http({
		        method : "GET",
		        url : namespace+'/resource/application/find?q='+$scope.appSearchText
		    }).then(function success(response) {
		    	$scope.appList=response.data;	    	
		    }, function failure(response) {
		        $log.error(response.status) 
		    });
		   }else{
			   $scope.getApplications(); 
		   }
	}


	$scope.getApplications=function(){
		   $http({
		        method : "GET",
		        url : namespace+'/resource/application/find'
		    }).then(function success(response) {
		    	$scope.appList=response.data;	    	
		    }, function failure(response) {
		        $log.error(response.status) 
		    });
	}
	$scope.getApplications();
	
	
	$scope.getEnv=function(){
		$scope.formData={};
		   $http({
		        method : "GET",
		        url : namespace+'/resource/environment'
		    }).then(function success(response) {
		    	$scope.envList=response.data;	    	
		    }, function failure(response) {
		        $log.error(response.status) 
		    });
	}
	
	$scope.getDetails=function(env){
		$scope.envname=env.name;
		$scope.envId=env.id;
	   $http({
	        method : "GET",
	        url : namespace+'/resource/environment/envDetails?appId='+$scope.search.applicationId+'&envId='+$scope.envId
	    }).then(function success(response) {
			$scope.formData=response.data;
	    }, function failure(response) {
	        $log.error(response.status) 
	    });
	}

	$scope.save=function(){

		$scope.formData['environmentId']=$scope.envId;
		$scope.formData['applicationId']=$scope.search.applicationId;
		
		$http({
            method  : 'POST',
            url     : namespace+'/resource/environment/envDetails',
            data    : $scope.formData,
            headers : {'Content-Type': 'application/json'}
           }).then(
	           function success(resp){
	               $log.info(resp.data);
	               $scope.formData=resp.data;
	            },
	           function failure(resp){
	            alert("Error Saving Environment Details:"+resp.data.message);
	            $log.error(resp.data)
	           });
	}
	
});

