/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fafic.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Luciano
 */
@SessionScoped
@ManagedBean(name = "langMB")
public class LangController implements Serializable {
    
    
    private List<Locale> listLocale;
    private String language;
    
    public LangController(){
        language = "pt_BR";
    }

    public List<Locale> getListLocale() {
      if(listLocale == null)  {
          listLocale = Arrays.asList(new Locale("English"), new Locale("Portuguese"));
      }
        return listLocale;
    }

    public void setListLocale(List<Locale> listLocale) {
        this.listLocale = listLocale;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

   
    
    
    
    
    
    
}
