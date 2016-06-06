app.service('userRegistartion', function(){
//	return({
//		register: register
//	});
//	
//	function register($rootScope, $http){
//		var request = $http({
//			method	: "POST",
//			url		: "api/user/register",
//			data	: {
//				user		: $rootScope.registeringUser,
//				userRoles	: $rootScope.userRoles
//			}
//				
//		}).then(
//				function successCallback(response){
//					console.log($rootScope.registeringUser);
//					window.alert("Success!!")
//			
//				}, 
//				function errorCallback(response){
//					window.alert("Failed!!")
//				}
//		);
//	};
});

app.service('userService', function($rootScope, $http){
	return ({
		getPrincipalInfo	: getPrincipalInfo,
		register			: register
	});
	
	function getPrincipalInfo($http){
		var unautherized = {
				username 	: "unauthorized",
				email		: "unavailable"
		}
		var request = $http({
			method: "GET",
			url	: "api/user/auth-info"
		})
			.then(
					function successCallback(response){
			
						var header = response.headers();
						if (("Content-Type" in header) && (header.content-type === "application/json;charset=UTF-8")){
							return response.data;
						} else return unautherized;
				
					}, 
					function errorCallback(response){
						return unautherized;
		
					}
			);
	};
	
	function register($rootScope, $http){
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
	 
});

