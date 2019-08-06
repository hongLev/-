app.service("GoodsService",function($http){
	//添加
	this.add = function(entity){
		return $http.post("../goods/add.do",entity);
	}
	//分页展示
	this.findPage = function(page,row,searchEntity){
		return $http.post("../goods/search.do?page="+page+"&size="+row,searchEntity);
	}
	//修改上架状态
	this.updataMarketable = function(id,marketable){
		return $http.get("../goods/shelves.do?id="+id+"&MarkeTable="+marketable);
	}
})