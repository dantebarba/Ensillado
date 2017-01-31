package ensillado.laboratorio.unlp.edu.ar.ensillado.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by CM690II on 30/01/2017.
 */
public class EstadoCaballo {

    private Set<ElementoCaballo> elementosPresentes = new HashSet<ElementoCaballo>();


    public EstadoCaballo() {
        elementosPresentes.add(ElementoCaballo.NINGUNO);
    }

    public boolean estaCompleto() {
        return elementosPresentes.containsAll(ElementoCaballo.getTodosLosElementos());
    }


    public Set<ElementoCaballo> elementosFaltantes() {
        Set<ElementoCaballo> elementosFaltantes = ElementoCaballo.getTodosLosElementos();
        elementosFaltantes.removeAll(this.elementosPresentes);
        return elementosFaltantes;
    }

    // HACER ESTE METODO.
    public ElementoCaballo siguienteELemento() {
        return ElementoCaballo.getSiguienteElemento(this.getElementoMayorOrden());
    }


    public void setElementosPresentes(Set<ElementoCaballo> elementosPresentes) {
        this.elementosPresentes = elementosPresentes;
    }

    public Set<ElementoCaballo> getElementosPresentes() {
        return this.elementosPresentes;
    }

    public ElementoCaballo getElementoMayorOrden() {
        List<ElementoCaballo> elementos = new ArrayList<ElementoCaballo>(this.getElementosPresentes());
        Collections.sort(elementos, new Comparator<ElementoCaballo>() {
            @Override
            public int compare(ElementoCaballo o1, ElementoCaballo o2) {
                return new Integer(o1.getOrden()).compareTo(o2.getOrden());
            }
        });
        if (!elementos.isEmpty()) {
            return elementos.get(0);
        }
        return ElementoCaballo.NINGUNO;
    }
}
