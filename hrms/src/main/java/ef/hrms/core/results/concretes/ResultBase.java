package ef.hrms.core.results.concretes;

import ef.hrms.core.results.abstracts.Result;

public abstract class ResultBase implements Result {
	private int code;
	private String message;
	private boolean success;
	
	public ResultBase(boolean success) {
		this.success = success;
	}
	
	public ResultBase(boolean success, String message) {
		this(success);
		this.message = message;
	}
	
	public ResultBase(boolean success, int code, String message) {
		this(success, message);
		this.code = code;
	}

	@Override
	public boolean isSuccess() {
		return success;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	
	

}
