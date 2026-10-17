package machuzu.converters;

import jakarta.el.ValueExpression;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import machuzu.entities.ObjectReference;
import machuzu.entities.controller.ReferenceController;

@FacesConverter(value = "referenceConverter")
public class ReferenceConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String referenceId) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{referenceController}", ReferenceController.class);

        ReferenceController referenceController = (ReferenceController)vex.getValue(ctx.getELContext());
        return referenceController.getReference(Long.valueOf(referenceId));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object reference) {
        return ((ObjectReference)reference).getId().toString();
    }

}