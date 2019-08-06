//商家审核页面
app.service("SellerService",function($http){
	//分页查询
	this.pageList = function(num,size,entity){
		return $http.post("../SellerBankend/pageList.do?num="+num+"&size="+size,entity);
	}
	//根据主键id查找
	this.findOne = function(sellerId){
		return $http.get("../SellerBankend/findOne.do?sellerId="+sellerId);
	}
	//审核
	this.updataStart = function(sellerId,start){
		return $http.get("../SellerBankend/updataStart.do?sellerId="+sellerId+"&start="+start);
	}
})