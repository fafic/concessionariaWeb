package br.edu.fafic.controller;

import br.edu.fafic.util.AlunoNota;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "notaMB")
@ViewScoped
public class NotaController implements Serializable {

    private List<AlunoNota> alunosNotas;
    private Double media;
    private List<Double> notas;
    private List<String> avs;
    private AlunoNota alunoNota;
    private Double nota;

    public NotaController() {
        notas = new ArrayList();

    }

    public List<AlunoNota> getAlunosNotas() {
        if (alunosNotas == null) {
            alunosNotas = new ArrayList();
            alunosNotas.add(new AlunoNota("José", null, Arrays.asList(5.0,4.0,8.0)));
            alunosNotas.add(new AlunoNota("João", null, Arrays.asList(6.0,9.0,9.0)));
            alunosNotas.add(new AlunoNota("Pedro", null, Arrays.asList(7.0,7.0,3.0)));
        }
        return alunosNotas;
    }

    public void setAlunosNotas(List<AlunoNota> alunosNotas) {
        this.alunosNotas = alunosNotas;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public List<Double> getNotas() {
        if (notas == null) {
            notas = new ArrayList();
        }
        return notas;
    }

    public void setNotas(List<Double> notas) {
        this.notas = notas;
    }

    private void init() {

    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
    
    

    public void mediaAluno(AlunoNota an) {
        double soma = 0;
        if (an.getNotas() != null) {
            for (Double not : an.getNotas()) {
                soma = +not;
            }
            media = soma / an.getNotas().size();
        }

    }

    public void addNota(AlunoNota an, Double nota) {
        if (an.getNotas().isEmpty()) {
            an.getNotas().add(nota);
        }
        an.getNotas().set(an.getNotas().indexOf(nota), nota);
    }

    public AlunoNota getAlunoNota() {
        return alunoNota;
    }

    public void setAlunoNota(AlunoNota alunoNota) {
        this.alunoNota = alunoNota;
    }

    public List<String> getAvs() {
        avs = new ArrayList<>();
        avs.add("AV 1");
        avs.add("AV 2");
        avs.add("AV 3");
        avs.add("Média");
        return avs;
    }

    public void setAvs(List<String> avs) {
        this.avs = avs;
    }

    public List<Double> notasDoAluno(AlunoNota an) {
        for (AlunoNota a : getAlunosNotas()) {
            if (a.getNome().equalsIgnoreCase((an.getNome()))) {
                notas = an.getNotas();
                break;
            }
        }
        return notas;
    }

}
