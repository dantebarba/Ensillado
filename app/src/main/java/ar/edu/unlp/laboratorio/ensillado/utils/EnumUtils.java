package ar.edu.unlp.laboratorio.ensillado.utils;

import ar.edu.unlp.laboratorio.ensillado.model.NivelEnum;

/**
 * Created by dbarba on 10/02/17.
 */

public class EnumUtils {

    public static CharSequence[] valuesAsString(Class<?> anEnum) {
        CharSequence[] sequence = new CharSequence[anEnum.getEnumConstants().length];
        for (int i = 0; i < anEnum.getEnumConstants().length; i++) {
            sequence[i] = anEnum.getEnumConstants()[i].toString();
        }
        return sequence;
    }


}
