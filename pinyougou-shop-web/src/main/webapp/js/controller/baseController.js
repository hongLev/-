
//共通controller


app.controller('baseController', function($scope){	
	// 重新加载列表
	$scope.reloadList = function() { 
		// 切换页码
		$scope.findPage($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
	}
	
	$scope.paginationConf = {
			currentPage : 1,// 当前页
			totalItems : 10,// 总记录数
			itemsPerPage : 10,// 每页显示多少条
			perPageOptions : [ 10, 20, 30, 40, 50 ],
			onChange : function() {// 分页触发时间
				$scope.reloadList();// 重新加载
			}
		}
		
		
		
		//定义一个容器 存放选中的id值
		$scope.selectIDS = [];
		//选中id事件
		$scope.selectId = function($event, id) {
			//1:判断是否选中
			if ($event.target.checked) {
				$scope.selectIDS.push(id);
			} else {
				//2:根据id来查找下标
				var index = $scope.selectIDS.indexOf(id);
				//3:根据下标进行删除
				$scope.selectIDS.splice(index, 1);
			}
		}
		
		//提取json字符串数据的某个属性，返回拼接字符串逗号分隔.
		$scope.jsonToString = function(jsonString,key){
			var json=JSON.parse(jsonString);//将json字符串转换为json对象
			var value="";
			for(var i=0;i<json.length;i++){		
				if(i>0){
					value+=","
				}
				value+=json[i][key];			
			}
			return value;
		}
		
		$scope.searchObjectByKey = function(list,key,keyValue){
			for(var i=0;i<list.length;i++){
				if(list[i][key]==keyValue){
					return list[i];
				}			
			}		
			return null;
		}

})