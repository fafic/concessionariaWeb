/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fafic.model;

import br.edu.fafic.enums.TipoPessoa;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Luciano
 */
@Entity
@NamedQueries({
@NamedQuery(name = Cliente.CLIENTES_GET_ALL, query = "select c from Cliente c "),
@NamedQuery(name = Cliente.CLIENTES_COUNT, query = "select count(c) from Cliente c")    
  
})
public class Cliente  extends PessoaFisica {
   
  public static final String CLIENTES_GET_ALL = "clientes.getAll"  ;
  public static final String CLIENTES_COUNT = "clientes.getCount";
 
    
  public Cliente(){
      setTipoPessoa(TipoPessoa.Cliente);
  }

   
  

    
    
}
