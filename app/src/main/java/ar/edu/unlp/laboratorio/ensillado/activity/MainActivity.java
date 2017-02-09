package ar.edu.unlp.laboratorio.ensillado.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.Set;

import ar.edu.unlp.laboratorio.ar.laboratorio.R;
import ar.edu.unlp.laboratorio.ensillado.factory.GameFactory;
import ar.edu.unlp.laboratorio.ensillado.model.Configuracion;
import ar.edu.unlp.laboratorio.ensillado.model.ElementoCaballo;
import ar.edu.unlp.laboratorio.ensillado.model.JuegoEnsillado;
import ar.edu.unlp.laboratorio.ensillado.model.RespuestaIntentoEnsillado;
import ar.edu.unlp.laboratorio.ensillado.modelView.CaballoModelView;
import ar.edu.unlp.laboratorio.ensillado.modelView.ElementoCaballoModelView;
import ar.edu.unlp.laboratorio.ensillado.modelView.Renderizable;

public class MainActivity extends AppCompatActivity {

    CaballoModelView caballoModelView;
    ElementoCaballoModelView elementoMostradoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        this.inicializarJuego();
    }

    private void inicializarJuego() {
        SharedPreferences settings = getSharedPreferences(Configuracion.PREFS_NAME, 0);
        String configuracionJson = settings.getString(Configuracion.PREFS_KEY, null);
        GameFactory.newInstance(configuracionJson);
        GameFactory.getInstance().comenzar();
        this.caballoModelView = new CaballoModelView((ImageView) findViewById(R.id
                .imagen_caballo));
        ImageView elementoMostradoImagen = (ImageView) findViewById(R.id
                .elemento_mostrado);
        this.elementoMostradoView = new ElementoCaballoModelView(elementoMostradoImagen);
        elementoMostradoImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RespuestaIntentoEnsillado respuesta = GameFactory.getInstance().ensillar
                        (elementoMostradoView.elementoActual);
                procesarRespuestaDeJuego(respuesta);

            }
        });

        actualizarCaballo(GameFactory.getInstance());
        actualizarElementosMostrados(GameFactory.getInstance());
    }

    private void procesarRespuestaDeJuego(RespuestaIntentoEnsillado respuesta) {
        if (respuesta.equals(RespuestaIntentoEnsillado.OK)) {
            actualizarElementosMostrados(GameFactory.getInstance());
            actualizarCaballo(GameFactory.getInstance());
        }
        if (respuesta.equals(RespuestaIntentoEnsillado.ELEMENTO_INCORRECTO)) {
            mostrarMensaje("El elemento ingresado no es correcto. Intente nuevamente.");
        }
        if (respuesta.equals(RespuestaIntentoEnsillado.ELEMENTO_PRESENTE)) {
            mostrarMensaje("El elemento ingresado ya se encuentra presente en el caballo.");
        }
        if (respuesta.equals(RespuestaIntentoEnsillado.FINALIZADO)) {
            mostrarMensaje("Used ya ha ganado!. Para volver a jugar reinicie el juego.");
            actualizarCaballo(GameFactory.getInstance());
        }
    }

    public void actualizarElementosMostrados(JuegoEnsillado juego) {
        Set<ElementoCaballo> elementos = juego.mostrarElementos();
        for (ElementoCaballo elemento : elementos) {
            innerRender(elemento);
        }
    }

    public void reiniciarJuego() {
        GameFactory.getInstance().reiniciar();
        GameFactory.getInstance().comenzar();
        this.actualizarCaballo(GameFactory.getInstance());
        this.actualizarElementosMostrados(GameFactory.getInstance());
    }

    public void innerRender(final ElementoCaballo elemento) {
        elementoMostradoView.render(new Renderizable() {
            @Override
            public void render() {
                switch (elemento) {
                    case NINGUNO:
                        elementoMostradoView.bind(CaballoModelView.caballoNinguno);
                        elementoMostradoView.setElementoActual(ElementoCaballo.NINGUNO);
                        break;
                    case CABEZADA:
                        elementoMostradoView.bind(ElementoCaballoModelView.CABEZADA);
                        elementoMostradoView.setElementoActual(ElementoCaballo.CABEZADA);
                        break;
                    case BOZAL:
                        elementoMostradoView.bind(ElementoCaballoModelView.BOZAL);
                        elementoMostradoView.setElementoActual(ElementoCaballo.BOZAL);
                        break;
                    case SUDADERA:
                        elementoMostradoView.bind(ElementoCaballoModelView.SUDADERA);
                        elementoMostradoView.setElementoActual(ElementoCaballo.SUDADERA);
                        break;
                    case MATRA:
                        elementoMostradoView.bind(ElementoCaballoModelView.MATRA);
                        elementoMostradoView.setElementoActual(ElementoCaballo.MATRA);
                        break;
                    case BAJO_MONTURA:
                        elementoMostradoView.bind(ElementoCaballoModelView.BAJO_MONTURA);
                        elementoMostradoView.setElementoActual(ElementoCaballo.BAJO_MONTURA);
                        break;
                    case MONTURA_ESTRIBOS:
                        elementoMostradoView.bind(ElementoCaballoModelView.MONTURA);
                        elementoMostradoView.setElementoActual(ElementoCaballo.MONTURA_ESTRIBOS);
                        break;
                }
            }
        });
    }

    private void actualizarCaballo(final JuegoEnsillado juego) {

        this.caballoModelView.render(new Renderizable() {
            @Override
            public void render() {
                ElementoCaballo elementoPresente = juego.getElementoPresenteMayorOrden();
                switch (elementoPresente) {

                    case NINGUNO:
                        caballoModelView.bind(CaballoModelView.caballoNinguno);
                        break;
                    case CABEZADA:
                        caballoModelView.bind(CaballoModelView.caballoCabezada);
                        break;
                    case BOZAL:
                        caballoModelView.bind(CaballoModelView.caballoBozal);
                        break;
                    case SUDADERA:
                        caballoModelView.bind(CaballoModelView.caballoSudadera);
                        break;
                    case MATRA:
                        caballoModelView.bind(CaballoModelView.caballoMatra);
                        break;
                    case BAJO_MONTURA:
                        caballoModelView.bind(CaballoModelView.caballoBajoMontura);
                        break;
                    case MONTURA_ESTRIBOS:
                        caballoModelView.bind(CaballoModelView.caballoMontura);
                        break;
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void mostrarMensaje(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Mensaje del juego");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ACEPTAR",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "REINICIAR", new DialogInterface
                .OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reiniciarJuego();
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

}
