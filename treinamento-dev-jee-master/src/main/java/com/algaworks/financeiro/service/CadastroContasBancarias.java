package com.algaworks.financeiro.service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.inject.Inject;

import com.algaworks.financeiro.model.ContaBancaria;
import com.algaworks.financeiro.repository.ContasBancarias;
import com.algaworks.financeiro.util.Transactional;

public class CadastroContasBancarias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ContasBancarias contasBancarias;

    @Transactional
    public void salvar(ContaBancaria contaBancaria) throws NegocioException {
        if (contaBancaria.getDataCadastro() != null && contaBancaria.getDataCadastro().after(new Date())) {
            throw new NegocioException(
                    "Data de pagamento não pode ser uma data futura.");
        }
        this.contasBancarias.guardar(contaBancaria);
    }

    @Transactional
    public void excluir(ContaBancaria contaBancaria) throws NegocioException {
        contaBancaria = this.contasBancarias.porId(contaBancaria.getId());

        if (contaBancaria.getHasLancamentos() == true) {
            throw new NegocioException("Não é possível excluir uma conta com lançamentos!");
        }

        this.contasBancarias.remover(contaBancaria);
    }

}