package ar.edu.unlp.laboratorio.ensillado.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by CM690II on 30/01/2017.
 */
public class JuegoEnsilladoTest {

    Configuracion configuracion = new Configuracion();


    @Before
    public void setUp() {

    }

    @Test
    public void probarJuego() {
        configuracion.estadoInicial = EstadoInicial.CON_ELEMENTOS;
        configuracion.nivelDeJuego = NivelEnum.FACIL;
        JuegoEnsillado juego = new JuegoEnsillado(configuracion);
        juego.comenzar();
        Set<ElementoCaballo> misElementos = juego.mostrarElementos();
        RespuestaIntentoEnsillado respuesta = juego.ensillar(this.ordenarElementos(misElementos));
        misElementos = juego.mostrarElementos();
        respuesta = juego.ensillar(this.ordenarElementos(misElementos));
        misElementos = juego.mostrarElementos();
        respuesta = juego.ensillar(this.ordenarElementos(misElementos));
        misElementos = juego.mostrarElementos();
        respuesta = juego.ensillar(this.ordenarElementos(misElementos));
        misElementos = juego.mostrarElementos();
        respuesta = juego.ensillar(this.ordenarElementos(misElementos));
        misElementos = juego.mostrarElementos();
        respuesta = juego.ensillar(this.ordenarElementos(misElementos));
        misElementos = juego.mostrarElementos();
        respuesta = juego.ensillar(this.ordenarElementos(misElementos));
        System.out.println(respuesta.toString());

        // ESTO DEBERIA PRINTEAR FINALIZADO.
    }

    /*
    Esto se llama hacer trampa :)
     */
    private ElementoCaballo ordenarElementos(Set<ElementoCaballo> misElementos) {
        List<ElementoCaballo> elementos = new ArrayList<ElementoCaballo>(misElementos);
        Collections.sort(elementos, new Comparator<ElementoCaballo>() {
            @Override
            public int compare(ElementoCaballo o1, ElementoCaballo o2) {
                return new Integer(o1.getOrden()).compareTo(o2.getOrden());
            }
        });
        if (!elementos.isEmpty()) {
            return elementos.get(0);
        }
        return ElementoCaballo.NINGUNO;
    }

}