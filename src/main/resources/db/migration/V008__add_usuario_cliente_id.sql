alter table pedido add column usuario_cliente_id bigint not null;
alter table pedido add constraint FKcccmjvm9ytuxbe00h3wmtm77y foreign key (usuario_cliente_id) references usuario (id);
