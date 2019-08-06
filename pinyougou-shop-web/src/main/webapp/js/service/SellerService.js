//商家Service
app.service("sellerService",function($http){
	this.add = function(entity){
		return $http.post("../seller/add.do",entity);
	}
})