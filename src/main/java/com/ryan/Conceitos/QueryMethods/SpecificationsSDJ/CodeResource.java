package com.ryan.Conceitos.QueryMethods.SpecificationsSDJ;

public class CodeResource {

    //@Autowired
    //private CodeRepository repository; //implemente o repository nescessario dependendo da sua escolha

    //@GetMapping("/specifications-sdj")
    //public List<Restaurante> restaurantesComFreteGratisENomeSemelhante(String nome) {
    //    var comFreteGratis = new RestauranteComFreteGratisSpec();                                 //RestauranteComFreteGratisSpec é uma especificacao que filtra restaurantes com frete gratis
    //    var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);                       //RestauranteComNomeSemelhanteSpec é uma especificacao que filtra restaurantes com nome semelhante 

    //    return repository.findAll(comFreteGratis.and(comNomeSemelhante));
    //}

    //-----------------------------------------------------------------------------------------------

    //Fabrica de Especificações
    //chama os construtores das especificações localizadas em RestauranteSpecs

    //    @GetMapping("/specifications-sdj-factory")
    //    public List<Restaurante> restaurantesComFreteGratisENomeSemelhanteFactory(String nome) {
    //        return repository.findAll(
    //            RestauranteSpecs.comFreteGratis().and(RestauranteSpecs.comNomeSemelhante(nome))
    //        );
    //    }

    // passa os metodos ao inves de criar uma nova instancia

    //-----------------------------------------------------------------------------------------------

    //Especificações com Query & Impl
    //ajuda na manutenção do código

    //    @GetMapping("/specifications-sdj-lazy")
    //    public List<Restaurante> restaurantesComFreteGratisENomeSemelhante(String nome) {
    //        return repository.findRestaurantesComFreteGratisENomeSemelhanteLazy(nome);    //chama o metodo criado
    //    }


}
