package ensillado.laboratorio.unlp.edu.ar.ensillado.model;

import java.util.Set;

/**
 * Created by CM690II on 30/01/2017.
 */

public class Corriendo extends EstadoJuego {


    public Corriendo(EstadoJuego contextoAnterior) {
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
    public void cambiarNivel(Nivel unNuevoNivel) {

    }

    @Override
    public RespuestaIntentoEnsillado ensillar(ElementosCaballo unElemento) {
        RespuestaIntentoEnsillado respuestaEnsillado = this.getCaballo().ensillar(unElemento);
        if (respuestaEnsillado.equals(RespuestaIntentoEnsillado.OK)) {
            if (this.getCaballo().estaCompleto()) {
                this.finalizar();
            }
        }
        return respuestaEnsillado;
    }

    @Override
    public void finalizar() {
        this.getJuego().setEstado(new Finalizado(this));
    }

    @Override
    public Set<ElementosCaballo> mostrarElementos() {
        return this.getNivel().getElementosMostrados();
    }


}
