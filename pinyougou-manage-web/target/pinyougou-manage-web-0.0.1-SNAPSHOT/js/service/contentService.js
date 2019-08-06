app.service("contentService",function($http){
	//广告管理
	this.findpage = function(page,size){
		
		return $http.get("../contnet/pageList.do?page="+page+"&size="+size);
	}
})