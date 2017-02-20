package ar.edu.unlp.laboratorio.ensillado.modelView;

/**
 * Created by CM690II on 03/02/2017.
 */

import ar.edu.unlp.laboratorio.ar.laboratorio.R;
import ar.edu.unlp.laboratorio.ensillado.factory.GameFactory;
import ar.edu.unlp.laboratorio.ensillado.model.AudioSet;
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
    public ImagenYTexto bindedResource;
    public ElementoCaballo elementoActual;

    public ElementoCaballoModelView(ImagenYTexto viewById) {
        if (viewById != null) {
            this.bindedResource = viewById;
            this.bindedResource.imagen.setImageResource(android.R.drawable
                    .screen_background_light_transparent);
            this.bindedResource.texto.setText("");
            this.bindedResource.imagen.setEnabled(false);
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
            this.bindedResource.imagen.setEnabled(true);
            this.setElementoActual(elemento);
        }
    }

    private void setImageResource(ElementoCaballo elemento) {
        switch (elemento) {

            case NINGUNO:
                bindedResource.imagen.setImageResource(android.R.drawable
                        .screen_background_light_transparent);
                bindedResource.texto.setText(ElementoCaballo.NINGUNO.toString());
                break;
            case CABEZADA:
                bindedResource.imagen.setImageResource(R.drawable.cabezada);
                bindedResource.texto.setText(ElementoCaballo.CABEZADA.toString());
                break;
            case BOZAL:
                bindedResource.imagen.setImageResource(R.drawable.bozal);
                bindedResource.texto.setText(ElementoCaballo.BOZAL.toString());
                break;
            case SUDADERA:
                bindedResource.imagen.setImageResource(R.drawable.sudadera);
                bindedResource.texto.setText(ElementoCaballo.SUDADERA.toString());
                break;
            case MATRA:
                bindedResource.imagen.setImageResource(R.drawable.matra);
                bindedResource.texto.setText(ElementoCaballo.MATRA.toString());
                break;
            case BAJO_MONTURA:
                bindedResource.imagen.setImageResource(R.drawable.bajomontura);
                bindedResource.texto.setText(ElementoCaballo.BAJO_MONTURA.toString());
                break;
            case MONTURA_ESTRIBOS:
                bindedResource.imagen.setImageResource(R.drawable.montura);
                bindedResource.texto.setText(ElementoCaballo.MONTURA_ESTRIBOS.toString());
                break;
        }
    }

    public void setElementoActual(ElementoCaballo elementoActual) {
        this.elementoActual = elementoActual;
    }

    public ElementoCaballo getElementoActual() {
        return elementoActual;
    }

    public int getAudioResource() {
        switch (elementoActual) {

            case NINGUNO:
                return -1;
            case CABEZADA:
                if (GameFactory.getInstance().getConfiguracion().voz.equals(AudioSet.MASCULINO)) {
                    return R.raw.cabezadamasc;
                }
                return R.raw.cabezadafem;
            case BOZAL:
                if (GameFactory.getInstance().getConfiguracion().voz.equals(AudioSet.MASCULINO)) {
                    return R.raw.bozalmasc;
                }
                return R.raw.bozalfem;
            case SUDADERA:
                if (GameFactory.getInstance().getConfiguracion().voz.equals(AudioSet.MASCULINO)) {
                    return R.raw.sudaderamasc;
                }
                return R.raw.sudaderafem;
            case MATRA:
                if (GameFactory.getInstance().getConfiguracion().voz.equals(AudioSet.MASCULINO)) {
                    return R.raw.matramasc;
                }
                return R.raw.matrafem;
            case BAJO_MONTURA:
                if (GameFactory.getInstance().getConfiguracion().voz.equals(AudioSet.MASCULINO)) {
                    return R.raw.bajomonturamasc;
                }
                return R.raw.bajomonturafem;
            case MONTURA_ESTRIBOS:
                if (GameFactory.getInstance().getConfiguracion().voz.equals(AudioSet.MASCULINO)) {
                    return R.raw.monturamasc;
                }
                return R.raw.monturafem;
        }
        return -1;
    }
}
