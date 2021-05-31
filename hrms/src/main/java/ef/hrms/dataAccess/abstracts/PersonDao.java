package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.Person;

public interface PersonDao extends JpaRepository<Person, Integer> {

	Person getByEmailIgnoreCase(String email);

	Person getByNationalId(String nationalId);
}
