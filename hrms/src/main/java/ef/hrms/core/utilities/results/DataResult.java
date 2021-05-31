package ef.hrms.core.utilities.results;


public abstract class DataResult<T> extends Result {
	private T data;

	public DataResult(boolean success) {
		super(success);
	}
	
	public DataResult(boolean success, T data) {
		super(success);
		this.data = data;
	}

	public DataResult(boolean success, String message) {
		super(success, message);
	}

	public DataResult(boolean success, T data, String message) {
		super(success, message);
		this.data = data;
	}

	public DataResult(boolean success, T data, int code, String message) {
		super(success, code, message);
		this.data = data;
	}

	public T getData() {
		return data;
	}

}
