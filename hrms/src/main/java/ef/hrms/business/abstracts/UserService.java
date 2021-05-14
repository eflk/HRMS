package ef.hrms.business.abstracts;

import java.util.List;

import ef.hrms.core.results.abstracts.DataResult;
import ef.hrms.entities.concrete.User;
import ef.hrms.entities.concrete.UserDto;

public interface UserService {
	DataResult<List<User>> getAll();
	DataResult<User> add(UserDto user);
	DataResult<User> update(UserDto user);
	DataResult<User> delete(User user);

}
