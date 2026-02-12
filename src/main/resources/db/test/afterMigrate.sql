set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;
delete from restaurante_usuario_responsavel;
delete from pedido;
delete from item_pedido;

set foreign_key_checks = 1;
-- Estados
INSERT INTO estado (id, nome) VALUES (1, 'São Paulo');
INSERT INTO estado (id, nome) VALUES (2, 'Rio de Janeiro');
INSERT INTO estado (id, nome) VALUES (3, 'Minas Gerais');
INSERT INTO estado (id, nome) VALUES (4, 'Bahia');
INSERT INTO estado (id, nome) VALUES (5, 'Paraná');

-- Cidades
INSERT INTO cidade (id, nome, estado_id) VALUES (1, 'São Paulo', 1);
INSERT INTO cidade (id, nome, estado_id) VALUES (2, 'Campinas', 1);
INSERT INTO cidade (id, nome, estado_id) VALUES (3, 'Rio de Janeiro', 2);
INSERT INTO cidade (id, nome, estado_id) VALUES (4, 'Niterói', 2);
INSERT INTO cidade (id, nome, estado_id) VALUES (5, 'Belo Horizonte', 3);
INSERT INTO cidade (id, nome, estado_id) VALUES (6, 'Uberlândia', 3);
INSERT INTO cidade (id, nome, estado_id) VALUES (7, 'Salvador', 4);
INSERT INTO cidade (id, nome, estado_id) VALUES (8, 'Curitiba', 5);

-- Cozinhas
INSERT INTO cozinha (id, nome) VALUES (1, 'Brasileira');
INSERT INTO cozinha (id, nome) VALUES (2, 'Italiana');
INSERT INTO cozinha (id, nome) VALUES (3, 'Japonesa');
INSERT INTO cozinha (id, nome) VALUES (4, 'Mexicana');
INSERT INTO cozinha (id, nome) VALUES (5, 'Árabe');
INSERT INTO cozinha (id, nome) VALUES (6, 'Francesa');

-- Formas de Pagamento
INSERT INTO forma_pagamento (id, descricao, ativo) VALUES (1, 'Dinheiro', 1);
INSERT INTO forma_pagamento (id, descricao, ativo) VALUES (2, 'Cartão de Crédito', 1);
INSERT INTO forma_pagamento (id, descricao, ativo) VALUES (3, 'Cartão de Débito', 1);
INSERT INTO forma_pagamento (id, descricao, ativo) VALUES (4, 'PIX', 1);
INSERT INTO forma_pagamento (id, descricao, ativo) VALUES (5, 'Vale Refeição', 1);
INSERT INTO forma_pagamento (id, descricao, ativo) VALUES (6, 'Vale Alimentação', 0);

-- Permissões
INSERT INTO permissao (id, nome, descricao) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permissao (id, nome, descricao) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
INSERT INTO permissao (id, nome, descricao) VALUES (3, 'CONSULTAR_RESTAURANTES', 'Permite consultar restaurantes');
INSERT INTO permissao (id, nome, descricao) VALUES (4, 'EDITAR_RESTAURANTES', 'Permite editar restaurantes');
INSERT INTO permissao (id, nome, descricao) VALUES (5, 'CONSULTAR_PEDIDOS', 'Permite consultar pedidos');
INSERT INTO permissao (id, nome, descricao) VALUES (6, 'GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos');
INSERT INTO permissao (id, nome, descricao) VALUES (7, 'CONSULTAR_USUARIOS', 'Permite consultar usuários');
INSERT INTO permissao (id, nome, descricao) VALUES (8, 'EDITAR_USUARIOS', 'Permite editar usuários');

-- Grupos
INSERT INTO grupo (id, nome) VALUES (1, 'Administrador');
INSERT INTO grupo (id, nome) VALUES (2, 'Gerente');
INSERT INTO grupo (id, nome) VALUES (3, 'Cliente');
INSERT INTO grupo (id, nome) VALUES (4, 'Restaurante');

-- Grupo Permissões
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (1, 1);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (1, 2);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (1, 3);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (1, 4);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (1, 5);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (1, 6);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (1, 7);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (1, 8);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (2, 1);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (2, 3);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (2, 5);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (2, 6);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (3, 5);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (4, 3);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (4, 4);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (4, 6);

-- Usuários
INSERT INTO usuario (id, nome, email, senha, data_criacao) VALUES (1, 'João Silva', 'joao.silva@email.com', '$2a$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', '2024-01-15 10:00:00');
INSERT INTO usuario (id, nome, email, senha, data_criacao) VALUES (2, 'Maria Santos', 'maria.santos@email.com', '$2a$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', '2024-01-20 14:30:00');
INSERT INTO usuario (id, nome, email, senha, data_criacao) VALUES (3, 'Pedro Oliveira', 'pedro.oliveira@email.com', '$2a$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', '2024-02-05 09:15:00');
INSERT INTO usuario (id, nome, email, senha, data_criacao) VALUES (4, 'Ana Costa', 'ana.costa@email.com', '$2a$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', '2024-02-10 16:45:00');
INSERT INTO usuario (id, nome, email, senha, data_criacao) VALUES (5, 'Carlos Mendes', 'carlos.mendes@email.com', '$2a$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', '2024-03-01 11:20:00');
INSERT INTO usuario (id, nome, email, senha, data_criacao) VALUES (6, 'Julia Ferreira', 'julia.ferreira@email.com', '$2a$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', '2024-03-15 13:00:00');

-- Usuario Grupos
INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (1, 1);
INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (2, 2);
INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (3, 3);
INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (4, 3);
INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (5, 4);
INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (6, 4);

-- Restaurantes
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairo, endereco_cep, endereco_cidade_id, data_criacao, data_atualizacao, ativo, aberto)
VALUES (1, 'Thai Gourmet', 10.00, 1, 'Rua das Flores', '123', 'Apto 101', 'Centro', '01310-100', 1, '2024-01-01 10:00:00', '2024-01-01 10:00:00', 1, 1);

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairo, endereco_cep, endereco_cidade_id, data_criacao, data_atualizacao, ativo, aberto)
VALUES (2, 'Bella Italia', 8.50, 2, 'Avenida Paulista', '1000', null, 'Bela Vista', '01310-200', 1, '2024-01-05 11:00:00', '2024-01-05 11:00:00', 1, 1);

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairo, endereco_cep, endereco_cidade_id, data_criacao, data_atualizacao, ativo, aberto)
VALUES (3, 'Sushi Master', 12.00, 3, 'Rua Oscar Freire', '500', 'Loja 2', 'Jardins', '01426-001', 1, '2024-01-10 09:30:00', '2024-01-10 09:30:00', 1, 1);

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairo, endereco_cep, endereco_cidade_id, data_criacao, data_atualizacao, ativo, aberto)
VALUES (4, 'Taco Loco', 7.00, 4, 'Rua Augusta', '2000', null, 'Consolação', '01304-000', 1, '2024-01-15 15:00:00', '2024-01-15 15:00:00', 1, 0);

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairo, endereco_cep, endereco_cidade_id, data_criacao, data_atualizacao, ativo, aberto)
VALUES (5, 'Kebab House', 9.00, 5, 'Rua da Consolação', '300', 'Sobreloja', 'Consolação', '01301-000', 1, '2024-01-20 12:00:00', '2024-01-20 12:00:00', 1, 1);

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairo, endereco_cep, endereco_cidade_id, data_criacao, data_atualizacao, ativo, aberto)
VALUES (6, 'Le Bistrot', 15.00, 6, 'Alameda Santos', '1500', 'Apto 10', 'Jardins', '01419-002', 1, '2024-02-01 10:30:00', '2024-02-01 10:30:00', 0, 0);

-- Restaurante Forma Pagamento
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 2);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 3);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 4);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (2, 2);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (2, 3);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (2, 4);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (3, 2);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (3, 4);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (4, 1);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (4, 4);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (5, 1);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (5, 2);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (5, 4);

-- Restaurante Usuário Responsável
INSERT INTO restaurante_usuario_responsavel (restaurante_id, usuario_id) VALUES (1, 5);
INSERT INTO restaurante_usuario_responsavel (restaurante_id, usuario_id) VALUES (2, 5);
INSERT INTO restaurante_usuario_responsavel (restaurante_id, usuario_id) VALUES (3, 6);
INSERT INTO restaurante_usuario_responsavel (restaurante_id, usuario_id) VALUES (4, 6);
INSERT INTO restaurante_usuario_responsavel (restaurante_id, usuario_id) VALUES (5, 5);

-- Produtos
INSERT INTO produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES (1, 'Pad Thai', 'Macarrão de arroz frito com camarão, amendoim e legumes', 32.90, 1, 1);
INSERT INTO produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES (2, 'Tom Yum', 'Sopa picante tailandesa com camarão e cogumelos', 28.50, 1, 1);
INSERT INTO produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES (3, 'Pizza Margherita', 'Molho de tomate, mussarela e manjericão fresco', 45.00, 1, 2);
INSERT INTO produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES (4, 'Spaghetti Carbonara', 'Massa com bacon, ovos, queijo parmesão e pimenta preta', 38.00, 1, 2);
INSERT INTO produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES (5, 'Risoto de Funghi', 'Arroz arbóreo com cogumelos e parmesão', 42.00, 1, 2);
INSERT INTO produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES (6, 'Combo Sushi 20 peças', '10 sushis e 10 sashimis variados', 68.00, 1, 3);
INSERT INTO produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES (7, 'Temaki de Salmão', 'Cone de alga com arroz, salmão e cream cheese', 22.00, 1, 3);
INSERT INTO produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES (8, 'Hot Roll', 'Sushi empanado com salmão e cream cheese', 35.00, 1, 3);
INSERT INTO produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES (9, 'Burrito de Carne', 'Tortilla recheada com carne, feijão, queijo e guacamole', 28.00, 1, 4);
INSERT INTO produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES (10, 'Tacos Mexicanos (3 unidades)', 'Tortilhas com carne moída temperada e molho picante', 24.00, 1, 4);
INSERT INTO produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES (11, 'Kebab de Cordeiro', 'Espetinho de cordeiro com molho tahine e salada', 36.00, 1, 5);
INSERT INTO produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES (12, 'Falafel', 'Bolinhos de grão de bico fritos com molho de iogurte', 26.00, 1, 5);
INSERT INTO produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES (13, 'Coq au Vin', 'Frango ao vinho tinto com cogumelos e bacon', 52.00, 0, 6);

-- Pedidos
INSERT INTO pedido (id, sub_total, taxa_frete, valor_total, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairo, status_pedido, data_criacao, data_confirmacao, data_entrega, data_cancelamento)
VALUES (1, 61.40, 10.00, 71.40, 1, 3, 4, 1, '01310-100', 'Rua das Acácias', '456', 'Apto 502', 'Vila Mariana', 'ENTREGUE', '2024-02-01 18:30:00', '2024-02-01 18:35:00', '2024-02-01 19:45:00', null);

INSERT INTO pedido (id, sub_total, taxa_frete, valor_total, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairo, status_pedido, data_criacao, data_confirmacao, data_entrega, data_cancelamento)
VALUES (2, 125.00, 8.50, 133.50, 2, 4, 2, 1, '01310-200', 'Avenida Brigadeiro', '2500', null, 'Consolação', 'ENTREGUE', '2024-02-03 19:00:00', '2024-02-03 19:05:00', '2024-02-03 20:15:00', null);

INSERT INTO pedido (id, sub_total, taxa_frete, valor_total, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairo, status_pedido, data_criacao, data_confirmacao, data_entrega, data_cancelamento)
VALUES (3, 90.00, 12.00, 102.00, 3, 3, 4, 1, '01310-100', 'Rua das Acácias', '456', 'Apto 502', 'Vila Mariana', 'CONFIRMADO', '2024-02-05 20:00:00', '2024-02-05 20:05:00', null, null);

INSERT INTO pedido (id, sub_total, taxa_frete, valor_total, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairo, status_pedido, data_criacao, data_confirmacao, data_entrega, data_cancelamento)
VALUES (4, 52.00, 7.00, 59.00, 4, 4, 1, 1, '01310-200', 'Avenida Brigadeiro', '2500', null, 'Consolação', 'CRIADO', '2024-02-07 21:00:00', null, null, null);

INSERT INTO pedido (id, sub_total, taxa_frete, valor_total, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairo, status_pedido, data_criacao, data_confirmacao, data_entrega, data_cancelamento)
VALUES (5, 36.00, 9.00, 45.00, 5, 3, 2, 1, '01310-100', 'Rua das Acácias', '456', 'Apto 502', 'Vila Mariana', 'CANCELADO', '2024-02-08 18:00:00', null, null, '2024-02-08 18:30:00');

-- Itens do Pedido
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) VALUES (1, 1, 1, 1, 32.90, 32.90, 'Sem pimenta');
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) VALUES (2, 1, 2, 1, 28.50, 28.50, null);

INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) VALUES (3, 2, 3, 1, 45.00, 45.00, 'Bem passada');
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) VALUES (4, 2, 4, 2, 38.00, 76.00, 'Pouco sal');
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) VALUES (5, 2, 5, 1, 42.00, 42.00, null);

INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) VALUES (6, 3, 6, 1, 68.00, 68.00, 'Sem wasabi');
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) VALUES (7, 3, 7, 1, 22.00, 22.00, null);

INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) VALUES (8, 4, 9, 1, 28.00, 28.00, 'Extra guacamole');
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) VALUES (9, 4, 10, 1, 24.00, 24.00, 'Bem picante');

INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) VALUES (10, 5, 11, 1, 36.00, 36.00, null);
