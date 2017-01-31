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
    private Set<ElementoCaballo> elementosMostrados = new HashSet<ElementoCaballo>();
    private Integer pisoDelConjunto = ARRAY_START;

    public Nivel(NivelEnum nivelEnum) {
        this.nivel = nivelEnum;
        elementosIniciales();

    }

    public boolean siguienteElemento() {
        CircularList<ElementoCaballo> list = (CircularList) Arrays.asList(ElementoCaballo.values());
        this.getElementosMostrados().add(list.get(pisoDelConjunto + 1));
        if (ElementoCaballo.values().length > pisoDelConjunto) {
            pisoDelConjunto++;
            return true;
        }
        return false;
    }

    private void elementosIniciales() {
        this.getElementosMostrados().addAll(Arrays.asList(Arrays.copyOfRange(ElementoCaballo.values(), this.pisoDelConjunto, this.getNivel().getValue())));
        this.pisoDelConjunto =  this.pisoDelConjunto + this.getNivel().getValue();
    }

    /**
     * TODO podria usar un iterator while it.hasNext().
     *
     * @return
     */
    private boolean hayMasElementos() {
        return this.pisoDelConjunto + this.getNivel().getValue() <= ElementoCaballo.getTodosLosElementos().size();
    }


    public Set<ElementoCaballo> getElementosMostrados() {
        return elementosMostrados;
    }

    public NivelEnum getNivel() {
        return nivel;
    }
}
