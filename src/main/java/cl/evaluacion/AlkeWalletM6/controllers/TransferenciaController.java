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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/transferencia")
public class TransferenciaController {

    private final UsuarioService usuarioService;
    private final TransferenciaService transferenciaService;

    @Autowired
    public TransferenciaController(UsuarioService usuarioService, TransferenciaService transferenciaService) {
        this.usuarioService = usuarioService;
        this.transferenciaService = transferenciaService;
    }

    // Muestra la página de transferencias, incluyendo el formulario para realizar una nueva transferencia.

    @GetMapping
    public String mostrarTransferencia(Model model, HttpSession session) {
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
        if (usuarioActual == null) {
            return "redirect:/login"; // Redirige al inicio de sesión si no hay usuario en sesión
        }
        model.addAttribute("usuario", usuarioActual);

        // Obtener todos los usuarios registrados excepto el usuario actual
        List<Usuario> usuarios = usuarioService.getAllUsuarios().stream()
                .filter(usuario -> !usuario.getEmail().equals(usuarioActual.getEmail()))
                .collect(Collectors.toList());

        model.addAttribute("usuarios", usuarios);
        Transferencia transferencia = new Transferencia();
        transferencia.setRecepcion(null);
        model.addAttribute("transferencia", transferencia);

        return "transferencia";
    }

    // Procesa la solicitud de realizar una transferencia de saldo entre usuarios.

    @PostMapping
    public String realizarTransferencia(@RequestParam int monto, @RequestParam String recepcion, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
        if (usuarioActual == null) {
            return "redirect:/login"; // Redirige al inicio de sesión si no hay usuario en sesión
        }
        Usuario usuarioDestino = usuarioService.findByEmail(recepcion);
        if (usuarioDestino == null) {
            model.addAttribute("error", "Usuario destino no encontrado");
            return "transferencia";
        }

        // Verificar si el usuario tiene saldo suficiente para realizar la transferencia
        if (usuarioActual.getBalance() >= monto) {
            Transferencia transferencia = new Transferencia();
            transferencia.setEnvio(usuarioActual);
            transferencia.setRecepcion(usuarioDestino);
            transferencia.setMonto(monto);
            transferencia.setFecha(new Date());
            transferenciaService.saveTransferencia(transferencia);

            // Actualizar los saldos de los usuarios involucrados en la transferencia
            usuarioActual.setBalance(usuarioActual.getBalance() - monto);
            usuarioDestino.setBalance(usuarioDestino.getBalance() + monto);
            usuarioService.saveUsuario(usuarioActual);
            usuarioService.saveUsuario(usuarioDestino);

            redirectAttributes.addFlashAttribute("mensajeExito", "La transferencia fue realizada correctamente.");
            return "redirect:/transferencia";
        } else {
            model.addAttribute("error", "Saldo insuficiente");
            return "transferencia";
        }
    }
}
