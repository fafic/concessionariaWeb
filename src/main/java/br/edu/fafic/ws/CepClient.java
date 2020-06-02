/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fafic.ws;

import br.edu.fafic.model.Endereco;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Luciano
 */
@Stateless
public class CepClient {
    
    
    //passar um cep - receber endereco
    
    public Endereco buscaEnderecoPorCEP(String cep){
       Endereco endereco = null;
       ObjectMapper mapper = new ObjectMapper();
       StringBuilder builder = new StringBuilder("http://www.viacep.com.br/ws/");
       builder.append(cep).append("/json/");
        try {
            endereco = mapper.readValue(new URL(builder.toString()), Endereco.class);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CepClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CepClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return endereco;
    }
    
}
