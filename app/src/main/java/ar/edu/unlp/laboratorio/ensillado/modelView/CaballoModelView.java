package ar.edu.unlp.laboratorio.ensillado.modelView;

import android.widget.ImageView;

import ar.edu.unlp.laboratorio.ar.laboratorio.R;

/**
 * Created by CM690II on 03/02/2017.
 */

public class CaballoModelView {

    public static int caballoNinguno = R.drawable.ninguno;
    public static int caballoBozal = R.drawable.caballobozal;
    public static int caballoMontura = R.drawable.caballomontura;
    public static int caballoBajoMontura = R.drawable.caballobajomontura;
    public static int caballoCabezada = R.drawable.caballocabezada;
    public static int caballoMatra = R.drawable.caballomatra;
    public static int caballoSudadera = R.drawable.caballosudadera;
    ImageView bindedImageResource;

    public CaballoModelView(ImageView image) {
        this.bindedImageResource = image;
    }

    public void render(Renderizable renderObject) {
        renderObject.render();
    }

    public void bind(int image) {
        if (this.bindedImageResource != null) {
            this.bindedImageResource.setImageResource(image);
        }
    }
}
