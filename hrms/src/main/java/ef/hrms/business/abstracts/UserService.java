package ef.hrms.business.abstracts;

import java.util.List;

import ef.hrms.core.utilities.results.DataResult;
import ef.hrms.core.utilities.results.Result;
import ef.hrms.entities.concrete.User;
import ef.hrms.entities.dtos.UserVerificationDto;
import ef.hrms.entities.dtos.UserResetPasswordDto;
import ef.hrms.entities.dtos.UserSignInDto;
import ef.hrms.entities.dtos.UserSignUpDto;

public interface UserService {
	DataResult<List<User>> getAll();
	DataResult<User> getById(int id);
	DataResult<User> getByUsername(String mail);
	
	DataResult<User> update(User user);
	Result delete(int userId);
	
	DataResult<?> signUp(UserSignUpDto user);
	DataResult<User> signIn(UserSignInDto user);
	Result verify(UserVerificationDto userConfirm);
	Result resetPassword(UserResetPasswordDto user);
	
}
