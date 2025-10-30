package com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.restaurante;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Cidade;
import com.ryan.food_delivery_api.domain.Cozinha;
import com.ryan.food_delivery_api.domain.Restaurante;
import com.ryan.food_delivery_api.domain.dto.restaurante.RestauranteInputDto;

@Component
public class RestauranteDtoDisassembler {

    /**
     * Responsável por converter objetos de entrada ({@link RestauranteInputDto})
     * em entidades de domínio ({@link Restaurante}).
     *
     * Essa classe executa o processo inverso do {@link RestauranteDtoAssembler},
     * sendo utilizada principalmente nas operações de criação e atualização.
     *
     * <p>
     * <strong>Principais métodos:</strong>
     * </p>
     * <ul>
     * <li>{@code toDomainObject} – converte um DTO de entrada em uma nova entidade
     * {@code Restaurante}.</li>
     * <li>{@code copyToDomainObject} – copia os dados do DTO para uma entidade
     * existente,
     * evitando erros de alteração de identificadores de entidades já gerenciadas
     * pelo Hibernate
     * (por exemplo,
     * {@code HibernateException: Identifier of an instance was altered}).</li>
     * </ul>
     *
     * <p>
     * Essa abordagem garante que o mapeamento seja seguro e compatível com o
     * contexto de persistência JPA,
     * evitando conflitos de estado de entidade.
     * </p>
     */

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInputDto inputDto) {
        return modelMapper.map(inputDto, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInputDto inputDto, Restaurante entit) {
        // Trata essa execption org.hibernate.Hibernateexception: identifier os an
        // instance of
        // com.ryan.food_delivery_api.domain.Cozinha was altered from 1 to 2
        entit.setCozinha(new Cozinha());

        if (entit.getEndereco() != null) {
            entit.getEndereco().setCidade(new Cidade());
        }
        modelMapper.map(inputDto, entit);
    }

}
