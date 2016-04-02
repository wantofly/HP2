
myApp.controller('signUpController', function($scope, $parse, $timeout, $interval, greeter, user, UserSvc) 
{
	//login
	$scope.onReg = function(user) {
		
		if ($scope.formReg.$valid) 
		{ 
			$scope.vm.dataLoading = true;
			UserSvc.ajaxSignUp($scope.vm)
			.success(function(data) {
				$scope.vm.dataLoading = false;
    			// user
    			ret = data.jsonret;
    			content = data.jsondata;
    			msg = data.jsonmsg;
    			
    			//alert(msg + '[' + ret + ']');
    			if (ret == 0){
    				UserSvc.setCurrentUser(content);
    				window.location = '/';
    			}else{
    				$scope.vm.dataLoading = false;
        			$scope.formReg.$error.loginerror = data;
    			}
    		}).error(function(data){
				$scope.vm.dataLoading = false;
    			$scope.formReg.$error.loginerror = data;
    		}); 
        } else { 
            $scope.formReg.submitted = true; 
        } 
	};
	///////////////////////////////////////////////////////
	//ng-click="sendphonecode(reg_phone)"
	$scope.startMobCodeCount = 0;
	$scope.sendPhoneCode = function(){
		if ($scope.paracont == "获取验证码"){
			//todo send mcode
			$scope.vm.dataLoading = true;
			UserSvc.ajaxSmsSend($scope.vm.userName).success(function(data) {
				ret = data.jsonret;
    			if (ret == 0){
    				
    			}else{
        			$scope.formReg.$error.loginerror = data;
    			}
    			$scope.vm.dataLoading = false;
    		}).error(function(data){
				$scope.vm.dataLoading = false;
    			$scope.formReg.$error.loginerror = data;
    		});
			//count down start
			$scope.startCount();
		}
	};
	$scope.paracont = "获取验证码";  
	$scope.paraclass = "but_null";  
	$scope.paraevent = true;  
	var second = 60,  
	timePromise = undefined;
	$scope.startCount = function()
	{
		$scope.paracont = "60秒后可重发";
		$scope.paraclass = "but_null"; 
		timePromise = $interval(function(){  
			if(second<=0){  
				$interval.cancel(timePromise);  
				timePromise = undefined;  

				second = 60;  
				$scope.paracont = "获取验证码";
				$scope.paraclass = "but_null";  
				$scope.paraevent = true;
				
			}else{
				$scope.paracont = second + "秒后可重发";  
				$scope.paraclass = "not but_null";  
				second--;  

			}  
		},1000,100);
	};
	
	
	/////////// watch ///////////////////////////
	
	$scope.vm = {'userName':'', 'dataLoading':false};
	
	var timeout; 
	$scope.$watch('vm.userName', function(newVal, oldVal, scope) { 
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