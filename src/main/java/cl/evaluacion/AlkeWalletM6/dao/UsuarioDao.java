package cl.evaluacion.AlkeWalletM6.dao;

import cl.evaluacion.AlkeWalletM6.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//busca usuario almacenados
@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    List<Usuario> findAll(); //todos los usuarios almacenados

    Usuario findByEmail(String email); //busca con correo electronico

    Optional<Usuario> findById(Long idUsuario); //busca por su identificador
    }

