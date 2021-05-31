package ef.hrms.core.utilities.results;

public class ErrorResult extends Result {

	public ErrorResult() {
		super(false);
	}

	public ErrorResult(String message) {
		super(false, message);
	}

	public ErrorResult(int code, String message) {
		super(false, code, message);
	}

}
