/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fafic.service;

import br.edu.fafic.model.Veiculo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Luciano
 */
@Stateless
public class VeiculoService extends GenerciService<Veiculo> {

    @Override
    public void save(Veiculo entity) {
        super.save(entity);
    }
    
    public void remove(Veiculo veiculo){
        Veiculo v = getEm().merge(veiculo);
        getEm().remove(v);
    }

    public List<Veiculo> getAll() {
        Query query = getEm().createNamedQuery("veiculos.getAll");
        return query.getResultList();
    }
    
    public Veiculo veiculoById(Long id){
        Query query = getEm().createNamedQuery("veiculo.byId");
        query.setParameter("id", id);
        return (Veiculo) query.getSingleResult();
    }
    
    public List<Veiculo> veiculoPorMarca(Veiculo veiculo){
        Query query = getEm().createNamedQuery("veiculo.porMarca");
        query.setParameter("marca", veiculo.getMarca());
        return query.getResultList();
    }
   
}
