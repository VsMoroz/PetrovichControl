app.factory('UserService',function($rootScope, $http){
	return ({
		login				: login(),
		getPrincipalInfo	: getPrincipalInfo,
		register			: register
	});
	
	function login(){
		var request = $http({
			method: "POST",
			url	: "process/api/user/authenticate",
			data	: {
				username	: $rootScope.username,
				password	: $rootScope.pass
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
	
	function getPrincipalInfo(){
		var unautherized = {
				name 	: "Anonimous",
				email		: "",
				isLogged : false
		}
		var request = $http({
			method: "GET",
			url	: "api/user/auth-info"
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
	};
	
	function register($rootScope){
		var request = $http({
			method	: "POST",
			url		: "api/user/register",
			data	: {
				user		: $rootScope.registeringUser,
				userRoles	: $rootScope.userRoles
			}
				
		}).then(
				function successCallback(response){
					console.log($rootScope.registeringUser);
					window.alert("Success!!")
			
				}, 
				function errorCallback(response){
					window.alert("Failed!!")
				}
		);
	};
	
	function checkHeaderValue(header, value){
		for (var key in header){
			if (header[key] === value) return true;
		}
		return false;
	};
	 
});



