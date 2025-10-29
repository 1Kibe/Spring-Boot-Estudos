package com.ryan.Conceitos.ModelMapper.modelMapper;

//@Configuration
public class ModelMapperConfig {

    // @Bean
    // public ModelMapper modelMapper(){
    // return new ModelMapper();
    // }

    // gera um bean para o Autowired
    // ============================================================================================

    // Seta nomes difrentes entre o dto e a classe

    // public ModelMapper modelMapper(){
    // var modelMapper = new ModelMapper();
    //
    // modelMapper.createTypeMap(Restaurante.class, RestauranteDto.class)
    // .addMappping(Restaurante::getTaxaFrete(), RestauranteDto::setPrecoFrete);
    // }

    // ============================================================================================

    // var modelMapper = new ModelMapper();
    //
    // // Configura o ModelMapper para copiar o nome do estado
    // // (src.getCidade().getEstado().getNome())
    // // para o campo cidade.estado (String) do DTO, já que o modelo original
    // possui
    // // uma hierarquia de objetos.
    // var enderecoToEnderecoDtoTypeMap = modelMapper.createTypeMap(Endereco.class,
    // EnderecoDto.class);
    // enderecoToEnderecoDtoTypeMap.<String>addMapping(
    // enderecosrc -> enderecosrc.getCidade().getEstado().getNome(),
    // (enderecoDtodest, value) -> enderecoDtodest.getCidade().setEstado(value));
    //
    // return modelMapper;
}
