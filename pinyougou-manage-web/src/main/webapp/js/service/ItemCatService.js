
app.service("ItemCatService",function($http){
	//根据父类id获取商品分类信息
	this.getItemCatByparenId = function(parenId){
		return $http.get("../ItemCat/select.do?id="+parenId);
	}
	
	//新增或者修改
	
	this.addOrupdata = function(entity){
		return $http.post("../ItemCat/addOrupdata.do",entity);
	}
	
	//根据主键id查找
	this.findOne = function(id){
		return $http.get("../ItemCat/findOne.do?id="+id);
	}
	
	//根据主键id删除
	this.delectByItemCatId = function(id){
		return $http.get("../ItemCat/deleteById.do?id="+id)
	}
	
	this.findAll = function(){
		return $http.get("../ItemCat/selectAll.do");
	}
})