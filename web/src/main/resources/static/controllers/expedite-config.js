var app = angular.module('myApp', ['ngRoute','ui.bootstrap','angucomplete']);

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