package ensillado.laboratorio.unlp.edu.ar.ensillado.model;

import java.util.Set;

/**
 * Created by CM690II on 30/01/2017.
 */
public class EstadoCaballo {

    private Set<ElementosCaballo> elementosPresentes;


    public boolean estaCompleto() {
        return elementosPresentes.containsAll(ElementosCaballo.getTodosLosElementos());
    }


    public Set<ElementosCaballo> elementosFaltantes() {
        Set<ElementosCaballo> elementosFaltantes = ElementosCaballo.getTodosLosElementos();
        elementosFaltantes.removeAll(this.elementosPresentes);
        return elementosFaltantes;
    }

    // HACER ESTE METODO.
    public ElementosCaballo siguienteELemento() {
        return ElementosCaballo.getSiguienteElemento(this.getElementoMayorOrden());
    }


    public void setElementosPresentes(Set<ElementosCaballo> elementosPresentes) {
        this.elementosPresentes = elementosPresentes;
    }

    public Set<ElementosCaballo> getElementosPresentes() {
        return this.elementosPresentes;
    }

    public ElementosCaballo getElementoMayorOrden() {
        for (this.elementosPresentes) {

        }
    }
}
