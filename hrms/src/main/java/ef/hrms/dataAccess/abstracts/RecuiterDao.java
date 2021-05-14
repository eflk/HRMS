package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.Recuiter;

public interface RecuiterDao extends JpaRepository<Recuiter, Integer> {

}