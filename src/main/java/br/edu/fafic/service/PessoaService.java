package br.edu.fafic.service;

import br.edu.fafic.model.Pessoa;

import javax.ejb.Stateless;

@Stateless
public class PessoaService extends GenerciService<Pessoa> {


    @Override
    public void save(Pessoa entity) {
        super.save(entity);
    }
}
