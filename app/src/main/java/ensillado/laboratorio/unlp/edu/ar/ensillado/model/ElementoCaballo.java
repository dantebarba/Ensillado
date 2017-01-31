package ensillado.laboratorio.unlp.edu.ar.ensillado.model;

import java.util.Arrays;
import java.util.EnumSet;

/**
 * Created by CM690II on 30/01/2017.
 */
public enum ElementoCaballo {
    NINGUNO(0), CABEZADA(1), BOZAL(2), SUDADERA(3), MATRA(4), BAJO_MONTURA(5), MONTURA_ESTRIBOS(6);

    static EnumSet<ElementoCaballo> todosLosElementos = EnumSet.copyOf(Arrays.asList(values()));
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

    public static EnumSet<ElementoCaballo> getTodosLosElementos() {
        return todosLosElementos;
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
}
