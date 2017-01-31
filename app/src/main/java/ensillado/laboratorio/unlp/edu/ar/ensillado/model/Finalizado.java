package ensillado.laboratorio.unlp.edu.ar.ensillado.model;

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
        this.getJuego().setEstado(new Nuevo(this.getNivel()));
    }

    @Override
    public void cambiarNivel(Nivel unNuevoNivel) {

    }

    @Override
    public boolean ensillar(ElementosCaballo unElemento) {
        return false;
    }

    @Override
    public void finalizar() {

    }
}
