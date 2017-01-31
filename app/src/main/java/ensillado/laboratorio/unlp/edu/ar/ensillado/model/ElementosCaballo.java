package ensillado.laboratorio.unlp.edu.ar.ensillado.model;

import java.util.Arrays;
import java.util.EnumSet;

/**
 * Created by CM690II on 30/01/2017.
 */
public enum ElementosCaballo {
    CABEZADA(1), BOZAL(2), SUDADERA(3), MATRA(4), BAJO_MONTURA(5), MONTURA_ESTRIBOS(6);

    static EnumSet<ElementosCaballo> todosLosElementos = EnumSet.copyOf(Arrays.asList(values()));
    private final int orden;


    ElementosCaballo(int orden) {
        this.orden = orden;
    }

    // FIXME: ESTO ANDA?
    public static ElementosCaballo getElementoSegunOrden(int orden) {
        return ElementosCaballo.values()[orden];
    }

    public int getOrden() {
        return this.orden;
    }

    public static EnumSet<ElementosCaballo> getTodosLosElementos() {
        return todosLosElementos;
    }

    /**
     * Retorna el siguiente elemento. Devuelve NULL si no existe siguiente elemento.
     * @param elemento
     * @return
     */
    public static ElementosCaballo getSiguienteElemento(ElementosCaballo elemento) {
        for (ElementosCaballo unElemento : ElementosCaballo.values()) {
            if (elemento.getOrden() + 1 == unElemento.getOrden()) {
                return unElemento;
            }
        }
        return null;
    }
}
