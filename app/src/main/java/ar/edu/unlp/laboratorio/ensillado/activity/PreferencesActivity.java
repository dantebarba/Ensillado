package ar.edu.unlp.laboratorio.ensillado.activity;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

import ar.edu.unlp.laboratorio.ensillado.model.AudioSet;
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

        ListPreference listaNiveles = new ListPreference(this);
        listaNiveles.setKey("nivel");
        listaNiveles.setTitle("Seleccione un nivel");
        listaNiveles.setEntries(EnumUtils.valuesAsString(NivelEnum.class));
        listaNiveles.setEntryValues(EnumUtils.valuesAsString(NivelEnum.class));
        categoryNivel.addPreference(listaNiveles);

        PreferenceCategory categoryVoz = new PreferenceCategory(this);
        categoryVoz.setTitle("Voz");
        screen.addPreference(categoryVoz);

        ListPreference listaVoz = new ListPreference(this);
        listaVoz.setKey("voz");
        listaVoz.setTitle("Seleccione un tipo de voz");
        listaVoz.setEntries(EnumUtils.valuesAsString(AudioSet.class));
        listaVoz.setEntryValues(EnumUtils.valuesAsString(AudioSet.class));
        categoryVoz.addPreference(listaVoz);

        PreferenceCategory categoryEstadoInicial = new PreferenceCategory(this);
        categoryEstadoInicial.setTitle("Estado inicial del caballo");
        screen.addPreference(categoryEstadoInicial);

        ListPreference listEstadoInicial = new ListPreference(this);
        listEstadoInicial.setKey("estado_inicial");
        listEstadoInicial.setTitle("Seleccione un estado");
        listEstadoInicial.setEntries(EnumUtils.valuesAsString(EstadoInicial.class));
        listEstadoInicial.setEntryValues(EnumUtils.valuesAsString(EstadoInicial.class));
        categoryEstadoInicial.addPreference(listEstadoInicial);

        setPreferenceScreen(screen);
    }


}


