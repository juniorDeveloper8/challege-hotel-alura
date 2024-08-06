package hotel.Steps;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class HuespedStep {

    private static String CREATE_ACCOUNT = "http://localhost:8080/login/create";

    @Step("Crear Cuenta {0}  en login-controller")
    public void createAccount(String login, String clave) {
        SerenityRest.given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"login\": \"" + login + "\",\n" +
                        "  \"calve\": \"" + clave + "\",\n" +
                        "}")
                .log().all()
                .post(CREATE_ACCOUNT);

    }

    public void validarType(String type) {
        restAssuredThat(response -> response.body("'type'", equalTo(type)));
        System.out.println("Type: " + SerenityRest.lastResponse().body().path("type").toString());
        System.out.println(SerenityRest.lastResponse().print());
    }

    public void validarCodigoRespuesta(int statusCode) {
        restAssuredThat(response -> response.statusCode(statusCode));
    }
}
