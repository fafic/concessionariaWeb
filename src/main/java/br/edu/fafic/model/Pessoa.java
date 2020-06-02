/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fafic.model;


import br.edu.fafic.enums.TipoPessoa;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Luciano
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
@NamedQuery(name = "pessoaByLogin" ,query = "select p from Pessoa p where p.login=:login and p.senha=:senha") 
})
public abstract class Pessoa implements Serializable {
    
    public static final String PESSOA_BY_LOGIN = "pessoaByLogin";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
   
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Veiculo> veiculos;
    
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
    
    private String login;
    
    private String senha;
    
    private String pathFoto;
    
    private String pathFile;
    
    private String telefone;
    @Column(unique = true)
    private String email;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Veiculo> getVeiculos() {
           return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
    

    public Long getId() {
        return id;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Endereco getEndereco() {
     if(endereco == null)   {
         endereco = new Endereco();
     }
         return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getLogin() {
        return login;
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

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email.toLowerCase();
        this.email = email;
    }
    
    
          

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", endereco=" + endereco + ", login=" + login + '}';
    }
    
    

   
    
}
