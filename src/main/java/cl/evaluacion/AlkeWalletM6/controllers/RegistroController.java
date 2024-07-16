package cl.evaluacion.AlkeWalletM6.controllers;

import cl.evaluacion.AlkeWalletM6.models.Usuario;
import cl.evaluacion.AlkeWalletM6.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping
    public String registrarUsuario(Usuario usuario, Model model) {
        // Verificar si el usuario ya existe
        if (usuarioService.findByEmail(usuario.getEmail()) != null) {
            model.addAttribute("error", "El email ya está registrado.");
            return "registro";
        }
        // Guardar el nuevo usuario
        usuarioService.saveUsuario(usuario);
        return "redirect:/";
    }
}

