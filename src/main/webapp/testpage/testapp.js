

var myApp = angular.module('myApp', ['ui.router'])
.run(function($rootScope) { 
    $rootScope.GlobalName = "[Global Name]";

    $rootScope.$on('$routeChangeStart', function(event, toState, toParams, fromState, fromParams){ 
    	// logic
    	console.log('route begin change');
    });
    
    $rootScope.$on('$routeChangeSuccess', function(evt, current, previous) {
        console.log('route have already changed');
      }); 
    
    $rootScope.$on('$viewContentLoading',
    		function(event, viewConfig) {
    		// 在这里可以访问所有视图配置属性
    		// 以及一个特殊的“ targetView”属性
    		// viewConfig.targetView
    	console.log('$viewContentLoading have already changed');
    		});
})
.config(function($stateProvider,$urlRouterProvider) {  
	  $stateProvider 
	  .state('start', {
	      url: '/start',
	      templateUrl: 'content.html',
	      controller: 'homeController'
	  })
	  .state('start.page1', {
		  url: '/page1',
		  templateUrl: 'page1.html',
		  onEnter: function(){
			  console.log('page1 enter');
		  },
		  onExit: function(){
			  console.log('page1 exit ');
		  }
	  })
	  .state('start.page2', {
		  url: '/page2',
		  templateUrl: 'page2.html',
		  onEnter: function(){
			  console.log('page2 enter');
		  },
		  onExit: function(){
			  console.log('page2 exit ');
		  }
	  })
	  ;
	  $urlRouterProvider.otherwise('/start/page1');
	})
;

myApp.controller('homeController', function($rootScope, $scope, $timeout) 
{
	$scope.scopename = 'home-Controller';
	
	$scope.$on('$routeChangeStart', function(event, toState, toParams, fromState, fromParams){ 
		// logic
		console.log('route begin change');
	});
	
	$scope.printme = function(){
		alert('printme');
	};
	
	$rootScope.$on('$stateChangeStart', 
			function(event, toState, toParams, fromState, fromParams, options){
				console.log('234sk: homeController $stateChangeStart happen');
//			    event.preventDefault(); 
			    // transitionTo() promise will be rejected with 
			    // a 'transition prevented' error
			})
});


