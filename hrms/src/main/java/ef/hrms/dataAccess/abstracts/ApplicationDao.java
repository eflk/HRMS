package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.Application;

public interface ApplicationDao extends JpaRepository<Application, Integer> {

}