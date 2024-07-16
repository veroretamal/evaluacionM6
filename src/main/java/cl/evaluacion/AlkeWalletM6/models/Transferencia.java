package cl.evaluacion.AlkeWalletM6.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Date;

//Entidad Transferencia
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransferencia;

    @ManyToOne
    @JoinColumn(name = "envio")
    private Usuario envio;

    @ManyToOne
    @JoinColumn(name = "recepcion")
    private Usuario recepcion;

    private int monto;
    private Date fecha;

    @Override
    public String toString() {
        return "Transferencia{" +
                "idTransferencia=" + idTransferencia +
                ", envio=" + envio +
                ", recepcion=" + recepcion +
                ", monto=" + monto +
                ", fecha=" + fecha +
                '}';
    }

    //Getters y Setters


    public Long getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(Long idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public Usuario getEnvio() {
        return envio;
    }

    public void setEnvio(Usuario envio) {
        this.envio = envio;
    }

    public Usuario getRecepcion() {
        return recepcion;
    }

    public void setRecepcion(Usuario recepcion) {
        this.recepcion = recepcion;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
