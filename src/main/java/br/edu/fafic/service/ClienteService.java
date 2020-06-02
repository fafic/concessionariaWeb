/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fafic.service;

import br.edu.fafic.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.Query;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Luciano
 */
@Stateless
public class ClienteService extends GenerciService<Cliente> {

    @Override
    public void save(Cliente entity) {
        super.save(entity); 
    }

    @Override
    public Cliente getSingleResultNamedQuery(String query, Map<String, Object> parametros) {
        return super.getSingleResultNamedQuery(query, parametros); 
    }
    
    public List<Cliente> getAll(){
        List<Cliente> clientes = new ArrayList();
        try {
            clientes  = super.getResultListNamedQuery(Cliente.CLIENTES_GET_ALL, null);
        } catch (Exception ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }
    
    
    public List<Cliente> listLazy(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
        Query query = em.createNamedQuery(Cliente.CLIENTES_GET_ALL);
        
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        
        return query.getResultList();
        
    }
    
    public Long totalRegistros(){
        Query query = em.createNamedQuery(Cliente.CLIENTES_COUNT);
        return (Long)query.getSingleResult();
    }
    
   
    
    
    
    
    
    
}
