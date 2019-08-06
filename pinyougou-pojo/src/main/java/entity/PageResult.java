package entity;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * @author 89568
 *
 */
public class PageResult implements Serializable{
	//总记录数
	private long total;
	//没页显示的记录数
	private List rows;
	
	public PageResult(){}
	
	public PageResult(long total,List rows){
		this.total=total;
		this.rows=rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
	
	
	
}
