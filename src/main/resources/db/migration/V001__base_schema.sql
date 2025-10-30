CREATE TABLE `estado` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `cidade` (
  `estado_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkworrwk40xj58kevvh3evi500` (`estado_id`),
  CONSTRAINT `FKkworrwk40xj58kevvh3evi500` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `cozinha` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `forma_pagamento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `permissao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `restaurante` (
  `taxa_frete` decimal(38,2) DEFAULT NULL,
  `cozinha_id` bigint(20) DEFAULT NULL,
  `data_atualizacao` TIMESTAMP(6) NOT NULL,
  `data_criacao` TIMESTAMP(6) NOT NULL,
  `endereco_cidade_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `endereco_bairo` varchar(255) DEFAULT NULL,
  `endereco_cep` varchar(255) DEFAULT NULL,
  `endereco_complemento` varchar(255) DEFAULT NULL,
  `endereco_logradouro` varchar(255) DEFAULT NULL,
  `endereco_numero` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK76grk4roudh659skcgbnanthi` (`cozinha_id`),
  KEY `FKbc0tm7hnvc96d8e7e2ulb05yw` (`endereco_cidade_id`),
  CONSTRAINT `FK76grk4roudh659skcgbnanthi` FOREIGN KEY (`cozinha_id`) REFERENCES `cozinha` (`id`),
  CONSTRAINT `FKbc0tm7hnvc96d8e7e2ulb05yw` FOREIGN KEY (`endereco_cidade_id`) REFERENCES `cidade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `produto` (
  `ativo` bit(1) DEFAULT NULL,
  `preco` decimal(38,2) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `restaurante_id` bigint(20) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb9jhjyghjcn25guim7q4pt8qx` (`restaurante_id`),
  CONSTRAINT `FKb9jhjyghjcn25guim7q4pt8qx` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `restaurante_forma_pagamento` (
  `forma_pagamento_id` bigint(20) NOT NULL,
  `restaurante_id` bigint(20) NOT NULL,
  KEY `FK7aln770m80358y4olr03hyhh2` (`forma_pagamento_id`),
  KEY `FKa30vowfejemkw7whjvr8pryvj` (`restaurante_id`),
  CONSTRAINT `FK7aln770m80358y4olr03hyhh2` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `forma_pagamento` (`id`),
  CONSTRAINT `FKa30vowfejemkw7whjvr8pryvj` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
