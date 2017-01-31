package ensillado.laboratorio.unlp.edu.ar.ensillado.model;

/**
 * Created by CM690II on 30/01/2017.
 */

public class Nuevo extends EstadoJuego {


    private Nuevo(EstadoJuego estadoPrevio) {
        super(estadoPrevio);
    }

    public Nuevo(Nivel unNivel) {
        super(unNivel);
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
    public boolean ensillar(ElementosCaballo unElemento) {
        return false;
    }
}
