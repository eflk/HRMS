package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {

}