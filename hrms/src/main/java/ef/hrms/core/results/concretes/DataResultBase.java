package ef.hrms.core.results.concretes;

import ef.hrms.core.results.abstracts.DataResult;

public abstract class DataResultBase<T> extends ResultBase implements DataResult<T> {
	private T data;

	public DataResultBase(boolean success) {
		super(success);
	}
	
	public DataResultBase(boolean success, T data) {
		super(success);
		this.data = data;
	}

	public DataResultBase(boolean success, T data, String message) {
		super(success, message);
		this.data = data;
	}

	public DataResultBase(boolean success, T data, int code, String message) {
		super(success, code, message);
		this.data = data;
	}

	@Override
	public T getData() {
		return data;
	}

}
