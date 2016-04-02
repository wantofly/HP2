
myApp.controller('userController', function($scope, $timeout, UserSvc) 
		{
	$scope.scopename = 'user-Controller';

	$scope.$on('$routeChangeStart', function(event, toState, toParams, fromState, fromParams){ 
		// logic
		console.log('route begin change');
	});
});
myApp.controller('welcomeController', function($scope, $timeout, UserSvc) 
{
	$scope.scopename = 'welcome-Controller';
	
	$scope.$on('$routeChangeStart', function(event, toState, toParams, fromState, fromParams){ 
	    	// logic
	    	console.log('route begin change');
	});
});
myApp.controller('homeController', function($scope, $timeout, UserSvc) 
{
	$scope.scopename = 'home-Controller';
	
	$scope.$on('$routeChangeStart', function(event, toState, toParams, fromState, fromParams){ 
		// logic
		console.log('route begin change');
	});
	
	$scope.printme = function(){
		alert('printme');
	};
	//login
	$scope.signOut = function(user) {
		
		UserSvc.ajaxSignOut()
		.success(function(data) {
			ret = data.jsonret;
			content = data.jsondata;
			msg = data.jsonmsg;
			
			UserSvc.setCurrentUser(null);
		}).error(function(data){
			
		});
	};
});

