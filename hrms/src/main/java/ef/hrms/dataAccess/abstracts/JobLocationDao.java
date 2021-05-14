package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.JobLocation;

public interface JobLocationDao extends JpaRepository<JobLocation, Integer> {

}