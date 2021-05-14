package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.Firm;

public interface FirmDao extends JpaRepository<Firm, Integer> {

}
