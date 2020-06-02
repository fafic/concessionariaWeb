/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fafic.util;

import javax.faces.application.FacesMessage;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Luciano
 */
public class FacesMessageUtil {
    
    public static void success(){
        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Operação realizada com sucesso!"), true);
    }
    public static void fail(String dynamicMessage){
        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem", dynamicMessage), true);
    }
    
    public static void successWithEmail(String mensagem){
        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", 
                "Operação realizada com sucesso!<br> Um email com as credenciais foi enviado para " +mensagem), true);
    }
    
    public static void senhasDifrentes(){
        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensagem", "As senhas devem ser iguais!"), true);
    }
    
    public static void senhaPadrao(){
        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensagem", "A nova senha não pode ser igual a anterior"), true);
    }
    
    public static void acessoNegado(){
        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensagem", "Acesso negado!"), true);
    }

   
   
    
}
