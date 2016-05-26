app.controller('headerController', function($rootScope, $scope) {
	$scope.username = $rootScope.username;

});

app.controller('loginController', function($rootScope, $scope, $http){
	$scope.test1 = "test1";
});

app.controller('registrationController', function($scope){
	$scope.test = 1;
	$scope.register = function(username, email, password, repeatPassword){
		window.alert(username + ", "+ email + ", "+ password + ", "+ repeatPassword);
	};
	 
	
});

app.controller('homeController', function($rootScope, $scope, $http, principalInfo){
	$scope.test = 1;
	if ($rootScope.isLoggedIn){
		var user = principalInfo.getPrincipalInfo($http);
		if (typeof user == "object" && user.hasOwnProperty("username") && user.username != "unauthorized"){
			$rootScope.isLogged = true;
			$rootScope.username = user.username;
			$scope.username = user.username;
			$scope.email = user.email;
			
		}
	}
	
});
app.controller('testController', function($scope){
	$scope.test = 1;
	$scope.funk = function(){
		$scope.test += 1 ;
	};
})