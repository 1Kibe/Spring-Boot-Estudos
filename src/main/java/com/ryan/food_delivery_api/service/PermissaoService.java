package com.ryan.food_delivery_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.food_delivery_api.domain.Permissao;
import com.ryan.food_delivery_api.exception.permissao.PermissaoNaoEncontradoException;
import com.ryan.food_delivery_api.repository.PermissaoRepository;

import jakarta.transaction.Transactional;

@Service
public class PermissaoService {
    @Autowired
    private PermissaoRepository repository;

    @Transactional
    public Permissao buscarOuFalhar(Long id) {
        // ResponseStatus
        return repository.findById(id)
                .orElseThrow(() -> new PermissaoNaoEncontradoException(id));
    }
}
