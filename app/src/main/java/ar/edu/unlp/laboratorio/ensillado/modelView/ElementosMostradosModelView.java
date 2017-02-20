package ar.edu.unlp.laboratorio.ensillado.modelView;

import android.content.ClipData;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unlp.laboratorio.ensillado.activity.MainActivity;
import ar.edu.unlp.laboratorio.ensillado.model.ElementoCaballo;

/**
 * Created by dbarba on 09/02/17.
 */
public class ElementosMostradosModelView {

    public MainActivity context;
    public Map<ImagenYTexto, ElementoCaballoModelView> bindedResources = new HashMap<ImagenYTexto,
            ElementoCaballoModelView>();

    public static ElementoCaballoModelView elementoArrastradoActualmente = null;

    public static ElementosMostradosModelView nuevaPantallaDeElementos
            (MainActivity mainActivity, List<ImagenYTexto> resources) {
        ElementosMostradosModelView elementosMostrados = new
                ElementosMostradosModelView();
        elementosMostrados.context = mainActivity;
        elementosMostrados.bindAllResources(resources);
        return elementosMostrados;
    }

    public void bindAllResources(List<ImagenYTexto> resources) {
        for (ImagenYTexto aResource : resources) {
            this.bindedResources.put(aResource, new ElementoCaballoModelView(aResource));
            this.assignHandler(aResource);
        }

    }

    private void assignHandler(final ImagenYTexto aResource) {
        aResource.imagen.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (aResource != null && aResource.imagen.isEnabled()) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                            view);
                    view.startDrag(data, shadowBuilder, view, 0);
                    ElementosMostradosModelView.elementoArrastradoActualmente = bindedResources.get
                            (aResource);
                    context.tocarAudio(bindedResources.get(aResource).getAudioResource());
                    return true;
                }
                return false;
            }
        });
        aResource.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // SI LA IMAGEN ESTA HABILITADA/CARGADA, entonces ES VALIDA, y TIENE CONTENIDO
                // ENVIAMOS EL ELEMENTOCABALLO CORRESPONDIENTE A LA IMAGEN POR PARAMETRO
                if (aResource != null && aResource.imagen.isEnabled()) {
                    handleElementoCaballoClick(aResource);
                }


            }
        });
    }

    private void handleElementoCaballoClick(ImagenYTexto aResource) {
        context.vibrar(500);
        context.tocarAudio(bindedResources.get(aResource).getAudioResource());
        ElementosMostradosModelView.elementoArrastradoActualmente = bindedResources.get
                (aResource);
    }


    public void bind(ElementoCaballo[] elementosArray) {
        List<ImagenYTexto> imagenesAProcesar = new ArrayList<ImagenYTexto>(this
                .bindedResources.keySet());
        for (int i = 0; i < elementosArray.length; i++) {
            ImagenYTexto imagenActual = imagenesAProcesar.get(i);
            ElementoCaballoModelView elementoViewActual = new
                    ElementoCaballoModelView(imagenActual);
            // ASOCIAMOS LA RESPECTIVA IMAGEN CON EL ELEMENTO
            // A MOSTRAR, Y LO ACTIVAMOS.
            elementoViewActual.bind(elementosArray[i]);
            this.bindedResources.put(imagenesAProcesar.get(i),
                    elementoViewActual);
        }
    }

    public void render(Renderizable renderObject) {
        renderObject.render();
    }


    public void reset() {
        ElementosMostradosModelView.elementoArrastradoActualmente = null;
        for (ImagenYTexto key : this.bindedResources.keySet()) {
            this.bindedResources.put(key, new ElementoCaballoModelView(key));
        }
    }
}