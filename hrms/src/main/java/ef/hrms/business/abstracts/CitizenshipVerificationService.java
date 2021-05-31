package ef.hrms.business.abstracts;

import ef.hrms.entities.concrete.Person;

public interface CitizenshipVerificationService {
	public boolean validateCitizenship(Person person);
}
