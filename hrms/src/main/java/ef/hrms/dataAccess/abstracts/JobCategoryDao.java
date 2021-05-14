package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.JobCategory;

public interface JobCategoryDao extends JpaRepository<JobCategory, Integer> {

}