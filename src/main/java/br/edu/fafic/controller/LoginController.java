/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fafic.controller;

import br.edu.fafic.model.Pessoa;
import br.edu.fafic.service.LoginService;
import br.edu.fafic.service.PessoaService;
import br.edu.fafic.util.FacesMessageUtil;
import br.edu.fafic.util.PasswordGeneration;
import org.primefaces.PrimeFaces;
import org.primefaces.context.PrimeRequestContext;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Luciano
 */
@ManagedBean(name = "loginMB")
@ViewScoped
public class LoginController implements Serializable {

    @EJB
    private LoginService loginService;

    @EJB
    private PessoaService pessoaService;

    private String login;

    private String senha;
    
    private String senhaRepetida;

    private Pessoa pessoa;

    private String cor;
    private int mode;
    private String text;
    private int size;
    private String method;

    public LoginController() {
        this.mode = 2;
        this.text = "localhost:8080/ConcWeb/vestibular.xhtml";
        this.size = 200;
        this.method = "canvas";
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getSenhaRepetida() {
        return senhaRepetida;
    }

    public void setSenhaRepetida(String senhaRepetida) {
        this.senhaRepetida = senhaRepetida;
    }
    
    

    public String logar() {

        String paginaDestino = "login.xtml";
        try {
            StringBuilder builder = new StringBuilder();
            FacesContext facesContext = FacesContext.getCurrentInstance();
           

            pessoa = loginService.getPessoaByLogin(login, PasswordGeneration.encryptPassword(senha));
            if (pessoa != null) {
                
                getExternalContext().getSessionMap().put("pessoa", pessoa);
                if (new String(PasswordGeneration.decryptPassword(pessoa.getSenha())).equals("123")) {
                    //redenderizar um dialog para atualizar senha
                    
                    PrimeFaces.current().executeScript("PF('dlgRedSenha').show();");
                    
                } else {

                    paginaDestino = "";
                    paginaDestino = builder.append("/")
                            .append(pessoa.getTipoPessoa().getTipo()
                                    .toLowerCase()).append("/index.xhtml?faces-redirect=true").toString();
                }

            } else {
                FacesMessageUtil.fail("Login ou senha inválidos");
            }
            NavigationHandler navigation = facesContext.getApplication().getNavigationHandler();
            navigation.handleNavigation(facesContext, null, paginaDestino);
            System.out.println(facesContext.getExternalContext().getContextName());
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return paginaDestino;

    }

    public ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public Pessoa pessoaLogada() {
        return (Pessoa) getExternalContext().getSessionMap().get("pessoa");
    }

    public void logout() {
        getExternalContext().getSessionMap().remove("pessoa");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler navigation = facesContext.getApplication().getNavigationHandler();
        navigation.handleNavigation(facesContext, null, "/login.xhtml?faces-redirect=true");
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    
    public boolean senhasIguais(){
        return this.senha.equals(this.senhaRepetida);
    }
    
    public boolean senhaPadrao(){
        return this.senha.equals("123");
    }
    
    public void updateSenha(){
        if(!senhasIguais()){
            this.senha = "";
            this.senhaRepetida = "";
            FacesMessageUtil.senhasDifrentes();
        }else if(senhaPadrao()){
            this.senha = "";
            this.senhaRepetida = "";
            FacesMessageUtil.senhaPadrao();
        }else{
            pessoa = this.pessoaLogada();
            pessoa.setSenha(PasswordGeneration.encryptPassword(this.senha));
            pessoaService.save(pessoa);
            // fechar o panel
            PrimeFaces.current().executeScript("PF('dlgRedSenha').hide();");
            FacesMessageUtil.success();
        }
    }

}
