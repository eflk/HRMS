package ef.hrms.core.messaging.concrate;

import java.util.ArrayList;
import java.util.List;

import ef.hrms.core.messaging.abstracts.MessagingOperations;
import ef.hrms.core.messaging.abstracts.MessagingService;
import ef.hrms.core.utilities.results.ErrorResult;
import ef.hrms.core.utilities.results.Result;
import ef.hrms.core.utilities.results.SuccessResult;
import ef.hrms.entities.dtos.MailDto;
import ef.hrms.entities.dtos.PushNotificationDto;
import ef.hrms.entities.dtos.SmsDto;

public class MessagingManager implements MessagingService {
	private List<MessagingOperations> messages;

	public MessagingManager() {
		this.messages = new ArrayList<MessagingOperations>();
	}

	@Override
	public MessagingOperations createEmail(MailDto mail) {
		MailManager mailManager = new MailManager(mail);
		messages.add(mailManager);
		return mailManager;
	}

	@Override
	public MessagingOperations createSms(SmsDto sms) {
		SmsManager smsManager = new SmsManager(sms);
		messages.add(smsManager);
		return smsManager;
	}

	@Override
	public MessagingOperations createPushNotification(PushNotificationDto notification) {
		PushNotificationManager pushManager = new PushNotificationManager(notification);
		messages.add(pushManager);
		return pushManager;
	}

	public Result sendAllWaitingMessages() {
		if (!messages.stream().anyMatch(m -> !m.isSent()))
			return new ErrorResult("No message found to sent");

		Result sendResult = null;
		for (MessagingOperations messages : messages) {
			if (!messages.isSent()) {
				sendResult = messages.send();
				if (!sendResult.isSuccess())
					return sendResult;
			}
		}
		return new SuccessResult();
	}

}
