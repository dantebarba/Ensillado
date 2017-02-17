package ar.edu.unlp.laboratorio.ensillado.activity;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

import ar.edu.unlp.laboratorio.ensillado.factory.GameFactory;
import ar.edu.unlp.laboratorio.ensillado.model.AudioSet;
import ar.edu.unlp.laboratorio.ensillado.model.Configuracion;
import ar.edu.unlp.laboratorio.ensillado.model.EstadoInicial;
import ar.edu.unlp.laboratorio.ensillado.model.NivelEnum;
import ar.edu.unlp.laboratorio.ensillado.utils.EnumUtils;


public class PreferencesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferencesFragment()).commit();

        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(this);

        PreferenceCategory categoryNivel = new PreferenceCategory(this);
        categoryNivel.setTitle("Nivel");
        screen.addPreference(categoryNivel);

        final ListPreference listaNiveles = new ListPreference(this);
        listaNiveles.setKey("nivel");
        listaNiveles.setTitle("Seleccione un nivel: " + GameFactory.getInstance().getConfiguracion().nivelDeJuego);
        listaNiveles.setEntries(EnumUtils.valuesAsString(NivelEnum.class));
        listaNiveles.setEntryValues(EnumUtils.valuesAsString(NivelEnum.class));
        listaNiveles.setDefaultValue(Configuracion.getDefaultConfiguration().nivelDeJuego.toString());
        categoryNivel.addPreference(listaNiveles);
        listaNiveles.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                listaNiveles.setTitle("Seleccione un nivel: " + newValue.toString());
                return true;
            }
        });


        PreferenceCategory categoryVoz = new PreferenceCategory(this);
        categoryVoz.setTitle("Voz");
        screen.addPreference(categoryVoz);

        final ListPreference listaVoz = new ListPreference(this);
        listaVoz.setKey("voz");
        listaVoz.setTitle("Seleccione un tipo de voz: " + GameFactory.getInstance().getConfiguracion().voz);
        listaVoz.setEntries(EnumUtils.valuesAsString(AudioSet.class));
        listaVoz.setEntryValues(EnumUtils.valuesAsString(AudioSet.class));
        listaVoz.setDefaultValue(Configuracion.getDefaultConfiguration().voz.toString());
        categoryVoz.addPreference(listaVoz);
        listaVoz.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                listaVoz.setTitle("Seleccione un tipo de voz: " + newValue.toString());
                return true;
            }
        });

        PreferenceCategory categoryEstadoInicial = new PreferenceCategory(this);
        categoryEstadoInicial.setTitle("Estado inicial del caballo");
        screen.addPreference(categoryEstadoInicial);

        final ListPreference listEstadoInicial = new ListPreference(this);
        listEstadoInicial.setKey("estado_inicial");
        listEstadoInicial.setTitle("Seleccione un estado: " + GameFactory.getInstance().getConfiguracion().estadoInicial);
        listEstadoInicial.setEntries(EnumUtils.valuesAsString(EstadoInicial.class));
        listEstadoInicial.setDefaultValue(Configuracion.getDefaultConfiguration().estadoInicial.toString());
        listEstadoInicial.setEntryValues(EnumUtils.valuesAsString(EstadoInicial.class));
        categoryEstadoInicial.addPreference(listEstadoInicial);
        listEstadoInicial.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                listEstadoInicial.setTitle("Seleccione un estado: " + newValue.toString());
                return true;
            }
        });

        setPreferenceScreen(screen);
    }


}


