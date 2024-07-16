package cl.evaluacion.AlkeWalletM6.controllers;

import cl.evaluacion.AlkeWalletM6.models.Transferencia;
import cl.evaluacion.AlkeWalletM6.models.Usuario;
import cl.evaluacion.AlkeWalletM6.services.TransferenciaService;
import cl.evaluacion.AlkeWalletM6.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/historial")
public class HistorialController {

    @Autowired
    private TransferenciaService transferenciaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String getHistorial(Model model, HttpSession session) {
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
        model.addAttribute("usuario", usuarioActual);
        List<Transferencia> transferencias = transferenciaService.getTransferenciasByUsuario(usuarioActual);

        model.addAttribute("transferencias", transferencias);

        return "historial";
    }
}
