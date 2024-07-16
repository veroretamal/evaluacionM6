package cl.evaluacion.AlkeWalletM6.services;

import cl.evaluacion.AlkeWalletM6.dao.CuentaDao;
import cl.evaluacion.AlkeWalletM6.models.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Gestiona operaciones relacionadas con la entidad Cuenta.
@Service
public class CuentaService {

    @Autowired
    private CuentaDao cuentaDao;

    //Guardar una cuenta en la base de datos.
        @Transactional
    public void saveCuenta(Cuenta cuenta) {
        cuentaDao.save(cuenta);
    }

    //Obtiene una cuenta por su ID.

    @Transactional(readOnly = true)
    public Cuenta getCuentaById(Long idCuenta) {
        return cuentaDao.findById(idCuenta).orElse(null);
    }
}
