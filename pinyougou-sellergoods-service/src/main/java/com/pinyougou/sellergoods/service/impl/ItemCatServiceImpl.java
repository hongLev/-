package com.pinyougou.sellergoods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.pinyougou.mapper.TbItemCatMapper;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.pojo.TbItemCatExample;
import com.pinyougou.pojo.TbItemCatExample.Criteria;
import com.pinyougou.sellergoods.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<TbItemCat> getItemCatByparenId(Long parenid) {
		TbItemCatExample example = new TbItemCatExample();

		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parenid);

		return this.itemCatMapper.selectByExample(example);
	}

	@Override
	public void addOrupdata(TbItemCat itemCat) {
		// TODO Auto-generated method stub
		if (itemCat != null && itemCat.getId() != null) {
			// 修改
			this.itemCatMapper.updateByPrimaryKey(itemCat);
		} else {
			// 增加
			this.itemCatMapper.insert(itemCat);
		}
	}

	@Override
	public TbItemCat findOne(Long id) {
		// TODO Auto-generated method stub
		return this.itemCatMapper.selectByPrimaryKey(id);
	}
	//删除
	@Override
	public void deleteItemCatById(Long[] ids) {
		if(ids != null && ids != null){
			for(Long id:ids){
				// TODO Auto-generated method stub
				// 1根据Id主键找到ItemCat对象
				TbItemCat itemCat = this.itemCatMapper.selectByPrimaryKey(id);
				TbItemCatExample example = new TbItemCatExample();

				Criteria criteria = example.createCriteria();
				criteria.andParentIdEqualTo(itemCat.getId());
				List<TbItemCat> list=this.itemCatMapper.selectByExample(example);
				//如果是顶级父类 依此删除 
				if (list.size() > 0 && list != null) {
					for (TbItemCat tbitemCat : list) {
						TbItemCatExample example1 = new TbItemCatExample();
						Criteria criteria1 = example1.createCriteria();
						criteria1.andParentIdEqualTo(tbitemCat.getId());
						List<TbItemCat> list1= this.itemCatMapper.selectByExample(example1);
						
						if (list1.size() > 0 && list1 !=null) {
							for (TbItemCat tbitemCat1 : list1) {
								this.itemCatMapper.deleteByPrimaryKey(tbitemCat1.getId());
							}
						}
						this.itemCatMapper.deleteByPrimaryKey(tbitemCat.getId());
					}
				}
				this.itemCatMapper.deleteByPrimaryKey(id);
			}
		}
	}

	@Override
	public List<TbItemCat> findAll() {
		// TODO Auto-generated method stub
		return this.itemCatMapper.selectAll();
	}

}
