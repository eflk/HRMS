package ef.hrms.core.messaging.concrate;

public class MessageCounter {
	private static int TemporaryMessageCounter = 0;

	public static int getNewCounter() {
		return ++TemporaryMessageCounter;
	}
}
