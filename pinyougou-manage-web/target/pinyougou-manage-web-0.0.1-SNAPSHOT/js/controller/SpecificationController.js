app.controller('SpecificationController', function($scope, $controller, SpecificationService) {
	// 继承
	$controller('baseController', {
		$scope : $scope
	});
	// 批量删除品牌信息
	$scope.delByid = (function() {
		if ($scope.selectIDS != null && $scope.selectIDS.length == 0) {
			alert("必须选着一条数据");
			return;
		}
		if (confirm("确定要删除吗？")) {
			SpecificationService.delbyid($scope.selectIDS).success(function(response) {
				if (response.success) {
					alert(response.message);
					$scope.reloadList();
				} else {
					alert(response.message);
				}
			})
		}
	})
	
	// 获取总记录数
	$scope.searchEntity = {};
	$scope.findPage = function(page, rows) {
		SpecificationService.findpage(page, rows, $scope.searchEntity).success(
				function(response) {
					$scope.list = response.rows;
					$scope.paginationConf.totalItems = response.total;// 更新总记录数
				});
	}
	// 添加 或者修改
	// 添加或者更新品牌信息
	$scope.saveOrUpdate = function() {
		SpecificationService.saveOrupdate($scope.entity).success(function(response) {
			if (response.success) {
				$scope.reloadList();
			} else {
				alert(response.message);
			}
		});
	}
	//根据id回显

	$scope.findOne = function(id) {
		SpecificationService.findone(id).success(function(response) {
			$scope.entity = response;
		})
	}
	
	$scope.addTableRow=function(){	
		($scope.entity.specificationOption).push({})	
	}
	
	$scope.deleTableRow=function(index){			
		$scope.entity.specificationOption.splice(index,1);//删除			
	} 


});	