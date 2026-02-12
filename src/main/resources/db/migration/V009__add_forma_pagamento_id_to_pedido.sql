-- Migration: Adiciona forma_pagamento_id na tabela pedido
ALTER TABLE pedido ADD COLUMN forma_pagamento_id BIGINT;
ALTER TABLE pedido ADD CONSTRAINT FK_pedido_forma_pagamento FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento(id);