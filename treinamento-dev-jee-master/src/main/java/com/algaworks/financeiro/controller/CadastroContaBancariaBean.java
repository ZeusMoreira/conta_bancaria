package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.financeiro.model.*;
import com.algaworks.financeiro.repository.ContasBancarias;
import com.algaworks.financeiro.service.CadastroContasBancarias;
import com.algaworks.financeiro.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class CadastroContaBancariaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroContasBancarias cadastro;

    @Inject
    private ContasBancarias contasBancarias;

    private ContaBancaria contaBancaria;


    public void prepararCadastro() {
        if (this.contaBancaria == null) {
            Date date = new Date();
            this.contaBancaria = new ContaBancaria(date);
        }
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.cadastro.salvar(this.contaBancaria);
            this.contaBancaria = new ContaBancaria();
            context.addMessage(null, new FacesMessage("Conta bancária salva com sucesso!"));
        } catch (NegocioException e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }


    public TipoPessoa[] getTipoPessoa() {
        return TipoPessoa.values();
    }

    public ContaBancaria getContaBancaria(){
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria){
        this.contaBancaria = contaBancaria;
    }

}

