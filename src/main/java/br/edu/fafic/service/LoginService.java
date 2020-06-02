/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fafic.service;

import br.edu.fafic.model.Pessoa;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Luciano
 */
@Stateless
public class LoginService extends GenerciService<Pessoa> {
    
    private Map<String,Object> parametros;
    
    public Pessoa getPessoaByLogin(String login, String senha) throws Exception{
        parametros = new HashMap<>();
        parametros.put("login", login);
        parametros.put("senha", senha);
        return getSingleResultNamedQuery(Pessoa.PESSOA_BY_LOGIN, parametros);
    }
    
    public Pessoa getPessoaByLoginQuery(String dataNascimento) throws Exception{
       Query q = em.createNamedQuery(Pessoa.PESSOA_BY_LOGIN);
       q.setParameter("dataNascimento", dataNascimento);
        return (Pessoa) q.getSingleResult();
    }
    
    
    
}
