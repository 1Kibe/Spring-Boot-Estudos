package com.ryan.food_delivery_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ryan.food_delivery_api.domain.Cidade;
import com.ryan.food_delivery_api.domain.Estado;
import com.ryan.food_delivery_api.exception.EntidadeEmUsoException;
import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;
import com.ryan.food_delivery_api.repository.CidadeRepository;

@Service
public class CidadeService {

    private static final String MSG_ENTIDADE_NAO_ENCONTRADA = "Entidade não encontrada";

    private static final String MSG_ENTIDADE_EM_USO = "Entidade atual nao pode ser removida, pois esta em uso";

    @Autowired
    private CidadeRepository repository;

    @Autowired
    private EstadoService estadoService;

    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    public List<Cidade> listar() {
        return repository.findAll();
    }

    public Optional<Cidade> buscar(Long id) {
        return repository.findById(id);
    }

    public Cidade buscarOuFalhar(Long id) {
        // ResponseStatus
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_ENTIDADE_NAO_ENCONTRADA));
    }

    public Cidade salvar(Cidade obj) {
        // verifica se existe um atributo presente
        Long atributoId = obj.getEstado().getId();
        Estado estado = estadoService.buscarOuFalhar(atributoId);

        obj.setEstado(estado);

        return repository.save(obj);
    }

    public void deletar(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_ENTIDADE_NAO_ENCONTRADA, id));
        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(MSG_ENTIDADE_EM_USO);
        }
    }
}
