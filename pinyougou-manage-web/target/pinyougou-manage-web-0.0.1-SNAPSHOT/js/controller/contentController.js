app.controller("contentController", function($scope, $controller,
		contentService) {
	// 继承
	$controller('baseController', {
		$scope : $scope
	});

	$scope.findPage = function(page, rows) {
		contentService.findpage(page, rows).success(function(response) {
			$scope.list = response.rows;
			$scope.paginationConf.totalItems = response.total;// 更新总记录数
		});
	}

})