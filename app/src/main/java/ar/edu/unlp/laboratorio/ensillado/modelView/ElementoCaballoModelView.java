package ar.edu.unlp.laboratorio.ensillado.modelView;

/**
 * Created by CM690II on 03/02/2017.
 */

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
    public ImageView bindedResource;
    public ElementoCaballo elementoActual;

    public ElementoCaballoModelView(ImageView viewById) {
        if (viewById != null) {
            this.bindedResource = viewById;
            this.bindedResource.setImageResource(android.R.drawable
                    .screen_background_light_transparent);
            this.bindedResource.setEnabled(false);
            this.elementoActual = ElementoCaballo.NINGUNO;
        }
    }


    public void render(Renderizable renderObject) {
        renderObject.render();
    }

    /**
     * Asocia el recurso gráfico con el recurso lógico. En este
     * caso asocia una ImageView con un ElementoCaballo.
     *
     * @param elemento
     */
    public void bind(ElementoCaballo elemento) {
        if (bindedResource != null && elemento != null) {
            this.setImageResource(elemento);
            this.bindedResource.setEnabled(true);
            this.setElementoActual(elemento);
        }
    }

    private void setImageResource(ElementoCaballo elemento) {
        switch (elemento) {

            case NINGUNO:
                bindedResource.setImageResource(android.R.drawable
                        .screen_background_light_transparent);
                break;
            case CABEZADA:
                bindedResource.setImageResource(R.drawable.cabezada);
                break;
            case BOZAL:
                bindedResource.setImageResource(R.drawable.bozal);
                break;
            case SUDADERA:
                bindedResource.setImageResource(R.drawable.sudadera);
                break;
            case MATRA:
                bindedResource.setImageResource(R.drawable.matra);
                break;
            case BAJO_MONTURA:
                bindedResource.setImageResource(R.drawable.bajomontura);
                break;
            case MONTURA_ESTRIBOS:
                bindedResource.setImageResource(R.drawable.montura);
                break;
        }
    }

    public void setElementoActual(ElementoCaballo elementoActual) {
        this.elementoActual = elementoActual;
    }

    public ElementoCaballo getElementoActual() {
        return elementoActual;
    }
}
