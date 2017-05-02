app.controller('ConfigController', function($scope,$http,$log,$httpParamSerializerJQLike) {
	$scope.formData = {};
	$scope.search={};
    $scope.resetPage=function(){
            $scope.showsucess=false;
            $scope.showerror=false;
            $scope.success="";
            $scope.error="";
        }
    $scope.reset=function(){
    	$scope.search={};
    	$scope.refresh();
    }
    $scope.currentPage = 0;
    $scope.add = function() {
    	$scope.resetPage();
    	 $http({
             method  : 'POST',
             url     : url,
             data    : $scope.formData,
             headers : {'Content-Type': 'application/json'}
            }).then(
                    function success(resp){
                        $log.info(resp.data)
                        $scope.refresh();
                        $scope.showsucess=true;
                        $scope.success=resp.data.message;
                        $scope.formData = {};
                     },
                    function failure(resp){
                    $log.error(resp.status)
                     $scope.showerror=true;
                     $scope.error=resp.data.message;
                    });
    };
    
    $scope.refresh = function(){
    $scope.resetPage();
	    $http({
	        method : "GET",
	        url : url+ "?p="+$scope.currentPage+"&"+$httpParamSerializerJQLike($scope.search)
	    }).then(function success(response) {
	    	$log.log(response.data.content)
             $scope.configurationList = response.data.content;
	    	 $scope.itemsPerPage = response.data.size;
	    	 $scope.totalPages=response.data.totalPages
	    }, function failure(response) {
	        $log.error(response.status)
             $scope.showerror=true;
             $scope.error=response.data.message;
	    });
    }

    $scope.pageChanged = function(){
    	$log.log($scope.currentPage);
    }
    
    $scope.modify=function (config)
    {
        $scope.resetPage();
        $log.log(config);
        $http({
         method  : 'PATCH',
         url     : url,
         data    : config,
         headers : {'Content-Type': 'application/json'}
        }).then(function success(resp){
                $log.info(resp.status)
                $scope.refresh();
                $scope.showsucess=true;
                $scope.success=resp.data.message;
                },
                function failure(resp){
                $log.error(resp.status)
                 $scope.showerror=true;
                 $scope.error=resp.data.message;
                });
    }
       $scope.resetPage();
       $scope.refresh();
       /** Pagination Starts**/

       $scope.range = function() {
    	   
 	    var rangeSize = 5;
 	    var ret = [];
 	    var start;

 	    start = $scope.currentPage;
 	    if($scope.totalPages < rangeSize){
 	    	rangeSize= $scope.totalPages;
 	    }else if ( start > $scope.pageCount()-rangeSize ) {
 	      start = $scope.totalPages-rangeSize+1;
 	    }
 	    

 		if(start+rangeSize>=$scope.totalPages){
 			start=$scope.totalPages-rangeSize;
 		}
 		for (var i=start; i<start+rangeSize; i++) {
 	 	    ret.push(i);
 	 	}
 	    return ret;
 	  };

 	  $scope.prevPage = function() {
 	    if ($scope.currentPage > 0) {
 	      $scope.currentPage--;
 	     $scope.refresh();
 	    }
 	  };

 	  $scope.prevPageDisabled = function() {
 	    return $scope.currentPage === 0 ? "disabled" : "";
 	  };

 	  $scope.pageCount = function() {
 	    return $scope.totalPages;
 	  };

 	  $scope.nextPage = function() {
 	    if ($scope.currentPage < $scope.pageCount()) {
 	      $scope.currentPage++;
 	     $scope.refresh();
 	    }
 	  };

 	  $scope.nextPageDisabled = function() {
 	    return $scope.currentPage === ($scope.totalPages-1) ? "disabled" : "";
 	  };

 	  $scope.setPage = function(n) {
 	    $scope.currentPage = n;
 	    $scope.refresh();
 	  };
 	 /** Pagination Ends**/
});


app.controller('roleController', function($scope,$http,$log) {
	$scope.formData = {};
	$scope.selection=[];
    $scope.resetPage=function(){
            $scope.showsucess=false;
            $scope.showerror=false;
            $scope.success="";
            $scope.error="";
        }
   
    $scope.add = function() {
    	$scope.resetPage();
    	 $http({
             method  : 'POST',
             url     : url,
             data    : $scope.formData,
             headers : {'Content-Type': 'application/json'}
            }).then(
                    function success(resp){
                        $log.info(resp.data)
                        $scope.refresh();
                        $scope.showsucess=true;
                        $scope.success=resp.data.message;
                        $scope.formData = {};
                     },
                    function failure(resp){
                    $log.error(resp.status)
                     $scope.showerror=true;
                     $scope.error=resp.data.message;
                    });
    };
    
    $scope.refresh = function(){
    $scope.resetPage();
	    $http({
	        method : "GET",
	        url : url,
	    }).then(function success(response) {
	    	$log.log(response.data.content)
            $scope.roleList = response.data;
	    }, function failure(response) {
	         $log.error(response.status)
             $scope.showerror=true;
             $scope.error=response.data.message;
	    });
    }

   
    $scope.toggle=function (role)
    {
        $scope.resetPage();
        $log.log(role);
        $http({
         method  : 'PATCH',
         url     : url,
         data    : role,
         headers : {'Content-Type': 'application/json'}
        }).then(function success(resp){
                $log.info(resp.status)
                $scope.refresh();
                $scope.showsucess=true;
                $scope.success=resp.data.message;
                },
                function failure(resp){
                $log.error(resp.status)
                 $scope.showerror=true;
                 $scope.error=resp.data.message;
                });
    }
       $scope.resetPage();
       $scope.refresh();
       $scope.configAccess=function(role){
    	   $scope.selectedRole=role;
    	   $http({
    	         method  : 'GET',
    	         url     : url+"/accessRef",
    	         params  : {"roleCode": role.roleCode},
    	         headers : {'Content-Type': 'application/json'}
    	        }).then(function success(resp){
    	        	
    	                $log.info(resp.status)
    	                $scope.selection=[];
    	                $scope.accessList=resp.data
    	                angular.forEach($scope.accessList,function(access,index){
    	                	//$log.info(access.accessCode);
    	                	if(access.active){
    	                		$log.debug("Active:"+access.accessCode);
    	                		$scope.selection.push(access.accessCode)
    	                	}
    	                });
    	                },
    	                function failure(resp){
    	                $log.error(resp.status)
    	                 $scope.showerror=true;
    	                 $scope.error="Error retrieving Access code";
    	                });
       }
       
       
		// toggle selection for a given 
		$scope.toggleSelection = function toggleSelection(accessCode) {
	    var idx = $scope.selection.indexOf(accessCode);
	   	 $log.debug("idx:"+idx);
	    // is currently selected
    	 $log.debug("TOGGLE:"+accessCode);
    	    $scope.roleAcc={};
	    	$scope.roleAcc["roleCode"]=$scope.selectedRole.roleCode;
	    	$scope.roleAcc["accessCode"]=accessCode;
	    	 $log.debug("TOGGLE:"+$scope.roleAcc);
	    	 
	    if (idx > -1) {
	    	//remove from DB

	    	 $http({
	             method  : 'DELETE',
	             url     : url+"/accessRef",
	             data    : $scope.roleAcc,
	             headers : {'Content-Type': 'application/json'}
	            }).then(function success(resp){
	                    $scope.selection.splice(idx, 1);
	                    },
	                    function failure(resp){
	                     $log.error(resp.status)
	                     $scope.showerror=true;
	                     $scope.error=resp.data.message;
	                    });
	     
	    }else {
	    	//Add to DB
	    	 $http({
	             method  : 'POST',
	             url     : url+"/accessRef",
	             data    : $scope.roleAcc,
	             headers : {'Content-Type': 'application/json'}
	            }).then(function success(resp){
	            	$scope.selection.push(accessCode);
                    },
                    function failure(resp){
                     $log.error(resp.status)
                     $scope.showerror=true;
                     $scope.error=resp.data.message;
                    });
	      
	    }
	  };
});

//accessCodes
app.controller('accessCodeController', function($scope,$http,$log) {
	$scope.formData = {};
	
    $scope.resetPage=function(){
            $scope.showsucess=false;
            $scope.showerror=false;
            $scope.success="";
            $scope.error="";
        }
   
    $scope.add = function() {
    	$scope.resetPage();
    	 $http({
             method  : 'POST',
             url     : url,
             data    : $scope.formData,
             headers : {'Content-Type': 'application/json'}
            }).then(
                    function success(resp){
                        $log.info(resp.data)
                        $scope.refresh();
                        $scope.showsucess=true;
                        $scope.success=resp.data.message;
                        $scope.formData = {};
                     },
                    function failure(resp){
                    $log.error(resp.status)
                     $scope.showerror=true;
                     $scope.error=resp.data.message;
                    });
    };
    
    $scope.refresh = function(){
    $scope.resetPage();
	    $http({
	        method : "GET",
	        url : url,
	    }).then(function success(response) {
	    	$log.log(response.data.content)
            $scope.accessCodes = response.data;
	    }, function failure(response) {
	         $log.error(response.status)
             $scope.showerror=true;
             $scope.error=response.data.message;
	    });
    }

   
    //get the resource url and methods
    	    $http({
    	        method :"GET" ,
    	        url : "/resource/config/mappings",
    	    }).then(function success(response) {
    	    	$log.log(response.data.content)
                $scope.mappings = response.data;
    	    }, function failure(response) {
    	         $log.error(response.status)
                 $scope.showerror=true;
                 $scope.error=response.data.message;
    	    });
    $scope.update=function(){
    	var selectedMapping=$("#accessMapping").val();
    	var mapAndMet = selectedMapping.split(":");
    	$scope.formData.mapping=mapAndMet[0];
    	$scope.formData.method=mapAndMet[1];
    }
    	    
    $scope.del=function (accessCode)
    {
        $scope.resetPage();
        $log.log(accessCode);
        $http({
         method  : 'DELETE',
         url     : url,
         data    : accessCode,
         headers : {'Content-Type': 'application/json'}
        }).then(function success(resp){
                $log.info(resp.status)
                $scope.refresh();
                $scope.showsucess=true;
                $scope.success=resp.data.message;
                },
                function failure(resp){
                $log.error(resp.status)
                 $scope.showerror=true;
                 $scope.error=resp.data.message;
                });
    }
       $scope.resetPage();
       $scope.refresh();
});

app.controller('usersController', function($scope,$http,$log) {
	/** Pagination Starts**/
    $scope.currentPage = 0;
    $scope.filterData={};
    $scope.formData={};
    $scope.userPwd={};
    $scope.updateData={};
    $scope.refresh = function(){
    	
    	f_url=url+"?p="+$scope.currentPage+"&"+$.param($scope.filterData);
    	$http({
    	        method : "GET",
    	        url : f_url
    	    }).then(function success(response) {
    	    	$log.log(response.data.content)
                 $scope.userList = response.data.content;
    	    	 $scope.itemsPerPage = response.data.size;
    	    	 $scope.totalPages=response.data.totalPages
    	    }, function failure(response) {
    	        $log.error(response.status)
                 $scope.showerror=true;
                 $scope.error=response.data.message;
    	    });
     }
    
    $scope.clearData=function(){
    	$scope.formData={};
    }
    
    
    $scope.save=function(){
       	$http({
	        method : "POST",
	        url : url,
	        data:$scope.formData
	    }).then(function success(response) {
	    	$log.log(response.data)
	    	 $scope.currentPage = 0;
            $scope.refresh();
	    }, function failure(response) {
	        $log.error(response.status)
           $scope.showerror=true;
           $scope.error=response.data.message;
	    });
    }
    
    
 $scope.resetPwd=function(user){
       	$http({
	        method : "GET",
	        url : url+"/resetPwd?userId="+user.userId
	    }).then(function success(response) {
	    	$log.log(response.data)
	    	 $scope.currentPage = 0;
	    	 $scope.showsucess=true;
	         $scope.success=response.data.message;
            $scope.refresh();
	    }, function failure(response) {
	        $log.error(response.status)
           $scope.showerror=true;
           $scope.error=response.data.message;
	    });
    }
    
 $scope.updatePwd=function(user){
	 if(user.reentryPassword!=user.password){
 		alert("Password not matching");
 	}
    	$http({
	        method : "GET",
	        url : url+"/resetPwd?userId="+user.userId+"&password"+user.password
	    }).then(function success(response) {
	    	$log.log(response.data)
	    	 $scope.currentPage = 0;
	    	 $scope.showsucess=true;
	         $scope.success=response.data.message;
         $scope.refresh();
	    }, function failure(response) {
	        $log.error(response.status)
        $scope.showerror=true;
        $scope.error=response.data.message;
	    });
 }
 $scope.update=function(){
    	$http({
	        method : "POST",
	        url : url+"/update",
	        data:$scope.updateData
	    }).then(function success(response) {
	    	$log.log(response.data)
	    	 $scope.currentPage = 0;
	    	 $scope.showsucess=true;
	         $scope.success=response.data.message;
         $scope.refresh();
	    }, function failure(response) {
	        $log.error(response.status)
        $scope.showerror=true;
        $scope.error=response.data.message;
	    });
 }
 
 $scope.editPwd=function(user){
 	$scope.userPwd.userId=user.userId;
 }
 
    $scope.edit=function(user){
    	$scope.updateData.userId=user.userId;
    	$scope.updateData.firstName=user.firstName;
    	$scope.updateData.secondName=user.secondName;
    	$scope.updateData.password=user.password;
    	$scope.updateData.email=user.email;
    	$scope.updateData.state=user.state;
    	$scope.updateData.modifiedDate=user.modifiedDate;
    	$scope.updateData.createdDate=user.createdDate;
    }
    
    $scope.filter = function(){
    	$scope.currentPage = 0;
    	$scope.refresh();
    }
    $scope.range = function() {
 	   
	    var rangeSize = 5;
	    var ret = [];
	    var start;

	    start = $scope.currentPage;
	    if($scope.totalPages < rangeSize){
	    	rangeSize= $scope.totalPages;
	    }else if ( start > $scope.pageCount()-rangeSize ) {
	      start = $scope.totalPages-rangeSize+1;
	    }
	    

		if(start+rangeSize>=$scope.totalPages){
			start=$scope.totalPages-rangeSize;
		}
		for (var i=start; i<start+rangeSize; i++) {
	 	    ret.push(i);
	 	}
	    return ret;
	  };

	  $scope.prevPage = function() {
	    if ($scope.currentPage > 0) {
	      $scope.currentPage--;
	     $scope.refresh();
	    }
	  };

	  $scope.prevPageDisabled = function() {
	    return $scope.currentPage === 0 ? "disabled" : "";
	  };

	  $scope.pageCount = function() {
	    return $scope.totalPages;
	  };

	  $scope.nextPage = function() {
	    if ($scope.currentPage < $scope.pageCount()) {
	      $scope.currentPage++;
	     $scope.refresh();
	    }
	  };

	  $scope.nextPageDisabled = function() {
	    return $scope.currentPage === ($scope.totalPages-1) ? "disabled" : "";
	  };

	  $scope.setPage = function(n) {
	    $scope.currentPage = n;
	    $scope.refresh();
	  };
	 /** Pagination Ends**/
	  $scope.refresh();
	  
	  $http({
	        method : "GET",
	        url : url+"/states",
	    }).then(function success(response) {
	    	$log.log(response.data)
            $scope.userStatusList = response.data;
	    }, function failure(response) {
	        $log.error(response.status)
           $scope.showerror=true;
           $scope.error=response.data.message;
	    });
	  
	 
	$scope.configureRole=function(user){
		$scope.selectedUser=user;
		$http({
	         method  : 'GET',
	         url     : url+"/getRolesForUser",
	         params  : {"userId": $scope.selectedUser.userId},
	         headers : {'Content-Type': 'application/json'}
	        }).then(function success(resp){
	                $log.info(resp.status)
	                $scope.selection=[];
	                $scope.roleList=resp.data
	                angular.forEach($scope.roleList,function(role,index){
	                	if(role.active){
	                		$log.debug("Active:"+role.roleCode);
	                		$scope.selection.push(role.roleCode)
	                	}
	                });
	                },
	                function failure(resp){
	                $log.error(resp.status)
	                 $scope.showerror=true;
	                 $scope.error="Error retrieving Access code";
	                });
	}
	$scope.selection=[];
	// toggle selection for a given 
	$scope.toggleSelection = function toggleSelection(roleCode) {
	    var idx = $scope.selection.indexOf(roleCode);
	   	 $log.debug("idx:"+idx);
	    // is currently selected
  	 $log.debug("TOGGLE:"+roleCode);
  	    $scope.roleAcc={};
  	    	$scope.roleAcc["userId"]=$scope.selectedUser.userId;
	    	$scope.roleAcc["roleCode"]=roleCode;
	    	
	    	 $log.debug("TOGGLE:"+$scope.roleAcc);
	    	 
	    if (idx > -1) {
	    	//remove from DB

	    	 $http({
	             method  : 'DELETE',
	             url     : url+"/deleteRoleFromUser",
	             data    : $scope.roleAcc,
	             headers : {'Content-Type': 'application/json'}
	            }).then(function success(resp){
	                    $scope.selection.splice(idx, 1);
	                    },
	                    function failure(resp){
	                     $log.error(resp.status)
	                     $scope.showerror=true;
	                     $scope.error=resp.data.message;
	                    });
	     
	    }else {
	    	//Add to DB
	    	 $http({
	             method  : 'POST',
	             url     : url+"/addRolesToUser",
	             data    : $scope.roleAcc,
	             headers : {'Content-Type': 'application/json'}
	            }).then(function success(resp){
	            	$scope.selection.push(roleCode);
                  },
                  function failure(resp){
                   $log.error(resp.status)
                   $scope.showerror=true;
                   $scope.error=resp.data.message;
                  });
	      
	    }
	  };
	  
});


app.controller('authController', function($scope,$http,$log) {
	
	$http({
        method : "GET",
        url : "/resource/users/principal",
    }).then(function success(response) {
    	$log.log(response.data)
        $scope.user = response.data;
    }, function failure(response) {
        $log.error(response.status)
       
    });
	
});
