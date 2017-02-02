package ensillado.laboratorio.unlp.edu.ar.ensillado.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by CM690II on 30/01/2017.
 */

public class Finalizado extends EstadoJuego {

    public Finalizado(EstadoJuego contextoAnterior) {
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
        this.getJuego().setEstado(new Nuevo(this.getJuego(), this.getJuego().getConfiguracion()));
    }

    @Override
    public void cambiarNivel(Nivel unNuevoNivel) {

    }

    @Override
    public RespuestaIntentoEnsillado ensillar(ElementoCaballo unElemento) {
        return RespuestaIntentoEnsillado.FINALIZADO;
    }

    @Override
    public void finalizar() {
        throw new IllegalStateException("El juego ya se encuentra finalizado. Para continuar por " +
                "favor" +
                "reinicie el juego");
    }

    @Override
    public Set<ElementoCaballo> mostrarElementos() {
        return new HashSet<ElementoCaballo>();
    }
}
