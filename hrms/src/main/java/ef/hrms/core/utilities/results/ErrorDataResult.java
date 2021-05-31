package ef.hrms.core.utilities.results;

public class ErrorDataResult<T> extends DataResult<T> {

	public ErrorDataResult() {
		super(false);
	}

	public ErrorDataResult(T data) {
		super(false, data);
	}

	public ErrorDataResult(String message) {
		super(false, message);
	}

	public ErrorDataResult(T data, String message) {
		super(false, data, message);
	}

	public ErrorDataResult(T data, int code, String message) {
		super(false, data, code, message);
	}

}
