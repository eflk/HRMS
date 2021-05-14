package ef.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ef.hrms.business.abstracts.UserService;
import ef.hrms.core.results.abstracts.DataResult;
import ef.hrms.core.results.concretes.ErrorDataResult;
import ef.hrms.core.results.concretes.SuccessDataResult;
import ef.hrms.dataAccess.abstracts.UserDao;
import ef.hrms.entities.concrete.User;
import ef.hrms.entities.concrete.UserDto;
import ef.hrms.core.base.Helper;
import ef.hrms.core.base.SaltedPassword;

@Service
public class UserManager implements UserService {
	private UserDao userDao;

	@Autowired // IoC containerdaki mapping'i otomatik yapıyor. field üzerine de koysak çalışır
				// fakat birden fazla servis inject edilme ihtimaline karşı constractor üzerine
				// koyuyoruz.
	public UserManager(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(userDao.findAll());

	}

	@Override
	public DataResult<User> add(UserDto user) {

		try {
			if (user.getEmail() == null || !Helper.isValidEmail(user.getEmail()))
				return new ErrorDataResult<User>(null, "Invalid email format");
			if (user.getPassword() == null || user.getPassword().length() < 6)
				return new ErrorDataResult<User>(null, "Password length must be at least \"6\"");
			if (userDao.findAll().stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(user.getEmail())))
				return new ErrorDataResult<User>(null, "The email address already exists");

			SaltedPassword saltedPassword = new SaltedPassword(user.getPassword());
			User newUser = new User(0, user.getEmail(), saltedPassword.getPasswordHashedEncoded(),
					saltedPassword.getPasswordSaltEncoded(), user.getCountryId(), user.getLanguageId());
			userDao.saveAndFlush(newUser);

			return new SuccessDataResult<User>(newUser);
		} catch (IllegalArgumentException e) {
			return new ErrorDataResult<User>(null, "Error in save operation.");
		}
	}

	@Override
	public DataResult<User> update(UserDto user) {

		try {
			if (user.getEmail() == null || !Helper.isValidEmail(user.getEmail()))
				return new ErrorDataResult<User>(null, "Invalid email format");

			User userToUpdate = userDao.findAll().stream()
					.filter(u -> u.getUsername().equalsIgnoreCase(user.getEmail())).findFirst().get();

			if (userToUpdate == null)
				return new ErrorDataResult<User>(null, "Incorrect email");

			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
				if (user.getPassword().length() < 6)
					return new ErrorDataResult<User>(null, "Password length must be at least \"6\"");

				SaltedPassword saltedPassword = new SaltedPassword(user.getPassword());
				userToUpdate.setPasswordHash(saltedPassword.getPasswordHashedEncoded());
				userToUpdate.setPasswordSalt(saltedPassword.getPasswordSaltEncoded());
			}
			if (user.getCountryId() != null && user.getCountryId() > 0)
				userToUpdate.setCountryId(user.getCountryId());

			if (user.getLanguageId() != null && user.getLanguageId() > 0)
				userToUpdate.setLanguageId(user.getLanguageId());

			userDao.saveAndFlush(userToUpdate);

			return new SuccessDataResult<User>(userToUpdate);
		} catch (IllegalArgumentException e) {
			return new ErrorDataResult<User>(null, "Error in save operation.");
		}
	}

	@Override
	public DataResult<User> delete(User user) {
		// TODO related permission will be checked before deleting.
		return null;
	}

}
