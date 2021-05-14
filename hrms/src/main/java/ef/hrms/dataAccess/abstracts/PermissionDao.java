package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.Permission;

public interface PermissionDao extends JpaRepository<Permission, Integer> {

}