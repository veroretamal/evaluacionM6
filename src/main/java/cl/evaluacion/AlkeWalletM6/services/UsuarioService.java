package cl.evaluacion.AlkeWalletM6.services;

import cl.evaluacion.AlkeWalletM6.dao.UsuarioDao;
import cl.evaluacion.AlkeWalletM6.models.Cuenta;
import cl.evaluacion.AlkeWalletM6.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//Gestiona operaciones relacionadas con los usuarios.

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private CuentaService cuentaService;

    //Obtiene todos los usuarios registrados.

    public List<Usuario> getAllUsuarios() {
        return usuarioDao.findAll();
    }

    //Valida las credenciales de inicio de sesión de un usuario.

    public boolean validar(String email, String password) {
        Usuario usuario = usuarioDao.findByEmail(email);
        return usuario != null && usuario.getPassword().equals(password);
    }

    // Guardar un nuevo usuario en la base de datos.

    @Transactional
    public void saveUsuario(Usuario usuario) {
        usuarioDao.save(usuario);

        // Crear una nueva cuenta partiendo desde 0
        Cuenta cuenta = new Cuenta();
        cuenta.setSaldo(0);
        cuenta.setUsuario(usuario);
        cuentaService.saveCuenta(cuenta);

        // Agregar la cuenta recién creada al usuario y guardar los cambios
        usuario.addCuenta(cuenta);
        usuarioDao.save(usuario);
    }

    //Buscar un usuario por su correo electrónico.

    @Transactional
    public Usuario findByEmail(String email) {
        return usuarioDao.findByEmail(email);
    }

    //Busca un usuario por su correo electrónico y carga sus cuentas asociadas.

    @Transactional
    public Usuario findByEmailWithCuentas(String email) {
        Usuario usuario = usuarioDao.findByEmail(email);
        if (usuario != null) {
            usuario.getCuentas().size();
        }
        return usuario;
    }

}