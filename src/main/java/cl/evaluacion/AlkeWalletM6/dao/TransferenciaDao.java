package cl.evaluacion.AlkeWalletM6.dao;

import cl.evaluacion.AlkeWalletM6.models.Transferencia;
import cl.evaluacion.AlkeWalletM6.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TransferenciaDao extends JpaRepository<Transferencia, Long> {

    //Consulta para buscar transferencias en las que un usuario fue el remitente o el destinatario.

    @Query("SELECT t FROM Transferencia t WHERE t.envio = :usuario OR t.recepcion = :usuario")
    List<Transferencia> findByEnvioRecepcion(@Param("usuario") Usuario usuario);

}
