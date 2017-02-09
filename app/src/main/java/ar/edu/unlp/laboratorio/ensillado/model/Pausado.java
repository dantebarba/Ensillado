package ar.edu.unlp.laboratorio.ensillado.model;

import java.util.Set;

/**
 * Created by CM690II on 30/01/2017.
 */

public class Pausado extends EstadoJuego {
    public Pausado(EstadoJuego contextoAnterior) {
        super(contextoAnterior);
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
    public RespuestaIntentoEnsillado ensillar(ElementoCaballo unElemento) {
        return null;
    }

    @Override
    public void finalizar() {

    }

    @Override
    public Set<ElementoCaballo> mostrarElementos() {
        return null;
    }
}
