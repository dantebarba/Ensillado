package ar.edu.unlp.laboratorio.ensillado.modelView;

import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

import ar.edu.unlp.laboratorio.ar.laboratorio.R;
import ar.edu.unlp.laboratorio.ensillado.activity.MainActivity;
import ar.edu.unlp.laboratorio.ensillado.factory.GameFactory;
import ar.edu.unlp.laboratorio.ensillado.model.RespuestaIntentoEnsillado;

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
    public MainActivity context;


    public CaballoModelView(MainActivity context, ImageView image) {
        this.context = context;
        this.bindedImageResource = image;
        this.bindedImageResource.setOnDragListener(new SoltarElementoEnCaballoListener());
        this.bindedImageResource.setOnClickListener(new SoltarElementoClickeadoEnCaballoListener());
    }

    public static RespuestaIntentoEnsillado handlerElementoCaballoSelection(MainActivity context,
                                                                            ElementoCaballoModelView resource) {
        RespuestaIntentoEnsillado respuesta = GameFactory.getInstance().ensillar
                (resource.getElementoActual());
        context.procesarRespuestaDeJuego(respuesta);
        ElementosMostradosModelView.elementoArrastradoActualmente = null;
        return respuesta;
    }

    public class SoltarElementoClickeadoEnCaballoListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (ElementosMostradosModelView.elementoArrastradoActualmente != null) {
                handlerElementoCaballoSelection(context,
                        ElementosMostradosModelView.elementoArrastradoActualmente);
            } else {
                context.mostrarMensajeInformativo("No se ha seleccionado ningún elemento.");
            }
        }
    }

    public class SoltarElementoEnCaballoListener implements View.OnDragListener {


        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            if (ElementosMostradosModelView.elementoArrastradoActualmente != null) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        break;
                    case DragEvent.ACTION_DROP:
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        RespuestaIntentoEnsillado respuesta =
                                handlerElementoCaballoSelection(context, ElementosMostradosModelView
                                        .elementoArrastradoActualmente);
                        return respuesta.equals(RespuestaIntentoEnsillado.OK);
                    default:
                        break;
                }
                return false;
            } else {
                context.mostrarMensajeInformativo("No se ha seleccionado ningún elemento.");
                return false;
            }
        }
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
