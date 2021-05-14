package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.JobPosition;

public interface JobPositionDao extends JpaRepository<JobPosition, Integer> {

}