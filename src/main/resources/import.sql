-- Dados de exemplo: >=10 registros por entidade

-- COZINHAS (10)
insert into cozinha (id, nome) values (1, 'Brasileira');
insert into cozinha (id, nome) values (2, 'Italiana');
insert into cozinha (id, nome) values (3, 'Japonesa');
insert into cozinha (id, nome) values (4, 'Chinesa');
insert into cozinha (id, nome) values (5, 'Mexicana');
insert into cozinha (id, nome) values (6, 'Indiana');
insert into cozinha (id, nome) values (7, 'Francesa');
insert into cozinha (id, nome) values (8, 'Mediterrânea');
insert into cozinha (id, nome) values (9, 'Nórdica');
insert into cozinha (id, nome) values (10, 'Vegetariana');

-- ESTADOS (10)
insert into estado (id, nome) values (1, 'São Paulo');
insert into estado (id, nome) values (2, 'Rio de Janeiro');
insert into estado (id, nome) values (3, 'Minas Gerais');
insert into estado (id, nome) values (4, 'Bahia');
insert into estado (id, nome) values (5, 'Paraná');
insert into estado (id, nome) values (6, 'Rio Grande do Sul');
insert into estado (id, nome) values (7, 'Santa Catarina');
insert into estado (id, nome) values (8, 'Pernambuco');
insert into estado (id, nome) values (9, 'Ceará');
insert into estado (id, nome) values (10, 'Paraíba');

-- CIDADES (10) -> cada uma referenciando um estado
insert into cidade (id, nome, estado_id) values (1, 'São Paulo', 1);
insert into cidade (id, nome, estado_id) values (2, 'Campinas', 1);
insert into cidade (id, nome, estado_id) values (3, 'Rio de Janeiro', 2);
insert into cidade (id, nome, estado_id) values (4, 'Niterói', 2);
insert into cidade (id, nome, estado_id) values (5, 'Belo Horizonte', 3);
insert into cidade (id, nome, estado_id) values (6, 'Salvador', 4);
insert into cidade (id, nome, estado_id) values (7, 'Curitiba', 5);
insert into cidade (id, nome, estado_id) values (8, 'Porto Alegre', 6);
insert into cidade (id, nome, estado_id) values (9, 'Recife', 8);
insert into cidade (id, nome, estado_id) values (10, 'Fortaleza', 9);

-- FORMAS DE PAGAMENTO (10)
insert into forma_pagamento (id, descricao) values (1, 'PIX');
insert into forma_pagamento (id, descricao) values (2, 'CARTAO_DEBITO');
insert into forma_pagamento (id, descricao) values (3, 'CARTAO_CREDITO');
insert into forma_pagamento (id, descricao) values (4, 'DINHEIRO');
insert into forma_pagamento (id, descricao) values (5, 'VALE_REFEICAO');
insert into forma_pagamento (id, descricao) values (6, 'BOLETO');
insert into forma_pagamento (id, descricao) values (7, 'TRANSFERENCIA');
insert into forma_pagamento (id, descricao) values (8, 'PAYPAL');
insert into forma_pagamento (id, descricao) values (9, 'APPLE_PAY');
insert into forma_pagamento (id, descricao) values (10, 'GOOGLE_PAY');

-- RESTAURANTES (10) -> cada um referenciando uma cozinha
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Sabor Brasil', 5.00, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Trattoria Milano', 7.50, 2);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Sushi House', 6.00, 3);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (4, 'Dragon Palace', 8.00, 4);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (5, 'El Mexicano', 4.50, 5);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (6, 'Curry & Co', 6.50, 6);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (7, 'Bistro Paris', 9.00, 7);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (8, 'Mediterrâneo Azul', 5.50, 8);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (9, 'Nordic Bites', 10.00, 9);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (10, 'Verde Natural', 3.00, 10);

-- RELAÇÃO RESTAURANTE <-> FORMA_PAGAMENTO
-- atribuindo 2-3 formas por restaurante
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 3), (1, 4);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (2, 2), (2, 3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (3, 1), (3, 9), (3, 10);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (4, 3), (4, 4), (4, 7);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (5, 1), (5, 5), (5, 4);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (6, 6), (6, 7);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (7, 2), (7, 3), (7, 8);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (8, 1), (8, 3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (9, 3), (9, 10);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (10, 1), (10, 2), (10, 5);

-- PERMISSÕES (10)
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHA', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'CRIA_COZINHA', 'Permite criar cozinhas');
insert into permissao (id, nome, descricao) values (3, 'EDITA_COZINHA', 'Permite editar cozinhas');
insert into permissao (id, nome, descricao) values (4, 'DELETE_COZINHA', 'Permite excluir cozinhas');
insert into permissao (id, nome, descricao) values (5, 'CONSULTAR_RESTAURANTE', 'Permite consultar restaurantes');
insert into permissao (id, nome, descricao) values (6, 'GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos');
insert into permissao (id, nome, descricao) values (7, 'CONSULTAR_CIDADE', 'Permite consultar cidades');
insert into permissao (id, nome, descricao) values (8, 'GERENCIAR_USUARIOS', 'Permite gerenciar usuários');
insert into permissao (id, nome, descricao) values (9, 'GERAR_RELATORIOS', 'Permite gerar relatórios');
insert into permissao (id, nome, descricao) values (10, 'ADMIN', 'Acesso administrativo completo');