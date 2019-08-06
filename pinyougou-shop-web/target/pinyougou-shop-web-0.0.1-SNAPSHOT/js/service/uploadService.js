//图片上传服务层
app.service("uploadService",function($http){
	this.uploadFile=function(){
		var formData=new FormData();
	    formData.append("file",file.files[0]);   
		return $http({
            method:'POST',
            url:"../upload.do",
            data: formData,
            //将表单转换成二进制流
            headers: {'Content-Type':undefined},
            transformRequest: angular.identity
        });		
	}	

})