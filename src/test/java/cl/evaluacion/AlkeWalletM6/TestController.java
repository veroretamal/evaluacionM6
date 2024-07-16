package cl.evaluacion.AlkeWalletM6;

import cl.evaluacion.AlkeWalletM6.models.Usuario;
import cl.evaluacion.AlkeWalletM6.dao.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TestController {

    @Autowired
    private UsuarioDao usuarioDao;

    @GetMapping("/test/findByEmail")
    public Optional<Usuario> testFindByEmail(@RequestParam("email") String email) {
        return usuarioDao.findByEmail(email);
    }
}
