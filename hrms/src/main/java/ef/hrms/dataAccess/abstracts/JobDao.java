package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.Job;

public interface JobDao extends JpaRepository<Job, Integer> {

}