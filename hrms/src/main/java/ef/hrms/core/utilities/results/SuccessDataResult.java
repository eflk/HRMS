package ef.hrms.core.utilities.results;

public class SuccessDataResult<T> extends DataResult<T> {

	public SuccessDataResult() {
		super(true);
	}

	public SuccessDataResult(T data) {
		super(true, data);
	}
	
	public SuccessDataResult(String message) {
		super(true, message);
	}

	public SuccessDataResult(T data, String message) {
		super(true, data, message);
	}

	public SuccessDataResult(T data, int code, String message) {
		super(true, data, code, message);
	}

}
