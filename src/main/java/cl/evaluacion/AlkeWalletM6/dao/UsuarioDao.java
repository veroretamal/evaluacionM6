package cl.evaluacion.AlkeWalletM6.dao;

import cl.evaluacion.AlkeWalletM6.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    List<Usuario> findAll();

    Usuario findByEmail(String email);

    Optional<Usuario> findById(Long idUsuario);
    }

