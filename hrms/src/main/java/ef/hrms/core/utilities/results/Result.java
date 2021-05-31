package ef.hrms.core.utilities.results;

public abstract class Result {
	private int code;
	private String message;
	private boolean success;

	public Result(boolean success) {
		this.success = success;
	}

	public Result(boolean success, String message) {
		this(success);
		this.message = message;
	}

	public Result(boolean success, int code, String message) {
		this(success, message);
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
