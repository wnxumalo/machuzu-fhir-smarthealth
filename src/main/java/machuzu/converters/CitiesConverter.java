package machuzu.converters;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import machuzu.entities.Cities;
import machuzu.entities.controller.CitiesController;

@FacesConverter(value = "citiesConverter")
public class CitiesConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String referenceId) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{citiesController}", CitiesController.class);

        CitiesController citiesController = (CitiesController)vex.getValue(ctx.getELContext());
        return citiesController.getCity(Long.valueOf(referenceId));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object reference) {
        return ((Cities)reference).getId().toString();
    }

}