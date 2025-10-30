package com.ryan.food_delivery_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ryan.food_delivery_api.domain.Produto;
import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;
import com.ryan.food_delivery_api.exception.produto.ProdutoEmUsoException;
import com.ryan.food_delivery_api.exception.produto.ProdutoNaoEncontradoException;
import com.ryan.food_delivery_api.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;


    @Transactional
    public List<Produto> listar() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Produto> buscar(Long id) {

        return repository.findById(id);

    }

    @Transactional
    public Produto buscarOuFalhar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    @Transactional
    public Produto salvar(Produto obj) {

        return repository.save(obj);
    }

    @Transactional
    public void deletar(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();// para tratar erro de lancar exception depois do metodo
        } catch (EntidadeNaoEncontradaException e) {
            throw new ProdutoNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new  ProdutoEmUsoException(id,
                    Produto.class);
        }
    }
}
