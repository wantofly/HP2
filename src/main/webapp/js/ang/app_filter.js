
angular.module('myApp.filter', [])
.filter('greet', function(){
	return function(name){
		return 'Hello, ' + name;
	}
});