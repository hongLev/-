app.controller('typeTemplateController', function($scope, $controller,
		TypeTempalteService, brandService, SpecificationService) {
	// 继承
	$controller('baseController', {
		$scope : $scope
	});

	// 获取总记录数
	$scope.searchEntity = {};
	$scope.findPage = function(page, rows) {
		TypeTempalteService.findPage(page, rows, $scope.searchEntity).success(
				function(response) {
					$scope.list = response.rows;
					$scope.paginationConf.totalItems = response.total;// 更新总记录数
				});
	}
	// 加载所有规格列表信息
	$scope.dataBrandList = {
		data : []
	};
	$scope.selectBrandList = function() {
		brandService.selectSpecList().success(function(response) {
			$scope.dataBrandList = {
				data : response
			}
		});
	}
	// 加载所有规格列表信息
	$scope.dataSpecList = {
		data : []
	};
	$scope.selectSpecList = function() {
		SpecificationService.selectSpecifList().success(function(response) {
			$scope.dataSpecList = {
				data : response
			}
		});
	}
	//新增扩展属性
	$scope.addTableRow = function(){
		($scope.entity.customAttributeItems).push({});
	}
	//删除扩展属性
	$scope.deleteRows = function(index){
		$scope.entity.customAttributeItems.splice(index,1);
	}
	//新增
	$scope.saveOrUpdate = function(){
		TypeTempalteService.sevaOfupdata($scope.entity).success(function(response){
			if(response.success){
				$scope.reloadList();
			}else{
				alert(response.message);
				$scope.reloadList();
			}
		})
	}
	//查看修改
	$scope.findOne = function(id){
		TypeTempalteService.findOne(id).success(function(response){
			$scope.entity = response;
			$scope.entity.brandIds = JSON.parse($scope.entity.brandIds);//转换成品牌列表
			$scope.entity.specIds = JSON.parse($scope.entity.specIds);//转换成规格
			$scope.entity.customAttributeItems = JSON.parse($scope.entity.customAttributeItems);//转换成扩展属性
		})
	}
	//删除
	$scope.deleteByids = function(){
		if($scope.selectIDS.length<0 && $scope.selectIDS!=null){
			alert("必须选着一条数据");
			return ;
		}
		if(confirm("确认删除吗？")){
			TypeTempalteService.deleteById($scope.selectIDS).success(function(response){
				if(response.success){
					$scope.reloadList();
				}else{
					alert(response.message);
					$scope.reloadList();
				}
				
			})
		}
	}
})