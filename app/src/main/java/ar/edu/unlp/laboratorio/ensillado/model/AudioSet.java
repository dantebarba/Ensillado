package ar.edu.unlp.laboratorio.ensillado.model;

/**
 * Created by CM690II on 02/02/2017.
 */
public enum AudioSet {
    FEMENINO, MASCULINO;


    public static AudioSet fromString(String string) {
        if (string != null) {
            for (AudioSet est : AudioSet
                    .values()) {
                if (est.toString().equals(string)) {
                    return est;
                }
            }
        }
        return null;
    }

    public String toString() {
        switch (this) {

            case FEMENINO:
                return "Femenino";
            case MASCULINO:
                return "Masculino";

        }
        return "Ninguno";
    }

}
