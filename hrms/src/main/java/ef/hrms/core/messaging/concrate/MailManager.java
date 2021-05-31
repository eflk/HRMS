package ef.hrms.core.messaging.concrate;

import ef.hrms.core.messaging.abstracts.MessagingOperations;
import ef.hrms.core.utilities.results.ErrorResult;
import ef.hrms.core.utilities.results.Result;
import ef.hrms.core.utilities.results.SuccessResult;
import ef.hrms.entities.dtos.MailDto;

public class MailManager implements MessagingOperations {
	private MailDto mail;

	public MailManager(MailDto mail) {
		this.mail = mail;
	}

	@Override
	public Result send() {
		if (mail.getMailType() == 0)
			return new ErrorResult("Unknown Mail Type");
		if (mail.getTo() == null)
			return new ErrorResult("TO must be entered");
		if (mail.getSenderMailAddress() == null)
			return new ErrorResult("Sender Mail Address must be entered");

		// Mail send service will be called here.
		// -----------------------------------------
		this.mail.setId(MessageCounter.getNewCounter());
		this.mail.setStatus(1);
		// ---------------------------------------------------------------------------------
		return new SuccessResult();
	}

	@Override
	public Result checkStatus(int id) {

		// Mail check service will be called here.
		// ----------------------------------------
		MailDto result = new MailDto();
		result.setId(id);
		result.setStatus(1);
		// ---------------------------------------------------------------------------------
		if (result.getStatus() == 1)
			return new SuccessResult("Mail has been sent successfully");
		
		return new ErrorResult();
	}

	@Override
	public boolean isSent() {
		return mail.getId() > 0;
	}

}
