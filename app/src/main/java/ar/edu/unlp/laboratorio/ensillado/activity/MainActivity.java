package ar.edu.unlp.laboratorio.ensillado.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ar.edu.unlp.laboratorio.ar.laboratorio.R;
import ar.edu.unlp.laboratorio.ensillado.factory.GameFactory;
import ar.edu.unlp.laboratorio.ensillado.model.AudioSet;
import ar.edu.unlp.laboratorio.ensillado.model.Configuracion;
import ar.edu.unlp.laboratorio.ensillado.model.ElementoCaballo;
import ar.edu.unlp.laboratorio.ensillado.model.EstadoInicial;
import ar.edu.unlp.laboratorio.ensillado.model.JuegoEnsillado;
import ar.edu.unlp.laboratorio.ensillado.model.NivelEnum;
import ar.edu.unlp.laboratorio.ensillado.model.RespuestaIntentoEnsillado;
import ar.edu.unlp.laboratorio.ensillado.modelView.CaballoModelView;
import ar.edu.unlp.laboratorio.ensillado.modelView.ElementosMostradosModelView;
import ar.edu.unlp.laboratorio.ensillado.modelView.ImagenYTexto;
import ar.edu.unlp.laboratorio.ensillado.modelView.Renderizable;

public class MainActivity extends AppCompatActivity implements SharedPreferences
        .OnSharedPreferenceChangeListener {

    CaballoModelView caballoModelView;
    ElementosMostradosModelView elementoMostradoView;
    MediaPlayer myPlayer = new MediaPlayer();
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        cargarConfiguracion();
        GameFactory.getInstance().comenzar();
        this.caballoModelView = new CaballoModelView(this, (ImageView) findViewById(R.id
                .imagen_caballo));
        this.elementoMostradoView = ElementosMostradosModelView.nuevaPantallaDeElementos(this,
                getElementosMostradosImageView());
        actualizarCaballo(GameFactory.getInstance());
        actualizarElementosMostrados(GameFactory.getInstance());
    }

    private void cargarConfiguracion() {
        this.prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
        Configuracion configuracion = Configuracion.getDefaultConfiguration();
        if (prefs != null) {
            configuracion.nivelDeJuego = NivelEnum.fromString(prefs.getString("nivel", NivelEnum.FACIL.toString()));
            configuracion.estadoInicial = EstadoInicial.fromString(prefs.getString
                    ("estado_inicial", EstadoInicial.DESNUDO.toString()));
            configuracion.voz = AudioSet.fromString(prefs.getString("voz", AudioSet.MASCULINO.toString()));
        }
        GameFactory.newInstance(configuracion);
        setTitle("Ensillado: Nivel " + configuracion.nivelDeJuego);
    }

    private List<ImagenYTexto> getElementosMostradosImageView() {
        List<ImagenYTexto> elementosMostrados = new ArrayList<ImagenYTexto>();
        ImagenYTexto elementoMostrado = new ImagenYTexto((ImageView) findViewById(R.id
                .elemento_mostrado), (TextView) findViewById(R.id.texto_elemento));
        elementosMostrados.add(elementoMostrado);
        elementoMostrado = new ImagenYTexto((ImageView) findViewById(R.id.elemento_mostrado2),
                (TextView) findViewById(R.id.texto_elemento2));
        elementosMostrados.add(elementoMostrado);

        elementoMostrado = new ImagenYTexto((ImageView) findViewById(R.id.elemento_mostrado3),
                (TextView) findViewById(R.id.texto_elemento3));
        elementosMostrados.add(elementoMostrado);

        elementoMostrado = new ImagenYTexto((ImageView) findViewById(R.id.elemento_mostrado4),
                (TextView) findViewById(R.id.texto_elemento4));
        elementosMostrados.add(elementoMostrado);

        elementoMostrado = new ImagenYTexto((ImageView) findViewById(R.id.elemento_mostrado5),
                (TextView) findViewById(R.id.texto_elemento5));
        elementosMostrados.add(elementoMostrado);

        elementoMostrado = new ImagenYTexto((ImageView) findViewById(R.id.elemento_mostrado6),
                (TextView) findViewById(R.id.texto_elemento6));
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
        }
        if (respuesta.equals(RespuestaIntentoEnsillado.ELEMENTO_PRESENTE)) {
            tocarAudio(R.raw.resoplido);
            mostrarMensajeInformativo("El elemento ingresado ya se encuentra presente en el " +
                    "caballo.");
        }
        if (respuesta.equals(RespuestaIntentoEnsillado.FINALIZADO)) {
            tocarAudio(R.raw.relincho);
            mostrarMensajeFinalizado("Felicitaciones!, ha ganado!. Para volver a jugar haga click" +
                    " en " +
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
        this.cargarConfiguracion();
        GameFactory.getInstance().reiniciar();
        GameFactory.getInstance().comenzar();
        this.actualizarCaballo(GameFactory.getInstance());
        this.actualizarElementosMostrados(GameFactory.getInstance());
    }

    public void reiniciarJuegoSinCargarConfiguracion() {
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
            Intent i = new Intent(this, PreferencesActivity.class);
            startActivity(i);
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
                        GameFactory.getInstance().getConfiguracion().nivelDeJuego = GameFactory
                                .getInstance().getConfiguracion().nivelDeJuego.siguienteNivel();
                        reiniciarJuegoSinCargarConfiguracion();
                        setTitle("Ensillado: Nivel " + GameFactory.getInstance().getConfiguracion
                                ().nivelDeJuego);
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

            myPlayer.reset();
            myPlayer.setDataSource(this, Uri.parse("android.resource://ar.edu.unlp" +
                    ".laboratorio.ensillado/" + audioResource));
            myPlayer.prepare();
            myPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        GameFactory.getInstance().finalizar();
        reiniciarJuego();
    }
}
