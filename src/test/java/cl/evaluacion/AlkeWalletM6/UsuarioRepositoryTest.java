package cl.evaluacion.AlkeWalletM6;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import cl.evaluacion.AlkeWalletM6.models.Usuario;
import cl.evaluacion.AlkeWalletM6.dao.UsuarioDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UsuarioRepositoryTest {

    @Mock
    private UsuarioDao usuarioDao;

    @Test
    public void testFindByEmail() {
        // Crear un cliente de ejemplo
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setPassword("password");

        // Configurar el comportamiento del mock
        when(usuarioDao.findByEmail("test@example.com")).thenReturn(Optional.of(usuario));

        // Llamar al método y verificar el resultado
        Optional<Usuario> found = usuarioDao.findByEmail("test@example.com");
        assertThat(found).isNotNull();
        assertThat(found.getClass()).isEqualTo("test@example.com");
    }
}
