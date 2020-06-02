/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fafic.util;

import static br.edu.fafic.enums.TipoPessoa.Administrador;
import br.edu.fafic.model.Administrador;
import br.edu.fafic.model.Cliente;
import br.edu.fafic.model.Funcionario;
import br.edu.fafic.model.Pessoa;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luciano
 */
//@WebFilter(filterName = "sessionFilter", urlPatterns = {"/administrador/*", "/cliente/*", "/funcionario/*"})
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession httpSession = httpRequest.getSession();
        Pessoa usuarioLogado = (Pessoa) httpSession.getAttribute("pessoa");
        
        String appContext = httpRequest.getContextPath();
        String uri = httpRequest.getRequestURI();
        
        StringBuilder builder = new StringBuilder();
        builder.append(appContext);
        
        if(usuarioLogado == null){
            httpResponse.sendRedirect(builder.append("/login.xhtml").toString());
        }else if(usuarioLogado instanceof Administrador && !uri.contains("/administrador/")){
            httpResponse.sendRedirect(builder.append("/administrador/index.xhtml").toString());
        }else if(usuarioLogado instanceof Cliente && !uri.contains("/cliente/")){
            httpResponse.sendRedirect(builder.append("/cliente/index.xhtml").toString());
        }else if(usuarioLogado instanceof Funcionario && !uri.contains("/funcionario/")){
            httpResponse.sendRedirect(builder.append("/funcionario/index.xhtml").toString());
        }else{
          try{
            chain.doFilter(request, response);
          }catch(ServletException ex)  {
              System.out.println("Erro: " +ex.getMessage());
          }
        }
    }

    @Override
    public void destroy() {
        
    }
    
}
