/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fafic.controller;

import br.edu.fafic.model.Veiculo;
import br.edu.fafic.service.VeiculoService;
import br.edu.fafic.util.FacesMessageUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Luciano
 */
@ManagedBean(name = "veiculoMB")
@RequestScoped
public class VeiculoController implements Serializable {

    @EJB
    private VeiculoService vs;
    private boolean renderedInit, renderedBtnCadastro;
    private UploadedFile file;
    
    private List<Veiculo> veiculos;

    @PostConstruct
    public void init() {
        renderedInit = false;
        renderedBtnCadastro = true;
        veiculos = getAll();
       
    }

    private Veiculo veiculo, veiculoSelecionado;

    public Veiculo getVeiculo() {
        if (veiculo == null) {
            veiculo = new Veiculo();
        }
       
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Veiculo getVeiculoSelecionado() {
        return veiculoSelecionado;
    }

    public void setVeiculoSelecionado(Veiculo veiculoSelecionado) {
        this.veiculoSelecionado = veiculoSelecionado;
    }
    
    

    

    public void save() {
        try {
            System.out.println("Veiculo: " +veiculo.toString());
            vs.save(veiculo);
            FacesMessageUtil.success();
        } catch (Exception e) {
            FacesMessageUtil.fail("Um erro ocorreu! A operação não realizada");
        }
        veiculo = null;
        renderedInit = false;
    }

    
    public List<Veiculo> getAll(){
        return vs.getAll();
    }
    

    public List<Veiculo> getVeiculos() {
      if(veiculos  == null)  {
          veiculos = new ArrayList();
      }
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
    
    

    public void prepareRemoveVeiculo(Veiculo veiculo) {
             this.veiculo = veiculo;
     }
    
    public void confirmRemove() {
        try {
            vs.remove(this.veiculo);
            FacesMessageUtil.success();
        } catch (Exception ex) {
            FacesMessageUtil.fail("Um erro ocorreu! A operação não realizada");
        }

    }

    public boolean isRenderedInit() {
        return renderedInit;
    }

    public void setRenderedInit(boolean renderedInit) {
        this.renderedInit = renderedInit;
        buttonCadastro();
    }

    public boolean isRenderedBtnCadastro() {
        return renderedBtnCadastro;
    }

    public void setRenderedBtnCadastro(boolean renderedBtnCadastro) {
        this.renderedBtnCadastro = renderedBtnCadastro;
    }

    public void buttonCadastro() {
        setRenderedBtnCadastro(false);
    }
    
    public void prepareUpdate(Veiculo veiculo){
        this.veiculo = veiculo;
        this.renderedInit = true;
        
    }
    
    public void placaUpperCase(){
        this.getVeiculo().setPlaca(this.getVeiculo().getPlaca().toUpperCase());
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void upload(){
       this.veiculo.setPathFile(file.getFileName());
    }
    
    public List<SelectItem> itensVeiculo(){
        List<SelectItem> veiculos = new ArrayList();
        Set<Veiculo> marcasDistintas = new HashSet();
        marcasDistintas.addAll(getAll());
        for(Veiculo v : marcasDistintas){
            veiculos.add(new SelectItem(v, v.getMarca()));
        }
        return veiculos;
    }
    
    public List<Veiculo> veiculosPorMarca(){
        return veiculos = vs.veiculoPorMarca(veiculoSelecionado);
    }
    
    
    
    
   
}
