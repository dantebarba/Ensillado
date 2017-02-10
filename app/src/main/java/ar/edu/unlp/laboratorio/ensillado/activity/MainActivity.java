package ar.edu.unlp.laboratorio.ensillado.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ar.edu.unlp.laboratorio.ar.laboratorio.R;
import ar.edu.unlp.laboratorio.ensillado.factory.GameFactory;
import ar.edu.unlp.laboratorio.ensillado.model.Configuracion;
import ar.edu.unlp.laboratorio.ensillado.model.ElementoCaballo;
import ar.edu.unlp.laboratorio.ensillado.model.JuegoEnsillado;
import ar.edu.unlp.laboratorio.ensillado.model.RespuestaIntentoEnsillado;
import ar.edu.unlp.laboratorio.ensillado.modelView.CaballoModelView;
import ar.edu.unlp.laboratorio.ensillado.modelView.ElementosMostradosModelView;
import ar.edu.unlp.laboratorio.ensillado.modelView.Renderizable;

public class MainActivity extends AppCompatActivity {

    CaballoModelView caballoModelView;
    ElementosMostradosModelView elementoMostradoView;
    MediaPlayer myPlayer = new MediaPlayer();

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
        myPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        myPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
            }
        });
        this.inicializarJuego();
    }

    private void inicializarJuego() {
        SharedPreferences settings = getSharedPreferences(Configuracion.PREFS_NAME, 0);
        String configuracionJson = settings.getString(Configuracion.PREFS_KEY, null);
        GameFactory.newInstance(configuracionJson);
        GameFactory.getInstance().comenzar();
        this.caballoModelView = new CaballoModelView(this, (ImageView) findViewById(R.id
                .imagen_caballo));
        this.elementoMostradoView = ElementosMostradosModelView.nuevaPantallaDeElementos(this,
                getElementosMostradosImageView());
        actualizarCaballo(GameFactory.getInstance());
        actualizarElementosMostrados(GameFactory.getInstance());
    }

    private List<ImageView> getElementosMostradosImageView() {
        List<ImageView> elementosMostrados = new ArrayList<>();
        ImageView elementoMostrado = (ImageView) findViewById(R.id.elemento_mostrado);
        elementosMostrados.add(elementoMostrado);
        elementoMostrado = (ImageView) findViewById(R.id.elemento_mostrado2);
        elementosMostrados.add(elementoMostrado);

        elementoMostrado = (ImageView) findViewById(R.id.elemento_mostrado3);
        elementosMostrados.add(elementoMostrado);

        elementoMostrado = (ImageView) findViewById(R.id.elemento_mostrado4);
        elementosMostrados.add(elementoMostrado);

        elementoMostrado = (ImageView) findViewById(R.id.elemento_mostrado5);
        elementosMostrados.add(elementoMostrado);

        elementoMostrado = (ImageView) findViewById(R.id.elemento_mostrado6);
        elementosMostrados.add(elementoMostrado);

        return elementosMostrados;
    }

    public void procesarRespuestaDeJuego(RespuestaIntentoEnsillado respuesta) {
        if (respuesta.equals(RespuestaIntentoEnsillado.OK)) {
            actualizarElementosMostrados(GameFactory.getInstance());
            actualizarCaballo(GameFactory.getInstance());
        }
        if (respuesta.equals(RespuestaIntentoEnsillado.ELEMENTO_INCORRECTO)) {
            tocarAudio(R.raw.resoplido);
            mostrarMensajeInformativo("El elemento ingresado no es correcto. Intente nuevamente.");
        }
        if (respuesta.equals(RespuestaIntentoEnsillado.ELEMENTO_PRESENTE)) {
            tocarAudio(R.raw.resoplido);
            mostrarMensajeInformativo("El elemento ingresado ya se encuentra presente en el caballo.");
        }
        if (respuesta.equals(RespuestaIntentoEnsillado.FINALIZADO)) {
            tocarAudio(R.raw.relincho);
            mostrarMensajeFinalizado("Felicitaciones!, ha ganado!. Para volver a jugar haga click en " +
                    "reiniciar.");
            actualizarCaballo(GameFactory.getInstance());
        }
    }

    public void actualizarElementosMostrados(JuegoEnsillado juego) {
        elementoMostradoView.reset();
        final Set<ElementoCaballo> elementos = juego.mostrarElementos();
        elementoMostradoView.render(new Renderizable() {
            @Override
            public void render() {
                ElementoCaballo[] elementosArray = elementos.toArray(new
                        ElementoCaballo[elementos.size()]);
                elementoMostradoView.bind(elementosArray);
            }
        });
    }


    public void reiniciarJuego() {
        GameFactory.getInstance().reiniciar();
        GameFactory.getInstance().comenzar();
        this.actualizarCaballo(GameFactory.getInstance());
        this.actualizarElementosMostrados(GameFactory.getInstance());
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

    public void mostrarMensajeFinalizado(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Mensaje del juego");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "SIGUIENTE NIVEL",
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

    public void mostrarMensajeInformativo(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Mensaje del juego");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CONTINUAR",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void vibrar(int miliseconds) {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(miliseconds);
    }

    public void tocarAudio(int audioResource) {
        try {
            // FIXME LA UNICA FORMA QUE ECONTRE PARA HACER
            // QUE REPRODUZCA DOS SONIDOS A LA VEZ CONSECUTIVOS.
            while (myPlayer.isPlaying()) {
            }
            myPlayer.reset();
            myPlayer.setDataSource(this, Uri.parse("android.resource://ar.edu.unlp" +
                    ".laboratorio.ensillado/" + audioResource));
            myPlayer.prepare();
            myPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
