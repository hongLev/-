app.controller("goodsController",function($scope,$controller,goodsService,ItemCatService){
	$controller('baseController', {
		$scope : $scope
	});// 继承
	
	// 获取总记录数
	$scope.searchEntity = {};
	$scope.findPage = function(page, rows) {
		goodsService.findPage(page, rows, $scope.searchEntity).success(
				function(response) {
					$scope.list = response.rows;
					$scope.paginationConf.totalItems = response.total;// 更新总记录数
				});
	}
	
	//审核状态
	$scope.updataStatus = function(status){
		goodsService.updataStatus($scope.selectIDS,status).success(function(response){
			if(response.success){
				//成功
				$scope.reloadList();
				$scope.selectIDS = [];
			}else{
				//失败
				alert(response.message);
			}
		})
	}
	//获取itemCat信息
	$scope.itemCatList = [];
	$scope.findItemList = function(){
		ItemCatService.findAll().success(function(response){
			for(var i=0;i<response.length;i++){
				$scope.itemCatList[response[i].id ]=response[i].name;		
			}
		})
	}
	//删除
	$scope.deleteGoods = function(){
		goodsService.deleteGoods($scope.selectIDS).success(function(response){
			if(response.success){
				//成功
				$scope.reloadList();
				$scope.selectIDS = [];
			}else{
				//失败
				alert(response.message);
			}
		})
	}
	
	
})