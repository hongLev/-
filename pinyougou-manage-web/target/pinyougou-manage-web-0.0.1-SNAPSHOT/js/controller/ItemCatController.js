
app.controller("ItemCatController",function($scope,$controller,ItemCatService){
	
	// 继承
	$controller('baseController', {
		$scope : $scope
	});
	//显示
	$scope.getItemCatByParenId = function(parenId){
		$scope.parenID_1 = parenId;
		ItemCatService.getItemCatByparenId(parenId).success(function(response){
			$scope.list = response;
		})
	}
	
	$scope.grade = 1;//设置级别
	
	//查看下级 自动增加赋值
	$scope.setGrade = function(value){
		$scope.grade = value;
	}
	//面包屑显示
	$scope.selectList = function(p_entity){	
		if($scope.grade==1){
			$scope.entity_1=null;
			$scope.entity_2=null;
		}
		if($scope.grade==2){
			$scope.entity_1=p_entity;
			$scope.entity_2=null;
		}
		
		if($scope.grade==3){
			$scope.entity_2=p_entity;
		}
		
		this.getItemCatByParenId(p_entity.id);
	}
	//新增or修改
	$scope.seavOrupdata = function(){
		$scope.entity.parentId = $scope.parenID_1;
		ItemCatService.addOrupdata($scope.entity).success(function(response){
			if(response.success){
				$scope.getItemCatByParenId($scope.entity.parentId);
			}else{
				alert(response.message)
			}
		})
	}
	//根据id查找
	$scope.findOne = function(id){
		ItemCatService.findOne(id).success(function(response){
			$scope.entity = response;
		})
	}
	// 批量删除品牌信息
	$scope.delByid = (function() {
		if ($scope.selectIDS != null && $scope.selectIDS.length == 0) {
			alert("必须选着一条数据");
			return;
		}
		if (confirm("确定要删除吗？")) {
			ItemCatService.delectByItemCatId($scope.selectIDS).success(function(response) {
				if (response.success) {
					alert(response.message);
					$scope.getItemCatByParenId($scope.parenID_1);
				} else {
					alert(response.message);
				}
			})
		}
	})
	
})