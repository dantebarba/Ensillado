package ensillado.laboratorio.unlp.edu.ar.ensillado.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by CM690II on 30/01/2017.
 */

public class JuegoEnsillado {

    private EstadoJuego estado;
    private Configuracion configuracionActual;

    public JuegoEnsillado() {

    }

    /**
     * Esto será cambiado por una configuracion global de entrada. Que puede
     * ser persistida en localstorage con JSON parsing para facilitar las
     * cosas.
     *
     */
    public JuegoEnsillado(Configuracion configuracion) {
        if (configuracion.nivelDeJuego == null || configuracion.estadoInicial == null) {
            throw new IllegalArgumentException("Los parametors ingresados son nulos.");
        }
        configuracionActual = configuracion;
        estado = new Nuevo(this, configuracion);
    }


    public EstadoJuego getEstado() {
        return estado;
    }

    public void setEstado(EstadoJuego estado) {
        this.estado = estado;
    }

    public Set<ElementoCaballo> mostrarElementos() {
        return new HashSet<ElementoCaballo>(this.getEstado().mostrarElementos());
    }

    public void comenzar() {
        this.getEstado().comenzar();
    }

    public RespuestaIntentoEnsillado ensillar(ElementoCaballo elemento) {
        return this.getEstado().ensillar(elemento);
    }

    public Configuracion getConfiguracion() {
        return this.configuracionActual;
    }

}
