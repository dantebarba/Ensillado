package ar.edu.unlp.laboratorio.ensillado.activity;


import android.os.Bundle;
import android.preference.PreferenceFragment;

import ar.edu.unlp.laboratorio.ar.laboratorio.R;


public class MyPreferencesFragment extends PreferenceFragment{

    @Override
    public void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
}