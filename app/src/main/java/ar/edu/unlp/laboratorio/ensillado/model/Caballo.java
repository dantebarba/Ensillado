package ar.edu.unlp.laboratorio.ensillado.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by CM690II on 30/01/2017.
 */
public class Caballo {

    private EstadoCaballo estadoCaballo;

    public Caballo(EstadoInicial estadoInicial) {
        estadoCaballo = new EstadoCaballo(estadoInicial);
    }

    public Set<ElementoCaballo> getElementosPresentes() {
        return new HashSet<ElementoCaballo>(this.estadoCaballo.getElementosPresentes());
    }


    public RespuestaIntentoEnsillado ensillar(ElementoCaballo unElemento) {
        if (unElemento.equals(estadoCaballo.siguienteElemento())) {
            estadoCaballo.getElementosPresentes().add(unElemento);
            return RespuestaIntentoEnsillado.OK;
        } else {
            return RespuestaIntentoEnsillado.ELEMENTO_INCORRECTO;
        }
    }

    public boolean estaCompleto() {
        return this.estadoCaballo.estaCompleto();
    }

    public void filtrarElementosYaPresentes(Set<ElementoCaballo> elementosMostrados) {
        elementosMostrados.removeAll(this.estadoCaballo.getElementosPresentes());
    }

    public ElementoCaballo getElementoPresenteMayorOrden() {
        return ElementoCaballo.ordenarElementos(this.getElementosPresentes());
    }


}
