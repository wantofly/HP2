
myApp.controller('signInController', function($rootScope, $scope, $parse, $timeout, greeter, user, UserSvc) 
{
	$scope.scopename = "ScopeName"; 
	$scope.cnt = 0; 
	$scope.add = function(amount) { $scope.cnt += amount; };
	$scope.person = { 
			name: 'Ari Lerner' 
	};
	$scope.greeting = greeter.greet(user.name);
	
	//login
	$scope.onLogin = function(user) {
		
		if ($scope.form8.$valid) 
		{ 
			$scope.vm.dataLoading = true;
			UserSvc.runLogin($scope.vm)
			.success(function(data) {
				$scope.vm.dataLoading = false;
    			// user
    			ret = data.jsonret;
    			content = data.jsondata;
    			msg = data.jsonmsg;
    			
    			//alert(msg + '[' + ret + ']');
    			if (ret == 0){
    				UserSvc.setCurrentUser(content);
    				$('#signInBox').modal('toggle');
//    				window.location = '/';
    			}else{
    				$scope.form8.$error.loginerror = data;
    			}
    		}).error(function(data){
				$scope.vm.dataLoading = false;
    			$scope.form8.$error.loginerror = {'jsonmsg':'服务器错误'};
    		}); 
        } else { 
            $scope.form8.submitted = true; 
        } 
	};
	/////////// watch
	
	$scope.vm = {'username':'', 'dataLoading':false};
	$scope.parsedValue = '2';
	var timeout; 
	
	$scope.$watch('vm.username', function(newVal, oldVal, scope) { 
//		if (timeout) $timeout.cancel(timeout); 
//        timeout = $timeout(function() { 
//        	var parseFun = $parse(newVal); 
//        	$scope.parsedValue = parseFun(scope);
//        }, 350);  //350 ms
		
		if (newVal !== oldVal) {
			if (newVal && newVal.toString().length == 11){
				//UserSvc.alertUser({'name':'Good'});
			}
		}
	});
	
});