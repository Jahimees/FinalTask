package test.by.epam.ft.servlet;

import by.epam.ft.servlet.ControllerServlet;
import org.testng.annotations.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.epam.ft.constant.PageConstant.MAIN_PAGE;
import static org.mockito.Mockito.*;

public class ControllerServletTest {

    @Test
    public void testProcessRequest() throws ServletException, IOException {
        final ControllerServlet servlet = new ControllerServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse responce = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(MAIN_PAGE)).thenReturn(dispatcher);
        servlet.doGet(request, responce);

        verify(request, times(1)).getRequestDispatcher(MAIN_PAGE);
        verify(request, never()).getSession();
        verify(dispatcher).forward(request,responce);
    }
}
