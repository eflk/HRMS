package ef.hrms.business.abstracts;

import java.util.List;

import ef.hrms.core.utilities.results.DataResult;
import ef.hrms.core.utilities.results.Result;
import ef.hrms.entities.concrete.UserVerificationCode;

public interface UserVerificationCodeService {

	DataResult<List<UserVerificationCode>> getAll();
	DataResult<UserVerificationCode> getActiveCodeByUserId(int userId);
	
	DataResult<UserVerificationCode> createNew(int userId);
	Result cancelAllVerificationCodesOfUser(int userId);
}
