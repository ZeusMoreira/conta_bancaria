package com.algaworks.financeiro.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.financeiro.model.ContaBancaria;

public class ContasBancarias implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    @Inject
    public ContasBancarias(EntityManager manager) {
        this.manager = manager;
    }

    public ContaBancaria porId(Long id) {
        return manager.find(ContaBancaria.class, id);
    }

    public ContaBancaria porNumero(BigDecimal numero) {
        return manager.find(ContaBancaria.class, numero);
    }

    public List<ContaBancaria> todos() {
        TypedQuery<ContaBancaria> query = manager.createQuery(
                "from ContaBancaria", ContaBancaria.class);
        return query.getResultList();
    }

    public ContaBancaria guardar(ContaBancaria contaBancaria) {
        return this.manager.merge(contaBancaria);
    }

    public void remover(ContaBancaria contaBancaria) {
        this.manager.remove(contaBancaria);
    }

}