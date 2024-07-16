package cl.evaluacion.AlkeWalletM6.controllers;

import cl.evaluacion.AlkeWalletM6.dao.UsuarioDao;
import cl.evaluacion.AlkeWalletM6.models.Usuario;
import cl.evaluacion.AlkeWalletM6.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ClienteController {

    private final UsuarioService usuarioService;
    private final UsuarioDao usuarioDao;

    @Autowired
    public ClienteController(UsuarioService usuarioService, UsuarioDao usuarioDao) {
        this.usuarioService = usuarioService;
        this.usuarioDao = usuarioDao;
    }

    @GetMapping("/")
    public String listar(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("cliente");
        if (usuario == null) {
            return "redirect:/login";
        }

        System.out.println("Proyecto corriendo");
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        model.addAttribute("clientes", usuarios);

        Optional<Usuario> clienteOptional = Optional.ofNullable(usuarioDao.findByEmail(usuario.getEmail()));
        model.addAttribute("cliente", clienteOptional.orElse(new Usuario()));
        return "index";
    }
}
