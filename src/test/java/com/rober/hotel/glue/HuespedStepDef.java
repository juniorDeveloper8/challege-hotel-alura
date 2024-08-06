package com.rober.hotel.glue;

import com.rober.hotel.Steps.HuespedStep;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class HuespedStepDef {

    @Steps
    HuespedStep huespedStep;

    @When("creo la cuenta con login {string}, clave {string}")
    public void creoCuentaUsuario(String login, String clave) {
        huespedStep.createAccount(login, clave);
    }
}


/*

    @When("creo el usuario con username {string}, firstname {string}, lastname {string}")
    public void crearUsuario(String username, String firstName, String lastName){
        crearUsuario.crearUsuario(username, firstName, lastName);
    }

    @Then("el código de respuesta es {int}")
    public void elCódigoDeRespuestaEs(int statusCode) {
       crearUsuario.validarCodigoRespuesta(statusCode);
    }

    @And("el type es {string}")
    public void elTypeEs(String type) {
      crearUsuario.validarType(type);
    }
 */