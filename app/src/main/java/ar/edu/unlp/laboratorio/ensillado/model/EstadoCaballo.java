package ar.edu.unlp.laboratorio.ensillado.model;

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


    public EstadoCaballo(EstadoInicial estadoInicial) {
        elementosPresentes.addAll(estadoInicial.getEstadosCaballoIniciales());
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
    public ElementoCaballo siguienteElemento() {
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
                return new Integer(o2.getOrden()).compareTo(o1.getOrden());
            }
        });
        if (!elementos.isEmpty()) {
            return elementos.get(0);
        }
        return ElementoCaballo.NINGUNO;
    }

}
