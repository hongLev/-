app.controller("indexController",function($scope,$controller,loginService){
	
	$scope.showName=function(){
		loginService.showName().success(function(response){
			$scope.loginName=response.loginName;
			
		})
	}
})