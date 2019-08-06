package com.pinyougou.sellergoods.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.mapper.TbGoodsDescMapper;
import com.pinyougou.mapper.TbGoodsMapper;
import com.pinyougou.mapper.TbItemCatMapper;
import com.pinyougou.mapper.TbItemMapper;
import com.pinyougou.mapper.TbSellerMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojo.TbGoodsExample;
import com.pinyougou.pojo.TbGoodsExample.Criteria;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.pojo.TbItemCatExample;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.pojogroup.Goods;
import com.pinyougou.query.GoodsQuery;
import com.pinyougou.sellergoods.service.GoodsService;

import entity.PageResult;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private TbGoodsMapper goodsMapper;
	@Autowired
	private TbGoodsDescMapper goodsDescMapper;
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbBrandMapper brandMapper;
	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Autowired
	private TbSellerMapper sellerMapper;

	public void add(Goods goods) {
		// TODO Auto-generated method stub
		// 封装商品信息
		goods.getGoods().setAuditStatus("0");// 申请待审核状态
		goods.getGoods().setIsMarketable("0");// 表示待审核 不上架
		// 添加商品信息
		this.goodsMapper.insert(goods.getGoods());
		// 给商品描述设置主键Id 添加商品信息
		goods.getGoodsDesc().setGoodsId(goods.getGoods().getId());
		this.goodsDescMapper.insert(goods.getGoodsDesc());
		if ("1".equals(goods.getGoods().getIsEnableSpec())) {
			for (TbItem item : goods.getItemList()) {
				// 标题
				String title = goods.getGoods().getGoodsName();
				Map<String, Object> specMap = JSON.parseObject(item.getSpec());
				for (String key : specMap.keySet()) {
					title += " " + specMap.get(key);
				}
				item.setTitle(title);
				setItemValus(goods, item);
				itemMapper.insert(item);
			}
		} else {
			TbItem item = new TbItem();
			item.setTitle(goods.getGoods().getGoodsName());// 商品KPU+规格描述串作为SKU名称
			item.setPrice(goods.getGoods().getPrice());// 价格
			item.setStatus("1");// 状态
			item.setIsDefault("1");// 是否默认
			item.setNum(99999);// 库存数量
			item.setSpec("{}");
			setItemValus(goods, item);
			itemMapper.insert(item);
		}
	}

	private void setItemValus(Goods goods, TbItem item) {
		item.setGoodsId(goods.getGoods().getId());// 商品SPU编号
		item.setSellerId(goods.getGoods().getSellerId());// 商家编号
		item.setCategoryid(goods.getGoods().getCategory3Id());// 商品分类编号（3级）
		item.setCreateTime(new Date());// 创建日期
		item.setUpdateTime(new Date());// 修改日期
		// 品牌名称
		TbBrand brand = brandMapper.selectByPrimaryKey(goods.getGoods().getBrandId());
		item.setBrand(brand.getName());
		// 分类名称
		TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(goods.getGoods().getCategory3Id());
		item.setCategory(itemCat.getName());

		// 商家名称
		TbSeller seller = sellerMapper.selectByPrimaryKey(goods.getGoods().getSellerId());
		item.setSeller(seller.getNickName());

		// 图片地址（取spu的第一个图片）
		List<Map> imageList = JSON.parseArray(goods.getGoodsDesc().getItemImages(), Map.class);
		if (imageList.size() > 0) {
			item.setImage((String) imageList.get(0).get("url"));
		}

	}

	@Override
	public PageResult findPage(GoodsQuery query, int page, int size) {
		PageHelper.startPage(page, size);
		TbGoodsExample example = new TbGoodsExample();
		Criteria criteria = example.createCriteria();
		if(query.getSellerId()!=null && query.getSellerId().length()>0){
			//criteria.andSellerIdLike("%"+goods.getSellerId()+"%");
			criteria.andSellerIdEqualTo(query.getSellerId());
		}
		if(query.getGoodName()!=null && query.getGoodName()!=""){
			criteria.andGoodsNameLike("%"+query.getGoodName()+"%");
		}
		if(query.getStatus()!=null && query.getStatus()!=""){
			criteria.andAuditStatusEqualTo(query.getStatus());
		}
		if(query.getMarketable()!=null && query.getMarketable()!=""){
			criteria.andIsMarketableEqualTo(query.getMarketable());
		}
		criteria.andIsDeleteIsNull();//非删除状态
		Page<TbGoods> page1= (Page<TbGoods>)this.goodsMapper.selectByExample(example);
		
		return new PageResult(page1.getTotal(), page1.getResult());
	}
	//上架下架
	@Override
	public void shelves(Long[] id, String marketable) {
		// TODO Auto-generated method stub
		for(Long ids:id){
			TbGoods goods = this.goodsMapper.selectByPrimaryKey(ids);
			//修改状态
			goods.setIsMarketable(marketable);
			this.goodsMapper.updateByPrimaryKey(goods);
		}
	}
	//审核
	@Override
	public void auditStatus(Long[] id, String status) {
		// TODO Auto-generated method stub
		for(Long ids:id){
			TbGoods goods = this.goodsMapper.selectByPrimaryKey(ids);
			//修改状态
			goods.setAuditStatus(status);
			this.goodsMapper.updateByPrimaryKey(goods);
		}
	}
	//删除
	@Override
	public void deleteGoods(Long[] id) {
		// TODO Auto-generated method stub
		for(Long ids:id){
			TbGoods goods = this.goodsMapper.selectByPrimaryKey(ids);
			//修改状态
			goods.setIsDelete("1");
			this.goodsMapper.updateByPrimaryKey(goods);
		}
	}

}
