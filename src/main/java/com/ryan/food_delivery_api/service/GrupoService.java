package com.ryan.food_delivery_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ryan.food_delivery_api.domain.Grupo;
import com.ryan.food_delivery_api.domain.Permissao;
import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;
import com.ryan.food_delivery_api.exception.grupo.GrupoEmUsoException;
import com.ryan.food_delivery_api.exception.grupo.GrupoNaoEncontradoException;
import com.ryan.food_delivery_api.repository.GrupoRepository;

import jakarta.transaction.Transactional;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository repository;

    // ===

    @Autowired
    private PermissaoService permissaoService;

    // ===

    @Transactional
    public List<Grupo> listar() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Grupo> buscar(Long id) {

        return repository.findById(id);

    }

    @Transactional
    public Grupo buscarOuFalhar(Long id) {
        // ResponseStatus
        return repository.findById(id)
                .orElseThrow(() -> new GrupoNaoEncontradoException(id));
    }

    @Transactional
    public Grupo salvar(Grupo obj) {

        return repository.save(obj);
    }

    @Transactional
    public void deletar(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();// para tratar erro de lancar exception depois do metodo
        } catch (EntidadeNaoEncontradaException e) {
            throw new GrupoNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new GrupoEmUsoException(id,
                    Grupo.class);
        }
    }

    // ===========================================

    @Transactional
    public void adcionarPermissoes(@PathVariable Long id,@PathVariable Long idP) {
        Grupo grupo = buscarOuFalhar(id);
        Permissao permissao = permissaoService.buscarOuFalhar(idP);

        grupo.getPermissoes().add(permissao);
    }

    @Transactional
    public void removerPermissoes(@PathVariable Long id,@PathVariable Long idP) {
        Grupo grupo = buscarOuFalhar(id);
        Permissao permissao = permissaoService.buscarOuFalhar(idP);

        grupo.getPermissoes().remove(permissao);
    }
    // ===========================================

    // Sub Rotas

    // @Transactional
    // public void ativar(Long id) {
    // Grupo entityAtual = buscarOuFalhar(id);
    // entityAtual.ativar();
    // }
    //
    // @Transactional
    // public void desativar(Long id) {
    // Grupo entityAtual = buscarOuFalhar(id);
    // entityAtual.desativar();
    // }

}
