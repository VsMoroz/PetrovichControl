var app = angular.module("ibeaconApp", ['ngRoute', 'ngResource']);

app.run(function($rootScope){
	$rootScope.user = {
			name : "Anonimous",
			email: "",
			isLogged:false
	};
});

app.config(function($routeProvider, $locationProvider, $httpProvider){
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
//	if(window.history && window.history.pushState){
//	    $locationProvider.html5Mode(true);
//	 }
	

});


