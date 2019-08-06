app.service("contentService",function($http){
	//根据广告类型id查询广告信息
	this.findByCategoryId=function(categoryId){
		return $http.get("/content/findByCategoryId.do?categoryId="+categoryId);
	}
})