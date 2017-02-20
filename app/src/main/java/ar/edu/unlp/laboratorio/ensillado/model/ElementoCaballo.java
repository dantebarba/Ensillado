package ar.edu.unlp.laboratorio.ensillado.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * Created by CM690II on 30/01/2017.
 */
public enum ElementoCaballo {
    NINGUNO(0), CABEZADA(1), BOZAL(2), SUDADERA(3), MATRA(4), BAJO_MONTURA(5), MONTURA_ESTRIBOS(6);

    static EnumSet<ElementoCaballo> todosLosElementos = EnumSet.copyOf(Arrays.asList(values()).subList(1, values().length));
    private final int orden;


    ElementoCaballo(int orden) {
        this.orden = orden;
    }

    // FIXME: ESTO ANDA?
    public static ElementoCaballo getElementoSegunOrden(int orden) {
        return ElementoCaballo.values()[orden];
    }

    public int getOrden() {
        return this.orden;
    }

    /**
     * Todos los elementos para mostrar. No incluye el Null-Object NINGUNO.
     * @return
     */
    public static EnumSet<ElementoCaballo> getTodosLosElementos() {
        return todosLosElementos;
    }

    public static ElementoCaballo[] getTodosLosElementosArray() {
        return Arrays.copyOfRange(values(), 1, values().length);
    }

    /**
     * Retorna el siguiente elemento. Devuelve NULL si no existe siguiente elemento.
     * @param elemento
     * @return
     */
    public static ElementoCaballo getSiguienteElemento(ElementoCaballo elemento) {
        for (ElementoCaballo unElemento : ElementoCaballo.values()) {
            if (elemento.getOrden() + 1 == unElemento.getOrden()) {
                return unElemento;
            }
        }
        return ElementoCaballo.getElementoSegunOrden(0);
    }

    public static ElementoCaballo ordenarElementos(Set<ElementoCaballo> misElementos) {
        List<ElementoCaballo> elementos = new ArrayList<ElementoCaballo>(misElementos);
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

    public String toString() {
        switch (this) {

            case NINGUNO:
                return "Ningun elemento";
            case CABEZADA:
                return "Cabezada";
            case BOZAL:
                return "Bozal";
            case SUDADERA:
                return "Sudadera";
            case MATRA:
                return "Matra";

            case BAJO_MONTURA:
                return "Bajo Montura";
            case MONTURA_ESTRIBOS:
                return "Montura";
        }
        return "";
    }


}
