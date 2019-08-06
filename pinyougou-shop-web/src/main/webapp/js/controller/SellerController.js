app.controller("SellerController",function($scope,$controller,sellerService){
	//商家入驻申请
	$scope.sellerAdd = function(){
		sellerService.add($scope.entity).success(function(response){
			if(response.success){
				location.href="shoplogin.html";
			}else{
				alert(response.message);
			}
		})
	}
	
})