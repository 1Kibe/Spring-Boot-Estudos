package com.ryan.Conceitos.ResourcesDiversos.ComRepondeStatusSimplificado;

public class CodeService {
/* 

    private static final String MSG_ENTIDADE_NAO_ENCONTRADA = "Entidade não encontrada";

    @Autowired
    private RestauranteRepository repository;

    public RestauranteService(RestauranteRepository repository) {
        this.repository = repository;
    }

    public List<Restaurante> listar() {
        return repository.findAllCat();
    }

    public Optional<Restaurante> buscar(Long id) {

        return repository.findById(id);

    }

    public Restaurante buscarOuFalhar(Long id) {
        // ResponseStatus
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_ENTIDADE_NAO_ENCONTRADA));
    }

    public Restaurante salvar(Restaurante obj) {
        return repository.save(obj);
    }

    public void deletar(Long id) {
        try {
            repository.deleteById(id);
        } catch (EntidadeNaoEncontradaException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_ENTIDADE_NAO_ENCONTRADA, id));
        }
    }

*/
}
