package cl.evaluacion.AlkeWalletM6.controllers;


import cl.evaluacion.AlkeWalletM6.models.Usuario;
import cl.evaluacion.AlkeWalletM6.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/inicio")
public class InicioController {

    @Autowired
    UsuarioService usuarioService;

    // Muestra la página de inicio del usuario actual, actualizando su información de cuenta si está logueado.

    @GetMapping
    public String showInicio(Model model, HttpSession session) {
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
        if (usuarioActual != null) {
            usuarioActual = usuarioService.findByEmailWithCuentas(usuarioActual.getEmail());
            session.setAttribute("usuarioActual", usuarioActual);

            System.out.println("Bienvenido " + usuarioActual.getNombre() + " " + usuarioActual.getApellido());
            System.out.println("Saldo : " + usuarioActual.getBalance());
            model.addAttribute("usuario", usuarioActual);
        } else {
            System.out.println("Usuario no encontrado en la sesión.");
        }
        return "inicio";
    }

    // Invalida la sesión actual del usuario, redirigiéndolo a la página de inicio de sesión.

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalida la sesión actual
        return "redirect:/";
    }
}
