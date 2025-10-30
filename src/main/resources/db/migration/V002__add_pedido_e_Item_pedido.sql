alter table forma_pagamento 
add column pedido_id bigint;

create table item_pedido 
(id bigint not null auto_increment, observacao varchar(255), preco_total decimal(38,2), preco_unitario decimal(38,2), quantidade integer, pedido_id bigint, 
primary key (id)) engine=InnoDB;

create table pedido 
(id bigint not null auto_increment, data_cancelamento TIMESTAMP(6), data_confirmacao TIMESTAMP(6), data_criacao TIMESTAMP(6) not null, data_entrega TIMESTAMP(6), endereco_bairo varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), status_pedido enum ('CANCELADO','CONFIRMADO','CRIADO','ENTREGUE'), sub_total decimal(38,2) not null, taxa_frete decimal(38,2) not null, valor_total decimal(38,2) not null, endereco_cidade_id bigint, restaurante_id bigint, 
primary key (id)) engine=InnoDB;

alter table produto 
add column item_pedido_id bigint;

alter table forma_pagamento 
add constraint FKhokk3fr2c39lp1ooiiv2gtpmr 
foreign key (pedido_id) references pedido (id);

alter table item_pedido 
add constraint FK60ym08cfoysa17wrn1swyiuda 
foreign key (pedido_id) references pedido (id);

alter table pedido 
add constraint FKk987vfg9cpgx7qxj3166fdqig 
foreign key (endereco_cidade_id) references cidade (id);

alter table pedido 
add constraint FK3eud5cqmgsnltyk704hu3qj71 
foreign key (restaurante_id) references restaurante (id);
alter table produto 
add constraint FKdtxpqofspbpknb8882xxf66vd 
foreign key (item_pedido_id) references item_pedido (id);
