package ensillado.laboratorio.unlp.edu.ar.ensillado.factory;

import ensillado.laboratorio.unlp.edu.ar.ensillado.model.Configuracion;
import ensillado.laboratorio.unlp.edu.ar.ensillado.model.JuegoEnsillado;

/**
 * Created by CM690II on 30/01/2017.
 */

public class GameFactory {

    private static JuegoEnsillado instance;

    public static void newInstance(Configuracion config) {
        GameFactory.instance = new JuegoEnsillado(config.nivelDeJuego);
    }

    public static JuegoEnsillado getInstance() {
        return instance;
    }
}
