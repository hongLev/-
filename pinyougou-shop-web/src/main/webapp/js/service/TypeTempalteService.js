
//service TypeTempalte
app.service("TypeTempalteService",function($http){
	//分页查询条件 显示所有
	this.findPage = function(num,size,entity){
		
		return $http.post("../TypeTempalte/findPage.do?num="+num+"&size="+size,entity);
	}
	//新增模板或者修改模板
	this.sevaOfupdata = function(entity){
		
		return $http.post("../TypeTempalte/seavOfupdata.do",entity);
	}
	//查询id模板显示可修改
	this.findOne = function(id){
		return $http.post("../TypeTempalte/findId.do?id="+id);
	}
	//删除模板
	this.deleteById=function(id){
		return $http.get("../TypeTempalte/deleteType.do?id="+id);
	}
	
	//回显JSON specids
	this.findSpecList = function (id){
		return $http.get("../TypeTempalte/findSpecList.do?id="+id);
	}
})