angular.module('directive.loading', []).directive('loading',   ['$http' ,function ($http)
{
    return {
        restrict: 'A',
        link: function (scope, elm, attrs)
        {
            scope.isLoading = function () {
                return $http.pendingRequests.length > 0;
            };

            scope.$watch(scope.isLoading, function (v)
            {
                if(v){
                    elm.show();
                }else{
                    elm.hide();
                }
            });
        }
    };

}]);


var app = angular.module('myApp', ['ngRoute','ui.bootstrap','angucomplete','directive.loading']);

var namespace="";

app.config(function($routeProvider) {
   
	var resolveController = function(path) {
        var deferred = $q.defer();
        var script = document.createElement('script');
        script.src = path;
        script.onload = function() {
            $scope.$apply(deferred.resolve());
        };
        document.body.appendChild(script);
        return deferred.promise;
    };
    
  $routeProvider
  .when('/', {
    templateUrl : 'pages/Configuration.html',
   })
  .when('/role', {
    templateUrl : 'pages/Role.html',
  })
  .when('/accessCode', {
    templateUrl : 'pages/AccessCode.html',
  })
  .when('/users', {
    templateUrl : 'pages/Users.html',
  })
  .when('/departments', {
    templateUrl : 'pages/Departments.html',
  })
  .when('/team', {
    templateUrl : 'pages/Team.html',
  })
   .when('/configuration', {
    templateUrl : 'pages/Configuration.html',
  })
  .when('/projectView/:projectId', {
    templateUrl : 'pages/ProjectView.html',
  })
   .when('/resourcePlanning', {
    templateUrl : 'pages/ResourcePlanningView.html',
  })
  .when('/application', {
    templateUrl : 'pages/Application.html',
  })
   .when('/myProject', {
    templateUrl : 'pages/MyProjects.html',
  })
  .when('/addDelivery', {
    templateUrl : 'pages/AddDelivery.html',
  })
  .when('/changeManagement', {
    templateUrl : 'pages/ChangeManagement.html',
  })
 .when('/approvedDelivery', {
    templateUrl : 'pages/ApprovedDelivery.html',
  })
  .when('/environment', {
    templateUrl : 'pages/Environment.html',
  })
  .when('/envDetails', {
    templateUrl : 'pages/EnvDetails.html',
  })
   .when('/searchDeployment', {
    templateUrl : 'pages/Deployment.html',
  })
 
  .otherwise({redirectTo: '/'});
});


app.directive('capitalize', function() {
    return {
      require: 'ngModel',
      link: function(scope, element, attrs, modelCtrl) {
        var capitalize = function(inputValue) {
          if (inputValue == undefined) inputValue = '';
          var capitalized = inputValue.toUpperCase();
          if (capitalized !== inputValue) {
            modelCtrl.$setViewValue(capitalized);
            modelCtrl.$render();
          }
          return capitalized;
        }
        modelCtrl.$parsers.push(capitalize);
        capitalize(scope[attrs.ngModel]); // capitalize initial value
      }
    };
  });

app.directive('toHtml', function() {
	return {
		  link: function(scope, element, attr) {
			element.html(attr.raw);  
		  }
	  };
   });

app.directive('loading',   ['$http' ,function ($http){
    return {
        restrict: 'A',
        link: function (scope, elm, attrs)
        	{
            	scope.isLoading = function () {
            		return $http.pendingRequests.length > 0;
            	};

            scope.$watch(scope.isLoading, function (v)
            	{
                if(v){
                    elm.show();
                	}else{
                    elm.hide();
                	}
            	});
        	}
    	};

	}]);


app.factory('authService',function($http) {
	var deps = {};
	return{
		getPrincipal:function (){
			return $http({
		        method : "GET",
		        url : namespace+"/resource/users/principal",
		    });
		},
		getTeamListForUser:function(){
			return $http({
		        method : "GET",
		        url : namespace + "/resource/team/myTeams",
		    });
		}
		
	}	
});


app.controller('markdownController', function($scope,$http,$log,$element) {

	$scope.toBold=function(){

        // Append/remove ### surround the selection
        var chunk, cursor, selected = $element.getSelection(),
            content = e.getContent();
        if (selected.length === 0) {
            // Give extra word
            chunk = e.locale.header.description + '\n';
        } else {
            chunk = selected.text + '\n';
        }
        var key = 0,
            hash='',
            start = selected.start-1,
            end = selected.start,
            prevChr = content.substring(start,end); 
        while (/^\s+$|^#+$/.test(prevChr)){
            if (/^#+$/.test(prevChr))
                hash = hash+'#';
            key +=1;
            prevChr = content.substring(start-key,end-key);
        }

        if (hash.length > 0){
            // already a title
            var startLinePos,
                endLinePos = content.indexOf('\n', selected.start);
        
            //  more  than ### -> #
            if (hash.length > 2){
                hash = '#';
                startLinePos = content.indexOf('\n', selected.start - 5);
                e.setSelection(startLinePos, endLinePos+1);
                e.replaceSelection('\n'+hash+' '+chunk);
                cursor = startLinePos+3;
            }else{
                hash = hash +'#';
                startLinePos = content.indexOf('\n', selected.start - (hash.length + 1));
                e.setSelection(startLinePos, endLinePos+1);
                e.replaceSelection('\n'+hash+' '+chunk);
                cursor = selected.start + 1;
            }
        }else{

            // new title
            hash= '#';
            e.replaceSelection('\n'+hash+' '+ chunk);
            cursor = selected.start + 3;
        }
        e.setSelection(cursor, cursor + chunk.length-1);
        return false;
    
	}
});