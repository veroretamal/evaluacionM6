package cl.evaluacion.AlkeWalletM6.dao;

import cl.evaluacion.AlkeWalletM6.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaDao extends JpaRepository<Cuenta, Long> {
}
