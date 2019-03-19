package ipromo_base.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import ipromo_base.GenerateCode;
import ipromo_base.ValidateEmail;
import ipromo_base.model.Person;
import ipromo_base.repository.ActiveCampaignRepository;
public class PersonService {
    private ActiveCampaignRepository repository;

    public PersonService(ActiveCampaignRepository repository) {
        this.repository = repository;
    }


    public void GenerateCode(Person person) throws UnirestException {
        if (ValidateEmail.isAddressValid(person.getEmail())) {
            repository.CreateContact(person);
            repository.UpdateGeneralList(person);
            GenerateCode generateCode = new GenerateCode();
            generateCode.setSerialNumber(person.getSerialnumber());
            person.setCode(generateCode.generateCode(person.getStockId()));
            repository.CreateCodeContact(person);
            repository.UpdateCodeList(person);
            repository.UpdateCustomFieldEmailaddress(person);
        }


    }


}
