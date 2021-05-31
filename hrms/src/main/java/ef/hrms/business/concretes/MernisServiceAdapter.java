package ef.hrms.business.concretes;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import ef.hrms.business.abstracts.CitizenshipVerificationService;
import ef.hrms.entities.concrete.Person;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoap;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisServiceAdapter implements CitizenshipVerificationService {

	@Override
	public boolean validateCitizenship(Person person) {

		KPSPublicSoap kpsPublicSoap = new KPSPublicSoapProxy();

		try {
			return kpsPublicSoap.TCKimlikNoDogrula(Long.parseLong(person.getNationalId()), person.getFirstName(),
					person.getLastName(), person.getBirthDate().getYear() + 1900);
		} catch (RemoteException e) {
		}
		return false;
	}

}
