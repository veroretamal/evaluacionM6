package cl.evaluacion.AlkeWalletM6.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cl.evaluacion.AlkeWalletM6.controllers.LoginController;
import cl.evaluacion.AlkeWalletM6.models.Usuario;
import cl.evaluacion.AlkeWalletM6.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @Mock
    private RedirectAttributes redirectAttributes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowLogin() {
        // Given
        Usuario usuario = new Usuario();
        when(model.addAttribute("usuario", new Usuario())).thenReturn(model);

        // When
        String viewName = loginController.showLogin(model);

        // Then
        assertEquals("index", viewName);
        verify(model).addAttribute("usuario", new Usuario());
    }

    @Test
    public void testLoginValidCredentials() {
        // Given
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setPassword("password");

        when(usuarioService.validar(usuario.getEmail(), usuario.getPassword())).thenReturn(true);
        when(usuarioService.findByEmail(usuario.getEmail())).thenReturn(usuario);

        // When
        String viewName = loginController.login(usuario, model, session);

        // Then
        assertEquals("redirect:/inicio", viewName);
        verify(session).setAttribute("usuarioActual", usuario);
        verify(usuarioService).validar(usuario.getEmail(), usuario.getPassword());
        verify(usuarioService).findByEmail(usuario.getEmail());
        verifyNoMoreInteractions(session, usuarioService);
    }

    @Test
    public void testLoginInvalidCredentials() {
        // Given
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setPassword("wrongpassword");

        when(usuarioService.validar(usuario.getEmail(), usuario.getPassword())).thenReturn(false);

        // When
        String viewName = loginController.login(usuario, model, session);

        // Then
        assertEquals("login", viewName);
        verify(model).addAttribute("errorMessage", "Email o contraseña incorrectos.");
        verify(usuarioService).validar(usuario.getEmail(), usuario.getPassword());
        verifyNoMoreInteractions(model, usuarioService);
    }
}
