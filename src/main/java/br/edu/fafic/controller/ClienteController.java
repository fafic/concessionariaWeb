/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fafic.controller;

import br.edu.fafic.model.Cliente;
import br.edu.fafic.service.ClienteService;
import br.edu.fafic.service.JavaMailService;
import br.edu.fafic.util.FacesMessageUtil;
import br.edu.fafic.util.PasswordGeneration;
import br.edu.fafic.ws.CepClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.imageio.stream.FileImageOutputStream;
import javax.mail.MessagingException;
import org.primefaces.event.CaptureEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Luciano
 */
@ManagedBean(name = "clienteMB")
@ViewScoped
public class ClienteController implements Serializable {

    private Cliente cliente;
    

    @EJB
    private ClienteService clienteService;

    @EJB
    private CepClient cepClient;
    
    @EJB
    private JavaMailService javaMail;

    private String sexo, fileName;

    private List<SelectItem> genero;

    private List<SelectItem> comidas;

    private List<String> comidasSelecionadas;

    private List<Cliente> clientes;

    private boolean renderedDataTableClientes, renderedFormFoto;

    private LazyDataModel<Cliente> clientesLazy;

    @ManagedProperty(value = "#{loginMB}")
    private LoginController loginMB;
    
    private StreamedContent fileDownload;
   

    @PostConstruct
    public void init() {

        clientesLazy = new LazyDataModel<Cliente>() {
            @Override
            public List<Cliente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                setRowCount(clienteService.totalRegistros().intValue());
                return clienteService.listLazy(first, pageSize, null, null, null);
            }

        };
    }

    public LazyDataModel<Cliente> getClientesLazy() {
        return clientesLazy;
    }

    public void setClientesLazy(LazyDataModel<Cliente> clientesLazy) {
        this.clientesLazy = clientesLazy;
    }

    public ClienteController() {
        this.renderedDataTableClientes = true;
    }

    public Cliente getCliente() {
        if (cliente == null) {
            cliente = new Cliente();
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LoginController getLoginMB() {
        return loginMB;
    }

    public void setLoginMB(LoginController loginMB) {
        this.loginMB = loginMB;
    }
    
    public void save() {
        try {
            
            clienteService.save(cliente);
            cliente.setSenha(PasswordGeneration.encryptPassword("123"));
            cliente.setLogin(cliente.getEmail());
            FacesMessageUtil.successWithEmail(cliente.getEmail());
        } catch (Exception e) {
            FacesMessageUtil.fail("Um erro ocorreu. Operação não realizada!");
        }
        cliente = null;
        this.renderedDataTableClientes = true;
        this.renderedFormFoto = false;

    }

    public void prepareUpdateCliente(Cliente cliente) {
        this.cliente = cliente;
        this.renderedDataTableClientes = false;
    }

    public void buscaEndereco() {
        cliente.setEndereco(cepClient.buscaEnderecoPorCEP(cliente.getEndereco().getCep()));
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public boolean isRenderedFormFoto() {
        return renderedFormFoto;
    }

    public void setRenderedFormFoto(boolean renderedFormFoto) {
        this.renderedFormFoto = renderedFormFoto;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Cliente> getClientes() {
        if (clientes == null) {
            clientes = new ArrayList();
            clientes = clienteService.getAll();
        }
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public boolean isRenderedDataTableClientes() {
        return renderedDataTableClientes;
    }

    public void setRenderedDataTableClientes(boolean renderedDataTableClientes) {
        this.renderedDataTableClientes = renderedDataTableClientes;
    }

    public List<SelectItem> getGenero() {
        if (genero == null) {
            genero = new ArrayList();
            genero.add(new SelectItem("M", "Masculino"));
            genero.add(new SelectItem("F", "Feminino"));
        }
        return genero;
    }

    public void setGenero(List<SelectItem> genero) {
        this.genero = genero;
    }

    public void printClient() {
        System.out.println(cliente.toString());
        System.out.println("Sexo: " + sexo);
    }

    public List<SelectItem> getComidas() {
        if (comidas == null) {
            comidas = new ArrayList();
            comidas.add(new SelectItem("feijoada", "Feijoada"));
            comidas.add(new SelectItem("churrasco", "Churrasco"));
            comidas.add(new SelectItem("pizza", "Pizza"));
            comidas.add(new SelectItem("sushi", "Sushi"));
        }
        return comidas;
    }

    public void setComidas(List<SelectItem> comidas) {
        this.comidas = comidas;
    }

    public List<String> getComidasSelecionadas() {
        if (comidasSelecionadas == null) {
            comidasSelecionadas = new ArrayList();
        }
        return comidasSelecionadas;
    }

    public void setComidasSelecionadas(List<String> comidasSelecionadas) {
        this.comidasSelecionadas = comidasSelecionadas;
    }

   
    
    
    public void oncapture(CaptureEvent captureEvent) throws Exception {
        fileName = String.valueOf(System.currentTimeMillis());
        byte[] data = captureEvent.getData();

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName = externalContext.getRealPath("") + File.separator + "resources"
                + File.separator + "imagens" + File.separator + fileName + ".png";

        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        } catch (IOException e) {
            throw new Exception("Error in writing captured image.", e);
        }

        cliente.setPathFoto(fileName + ".png");
    }

    
    public void fileUpload(FileUploadEvent event){
        try {
            
        String nomeDoArquivo = String.valueOf(System.currentTimeMillis());
        UploadedFile fileRecebido = event.getFile();
        InputStream inpuStream = fileRecebido.getInputstream();
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName = externalContext.getRealPath("") + File.separator + "resources"
                + File.separator + "arquivos" + File.separator + nomeDoArquivo + ".pdf";
        
        OutputStream out = new FileOutputStream(newFileName);
        byte buff[] = new byte[1024];
        int length;
        
        while((length = inpuStream.read(buff)) > 0){
            out.write(buff, 0, length);
        }
        
        inpuStream.close();
        out.close();
        
        cliente = (Cliente) loginMB.pessoaLogada();
        cliente.setPathFile(nomeDoArquivo+".pdf");
        save();
        
        
        } catch (IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    public StreamedContent getFileDownload() throws FileNotFoundException {
       ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName = externalContext.getRealPath("") + File.separator + "resources"
                + File.separator + "arquivos" + File.separator;
       
        String contenType = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getMimeType(newFileName);
        
        return new DefaultStreamedContent(new FileInputStream(newFileName+cliente.getPathFile()), contenType, cliente.getPathFile());
        
    }

    public void setFileDownload(StreamedContent fileDownload) {
        this.fileDownload = fileDownload;
    }
    
    public void sendCredentials(){
        try {
            javaMail.sendMessage(cliente.getEmail(),
                    "Credenciais de Acesso ao Sistema FafiCAR",
                    "Olá, " +cliente.getNome()
                            + " essas são suas credenciais de acesso ao sistena<br>"
                            + "Login: " +cliente.getEmail()+"<br>"
                            + "Senha:  123");
        } catch (MessagingException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    
    
    
   

   
}
