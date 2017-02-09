package ar.edu.unlp.laboratorio.ensillado.factory;

import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.edu.unlp.laboratorio.ensillado.model.Configuracion;
import ar.edu.unlp.laboratorio.ensillado.model.JuegoEnsillado;
import gov.senasa.autenticacion.webservices.FalloElServicioException_Exception;
import gov.senasa.autenticacion.webservices.LoginConUsuarioYPassword;
import gov.senasa.autenticacion.webservices.RespuestaLogin;
import gov.senasa.autenticacion.webservices.UsuarioPasswordInvalidoException_Exception;
import gov.senasa.autenticacion.webservices.provider.ServicioAutenticacionProvider;

/**
 * Created by CM690II on 30/01/2017.
 */

public class GameFactory {

    private static JuegoEnsillado instance;

    public static void newInstance(String config) {
        Configuracion configuracionNueva = Configuracion.getDefaultConfiguration();
        if (config != null) {
            configuracionNueva = new Gson().fromJson(config, Configuracion.class);
        }
        GameFactory.instance = new JuegoEnsillado(configuracionNueva);
    }

    public static void actualizarConfiguracion(String json) {
        Configuracion configuracionNueva = new Gson().fromJson(json, Configuracion.class);
        getInstance().setConfiguracionActual(configuracionNueva);
    }

    public static String guardarConfiguracion() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(getInstance().getConfiguracion());
    }

    public static JuegoEnsillado getInstance() {
        if (GameFactory.instance != null) {
            return GameFactory.instance;
        }
        throw new IllegalStateException("La instancia no fue creada. Cree la instancia primero.");
    }
}
