package com.ryan.Conceitos.ResourcesDiversos.ComRepondeStatusSimplificado;

public class CodeResource {
/* 

    @Autowired
    private RestauranteService service;

    public RestauranteResource(RestauranteService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Restaurante> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Restaurante findById(@PathVariable Long id){
        return service.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante salvar(@RequestBody Restaurante obj) {
        return service.salvar(obj);
    }

    @PutMapping("/{id}")
    public Restaurante atualizar(@PathVariable Long id, @RequestBody Restaurante entity) {
            Restaurante entidadeAtual = service.buscarOuFalhar(id);

            BeanUtils.copyProperties(entity, entidadeAtual,
            "id","formasPagameto","endereco","dataCriacao","dataAtualizacao");

            return service.salvar(entidadeAtual);
            
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
*/
}

