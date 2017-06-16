//Department , Team , Team member
app
		.controller(
				'departmentsController',
				function($scope, $http, $log, $httpParamSerializerJQLike) {
					$scope.formData = {};

					var filter = function() {

					}
					var clearData = function() {

					}

					$scope.edit = function(content) {
						$scope.formData['departmentName'] = content.departmentName;
						$scope.formData['id'] = content.id;
					}

					$scope.save = function() {
						$scope.resetPage();
						
						$scope.formData['parentDepartmentId'] = $scope.selectedDept.id;
						$scope.formData['managerId'] = $scope.selectedManager.userId;

						$http({
							method : 'POST',
							url : url,
							data : $scope.formData,
							headers : {
								'Content-Type' : 'application/json'
							}
						}).then(function success(resp) {
							$log.info(resp.data)
							$scope.refresh();
							$scope.showsucess = true;
							$scope.success = resp.data.message;
							$scope.formData = {};
						}, function failure(resp) {
							$log.error(resp.status)
							$scope.showerror = true;
							$scope.error = resp.data.message;
						});
					}

					/** Pagination Starts and list * */

					$scope.search = {};

					$scope.resetPage = function() {
						$scope.showsucess = false;
						$scope.showerror = false;
						$scope.success = "";
						$scope.error = "";
					}

					$scope.reset = function() {
						$scope.search = {};
						$scope.refresh();
					}

					$scope.currentPage = 0;
					$scope.refresh = function() {
						$scope.resetPage();
						$http({
							method : "GET",
							url : url
						}).then(function success(response) {
							$log.log(response.data.content)
							$scope.contentList = response.data;
						}, function failure(response) {
							$log.error(response.status)
							$scope.showerror = true;
							$scope.error = response.data.message;
						});
					}

					$scope.resetPage();
					$scope.refresh();

					$scope.range = function() {

						var rangeSize = 5;
						var ret = [];
						var start;

						start = $scope.currentPage;
						if ($scope.totalPages < rangeSize) {
							rangeSize = $scope.totalPages;
						} else if (start > $scope.pageCount() - rangeSize) {
							start = $scope.totalPages - rangeSize + 1;
						}

						if (start + rangeSize >= $scope.totalPages) {
							start = $scope.totalPages - rangeSize;
						}
						for (var i = start; i < start + rangeSize; i++) {
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
						return $scope.currentPage === ($scope.totalPages - 1) ? "disabled"
								: "";
					};

					$scope.setPage = function(n) {
						$scope.currentPage = n;
						$scope.refresh();
					};
					/** Pagination Ends* */

					$scope.viewManager = function(managerId) {
						$http(
								{
									method : "GET",
									url : "/resource/users/manager?managerId="
											+ managerId
								})
								.then(
										function success(response) {
											$log.log(response.data.content)
											$scope.manager = response.data;
											$scope.manager['userName'] = $scope.manager.firstName
													+ " "
													+ $scope.manager.secondName
										},
										function failure(response) {
											$log.error(response.status)
											$scope.showerror = true;
											$scope.error = response.data.message;
										});
					}

					$scope.viewDept = function(deptId) {
						$http({
							method : "GET",
							url : url + "/deptById?deptId=" + deptId
						}).then(function success(response) {
							$log.log(response.data.content)
							$scope.pdept = response.data;
						}, function failure(response) {
							$log.error(response.status)
							$scope.showerror = true;
							$scope.error = response.data.message;
						});
					}
					
					$scope.toggleSearchSelect=function(){
						if($scope.showSearchSelect===false){
							$scope.showSearchSelect=true;
						}else{
							$scope.showSearchSelect=false;
						}
							
					}
					
					$scope.selectThisDepartment=function(dept){
						$scope.selectedDept=dept;
						$scope.showSearchSelect=false;
					}
					
					
					$scope.toggleMngrSearchSelect=function(){
						if($scope.showSearchMngrSelect===false){
							$scope.showSearchMngrSelect=true;
						}else{
							$scope.showSearchMngrSelect=false;
						}
							
					}
					
					$scope.selectThisManager=function(mngr){
						$scope.selectedManager=mngr;
						$scope.showSearchMngrSelect=false;
					}
					
					$scope.getAllUsers=function(){
						$http({
							method : "GET",
							url : namespace+"/resource/users/all"
						}).then(function success(response) {
							$scope.Users = response.data;
						}, function failure(response) {
							$log.error(response.status)
						});
					}
					$scope.getAllUsers();
				});

app
		.controller(
				'teamController',
				function($scope, $http, $log, $httpParamSerializerJQLike) {
					$scope.formData = {};
					$scope.memberData = {};

					$scope.deleteTeamMember = function(member) {
						$http(
								{
									method : 'DELETE',
									url : url + "/members?userId="
											+ member.userId + "&teamId="
											+ $scope.selectedTeamId
								}).then(function success(resp) {
							$log.info(resp.data);
							alert(resp.data.message);
							$scope.getTeamMembers($scope.selectedTeamId);
						}, function failure(resp) {
							$log.error(resp.status)
							alert(resp.data.message);
						});
					}

					$scope.saveTeamMember = function() {

						$scope.memberData['teamId'] = $scope.selectedTeamId;
						$scope.memberData['userId'] = $scope.memberObj.originalObject.userId;

						$http({
							method : 'POST',
							url : url + "/addMember",
							data : $scope.memberData,
							headers : {
								'Content-Type' : 'application/json'
							}
						}).then(function success(resp) {
							$log.info(resp.data)
							$scope.getTeamMembers($scope.selectedTeamId);
							alert(resp.data.message);
							$scope.memberData = {};
						}, function failure(resp) {
							$log.error(resp.status)
							alert(resp.data.message);
						});
					}

					$scope.getTeamMembers = function(teamId) {
						$scope.selectedTeamId = teamId;
						$http({
							method : 'GET',
							url : namespace+"/resource/users/members?teamId=" + teamId
						}).then(function success(resp) {
							$log.info(resp.data)
							$scope.memberList = resp.data;
						}, function failure(resp) {
							$log.error(resp.status)
							$scope.showerror = true;
							$scope.error = resp.data.message;
						});
					}

					$scope.save = function() {
						$scope.resetPage();

						if (typeof $scope.departObj !== 'undefined') {
							$scope.formData['departmentId'] = $scope.departObj.originalObject.id;
						}
						if (typeof $scope.managerObj !== 'undefined') {
							$scope.formData['managerId'] = $scope.managerObj.originalObject.userId;
						}
						$http({
							method : 'POST',
							url : url,
							data : $scope.formData,
							headers : {
								'Content-Type' : 'application/json'
							}
						}).then(function success(resp) {
							$log.info(resp.data)
							$scope.refresh();
							$scope.showsucess = true;
							$scope.success = resp.data.message;
							$scope.formData = {};
						}, function failure(resp) {
							$log.error(resp.status)
							$scope.showerror = true;
							$scope.error = resp.data.message;
						});
					}

					$scope.resetPage = function() {
						$scope.showsucess = false;
						$scope.showerror = false;
						$scope.success = "";
						$scope.error = "";
					}

					$scope.reset = function() {
						$scope.search = {};
						$scope.refresh();
					}

					$scope.refresh = function() {
						$scope.resetPage();
						$http({
							method : "GET",
							url : url
						}).then(function success(response) {
							$log.log(response.data.content)
							$scope.teamList = response.data;
						}, function failure(response) {
							$log.error(response.status)
							$scope.showerror = true;
							$scope.error = response.data.message;
						});
					}

					$scope.resetPage();
					$scope.refresh();

					$scope.viewManager = function(managerId) {
						$http(
								{
									method : "GET",
									url : namespace+"/resource/users/manager?managerId="
											+ managerId
								})
								.then(
										function success(response) {
											$log.log(response.data.content)
											$scope.manager = response.data;
											$scope.manager['userName'] = $scope.manager.firstName
													+ " "
													+ $scope.manager.secondName
										},
										function failure(response) {
											$log.error(response.status)
											$scope.showerror = true;
											$scope.error = response.data.message;
										});
					}
				});

app.controller('applicationController', function($scope, $http, $log,
		$httpParamSerializerJQLike, $routeParams,authService) {
	
	$scope.formData = {};
	$scope.getApplications = function() {
		$http(
				{
					method : "GET",
					url : namespace+"/resource/application?teamId="
							+ $scope.selectedTeam
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
	
	$scope.edit=function(app){
		$scope.formData=app;
	}
	
	$scope.reset=function(app){
		$scope.formData={};
	}
	
	$scope.save = function() {
		
		$scope.formData['teamId'] = $scope.selectedTeam;
		
		$http({
			method : 'POST',
			url : namespace+"/resource/application",
			data : $scope.formData,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function success(resp) {
			$log.info(resp.data)
			$scope.getApplications();

		}, function failure(resp) {
			$log.error(resp.status)
			$scope.showerror = true;
			$scope.error = resp.data.message;
		});
	}
	
	$scope.getMyTeam = function() {
		authService.getTeamListForUser().success(function(data, status) {
			$scope.myteam = data;
	    });
	}
	$scope.getMyTeam();
	
	$scope.loadTeam=function(){
		console.log($scope.selectedTeam);
		$scope.getApplications();
	}
	
});
