package entity;

public class JSONResult {
	//成功还是失败
	private boolean success;
	//提示信息
	private String message;
	
	public JSONResult(){
		
	}
	public JSONResult(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
