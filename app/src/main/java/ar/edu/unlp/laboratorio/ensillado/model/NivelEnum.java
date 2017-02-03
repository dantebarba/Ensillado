package ar.edu.unlp.laboratorio.ensillado.model;

/**
 * Created by CM690II on 30/01/2017.
 */
public enum NivelEnum {
    FACIL(1), MEDIO(2), AVANZADO(4), EXPERTO(6);

    private final int value;

    NivelEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}
