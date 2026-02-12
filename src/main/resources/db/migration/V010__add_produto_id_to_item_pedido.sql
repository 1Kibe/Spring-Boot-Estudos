-- Adiciona a coluna produto_id na tabela item_pedido e cria a foreign key para produto
ALTER TABLE item_pedido ADD COLUMN produto_id BIGINT;
ALTER TABLE item_pedido ADD CONSTRAINT FK_item_pedido_produto FOREIGN KEY (produto_id) REFERENCES produto(id);
