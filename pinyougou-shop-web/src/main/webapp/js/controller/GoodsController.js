app.controller("GoodsController", function($scope, $controller, GoodsService,
		uploadService, ItemCatService, TypeTempalteService) {

	$controller('baseController', {
		$scope : $scope
	});

	// 添加
	$scope.add = function() {
		$scope.entity.goodsDesc.introduction = editor.html();
		editor.html('');// 清空富文本编辑器
		GoodsService.add($scope.entity).success(function(response) {
			if (response.success) {
				$scope.entity = {};
			} else {
				alert(response.message);
			}
		})
	}

	/**
	 * 上传图片
	 */
	$scope.uploadFile = function() {
		uploadService.uploadFile().success(function(response) {
			if (response.success) {// 如果上传成功，取出url
				$scope.image_entity.url = response.message;// 设置文件地址
			} else {
				alert(response.message);
			}
		}).error(function() {
			alert("上传发生错误");
		});
	};
	$scope.entity = {
		goods : {},
		goodsDesc : {
			itemImages : [],
			specificationItems : []
		},
		itemList : {}
		
	};// 定义页面实体结构//对象实例化
	// 保存图片后回显下·
	$scope.add_image_entity = function() {
		$scope.entity.goodsDesc.itemImages.push($scope.image_entity);
	}
	$scope.remove_image = function(index) {
		$scope.entity.goodsDesc.itemImages.splice(index, 1);
	}

	// 省三级联动 一级目录
	$scope.selectItemCat1List = function() {
		ItemCatService.getItemCatByparenId(0).success(function(response) {
			$scope.ItemCat1List = response;
		})
	}
	// 二级目录
	$scope.$watch('entity.goods.category1Id', function(newValue, oldValue) {
		// 根据选择的值，查询二级分类
		
		ItemCatService.getItemCatByparenId(newValue).success(
				function(response) {
					$scope.ItemCat2List = response;
				})
	});
	// 三级级目录
	$scope.$watch('entity.goods.category2Id', function(newValue, oldValue) {
		// 根据选择的值，查询三级级分类
		
		ItemCatService.getItemCatByparenId(newValue).success(
				function(response) {
					$scope.ItemCat3List = response;
				})
	});
	// 模板id
	$scope.$watch('entity.goods.category3Id', function(newValue, oldValue) {
		// 根据选择的值，模板id
		ItemCatService.findOne(newValue).success(function(response) {
			$scope.entity.goods.typeTemplateId = response.typeId;
		})
	});

	$scope.$watch('entity.goods.typeTemplateId', function(newValue, oldValue) {
		TypeTempalteService.findOne(newValue).success(
				function(response) {
					$scope.typeTemplateId = response;
					// 转换成js格式
					// 品牌
					$scope.typeTemplateId.brandIds = JSON
							.parse($scope.typeTemplateId.brandIds);
					// 扩展属性
					$scope.entity.goodsDesc.customAttributeItems = JSON
							.parse($scope.typeTemplateId.customAttributeItems);
				})
		//
		TypeTempalteService.findSpecList(newValue).success(function(response) {
			$scope.specList = response;
		})

	});

	$scope.updataSpecAttribute = function($event, name, value) {
		// $scope.entity.goodsDesc.specificationItems = [{}]
		var object = $scope.searchObjectByKey(
				$scope.entity.goodsDesc.specificationItems, "attributeName",
				name);
		// 判断是否存在
		if (object != null) {
			if ($event.target.checked) {
				// 勾选
				object.attributeValue.push(value);
			} else {
				// 取消勾选
				object.attributeValue.splice(object.attributeValue
						.indexOf(value), 1);

				// 如果选项都取消了，将此条记录移除
				if (object.attributeValue.length == 0) {
					$scope.entity.goodsDesc.specificationItems.splice(
							$scope.entity.goodsDesc.specificationItems
									.indexOf(object), 1);
				}
			}
		} else {
			// 没有值
			$scope.entity.goodsDesc.specificationItems.push({
				"attributeName" : name,
				"attributeValue" : [ value ]
			});
		}

	}
	//创建SKU列表
	$scope.createItemList = function (){
		//创建一个克隆对象
		$scope.entity.itemList=[{spec:{},price:0,num:99999,status:'0',isDefault:'0' } ];
		//获取对象信息进行迭代
		
		var items = $scope.entity.goodsDesc.specificationItems;
		
		for(var i =0 ; i<items.length ; i++){
			$scope.entity.itemList = addColumn($scope.entity.itemList,items[i].attributeName,items[i].attributeValue);
		}
	}
	
	//添加字段
	addColumn = function (list,coloumnName, coloumnValues){
		//创建一个新的集合来接收
		var newList = [];
		for( var i = 0 ; i < list.length ; i++){
			var oldRow = list[i];
			for(var j = 0;j<coloumnValues.length;j++){
				//深度克隆，
				var newRow = JSON.parse(JSON.stringify(oldRow));//转换成json格式
				newRow.spec[coloumnName] = coloumnValues[j];
				newList.push(newRow);
			}
			
		}
		return newList;
	}
	
	//Goods分页展示
	// 获取总记录数
	$scope.searchEntity = {};
	$scope.findPage = function(page, rows) {
		GoodsService.findPage(page, rows,$scope.searchEntity).success(
				function(response) {
					$scope.list = response.rows;
					$scope.paginationConf.totalItems = response.total;// 更新总记录数
				});
	}
	//商品审核状态可视化
	$scope.status=['未审核','申请中','审核通过','审核驳回'];
	
	//获取itemCat信息
	$scope.itemCatList = [];
	$scope.findItemList = function(){
		ItemCatService.findAll().success(function(response){
			for(var i=0;i<response.length;i++){
				$scope.itemCatList[response[i].id ]=response[i].name;		
			}
		})
	}
	//商品上架或者下架
	$scope.updataMarketable = function(marketable){
		GoodsService.updataMarketable($scope.selectIDS,marketable).success(function(response){
			if(response.success){
				$scope.reloadList()
				$scope.selectIDS=[];
			}else{
				alert(response.message);
			}
		})
	}
	
	
})