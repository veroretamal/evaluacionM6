package cl.evaluacion.AlkeWalletM6.controllers;

import cl.evaluacion.AlkeWalletM6.models.Usuario;
import cl.evaluacion.AlkeWalletM6.dao.UsuarioDao;
import cl.evaluacion.AlkeWalletM6.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    UsuarioService usuarioService;


    @GetMapping("/")
    public String showLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "index";
    }

    @PostMapping("/login")
    public String login(Usuario usuario, Model model, HttpSession session) {
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Password: " + usuario.getPassword());

        if (usuarioService.validar(usuario.getEmail(), usuario.getPassword())) {
            // Obtener el usuario desde la base de datos
            Usuario usuarioLogueado = usuarioService.findByEmail(usuario.getEmail());
            session.setAttribute("usuarioActual", usuarioLogueado);
            return "redirect:/inicio";
        } else {
            model.addAttribute("errorMessage", "Email o contraseña incorrectos.");
            return "login";
        }
    }
}
