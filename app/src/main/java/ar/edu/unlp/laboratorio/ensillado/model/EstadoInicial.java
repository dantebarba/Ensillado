package ar.edu.unlp.laboratorio.ensillado.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by CM690II on 01/02/2017.
 */
public enum EstadoInicial {
    DESNUDO, CON_ELEMENTOS;

    public List<ElementoCaballo> getEstadosCaballoIniciales() {
        if (this == CON_ELEMENTOS) {
            return new ArrayList<ElementoCaballo>(Arrays.asList(ElementoCaballo.NINGUNO,
                    ElementoCaballo.BOZAL, ElementoCaballo.CABEZADA));
        }
        return new ArrayList<ElementoCaballo>(Arrays.asList(ElementoCaballo.NINGUNO));

    }

    /**
     * Retorna la cantidad de elementos iniciales para establecer el nivel correctamente.
     * Se resta un elemento para compensar el elemento null-object ninguno.
     *
     * @return La cantidad de elementos iniciales presentes en el caballo. También puede obtenerse
     * el mismo valor con {@link EstadoCaballo#getElementosPresentes()} - 1
     */
    public Integer getCantidadElementosIniciales() {
        return this.getEstadosCaballoIniciales().size() - 1;
    }

    public static EstadoInicial fromString(String string) {
        if (string != null) {
            for (EstadoInicial est : EstadoInicial
                    .values()) {
                if (est.toString().equals(string)) {
                    return est;
                }
            }
        }
        return null;
    }
}
