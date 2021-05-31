package ef.hrms.core.messaging.abstracts;

import ef.hrms.core.utilities.results.Result;

public interface MessagingOperations {
	public Result send();

	public Result checkStatus(int id);
	
	public boolean isSent();

}
