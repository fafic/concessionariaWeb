
package br.edu.fafic.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AlunoNota implements Serializable {
    
    private String nome;
    private Double media;
    private List<Double> notas;

    public AlunoNota(String nome, Double media,List<Double> notas) {
        this.nome = nome;
        this.media = media;
        this.notas =  notas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public List<Double> getNotas() {
     if(notas  == null)   {
         notas = new ArrayList();
     }
        System.out.println("Notas: " +notas);
        return notas;
    }

    public void setNota(List<Double> notas) {
        this.notas = notas;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AlunoNota other = (AlunoNota) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

   
    
    
    
}
