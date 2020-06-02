/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fafic.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Luciano
 * @param <T>
 */
public class GenerciService<T> {

    @PersistenceContext(unitName = "ConcWeb_PU")
    EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void save(T entity) {
        em.merge(entity);
    }
    
    public T getSingleResultNamedQuery(String query, Map<String,Object> parametros){
      try{  
        return (T)createNamedQuery(query, parametros).getSingleResult();
      }catch(NoResultException ne){
        return null;
      }
    }
    public List<T> getResultListNamedQuery(String query, Map<String,Object> parametros)throws Exception{
        return (List<T>)createNamedQuery(query, parametros).getResultList();
    }

    private Query createNamedQuery(String query, Map<String, Object> parametros)  {
        Query q = em.createNamedQuery(query);
        
        if (parametros != null) {
            for (Entry<String, Object> param : parametros.entrySet()) {
                q.setParameter(param.getKey(), param.getValue());
            }

        }
        
        return q;
    }

}
