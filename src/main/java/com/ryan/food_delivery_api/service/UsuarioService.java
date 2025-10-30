package com.ryan.food_delivery_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ryan.food_delivery_api.domain.Usuario;
import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;
import com.ryan.food_delivery_api.exception.NegocioException;
import com.ryan.food_delivery_api.exception.usuario.UsuarioEmUsoException;
import com.ryan.food_delivery_api.exception.usuario.UsuarioNaoEncontradoException;
import com.ryan.food_delivery_api.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public List<Usuario> listar() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Usuario> buscar(Long id) {

        return repository.findById(id);

    }

    @Transactional
    public Usuario buscarOuFalhar(Long id) {
        // ResponseStatus
        return repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    @Transactional
    public Usuario salvar(Usuario obj) {

        return repository.save(obj);
    }

    @Transactional
    public void deletar(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();// para tratar erro de lancar exception depois do metodo
        } catch (EntidadeNaoEncontradaException e) {
            throw new UsuarioNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new UsuarioEmUsoException(id,
                    Usuario.class);
        }
    }

    @Transactional
    public void alterarSenha(Long id, String senhaAtual, String novaSenha){
        Usuario entity = buscarOuFalhar(id);

        if(entity.senhaNaoCoincide(senhaAtual)){
            throw new NegocioException("Senha atual informada não coincide com a senha do usuario.");
        }

        entity.setSenha(novaSenha);
    }

    //Obj alterado fora da Transacional
    
}
