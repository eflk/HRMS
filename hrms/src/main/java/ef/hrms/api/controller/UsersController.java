package ef.hrms.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ef.hrms.business.abstracts.UserService;
import ef.hrms.core.utilities.results.DataResult;
import ef.hrms.core.utilities.results.ErrorDataResult;
import ef.hrms.core.utilities.results.Result;
import ef.hrms.entities.concrete.User;
import ef.hrms.entities.dtos.UserVerificationDto;
import ef.hrms.entities.dtos.UserResetPasswordDto;
import ef.hrms.entities.dtos.UserSignInDto;
import ef.hrms.entities.dtos.UserSignUpDto;

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
	public ResponseEntity<?> getAll() {
		return responseBuilder(userService.getAll());
	}

	@GetMapping("/getbymail")
	public ResponseEntity<?> getByMail(String mail) {
		return responseBuilder(userService.getByUsername(mail));
	}

	@GetMapping("/getbyuserid")
	public ResponseEntity<?> getByUserId(int userId) {
		return responseBuilder(userService.getById(userId));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody User user) {
		return responseBuilder(userService.update(user));
	}

	@PostMapping("/delete")
	public ResponseEntity<?> delete(int userId) {
		return responseBuilder(userService.delete(userId));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@Valid @RequestBody UserSignUpDto user) {
		return responseBuilder(userService.signUp(user));
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@Valid @RequestBody UserSignInDto user) {
		return responseBuilder(userService.signIn(user));
	}

	@PostMapping("/verify")
	public ResponseEntity<?> verify(@Valid @RequestBody UserVerificationDto user) {
		return responseBuilder(userService.verify(user));
	}

	@PostMapping("/resetpassword")
	public ResponseEntity<?> resetPassword(@Valid @RequestBody UserResetPasswordDto user) {
		return responseBuilder(userService.resetPassword(user));
	}
	
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ErrorDataResult<Object>(validationErrors);
	}
	
	public ResponseEntity<?> responseBuilder(Object result){
		return ResponseEntity.status(((Result)result).isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(result);
	}

}
