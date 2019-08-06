package com.pinyougou.sellergoods.service;

import java.util.List;

import com.pinyougou.pojo.TbItemCat;
/**
 * 业务逻辑接口层
 * @author 89568
 *
 */
public interface ItemCatService {
	/**
	 * 分页
	 * @param parenid
	 * @return
	 */
	public List<TbItemCat> getItemCatByparenId(Long parenid);
	/**
	 * 新增和修改
	 * @param itemCat
	 */
	public void addOrupdata(TbItemCat itemCat);
	/**
	 * 根据主键id查找
	 * @param id
	 * @return
	 */
	public TbItemCat findOne(Long id);
	/**
	 * 删除
	 * @param id
	 */
	public void deleteItemCatById(Long[] id);
	/**
	 * 查找全部
	 */
	public List<TbItemCat> findAll();
}
