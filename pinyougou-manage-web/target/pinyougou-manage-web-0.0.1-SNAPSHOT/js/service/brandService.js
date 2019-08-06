app.service("brandService",function($http){
	//分页的service
	this.findpage=function(page,rows,searchEntity){
		return $http.post('../brand/findPage.do?page=' + page + '&rows=' + rows,
				searchEntity);
	}
	
	//添加或者修改
	this.saveOrupdate=function(entity){
		return $http.post("../brand/saveOrUpdate.do", entity);
	}
	//根据id回显
	this.findone=function(id){
		return $http.get("../brand/findId.do?id=" + id);
	}
	//删除id
	this.delbyid=function(selectIDS){
		return $http.get("../brand/bathbrand.do?ids=" + selectIDS);
	}
	//查询所有
	this.selectSpecList = function(){
		return $http.get("../brand/selectOptionList.do");
	}
})