package cl.evaluacion.AlkeWalletM6.controllers;

import cl.evaluacion.AlkeWalletM6.models.Cuenta;
import cl.evaluacion.AlkeWalletM6.models.Usuario;
import cl.evaluacion.AlkeWalletM6.services.CuentaService;
import cl.evaluacion.AlkeWalletM6.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Controlador para manejar las operaciones de depósito y retiro.

@Controller
@RequestMapping("/deposito")
public class DepositoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public String showDeposito(Model model, HttpSession session) {
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
        usuarioActual = usuarioService.findByEmailWithCuentas(usuarioActual.getEmail());
        model.addAttribute("usuario", usuarioActual);
        return "deposito";
    }

    // Procesa la transacción de depósito o retiro según la acción especificada.

    @PostMapping
    public String transaction(@RequestParam int monto, @RequestParam String action, Model model, HttpSession session) {
        // Obtener el usuario logueado en la sesión actual
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
        usuarioActual = usuarioService.findByEmailWithCuentas(usuarioActual.getEmail());

        if (usuarioActual != null) {
            Cuenta cuenta = usuarioActual.getCuentas().get(0); // Se asume la primera cuenta relacionada al usuario

            if (action.equals("depositar")) {
                cuenta.setSaldo(cuenta.getSaldo() + monto);
            } else if (action.equals("retirar")) {
                if (cuenta.getSaldo() >= monto) {
                    cuenta.setSaldo(cuenta.getSaldo() - monto);
                } else {
                    model.addAttribute("error", "Saldo insuficiente");
                    return "deposito"; // Volver a la página de depósito con mensaje de error
                }
            }
            cuentaService.saveCuenta(cuenta); // Guardar los cambios en la cuenta
            usuarioActual.setBalance(usuarioActual.getBalance() + (action.equals("depositar") ? monto : -monto));
            usuarioService.saveUsuario(usuarioActual); // Actualizar el balance del usuario
        }

        return "redirect:/deposito"; // Redirigir a la página de depósito después de procesar la transacción
    }
}
