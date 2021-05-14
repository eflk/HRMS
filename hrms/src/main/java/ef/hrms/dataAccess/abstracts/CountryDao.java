package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.Country;

public interface CountryDao extends JpaRepository<Country, Integer> {

}
