angular.module('myApp.service',[])
.value('greeter',{
	salutation:'Hello',
	localize:function(localization){
		this.salutation = localization.salutation;
	},
	greet:function(name){
		return this.salutation+' '+name+'!';
	}
})
.value('user',{
	load:function(name){
		this.name=name;
	}
})
.factory('UserSvc',function($http){
	var current_user; 
	var runUserRequest = function(username, path) { 
        // 从使用JSONP 调用Github API的$http 服务中返回promise 
        return $http({ 
            method: 'JSONP', 
            url: 'https://api.github.com/users/' + 
                  username + '/' + 
                  path + '?callback=JSON_CALLBACK' 
        }); 
    };
    return { 
    	scopename: 'UserSvc.',
        getCurrentUser: function() { 
            return current_user; 
        },
        setCurrentUser: function(user) { 
            current_user = user; 
        },
        alertUser: function(user){
        	alert(user.name);
        },
        runLogin: function(user){
        	return $http({ 
    			method: 'POST', 
    			url: 'act/signIn/jsonsub', 
    			params: { 
    				'name': user.username,
    				'pass': user.password
    			} 
    		});
        },
        ajaxSignUp: function(user){
        	return $http({ 
        		method: 'POST', 
        		url: 'act/signUp/jsonsub', 
        		params: {
        			'inputUser.userName':user.userName,
        			'inputUser.password':user.password,
        			'inputUser.recommendCode':user.recommendCode,
        			'inputUser.mobcode':user.mobcode,
        			}
        	});
        },
        ajaxForget: function(user){
        	return $http({ 
        		method: 'POST', 
        		url: 'act/signUp/forget', 
        		params: {
        			'inputUser.userName':user.userName,
        			'inputUser.password':user.password,
        			'inputUser.mobcode':user.mobcode,
        		}
        	});
        },
        ajaxSignOut: function(user){
        	return $http({ 
        		method: 'POST', 
        		url: 'act/signOut/json',
        	});
        },
        ajaxGetUserBasicInfo: function(){
        	if (!current_user) 
        		return {'success':function(){return {'error':function(){}}}};
        	return $http({ 
        		method: 'POST', 
        		url: 'act/usersvc/getbasicinfo',
        		params: {
        			'inputUser.userName':current_user.userName,
        		}
        	}).success(function(data) {
				if (data.ret == 0)
					UserSvc.setCurrentUser(data.jsondata);
				console.log(data);
			}).error(function(data){
				alert(data.jsonmsg);
				console.log(data);
			});
        },
        ajaxSmsSend: function(mobile){
        	return $http({ 
        		method: 'GET', 
        		url: 'act/signUp/smsSend',
        		params: {
        			'mobile':mobile,
        		}
        	});
        }
    };
})
;

