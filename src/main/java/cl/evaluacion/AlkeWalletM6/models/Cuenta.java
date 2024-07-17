package cl.evaluacion.AlkeWalletM6.models;

import jakarta.persistence.*;
import lombok.*;

//Entidad Cuenta en la base de datos.

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuenta;

    private int numeroCuenta;
    private int saldo;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    //getters y setters de cada parametro a utilizar por la entidad
    public Long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }


    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }


    public int getSaldo() {
        return saldo;
    }


    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }


    public Usuario getUsuario() {
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
