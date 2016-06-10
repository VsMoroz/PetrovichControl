app.controller('headerController', function($rootScope, $scope) {
	$scope.username = $rootScope.username;

});

app.controller('loginController', function($rootScope, $scope, $http){
	$scope.test1 = "test1";
});

app.controller('registrationController', function($scope, $http){
	
	$scope.register = function(username, email, password, repeatPassword){
		var data = $.param({
			'username'	: username,
			'email'		: email,
			'password'	: password,
			'userRoles'	: ['ROLE_USER']
		});
		
		var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }
		$http.post('api/user/register', data, config)
        .success(function (data, status, headers, config) {
            $scope.PostDataResponse = data;
            console.log(data);

        })
        .error(function (data, status, header, config) {
            $scope.ResponseDetails = "Data: " + data +
                "<hr />status: " + status +
                "<hr />headers: " + header +
                "<hr />config: " + config;
        });
		
//		$scope.userRoles = ["ROLE_USER"];
//		$scope.registeringUser = {
//				'username': username,
//				'email' : email,
//				'password' : password
//		};
//		$http({
//			method	: "POST",
//			url		: "api/user/register",
//			data	: {
//				'username'	: username,
//				'email'		: email,
//				'password'	: password,
//				'userRoles'	: 'ROLE_USER'
//			}
//				
//		}).then(
//				function successCallback(response){
//					console.log(response.data);
//					window.alert("Success!!")
//			
//				}, 
//				function errorCallback(response){
//					window.alert("Failed!!")
//				}
//		);
	};
	
});

app.controller('homeController', function($rootScope, $scope, $http, userService){
	$scope.test = 1;
	if ($rootScope.isLoggedIn){
		var user = userService.getPrincipalInfo($http);
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