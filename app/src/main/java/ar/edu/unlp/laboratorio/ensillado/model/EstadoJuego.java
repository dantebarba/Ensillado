package ar.edu.unlp.laboratorio.ensillado.model;

import java.util.Set;

/**
 * Created by CM690II on 30/01/2017.
 */
public abstract class EstadoJuego {

    private Nivel nivel;
    private JuegoEnsillado juego;


    /**
     * Los elementos que se muestran en la pantalla de seleccion
     */

    /**
     * El caballo con su estado de ensillado actual.
     */
    private Caballo caballo;

    public EstadoJuego(EstadoJuego contextoAnterior) {
        copyContext(contextoAnterior);
    }

    protected EstadoJuego(Nivel unNivel, JuegoEnsillado nuevoJuego, EstadoInicial desnudo) {
        this.setJuego(nuevoJuego);
        this.nivel = unNivel;
        this.setCaballo(new Caballo(desnudo));
    }


    private void copyContext(EstadoJuego contextoAnterior) {
        this.caballo = contextoAnterior.getCaballo();
        this.juego = contextoAnterior.getJuego();
        this.nivel = contextoAnterior.getNivel();
    }

    public abstract void pausar();

    public abstract void continuar();

    public abstract void getTiempoDeJuego();

    public abstract void reiniciar();

    public JuegoEnsillado getJuego() {
        return juego;
    }

    public void setJuego(JuegoEnsillado juego) {
        this.juego = juego;
    }

    public abstract RespuestaIntentoEnsillado ensillar(ElementoCaballo unElemento);


    public Caballo getCaballo() {
        return caballo;
    }

    public void setCaballo(Caballo caballo) {
        this.caballo = caballo;
    }

    public Nivel getNivel() {
        return this.nivel;
    }

    public Set<ElementoCaballo> getElementosMostrados() {
        return this.getNivel().getElementosMostrados();
    }

    public abstract void finalizar();

    public abstract Set<ElementoCaballo> mostrarElementos();

    public void comenzar() {
    }

    public void siguienteElemento() {
        this.getNivel().siguienteElemento(this.getCaballo());
    }
}
