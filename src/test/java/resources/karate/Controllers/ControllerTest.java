package resources.karate.Controllers;

import com.intuit.karate.junit5.Karate;

public class ControllerTest {

    /**
     * Test de AuthController
     * @return
     */
    @Karate.Test
    Karate testCreateAccount() {
        return Karate.run("classpath:AuthController/registroAccount.feature");
    }

    @Karate.Test
    Karate testLoginAccount() {
        return Karate.run("classpath:AuthController/loginAccount.feature");
    }

    /**
     * Test de UsuariosController
     *
     * @return
     */
    @Karate.Test
    Karate testRegisterUser() {
        return Karate.run("classpath:UsuariosController/registrarHuesped.feature");
    }

    @Karate.Test
    Karate testListUser() {
        return Karate.run("classpath:UsuariosController/listadoHuesped.feature");
    }

    @Karate.Test
    Karate testUpdateUser() {
        return Karate.run("classpath:UsuariosController/actualizarHuesped.feature");
    }

    @Karate.Test
    Karate testDeleteUser() {
        return Karate.run("classpath:UsuariosController/eliminarHuesped.feature");
    }

    /**
     *  Test de ReservasController
     * @return
     */
    @Karate.Test
    Karate testRegisterReserve() {
        return Karate.run("classpath:ReservasController/registrarReserva.feature");
    }

    @Karate.Test
    Karate testListReserve() {
        return Karate.run("classpath:ReservasController/listarReserva.feature");
    }

    @Karate.Test
    Karate testUpdateReserve() {
        return Karate.run("classpath:ReservasController/actualizarReserva.feature");
    }

    @Karate.Test
    Karate testDeleteReserve() {
        return Karate.run("classpath:ReservasController/eliminarReserva.feature");
    }
}
