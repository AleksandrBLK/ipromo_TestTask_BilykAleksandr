package ipromo_base.repository;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import ipromo_base.model.Person;
import org.json.JSONObject;


public class ActiveCampaignRepository {
    private final String url = "https://aleksandrbilyk.api-us1.com/api/3";
    private final String apiKey = "7f7383668ab543929cdeacda2fbe86e112733a85b0f7d01a12b54e450561cac0ec6c124d";

    public ActiveCampaignRepository() {
    }

    public void CreateContact(Person person) throws UnirestException {
        if (person.isNew()) {
            HttpResponse<JsonNode> response = Unirest.post(url + "/contacts")
                    .header("Api-Token", apiKey)
                    .body("{ \"contact\": {\"email\":\"" + person.getEmail() + "\", \"firstName\":\"" + person.getName() + "\", \"lastName\":\"" + person.getSecondName() + "\", \"phone\":\"" + person.getPhone() + "\" }}")
                    .asJson();
            SuccessResponseStatusAndSetPersonId(response, person);

            if (response.getStatus() == 422) {
                System.out.println("Адрес электронной почты уже существует в системе");
            }
        }

    }


    public void UpdateGeneralList(Person person) throws UnirestException {
        if (!person.isNew()) {
            HttpResponse<JsonNode> response = Unirest.post(url + "/contactLists")
                    .header("Api-Token", apiKey)
                    .body("{ \"contactList\": {\"list\":41, \"contact\":\"" + person.getId() + "\", \"status\": 1 }}")
                    .asJson();
            if (response.getStatus() != 201) {
                System.out.println("Ошибка добавления пользователя в лист");
            }
        }
    }

    public void CreateCodeContact(Person person) throws UnirestException {
        if (!person.hasCode()) {
            HttpResponse<JsonNode> response = Unirest.post(url + "/contacts")
                    .header("Api-Token", apiKey)
                    .body("{ \"contact\": {\"email\":\"" + person.getCode() + "@ipromo.digital" + "\"}}")
                    .asJson();
            SuccessResponseStatusAndSetPersonId(response, person);

        }

    }

    public void UpdateCodeList(Person person) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post(url + "/contactLists")
                .header("Api-Token", apiKey)
                .body("{ \"contactList\": {\"list\":42,\"contact\":\"" + person.getId() + "\", \"status\": 1 }}")
                .asJson();
        if (response.getStatus() != 201) {
            System.out.println("Ошибка добавления пользователя в лист");
        }


    }

    public void UpdateCustomFieldEmailaddress(Person person) throws UnirestException {
        HttpResponse<JsonNode> response1 = Unirest.post(url + "/fieldValues").header("Api-Token", apiKey)
                .body("{ \"fieldValue\": {\"contact\":" + person.getId() + ", \"field\":42, \"value\":\"" + person.getEmail() + "\"}}")
                .asJson();

    }

    private void SuccessResponseStatusAndSetPersonId(HttpResponse<JsonNode> response, Person person) {
        if (response.getStatus() == 201) {
            JSONObject responseBody = response.getBody().getObject();
            JSONObject responseBody2 = responseBody.getJSONObject("contact");
            String id = (String) responseBody2.get("id");
            person.setId(id);

        }

    }


}
