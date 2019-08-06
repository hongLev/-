
//商家前端控制层
app.controller("SellerController",function($scope,$controller,SellerService){
	//继承BaseController共通方法
	$controller('baseController', {
		$scope : $scope
	});
	//分页
	$scope.searchEntity = {};
	$scope.findPage = function(page, rows) {
		SellerService.pageList(page, rows, $scope.searchEntity).success(function(response) {
					$scope.list = response.rows;
					$scope.paginationConf.totalItems = response.total;// 更新总记录数
		});
	}
	//详情
	$scope.findOne = function(sellerId){
		SellerService.findOne(sellerId).success(function(response){
			$scope.entity = response;
		})
	}
	//审核
	$scope.updateStatus = function (sellerId,start){
		SellerService.updataStart(sellerId,start).success(function(response){
			if(response.success){
				$scope.reloadList();
			}else{
				alert(response.message)
				$scope.reloadList();
			}
		})
	}
	
})