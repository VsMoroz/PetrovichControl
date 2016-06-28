app.controller('headerController', function($rootScope, $scope) {
	$scope.username = $rootScope.username;

});

app.controller('loginController', function($rootScope, $scope, $http){
	$scope.login = function(){
		$http({
			method: "POST",
			url	: "process/api/user/authenticate",
			data	: {
				username	: $scope.username,
				password	: $scope.pass
			}
		})
			.then(
					function successCallback(response){
						console.log("user has been logged!")
						console.log(response.data);
			
						var header = response.headers();
						if (checkHeaderValue(header, "application/json;charset=UTF-8")){
							var user = {
									name: response.data.username,
									email: response.data.email,
									isLogged: true
								};
							return user;
						} else return unautherized;
				
					}, 
					function errorCallback(response){
						return unautherized;
		
					}
			);
	}
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
		
	};
	
});

app.controller('homeController', function($rootScope, $scope, $http, UserService){
	/*
		var user = UserService.getPrincipalInfo();
		if (typeof user == "object" && user.hasOwnProperty("username") && user.username != "unauthorized"){
			$rootScope.user.isLogged = true;
			$rootScope.user.name = user.name;
			$rootScope.user.email = user.email;
		}
	*/
	
});
app.controller('testController', function($scope){
	$scope.test = 1;
	$scope.funk = function(){
		$scope.test += 1 ;
	};
})