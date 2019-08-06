package com.pinyougou.pojogroup;

import java.io.Serializable;
import java.util.List;

import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;

public class Specification implements Serializable{

	private TbSpecification specification;//实体类
	
	private List<TbSpecificationOption> specificationOption;

	public TbSpecification getSpecification() {
		return specification;
	}

	public void setSpecification(TbSpecification specification) {
		this.specification = specification;
	}

	public List<TbSpecificationOption> getSpecificationOption() {
		return specificationOption;
	}

	public void setSpecificationOption(List<TbSpecificationOption> specificationOption) {
		this.specificationOption = specificationOption;
	}
	
	
}
