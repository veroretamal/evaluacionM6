package cl.evaluacion.AlkeWalletM6.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

//Entidad Usuario
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private int balance;

    // Relación con cuentas
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cuenta> cuentas = new ArrayList<>();

    public void addCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
        cuenta.setUsuario(this);
    }

    // Relación con transacciones
    @OneToMany(mappedBy = "envio", cascade = CascadeType.ALL)
    private List<Transferencia> envio;

    @OneToMany(mappedBy = "recepcion", cascade = CascadeType.ALL)
    private List<Transferencia> recepcion;


    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public List<Transferencia> getEnvio() {
        return envio;
    }

    public void setEnvio(List<Transferencia> envio) {
        this.envio = envio;
    }

    public List<Transferencia> getRecepcion() {
        return recepcion;
    }

    public void setRecepcion(List<Transferencia> recepcion) {
        this.recepcion = recepcion;
    }
}
