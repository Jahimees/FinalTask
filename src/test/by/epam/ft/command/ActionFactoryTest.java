package test.by.epam.ft.command;

import by.epam.ft.command.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

import java.io.IOException;

import static by.epam.ft.constant.PageConstant.MAIN_PAGE;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ActionFactoryTest {

    @Test
    public void defineCommandTest() throws ServletException, IOException {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final ActionFactory actionFactoryMock = mock(ActionFactory.class);

        EmptyCommand command = new EmptyCommand();
        when(actionFactoryMock.defineCommand(request)).thenReturn(command);
    }
}
