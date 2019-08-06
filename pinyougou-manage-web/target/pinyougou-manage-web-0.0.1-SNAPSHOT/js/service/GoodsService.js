app.service("goodsService",function($http){
	
	//分页显示
	this.findPage = function(page,size,entity){
		return $http.post("../goodss/pageList.do?page="+page+"&size="+size,entity);
	}
	
	//审核
	this.updataStatus = function(id,status){
		return $http.get("../goodss/updataStatus.do?id="+id+"&status="+status);
	}
	
	this.deleteGoods = function(id){
		return $http.get("../goodss/deleteGoods.do?id="+id)
	}
})