package ensillado.laboratorio.unlp.edu.ar.ensillado.model;

/**
 * Created by CM690II on 30/01/2017.
 */
public class Caballo {

    private EstadoCaballo estadoCaballo;


    public RespuestaIntentoEnsillado ensillar(ElementoCaballo unElemento) {
        if (unElemento.equals(estadoCaballo.siguienteELemento())) {
            estadoCaballo.getElementosPresentes().add(unElemento);
            return RespuestaIntentoEnsillado.OK;
        } else {
            return RespuestaIntentoEnsillado.ELEMENTO_INCORRECTO;
        }
    }

    public boolean estaCompleto() {
        return this.estadoCaballo.estaCompleto();
    }
}
