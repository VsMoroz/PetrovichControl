var app = angular.module("ibeaconApp", ["ngRoute"]);

app.run(function($rootScope){ 
	$rootScope.username = "Login";
	$rootScope.email = "";
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
	$routeProvider.when('/', {
		templateUrl:'pages/views/home.html',
		controller:'homeController'
	});
	
	//disable # hash tag 
	//if(window.history && window.history.pushState){
	 //   $locationProvider.html5Mode(true);
	  //}
});


