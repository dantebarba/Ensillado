package ensillado.laboratorio.unlp.edu.ar.ensillado.model;

import java.util.Set;

/**
 * Created by CM690II on 30/01/2017.
 */

public class Nuevo extends EstadoJuego {


    private Nuevo(EstadoJuego estadoPrevio) {
        super(estadoPrevio);
    }

    public Nuevo(Nivel unNivel, JuegoEnsillado nuevoJuego) {
        super(unNivel, nuevoJuego);
    }

    @Override
    public void pausar() {

    }

    @Override
    public void continuar() {

    }

    @Override
    public void getTiempoDeJuego() {

    }

    @Override
    public void reiniciar() {

    }

    @Override
    public void cambiarNivel(Nivel unNuevoNivel) {

    }

    @Override
    public RespuestaIntentoEnsillado ensillar(ElementoCaballo unElemento) {
        return RespuestaIntentoEnsillado.ELEMENTO_INCORRECTO;
    }

    @Override
    public void finalizar() {

    }

    @Override
    public Set<ElementoCaballo> mostrarElementos() {
        return null;
    }
}
