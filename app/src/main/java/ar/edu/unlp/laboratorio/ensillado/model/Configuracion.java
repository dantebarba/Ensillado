package ar.edu.unlp.laboratorio.ensillado.model;

/**
 * Created by CM690II on 30/01/2017.
 */
public class Configuracion {

    public static final String PREFS_KEY = "jsonObject";
    public NivelEnum nivelDeJuego;
    public String nombreJugador;
    public AudioSet voz;

    public EstadoInicial estadoInicial;

    public static String PREFS_NAME = "preferencias.dat";

    public static Configuracion getDefaultConfiguration() {
        Configuracion configuracion = new Configuracion();
        configuracion.nivelDeJuego = NivelEnum.EXPERTO;
        configuracion.nombreJugador = "NuevoJugador";
        configuracion.estadoInicial = EstadoInicial.DESNUDO;
        configuracion.voz = AudioSet.FEMENINO;
        return configuracion;
    }
}
