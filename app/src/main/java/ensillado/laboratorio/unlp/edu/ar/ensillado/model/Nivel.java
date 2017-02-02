package ensillado.laboratorio.unlp.edu.ar.ensillado.model;

import android.icu.util.RangeValueIterator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import ensillado.laboratorio.unlp.edu.ar.ensillado.utils.CircularList;

/**
 * Created by CM690II on 30/01/2017.
 */
public class Nivel {


    public static final int ARRAY_START = 0;

    private NivelEnum nivel;
    private Set<ElementoCaballo> elementosMostrados = new HashSet<ElementoCaballo>();
    private Integer pisoDelConjunto = ARRAY_START;


    public Nivel(NivelEnum nivelEnum, EstadoInicial estadoInicial) {
        this.nivel = nivelEnum;
        elementosIniciales(estadoInicial);

    }

    /**
     * @param caballo
     * @return verdadero si existe siguiente elemento. Falso si no hay mas elementos siguientes,
     * esto es
     * array de elementos es igual al piso del conjunto de elementos.
     */
    public boolean siguienteElemento(Caballo caballo) {
        if (ElementoCaballo.getTodosLosElementosArray().length > this.pisoDelConjunto) {
            int maxIndex = ElementoCaballo.getTodosLosElementosArray().length;
            if (this.pisoDelConjunto + this.getNivel().getValue() < ElementoCaballo
                    .getTodosLosElementosArray().length) {
                maxIndex = pisoDelConjunto + this.getNivel().getValue();
            }
            // limpiamos los elementos previamente mostrados, mostramos el siguiente conjunto
            this.getElementosMostrados().clear();
            this.getElementosMostrados().addAll(Arrays.asList(Arrays.copyOfRange(ElementoCaballo
                    .getTodosLosElementosArray(), pisoDelConjunto, maxIndex)));
            this.pisoDelConjunto = this.pisoDelConjunto + 1;
            //           caballo.filtrarElementosYaPresentes(this.getElementosMostrados());
            return true;
        }
        //       caballo.filtrarElementosYaPresentes(this.getElementosMostrados());
        return false;
    }

    private void elementosIniciales(EstadoInicial estadoInicial) {
        this.getElementosMostrados().addAll(
                Arrays.asList(Arrays.copyOfRange(
                        ElementoCaballo.getTodosLosElementosArray(), this.pisoDelConjunto +
                                estadoInicial.getCantidadElementosIniciales(), maximoIndiceDeInicio(estadoInicial))));
        //       this.pisoDelConjunto = this.pisoDelConjunto + this.getNivel().getValue();
        this.pisoDelConjunto = this.pisoDelConjunto + estadoInicial.getCantidadElementosIniciales
                () + 1;
    }

    private int maximoIndiceDeInicio(EstadoInicial estadoInicial) {
        return this
                .pisoDelConjunto + estadoInicial.getCantidadElementosIniciales()
                + this
                .getNivel().getValue() < ElementoCaballo.getTodosLosElementosArray().length ? this
                .pisoDelConjunto + estadoInicial.getCantidadElementosIniciales()
                + this
                .getNivel().getValue() : ElementoCaballo.getTodosLosElementosArray().length;
    }

    /**
     * TODO podria usar un iterator while it.hasNext().
     *
     * @return
     */
    private boolean hayMasElementos() {
        return this.pisoDelConjunto + this.getNivel().getValue() <= ElementoCaballo
                .getTodosLosElementos().size();
    }


    public Set<ElementoCaballo> getElementosMostrados() {
        return elementosMostrados;
    }

    public NivelEnum getNivel() {
        return nivel;
    }
}
