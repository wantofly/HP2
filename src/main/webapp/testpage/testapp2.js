

var myApp = angular.module('myApp', ['ngRoute'])
.run(function($rootScope) { 
    $rootScope.GlobalName = "[Global Name]";

//    $rootScope.$on('$routeChangeStart', function(event, toState, toParams, fromState, fromParams){ 
//    	// logic
//    	console.log('route begin change');
//    });
//    
//    $rootScope.$on('$routeChangeSuccess', function(evt, current, previous) {
//        console.log('route have already changed');
//      }); 
})
.config(['$routeProvider', function($routeProvider) {
	console.log('route provider start to configure.');
	$routeProvider
	.when('/', {
		template: '<div><h2>Route!!!!!!!!!!!!</h2></div>',
		controller: 'homeController'
	})
	.when('/page1', {
		templateUrl: 'page1.html',
		controller: 'homeController'
	})
	.when('/page2', {
		templateUrl: 'page2.html',
		controller: 'homeController'
	})
	.otherwise({
		redirectTo: '/page1'
	});
}]);

myApp.controller('homeController', function($scope, $timeout) 
{
	$scope.scopename = 'home-Controller';
	
	$scope.$on('$routeChangeStart', function(event, toState, toParams, fromState, fromParams){ 
		// logic
		console.log('route begin change');
	});
	
	$scope.printme = function(){
		alert('printme');
	};
	
});


