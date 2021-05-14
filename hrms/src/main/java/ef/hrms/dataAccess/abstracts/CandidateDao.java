package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.Candidate;

public interface CandidateDao extends JpaRepository<Candidate, Integer> {

}