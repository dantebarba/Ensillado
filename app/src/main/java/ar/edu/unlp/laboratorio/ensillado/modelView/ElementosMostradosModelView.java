package ar.edu.unlp.laboratorio.ensillado.modelView;

import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.edu.unlp.laboratorio.ar.laboratorio.R;
import ar.edu.unlp.laboratorio.ensillado.factory.GameFactory;
import ar.edu.unlp.laboratorio.ensillado.model.ElementoCaballo;
import ar.edu.unlp.laboratorio.ensillado.model.RespuestaIntentoEnsillado;

/**
 * Created by dbarba on 09/02/17.
 */
public class ElementosMostradosModelView<T> {

    public Map<ImageView, ElementoCaballo> bindedResources = new HashMap<ImageView, ElementoCaballo>();

    public static ElementosMostradosModelView<ElementoCaballoModelView> nuevaPantallaDeElementos(List<ImageView> resources) {
        ElementosMostradosModelView<ElementoCaballoModelView> elementosMostrados = new ElementosMostradosModelView<ElementoCaballoModelView>();
        elementosMostrados.bindAllResources(resources);
        return elementosMostrados;
    }

    public void bindAllResources(List<ImageView> resources) {
        for (ImageView aResource : resources) {
            this.assignHandler(aResource);
            this.bindedResources.put(aResource, ElementoCaballo.NINGUNO);
        }

    }

    private void assignHandler(ImageView aResource) {
        aResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RespuestaIntentoEnsillado respuesta = GameFactory.getInstance().ensillar
                        (elementoMostradoView.elementoActual);
                procesarRespuestaDeJuego(respuesta);

            }
        });
    }

    public void render(Renderizable renderObject) {
        renderObject.render();
    }


    public void renderAll(final Set<ElementoCaballo> elemento) {
        this.render(new Renderizable() {
            @Override
            public void render() {
                for (ElementoCaballo unElemento : elemento) {switch (elemento) {
            }
        });
    }
}
