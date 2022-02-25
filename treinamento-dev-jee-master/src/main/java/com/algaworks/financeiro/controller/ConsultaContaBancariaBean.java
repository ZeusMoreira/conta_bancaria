package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.financeiro.model.ContaBancaria;
import com.algaworks.financeiro.repository.ContasBancarias;
import com.algaworks.financeiro.service.CadastroContasBancarias;
import com.algaworks.financeiro.service.NegocioException;

@ManagedBean
@Named
@ViewScoped
public class ConsultaContaBancariaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ContasBancarias contasBancariasRepository;

    @Inject
    private CadastroContasBancarias cadastro;

    private List<ContaBancaria> contasBancarias;

    private ContaBancaria contaBancariaSelecionada;

    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            this.cadastro.excluir(this.contaBancariaSelecionada);
            this.consultar();

            context.addMessage(null, new FacesMessage("Conta bancária excluída com sucesso!"));
        } catch (NegocioException e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public void consultar() {
        this.contasBancarias = contasBancariasRepository.todos();
    }

    public List<ContaBancaria> getContasBancarias() {
        return contasBancarias;
    }

    public ContaBancaria getContaBancariaSelecionada() {
        return contaBancariaSelecionada;
    }

    public void setContaBancariaSelecionada(ContaBancaria contaBancariaSelecionada) {
        this.contaBancariaSelecionada = contaBancariaSelecionada;
    }

}