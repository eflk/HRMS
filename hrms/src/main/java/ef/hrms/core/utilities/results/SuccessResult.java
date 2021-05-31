package ef.hrms.core.utilities.results;

public class SuccessResult extends Result {

	public SuccessResult() {
		super(true);
	}

	public SuccessResult(String message) {
		super(true, message);
	}

	public SuccessResult(int code, String message) {
		super(true, code, message);
	}

}
