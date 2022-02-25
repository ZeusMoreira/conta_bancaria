package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.financeiro.model.ContaBancaria;
import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.model.TipoLancamento;
import com.algaworks.financeiro.repository.ContasBancarias;
import com.algaworks.financeiro.repository.Lancamentos;
import com.algaworks.financeiro.repository.Pessoas;
import com.algaworks.financeiro.service.CadastroContasBancarias;
import com.algaworks.financeiro.service.CadastroLancamentos;
import com.algaworks.financeiro.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class CadastroLancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroLancamentos cadastro;

	@Inject
	private CadastroContasBancarias cadastro2;

	@Inject
	private Pessoas pessoas;

	@Inject
	private Lancamentos lancamentos;

	@Inject
	private ContasBancarias contasBancarias;

	private Lancamento lancamento;
	private List<Pessoa> todasPessoas;
	private List<ContaBancaria> todasContas;

	public void prepararCadastro() {
		this.todasPessoas = this.pessoas.todas();
		this.todasContas = this.contasBancarias.todos();
		if (this.lancamento == null) {
			this.lancamento = new Lancamento();
		}
	}

	public List<String> pesquisarDescricoes(String descricao) {
		return this.lancamentos.descricoesQueContem(descricao);
	}

	public void dataVencimentoAlterada(AjaxBehaviorEvent event) {
		if (this.lancamento.getDataPagamento() == null) {
			this.lancamento.setDataPagamento(this.lancamento.getDataVencimento());
		}
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if(lancamento.getTipo().getDescricao() == "Receita"){
				lancamento.getConta().setSaldo(lancamento.getConta().getSaldo().add(lancamento.getValor()));
			} else{
				lancamento.getConta().setSaldo(lancamento.getConta().getSaldo().subtract(lancamento.getValor()));
			}

			if(lancamento.getConta().getHasLancamentos() == false){
				lancamento.getConta().setHasLancamentos(true);
			}

			this.cadastro2.salvar(lancamento.getConta());
			this.cadastro.salvar(this.lancamento);
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Pessoa> getTodasPessoas() {
		return this.todasPessoas;
	}

	public List<ContaBancaria> getContasBancarias(){
		return this.todasContas;
	}

	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

}
