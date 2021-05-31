package ef.hrms.core.messaging.abstracts;

import ef.hrms.entities.dtos.MailDto;
import ef.hrms.entities.dtos.PushNotificationDto;
import ef.hrms.entities.dtos.SmsDto;

public interface MessagingService {
	public MessagingOperations createEmail(MailDto mail);

	public MessagingOperations createSms(SmsDto sms);

	public MessagingOperations createPushNotification(PushNotificationDto notification);

}
