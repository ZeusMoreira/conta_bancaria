package com.algaworks.financeiro.model;

import com.algaworks.financeiro.repository.Lancamentos;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contaBancaria")
public class ContaBancaria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private boolean hasLancamentos;
    private String numero;
    private Date dataCadastro;
    private String banco;
    private BigDecimal saldo;
    private TipoPessoa tipo;

    public ContaBancaria(Date date) {
        this.dataCadastro = date;
    }

    public ContaBancaria(){

    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @JoinColumn(name = "tipo_pessoa")
    public TipoPessoa getTipo() { return tipo;}
    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
    }


    @Column(name = "data_cadastro")
    public Date getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @NotNull
    @Size(max = 100)
    @NotEmpty
    @Column(name = "banco", nullable = false)
    public String getBanco(){ return banco; }
    public void setBanco(String banco) { this.banco = banco; }

    @Column(name = "has_lancamentos")
    public boolean getHasLancamentos() {
        return hasLancamentos;
    }
    public void setHasLancamentos(boolean hasLancamentos) {
        this.hasLancamentos = hasLancamentos;
    }

    @NotNull
    @NotEmpty
    @Size(max = 12)
    @Column(name = "numero", nullable = false)
    public String getNumero(){ return numero; }
    public void setNumero(String numero){ this.numero = numero; }

    @NotNull
    @Column(name = "saldo", nullable = false)
    public BigDecimal getSaldo(){ return saldo; }
    public void setSaldo(BigDecimal saldo){ this.saldo = saldo; }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContaBancaria other = (ContaBancaria) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}