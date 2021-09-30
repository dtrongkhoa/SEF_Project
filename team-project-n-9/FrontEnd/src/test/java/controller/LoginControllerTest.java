package controller;

import model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.HashMap;

import static controller.LoginController.loginHelper;
import static controller.LoginController.logoutHelper;
import static org.junit.jupiter.api.Assertions.*;

public class LoginControllerTest {

    HashMap<String, Object> model;

    @BeforeEach
    public void init() {
        HashMap<String, Object> model = new HashMap<>();
    }


    @Test
    public void controllerShouldHaveWarningWhenLoginFailed()
    {
        //Given
        boolean loginPass = false;
        String warning = "WARNING";

        //When
        model =  loginHelper(loginPass, new HashMap());

        //Then
        assertTrue(model.containsKey(warning));
    }

    @Test
    public void controllerShouldNotHaveWarningWhenLoginPass()
    {
        //Given
        boolean loginPass = true;
        String warning = "WARNING";

        //When
        model =  loginHelper(loginPass, new HashMap());

        //Then
        assertFalse(model.containsKey(warning));
    }

    @Test
    public void controllerShouldHaveCurrentUserWhenLoginPass()
    {
        //Given
        boolean loginPass = true;
        String currentUser = "CURRENT_USER";

        //When
        model =  loginHelper(loginPass, new HashMap());

        //Then
        assertTrue(model.containsKey(currentUser));
    }

    @Test
    public void controllerShouldNotHaveCurrentUserWhenLoginFail()
    {
        //Given
        boolean loginPass = false;
        String currentUser = "CURRENT_USER";

        //When
        model =  loginHelper(loginPass, new HashMap());

        //Then
        assertFalse(model.containsKey(currentUser));
    }

    @Test
    public void whenLoggingOutCurrentUserShouldExist()
    {
        //given
        String currentUser = "CURRENT_USER";
        model = new HashMap<>();

        //when
        model = logoutHelper(model);

        //then
        assertTrue(model.containsKey(currentUser));
    }

    @Test
    public void currentUserShouldBeNullIfItExistedLoggingOut()
    {
        //given
        String currentUser = "CURRENT_USER";
        model = new HashMap<>();

        //when
        model = logoutHelper(model);

        assertNull(model.get(currentUser));
    }


}
