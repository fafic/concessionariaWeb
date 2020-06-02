
package br.edu.fafic.converter;

import br.edu.fafic.model.Veiculo;
import br.edu.fafic.service.VeiculoService;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "veiculoConverter")
public class VeiculoConverter implements Converter {
    
    @EJB
    VeiculoService vs;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Veiculo v = new Veiculo();
        if(value != null && value.trim().length() > 0){
            v = vs.veiculoById(Long.valueOf(value));
            return v;
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && !value.equals("")){
            Veiculo v = (Veiculo) value;
            return v.getId().toString();
        }
        
        return null;
    }
    
}
