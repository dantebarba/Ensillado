package ar.edu.unlp.laboratorio.ensillado.modelView;

/**
 * Created by CM690II on 03/02/2017.
 */

import android.icu.util.RangeValueIterator;
import android.widget.ImageView;

import ar.edu.unlp.laboratorio.ar.laboratorio.R;
import ar.edu.unlp.laboratorio.ensillado.model.ElementoCaballo;

/**
 * Contiene el sonido y la imagen.
 */
public class ElementoCaballoModelView {

    public static int MATRA = R.drawable.matra;
    public static int CABEZADA = R.drawable.cabezada;
    public static int BAJO_MONTURA = R.drawable.bajomontura;
    public static int BOZAL = R.drawable.bozal;
    public static int MONTURA = R.drawable.montura;
    public static int SUDADERA = R.drawable.sudadera;

    public ImageView imagenActual;
    public ElementoCaballo elementoActual;


    public void render(Renderizable renderObject) {
        renderObject.render();
    }

    public void bind(int elemento) {
        if (imagenActual != null) {
            imagenActual.setImageResource(elemento);
        }
    }

    public void setElementoActual(ElementoCaballo elementoActual) {
        this.elementoActual = elementoActual;
    }
}
