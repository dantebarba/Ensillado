package ar.edu.unlp.laboratorio.ensillado.model;

/**
 * Created by CM690II on 30/01/2017.
 */
public enum NivelEnum {
    FACIL(1), MEDIO(2), AVANZADO(4), EXPERTO(6);

    private final int value;
    private static NivelEnum[] vals = values();

    NivelEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }


    public NivelEnum siguienteNivel() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public static NivelEnum fromString(String string) {
        if (string != null) {
            for (NivelEnum est : NivelEnum
                    .values()) {
                if (est.toString().equals(string)) {
                    return est;
                }
            }
        }
        return null;
    }

}
