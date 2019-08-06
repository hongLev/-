app.service("SpecificationService",function($http){
	//分页的service
	this.findpage=function(page,rows,searchEntity){
		return $http.post('../Specification/findPage.do?page=' + page + '&rows=' + rows,
				searchEntity);
	}
	//根据id查找
	this.findone = function(id){
		return $http.get('../Specification/findOne.do?id='+id);
	}
	//修改或者新增
	this.saveOrupdate = function(entity){
		return $http.post('../Specification/saveOrupdate.do',entity);
	}
	
	//删除id
	this.delbyid=function(selectIDS){
		return $http.get("../Specification/delbyid.do?id=" + selectIDS);
	}
	
	//查询所有  返回json
	
	this.selectSpecifList=function(){
		return $http.get("../Specification/selectSpecificationList.do");
	}
});