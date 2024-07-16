package cl.evaluacion.AlkeWalletM6.services;

import cl.evaluacion.AlkeWalletM6.dao.CuentaDao;
import cl.evaluacion.AlkeWalletM6.models.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CuentaService {
    @Autowired
    CuentaDao cuentaDao;

    @Transactional
    public void saveCuenta(Cuenta cuenta) {
        cuentaDao.save(cuenta);
    }

    @Transactional
    public Cuenta getCuentaById(Long idCuenta) {
        return cuentaDao.findById(idCuenta).orElse(null);
    }
}
