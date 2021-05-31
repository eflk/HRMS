package ef.hrms.business.abstracts;

import java.util.List;

import ef.hrms.core.utilities.results.DataResult;
import ef.hrms.core.utilities.results.Result;
import ef.hrms.entities.concrete.Person;

public interface PersonService {
	public DataResult<Person> add(Person person);

	public Result update(Person person);

	public Result delete(Person person);
	
	public DataResult<List<Person>> getAll();
	
	public DataResult<Person> getById(int personId);
	
	public DataResult<Person> getByEmail(String email);
	
	public DataResult<Person> getByNationalId(String nationalId);
}
