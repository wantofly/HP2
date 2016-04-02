
myApp.controller('forgetController', function($scope, $parse, $timeout, $interval, greeter, user, UserSvc) 
{
	//login
	$scope.onReg = function(user) {
		
		if ($scope.formForget.$valid) 
		{ 
			$scope.vm.dataLoading = true;
			UserSvc.ajaxForget($scope.vm)
			.success(function(data) {
				$scope.vm.dataLoading = false;
    			//alert(msg + '[' + ret + ']');
    			if (data.jsonret == 0){
    				alert('更新成功，请重新登录');
    			}else{
    				$scope.vm.dataLoading = false;
        			$scope.formForget.$error.loginerror = data;
    			}
    		}).error(function(data){
				$scope.vm.dataLoading = false;
    			$scope.formForget.$error.loginerror = data;
    		}); 
        } else { 
            $scope.formForget.submitted = true; 
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
        			$scope.formForget.$error.loginerror = data;
    			}
    			$scope.vm.dataLoading = false;
    		}).error(function(data){
				$scope.vm.dataLoading = false;
    			$scope.formForget.$error.loginerror = data;
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
	
	$scope.vm = {'userName':'', 'dataLoading':false};
	
	
});