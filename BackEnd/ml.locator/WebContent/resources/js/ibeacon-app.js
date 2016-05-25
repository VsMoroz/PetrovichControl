var app = angular.module("ibeaconApp", ["ngRoute"]);

app.run(function($rootScope){ 
	$rootScope.username = "Login";
	$rootScope.isLogged = false;
});

app.config(function($routeProvider){
	$routeProvider.when('/login', {
		templateUrl:'pages/views/loginForm.html',
		controller:'loginController'
	});
	$routeProvider.when('/registration', {
		templateUrl:'pages/views/registration.html',
		controller:'registrationController'
	});
	$routeProvider.when('/home', {
		templateUrl:'pages/views/home.html',
		controller:'homeController'
	});
});


app.controller('headerController', function($scope) {

});

app.controller('loginController', function($scope){
	
});

app.controller('registrationController', function($scope){
	
});

app.controller('homeController', function($scope){
	
});