package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.RolePermission;

public interface RolePermissionDao extends JpaRepository<RolePermission, Integer> {

}