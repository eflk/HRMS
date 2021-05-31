package ef.hrms.core.messaging.concrate;

import ef.hrms.core.messaging.abstracts.MessagingOperations;
import ef.hrms.core.utilities.results.ErrorResult;
import ef.hrms.core.utilities.results.Result;
import ef.hrms.core.utilities.results.SuccessResult;
import ef.hrms.entities.dtos.PushNotificationDto;
import ef.hrms.entities.dtos.SmsDto;

public class PushNotificationManager implements MessagingOperations {
	private PushNotificationDto notification;

	public PushNotificationManager(PushNotificationDto notification) {
		this.notification = notification;

	}

	@Override
	public Result send() {
		if (notification.getNotificationType() == 0)
			return new ErrorResult("Unknown Push Notification type");
		if (notification.getSubject() == null)
			return new ErrorResult("Push Notification subject cannot be empty");
		if (notification.getBody() == null)
			return new ErrorResult("Push Notification body cannot be empty");

		// Push Notification send service will be called here.
		// -----------------------------------------
		notification.setId(MessageCounter.getNewCounter());
		notification.setStatus(1);
		// ---------------------------------------------------------------------------------
		return new SuccessResult();
	}

	@Override
	public Result checkStatus(int id) {

		// Push Notification check service will be called here.
		// ----------------------------------------
		SmsDto result = new SmsDto();
		result.setId(id);
		result.setStatus(1);
		// ---------------------------------------------------------------------------------
		if (result.getStatus() == 1)
			return new SuccessResult("Sms has been sent successfully");

		return new ErrorResult();
	}

	@Override
	public boolean isSent() {
		return notification.getId() > 0;
	}

}