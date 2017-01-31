package ensillado.laboratorio.unlp.edu.ar.ensillado.model;

import java.util.Set;

/**
 * Created by CM690II on 30/01/2017.
 */

public class JuegoEnsillado {

    private EstadoJuego estado;

    public JuegoEnsillado() {

    }


    public JuegoEnsillado(Nivel unNivel) {
        estado = new Nuevo(unNivel, this);
    }


    public EstadoJuego getEstado() {
        return estado;
    }

    public void setEstado(EstadoJuego estado) {
        this.estado = estado;
    }

    public Set<ElementoCaballo> mostrarElementos() {
        return this.getEstado().mostrarElementos();
    }

    public void comenzar() {

    }

    public RespuestaIntentoEnsillado ensillar(ElementoCaballo elemento) {
        return this.getEstado().ensillar(elemento);
    }
}
