package ef.hrms.business.concretes;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ef.hrms.business.abstracts.UserService;
import ef.hrms.business.abstracts.UserVerificationCodeService;
import ef.hrms.core.utilities.Helper;
import ef.hrms.core.utilities.results.DataResult;
import ef.hrms.core.utilities.results.ErrorDataResult;
import ef.hrms.core.utilities.results.Result;
import ef.hrms.core.utilities.results.SuccessDataResult;
import ef.hrms.core.utilities.results.SuccessResult;
import ef.hrms.dataAccess.abstracts.UserVerificationCodeDao;
import ef.hrms.entities.concrete.UserVerificationCode;

@Service
public class UserVerificationCodeManager implements UserVerificationCodeService {
	private UserVerificationCodeDao userVerificationCodeDao;

	@Autowired
	public UserVerificationCodeManager(UserVerificationCodeDao userVerificationCodeDao) {
		super();
		this.userVerificationCodeDao = userVerificationCodeDao;
	}

	@Override
	public DataResult<List<UserVerificationCode>> getAll() {
		return new SuccessDataResult<List<UserVerificationCode>>(userVerificationCodeDao.findAll());
	}

	@Override
	public DataResult<UserVerificationCode> getActiveCodeByUserId(int userId) {
		UserVerificationCode userVerificationCode = userVerificationCodeDao
				.getByUserIdAndExpirationDateAfterAndActiveTrue(userId, Helper.getZDateTimeSystemDateUTC());
		if (userVerificationCode != null)
			return new SuccessDataResult<UserVerificationCode>(userVerificationCode);
		return new ErrorDataResult<UserVerificationCode>("No active verification code found");
	}

	@Override
	public DataResult<UserVerificationCode> createNew(int userId) {
		DataResult<UserVerificationCode> activeCode = this.getActiveCodeByUserId(userId);
		if (activeCode.isSuccess())
			return new ErrorDataResult<UserVerificationCode>(activeCode.getData(),
					"There is currently a validation code already active");

		String verificationCode = this.generateVerificationCode(userId);
		return new SuccessDataResult<UserVerificationCode>(
				userVerificationCodeDao.save(new UserVerificationCode(0, userId, verificationCode,
						Date.from(Helper.getZDateTimeSystemDateUTC().plusMinutes(20).toInstant()), true)));
	}

	@Override
	public Result cancelAllVerificationCodesOfUser(int userId) {
		DataResult<UserVerificationCode> verificationCode = getActiveCodeByUserId(userId);
		if (!verificationCode.isSuccess())
			return verificationCode;
		verificationCode.getData().setActive(false);
		userVerificationCodeDao.saveAndFlush(verificationCode.getData());
		return new SuccessResult();
	}

	private String generateVerificationCode(int userId) {
		// userService.getById(userId);
		return Helper.generateRandomPassword(10);
	}
}
