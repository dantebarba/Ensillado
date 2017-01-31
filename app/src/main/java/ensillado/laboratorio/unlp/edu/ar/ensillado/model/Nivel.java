package ensillado.laboratorio.unlp.edu.ar.ensillado.model;

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
    private Set<ElementosCaballo> elementosMostrados = new HashSet<ElementosCaballo>();
    private Integer pisoDelConjunto = ARRAY_START;

    public Nivel(NivelEnum nivelEnum) {
        this.nivel = nivelEnum;
        elementosIniciales();

    }

    public boolean siguienteElemento() {
        CircularList<ElementosCaballo> list = (CircularList) Arrays.asList(ElementosCaballo.values());
        this.getElementosMostrados().add(list.get(pisoDelConjunto + 1));
        if (ElementosCaballo.values().length > pisoDelConjunto) {
            pisoDelConjunto++;
            return true;
        }
        return false;
    }

    private void elementosIniciales() {
        this.getElementosMostrados().addAll(Arrays.asList(Arrays.copyOfRange(ElementosCaballo.values(), this.pisoDelConjunto, this.getNivel().getValue())));
        this.pisoDelConjunto =  this.pisoDelConjunto + this.getNivel().getValue();
    }

    /**
     * TODO podria usar un iterator while it.hasNext().
     *
     * @return
     */
    private boolean hayMasElementos() {
        return this.pisoDelConjunto + this.getNivel().getValue() <= ElementosCaballo.getTodosLosElementos().size();
    }


    public Set<ElementosCaballo> getElementosMostrados() {
        return elementosMostrados;
    }

    public NivelEnum getNivel() {
        return nivel;
    }
}
