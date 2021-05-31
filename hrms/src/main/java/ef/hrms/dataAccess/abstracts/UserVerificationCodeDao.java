package ef.hrms.dataAccess.abstracts;

import java.time.ZonedDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.UserVerificationCode;

public interface UserVerificationCodeDao extends JpaRepository<UserVerificationCode, Integer> {
	UserVerificationCode getByUserIdAndExpirationDateAfterAndActiveTrue(int userId, ZonedDateTime now);
}
