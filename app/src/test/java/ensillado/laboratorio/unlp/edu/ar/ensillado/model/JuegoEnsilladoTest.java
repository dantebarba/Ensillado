package ensillado.laboratorio.unlp.edu.ar.ensillado.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

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
        Set<ElementosCaballo> misElementos = juego.mostrarElementos();
        RespuestaIntentoEnsillado respuesta = juego.ensillar(ElementosCaballo.BOZAL);
        System.out.println(respuesta.toString());
        // ESTO DEBERIA PRINTEAR OK.
    }

}