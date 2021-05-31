package ef.hrms.core.messaging.concrate;

import ef.hrms.core.messaging.abstracts.MessagingOperations;
import ef.hrms.core.utilities.results.ErrorResult;
import ef.hrms.core.utilities.results.Result;
import ef.hrms.core.utilities.results.SuccessResult;
import ef.hrms.entities.dtos.SmsDto;

public class SmsManager implements MessagingOperations {
	private SmsDto sms;

	public SmsManager(SmsDto sms) {
		this.sms = sms;
	}

	@Override
	public Result send() {
		if (sms.getSmsType() == 0)
			return new ErrorResult("Unknown Sms Type");
		if (sms.getFrom() == null)
			return new ErrorResult("From cannot be empty");
		if (sms.getBody() == null)
			return new ErrorResult("Sms body cannot be empty");

		// Sms send service will be called here.
		// -----------------------------------------
		this.sms.setId(MessageCounter.getNewCounter());
		this.sms.setStatus(1);
		// ---------------------------------------------------------------------------------
		return new SuccessResult();
	}

	@Override
	public Result checkStatus(int id) {

		// Sms check service will be called here.
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
		return sms.getId() > 0;
	}

}
