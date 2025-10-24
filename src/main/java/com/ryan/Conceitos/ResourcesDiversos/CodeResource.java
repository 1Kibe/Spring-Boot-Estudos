package com.ryan.Conceitos.ResourcesDiversos;

public class CodeResource {
/* 

    //------------------------------------------------------------- GET ALL
    @GetMapping()
    public List<Restaurante> listar() {
    return service.listar();
    }



    //------------------------------------------------------------- GET {ID}
    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> findById(@PathVariable Long id) {
    Optional<Restaurante> obj = service.buscar(id);

    if(obj.isPresent()){
    return ResponseEntity.ok(obj.get());
    }

    return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public Cozinha findById(@PathVariable Long id) {
    return service.buscarOuFalhar(id);
    }



    //------------------------------------------------------------- POST
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Restaurante obj) {
    try{
    obj = service.salvar(obj);

    return ResponseEntity.status(HttpStatus.CREATED)
    .body(obj);
    }catch(EntidadeNaoEncontradaException e){
    return ResponseEntity.badRequest().body(e.getMessage());
    }
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha salvar(@RequestBody Cozinha obj) {
    return service.salvar(obj);
    }



    //-------------------------------------------------------------PUT {id}
    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long id,
    @RequestBody Restaurante obj) {
     return service.buscar(id)
     .map(existente -> {
     obj.setId(id);
     Restaurante atualizado = service.salvar(obj);
     return ResponseEntity.ok(atualizado);
     })
     .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody
    Restaurante entity) {
    try{
    Restaurante entidadeAtual = service.buscar(id).orElse(null);

    if(entidadeAtual != null){
    BeanUtils.copyProperties(entity, entidadeAtual,
    "id","formasPagameto","endereco","dataCriacao","dataAtualizacao");

    entidadeAtual = service.salvar(entidadeAtual);
    return ResponseEntity.ok(entidadeAtual);
    }
    return ResponseEntity.notFound().build();
    }catch(EntidadeNaoEncontradaException e){
    return ResponseEntity.badRequest().body(e.getMessage());
    }
    }

    @PutMapping("/{id}")
    public Cozinha atualizar(@PathVariable Long id, @RequestBody Cozinha entity)
    {
    Cozinha entidadeAtual = service.buscarOuFalhar(id);

    BeanUtils.copyProperties(entity, entidadeAtual,
    "id", "formasPagameto", "endereco", "dataCriacao", "dataAtualizacao");

    return service.salvar(entidadeAtual);

    }



    //------------------------------------------------------------- DELETE{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
    if (service.buscar(id).isPresent()) {
    service.deletar(id);
    return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){
    service.deletar(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
    if (service.buscar(id).isPresent()) {
    service.deletar(id);
    return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
    }

*/
}