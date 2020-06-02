/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fafic.util;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Luciano
 */

public class LazyDataModelGeneric extends LazyDataModel<Object> {

    @Override
    public List<Object> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return super.load(first, pageSize, sortField, sortOrder, filters); 
    }
    
    
    
    
}
