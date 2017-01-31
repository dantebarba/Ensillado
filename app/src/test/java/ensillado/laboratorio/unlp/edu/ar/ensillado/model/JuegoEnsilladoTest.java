package ensillado.laboratorio.unlp.edu.ar.ensillado.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

/**
 * Created by CM690II on 30/01/2017.
 */
public class JuegoEnsilladoTest {

    @Before
    public void setUp() {

    }

    @Test
    public void probarJuego() {
        JuegoEnsillado juego = new JuegoEnsillado(new Nivel(NivelEnum.FACIL));
        juego.comenzar();
        Set<ElementoCaballo> misElementos = juego.mostrarElementos();
        RespuestaIntentoEnsillado respuesta = juego.ensillar(ElementoCaballo.BOZAL);
        System.out.println(respuesta.toString());
        // ESTO DEBERIA PRINTEAR OK.
    }

}