package ef.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ef.hrms.business.abstracts.CitizenshipVerificationService;
import ef.hrms.business.abstracts.PersonService;
import ef.hrms.core.utilities.results.DataResult;
import ef.hrms.core.utilities.results.ErrorDataResult;
import ef.hrms.core.utilities.results.Result;
import ef.hrms.core.utilities.results.SuccessDataResult;
import ef.hrms.dataAccess.abstracts.PersonDao;
import ef.hrms.entities.concrete.Person;

@Service
public class PersonManager implements PersonService {
	private PersonDao personDao;
	private CitizenshipVerificationService citizenshipVerificationService;

	@Autowired
	public PersonManager(PersonDao personDao,
			CitizenshipVerificationService citizenshipVerificationService) {
		super();
		this.personDao = personDao;
		this.citizenshipVerificationService = citizenshipVerificationService;
	}

	@Override
	public DataResult<Person> add(Person person) {
		String err = null;

		err = checkIfEmailInUse(person.getEmail());
		if (err != null)
			return new ErrorDataResult<Person>(err);

		err = checkIfNationalityIdValid(person);
		if (err != null)
			return new ErrorDataResult<Person>(err);
		
				
		Person savedPerson = personDao.save(person);
		return (savedPerson != null) ? new SuccessDataResult<Person>(savedPerson)
				: new ErrorDataResult<Person>(savedPerson);
	}

	@Override
	public Result update(Person person) {
		Person savedPerson = personDao.save(person);
		return (savedPerson != null) ? new SuccessDataResult<Person>(savedPerson)
				: new ErrorDataResult<Person>(savedPerson);
	}

	@Override
	public Result delete(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<List<Person>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<Person> getById(int personId) {
		Person person = personDao.getOne(personId);
		if (person != null)
			return new SuccessDataResult<Person>(person);
		return new ErrorDataResult<Person>();
	}

	@Override
	public DataResult<Person> getByEmail(String email) {
		Person person = personDao.getByEmailIgnoreCase(email);
		if (person != null)
			return new SuccessDataResult<Person>(person);
		return new ErrorDataResult<Person>();
	}

	@Override
	public DataResult<Person> getByNationalId(String nationalId) {
		Person person = personDao.getByNationalId(nationalId);
		if (person != null)
			return new SuccessDataResult<Person>(person);
		return new ErrorDataResult<Person>();
	}
	
	private String checkIfEmailInUse(String email) {
		return (personDao.getByEmailIgnoreCase(email) != null) ? "" : "The email address already exists";
	}
	
	public String checkIfNationalityIdValid(Person person) {
		return (citizenshipVerificationService.validateCitizenship(person)) ? "" : "Not a valid citizenship";
	}

}
