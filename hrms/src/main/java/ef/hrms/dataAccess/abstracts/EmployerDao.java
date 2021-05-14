package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer> {

}
