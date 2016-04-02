
angular.module('myApp.directive', []);

///////////////////////////////////////////////////

var myApp = angular.module('myApp', ['ngAnimate','ui.router','myApp.filter', 'myApp.directive', 'myApp.service'])
.run(function($rootScope, UserSvc) { 
    $rootScope.GlobalName = "[Global Name]";
    $rootScope.UserSvc = UserSvc;
//    $rootScope.cuser = UserSvc;

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
	      templateUrl: 'parts/welcome.html',
	      controller: 'welcomeController'
	  })
	  .state('start.main', {
		  url: '/main',
		  templateUrl: 'parts/main.html',
	  })
	  .state('start.other', {
		  url: '/other',
		  templateUrl: 'parts/other.html',
	  })
	  .state('start.about', {
		  url: '/about',
		  templateUrl: 'parts/about.html',
	  })
	  .state('start.forget', {
		  url: '/forget',
		  templateUrl: 'parts/forget.html',
	  })
	  .state('start.contact', {
		  url: '/contact',
		  templateUrl: 'parts/contact.html',
	  })
	  .state('userinfo', {
		  url: '/userinfo',
		  templateUrl: 'parts/user/userinfo.html',
	      controller: 'userController'
	  })
	  .state('userinfo.msg', {
		  url: '/msg',
		  templateUrl: 'parts/user/msg.html',
	  })
	  .state('userinfo.balance', {
		  url: '/balance',
		  templateUrl: 'parts/user/balance.html',
	  })
	  .state('userinfo.basic', {
		  url: '/basic',
		  templateUrl: 'parts/user/basic.html',
	      controller: 'userController',
	      onEnter: function(UserSvc)
	      {
			  console.log(' -->> userinfo.basic');
			  UserSvc.ajaxGetUserBasicInfo();
		  },
	  })
	  ;
	  $urlRouterProvider.otherwise('/start/main');
	})
;



