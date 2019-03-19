package ipromo_base.web;

import com.mashape.unirest.http.exceptions.UnirestException;
import ipromo_base.model.Person;
import ipromo_base.service.PersonService;
import java.io.IOException;


public class PersonRestController {
    private final PersonService service;


    public PersonRestController(PersonService service) {

        this.service = service;
    }

    public void GenerateCode(Person person) throws UnirestException, IOException {
        service.GenerateCode(person);

    }



}
