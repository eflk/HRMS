package ef.hrms.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ef.hrms.business.abstracts.CitizenshipVerificationService;
import ef.hrms.business.abstracts.PersonService;
import ef.hrms.business.abstracts.UserService;
import ef.hrms.business.abstracts.UserVerificationCodeService;
import ef.hrms.core.messaging.concrate.MessagingManager;
import ef.hrms.core.utilities.Helper;
import ef.hrms.core.utilities.SaltedPassword;
import ef.hrms.core.utilities.results.DataResult;
import ef.hrms.core.utilities.results.ErrorDataResult;
import ef.hrms.core.utilities.results.ErrorResult;
import ef.hrms.core.utilities.results.Result;
import ef.hrms.core.utilities.results.SuccessDataResult;
import ef.hrms.core.utilities.results.SuccessResult;
import ef.hrms.dataAccess.abstracts.UserDao;
import ef.hrms.entities.concrete.Person;
import ef.hrms.entities.concrete.User;
import ef.hrms.entities.concrete.UserVerificationCode;
import ef.hrms.entities.dtos.MailDto;
import ef.hrms.entities.dtos.SmsDto;
import ef.hrms.entities.dtos.UserVerificationDto;
import ef.hrms.entities.dtos.UserResetPasswordDto;
import ef.hrms.entities.dtos.UserSignInDto;
import ef.hrms.entities.dtos.UserSignUpDto;

@Service
public class UserManager implements UserService {
	private UserDao userDao;
	private UserVerificationCodeService userVerificationCodeService;
	private PersonService personService;

	// IoC containerdaki mapping'i otomatik yapıyor. field üzerine de koysak çalışır
	// fakat birden fazla servis inject edilme ihtimaline karşı constractor üzerine
	// koyuyoruz.
	@Autowired
	public UserManager(UserDao userDao, PersonService personService,
			UserVerificationCodeService userVerificationCodeService) {
		this.userDao = userDao;
		this.personService = personService;
		this.userVerificationCodeService = userVerificationCodeService;
	}

	@Override
	public DataResult<User> getById(int id) {
		User user = userDao.getOne(id);
		if (user != null)
			return new SuccessDataResult<User>(user);
		return new ErrorDataResult<User>();
	}

	@Override
	public DataResult<User> getByUsername(String mail) {
		if (mail == null || mail.isBlank())
			return new ErrorDataResult<User>();
		return new SuccessDataResult<User>(userDao.findByUsernameIgnoreCase(mail.trim()));
	}

	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(userDao.findAll());

	}

	@Override
	public DataResult<User> update(User user) {

		try {

			User userToUpdate = userDao.findByUsernameIgnoreCase(user.getUsername());

			if (userToUpdate == null)
				return new ErrorDataResult<User>(null, "Incorrect email");
			/*
			 * if (user.getPassword() != null && !user.getPassword().isEmpty()) { if
			 * (user.getPassword().length() < 6) return new ErrorDataResult<User>(null,
			 * "Password length must be at least \"6\"");
			 * 
			 * SaltedPassword saltedPassword = new SaltedPassword(user.getPassword());
			 * userToUpdate.setPasswordHash(saltedPassword.getPasswordHashedEncoded());
			 * userToUpdate.setPasswordSalt(saltedPassword.getPasswordSaltEncoded()); }
			 */
			if (user.getCountryId() > 0)
				userToUpdate.setCountryId(user.getCountryId());

			if (user.getLanguageId() > 0)
				userToUpdate.setLanguageId(user.getLanguageId());

			userDao.saveAndFlush(userToUpdate);

			return new SuccessDataResult<User>(userToUpdate);
		} catch (IllegalArgumentException e) {
			return new ErrorDataResult<User>(null, "Error in save operation.");
		}
	}

	@Override
	public Result delete(int userId) {
		// TODO related permission will be checked before deleting.
		DataResult<User> user = getById(userId);
		if (!user.isSuccess())
			return user;
		userDao.delete(user.getData());
		return new SuccessResult();
	}

	@Override
	public DataResult<?> signUp(UserSignUpDto user) {

		try {
			String err = null;

			err = checkIfEmailInUse(user.getUsername());
			if (err != null)
				return new ErrorDataResult<UserSignUpDto>(err);

			err = checkIfPasswordMatches(user.getPassword(), user.getPassword2());
			if (err != null)
				return new ErrorDataResult<UserSignUpDto>(err);

			if (err == null) {
				DataResult<Person> person = personService.add(user.getPerson());
				if (!person.isSuccess())
					return new ErrorDataResult<UserSignUpDto>(user, person.getMessage());
				user.setPerson(person.getData());
			}

			SaltedPassword saltedPassword = new SaltedPassword(user.getPassword());
			User newUser = new User(0, user.getUsername(), saltedPassword.getPasswordHashedEncoded(),
					saltedPassword.getPasswordSaltEncoded(), user.getCountryId(), user.getLanguageId(), 0);

			userDao.save(newUser);

			return new SuccessDataResult<User>(newUser);
		} catch (IllegalArgumentException e) {
			String personStatus = null;
			if (user.getPerson().getId() > 0)
				if (personService.delete(user.getPerson()).isSuccess())
					personStatus = "Person transaction rollback error !"; // TODO will be write in logs
			return new ErrorDataResult<User>(null, "Error in save operation. " + personStatus);
		}
	}

	@Override
	public DataResult<User> signIn(UserSignInDto user) {
		User loginUser = userDao.findByUsernameIgnoreCase(user.getUsername());
		try {
			if (loginUser != null)
				if (SaltedPassword.checkPassword(user.getPassword(), loginUser.getPasswordHash(),
						loginUser.getPasswordSalt())) {
					return new SuccessDataResult<User>();
				}
		} catch (Exception e) {
		}

		return new ErrorDataResult<User>("Invalid User");

	}

	@Override
	public Result verify(UserVerificationDto userConfirm) {
		// TODO User'a gönderilen verification kod bir tabloda tutulup expiration
		// datelerine göre gönderilen verification code ile aranmalı..
		// username + verificationCode + expirationDate.isBefore(LocalDate.now())
		DataResult<User> user = getByUsername(userConfirm.getUsername());
		if (!user.isSuccess())
			return user;

		DataResult<UserVerificationCode> userVerificationCode = userVerificationCodeService
				.getActiveCodeByUserId(user.getData().getId());

		if (userVerificationCode.isSuccess())
			if (userVerificationCode.getData().getVerificationCode().equals(userConfirm.getVerificationCode()))
				return new SuccessResult();

		return new ErrorResult();
	}

	@Override
	public Result resetPassword(UserResetPasswordDto user) {
		// TODO Burası reset password kodu gönderen ve bu kodu confirm eden 2 ayrı
		// fonksiyon şekilde düzeltilecek.

		User userToUpdate = userDao.findByUsernameIgnoreCaseAndStatus(user.getUsername(), 1);

		if (userToUpdate != null) {
			String resetMessage = "Click the link to reset your password. https://bla+bla.com/resetpassword/1234";

			MailDto mail = new MailDto();
			mail.setMailType(1);
			List<String> to = new ArrayList<String>();
			to.add("emrefelek+hrms@gmail.com");
			mail.setTo(to);
			mail.setSubject("HRMS Reset Password Mail");
			mail.setBody(resetMessage);

			SmsDto sms = new SmsDto();
			sms.setSmsType(1);
			sms.setFrom("HRMS");
			sms.setBody(resetMessage);

			MessagingManager m = new MessagingManager();
			m.createEmail(mail);
			m.createSms(sms);
			Result messageSendResult = m.sendAllWaitingMessages();
			if (messageSendResult.isSuccess())
				return new SuccessResult();
			return new ErrorResult(messageSendResult.getMessage());

		}
		return new ErrorResult("User not found");
	}

	private String checkIfPasswordMatches(String password, String password2) {
		return password.equals(password2) ? "" : "Passwords do not match";
	}

	private String checkIfEmailInUse(String email) {
		return (userDao.findByUsernameIgnoreCase(email) != null) ? "" : "The email address already exists";
	}
}
