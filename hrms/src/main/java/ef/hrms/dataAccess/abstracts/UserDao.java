package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
