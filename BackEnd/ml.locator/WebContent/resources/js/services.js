app.service("principalInfo", function($http){
	return ({
		getPrincipalInfo	:getPrincipalInfo
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
	 
});