package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.JobPlatform;

public interface JobPlatformDao extends JpaRepository<JobPlatform, Integer> {

}