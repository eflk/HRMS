package ef.hrms.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ef.hrms.business.abstracts.UserService;
import ef.hrms.core.results.abstracts.DataResult;
import ef.hrms.entities.concrete.User;
import ef.hrms.entities.concrete.UserDto;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	private UserService userService;
	// TODO UserService'i UserManager gibi başka bir class'a da implement edilirse
	// patlar.

	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/getall")
	public DataResult<List<User>> getAll() {
		// return userService.getAll();
		// StopWatch watch = new StopWatch();
		// watch.start();
		DataResult<List<User>> result = userService.getAll();
		// watch.stop();
		// System.out.println(watch.getTotalTimeMillis());
		return result;
	}

	@PostMapping("/add")
	public DataResult<User> add(UserDto user) {
		DataResult<User> result = userService.add(user);
		return result;
	}

	@PostMapping("/update")
	public DataResult<User> update(UserDto user) {
		DataResult<User> result = userService.update(user);
		return result;
	}

}
