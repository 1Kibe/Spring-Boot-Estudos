package com.ryan.food_delivery_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.food_delivery_api.domain.Produto;
import com.ryan.food_delivery_api.exception.produto.ProdutoNaoEncontradoException;
import com.ryan.food_delivery_api.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;


    @Transactional
    public Produto buscarOuFalhar(Long idR,Long id) {
        return repository.findById(idR,id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    @Transactional
    public Produto salvar(Produto obj) {

        return repository.save(obj);
    }
}
