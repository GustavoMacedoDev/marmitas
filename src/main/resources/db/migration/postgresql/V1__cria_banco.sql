-- Table: categoria


CREATE TABLE categoria
(
  id serial NOT NULL,
  nome character varying(255),
  CONSTRAINT categoria_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE categoria
  OWNER TO postgres;

-- Table: cliente

-- DROP TABLE cliente;

CREATE TABLE cliente
(
  id serial NOT NULL,
  nome character varying(255) NOT NULL,
  telefone bigint NOT NULL,
  CONSTRAINT cliente_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cliente
  OWNER TO postgres;

-- Table: endereco



CREATE TABLE endereco
(
  id_endereco serial NOT NULL,
  bairro character varying(255),
  complemento character varying(255),
  logradouro character varying(255),
  numero character varying(255),
  cliente_id integer,
  CONSTRAINT endereco_pkey PRIMARY KEY (id_endereco),
  CONSTRAINT fk8s7ivtl4foyhrfam9xqom73n9 FOREIGN KEY (cliente_id)
      REFERENCES cliente (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE endereco
  OWNER TO postgres;

-- Table: forma_pagamento



CREATE TABLE forma_pagamento
(
  id serial NOT NULL,
  forma_pagamento character varying(255),
  CONSTRAINT forma_pagamento_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE forma_pagamento
  OWNER TO postgres;

-- Table: mesa

-- DROP TABLE mesa;

CREATE TABLE mesa
(
  id serial NOT NULL,
  codigo_mesa integer,
  numero_mesa character varying(255) NOT NULL,
  total_mesa double precision,
  valor_pago_parcial double precision,
  CONSTRAINT mesa_pkey PRIMARY KEY (id),
  CONSTRAINT numero_mesa_unique UNIQUE (numero_mesa)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE mesa
  OWNER TO postgres;

-- Table: opcao_atendimento



CREATE TABLE opcao_atendimento
(
  id integer NOT NULL,
  opcao character varying(255),
  CONSTRAINT opcao_atendimento_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE opcao_atendimento
  OWNER TO postgres;

-- Table: pagamento



CREATE TABLE pagamento
(
  id serial NOT NULL,
  valor_pago double precision,
  instante timestamp without time zone,
  forma_pagamento_id integer,
  mesa_id integer,
  cliente_id integer,
  status integer,
  CONSTRAINT pagamento_pkey PRIMARY KEY (id),
  CONSTRAINT fk352ao69evqjcu1364qecchoht FOREIGN KEY (mesa_id)
      REFERENCES mesa (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkn31voy77opqggmeklxo98saio FOREIGN KEY (forma_pagamento_id)
      REFERENCES forma_pagamento (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT cliente_id_fk FOREIGN KEY (cliente_id)
      REFERENCES cliente (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pagamento
  OWNER TO postgres;
  
  -- Table: pedido



CREATE TABLE pedido
(
  id_pedido serial NOT NULL,
  instante timestamp without time zone,
  cliente_id integer,
  forma_pagamento_id integer,
  mesa_id integer,
  total_pedido double precision,
  valor_pago double precision,
  op_atendimento_id integer,
  status integer,
  CONSTRAINT pedido_pkey PRIMARY KEY (id_pedido),
  CONSTRAINT fk30s8j2ktpay6of18lbyqn3632 FOREIGN KEY (cliente_id)
      REFERENCES cliente (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkf32po93klqxcumfjsf303g2vl FOREIGN KEY (mesa_id)
      REFERENCES mesa (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkoilw3jlmuak522jaf1a58cx9g FOREIGN KEY (op_atendimento_id)
      REFERENCES opcao_atendimento (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkqaa411xsl0xu4tkvt1wpccd3b FOREIGN KEY (forma_pagamento_id)
      REFERENCES forma_pagamento (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pedido
  OWNER TO postgres;
  
  
  -- Table: pessoa_juridica



CREATE TABLE pessoa_juridica
(
  id bigserial NOT NULL,
  cnpj character varying(255) NOT NULL,
  data_atualizacao timestamp without time zone NOT NULL,
  data_criacao timestamp without time zone NOT NULL,
  razao_social character varying(255) NOT NULL,
  status integer,
  CONSTRAINT pessoa_juridica_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pessoa_juridica
  OWNER TO postgres;

-- Table: produto



CREATE TABLE produto
(
  id_produto serial NOT NULL,
  nome character varying(255),
  preco double precision,
  categoria_id integer,
  pessoa_juridica_id integer,
  status integer NOT NULL,
  op_atendimento_id integer,
  CONSTRAINT produto_pkey PRIMARY KEY (id_produto),
  CONSTRAINT fknejovachcjmiujutr3jlqi5vy FOREIGN KEY (op_atendimento_id)
      REFERENCES opcao_atendimento (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fknyr8rqb4ik6vd23t5nkswnmv0 FOREIGN KEY (pessoa_juridica_id)
      REFERENCES pessoa_juridica (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkopu9jggwnamfv0c8k2ri3kx0a FOREIGN KEY (categoria_id)
      REFERENCES categoria (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE produto
  OWNER TO postgres;
  
  -- Table: taxa_entrega



CREATE TABLE taxa_entrega
(
  id serial NOT NULL,
  descricao character varying(255),
  valor double precision,
  CONSTRAINT taxa_entrega_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE taxa_entrega
  OWNER TO postgres;

-- Table: usuario



CREATE TABLE usuario
(
  id serial NOT NULL,
  email character varying(255),
  perfil character varying(255) NOT NULL,
  senha character varying(255),
  CONSTRAINT usuario_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE usuario
  OWNER TO postgres;
  
  -- Table: item_pedido

-- DROP TABLE item_pedido;

CREATE TABLE item_pedido
(
  desconto double precision,
  preco double precision,
  quantidade integer,
  pedido_id integer NOT NULL,
  produto_id integer NOT NULL,
  CONSTRAINT item_pedido_pkey PRIMARY KEY (pedido_id, produto_id),
  CONSTRAINT fk60ym08cfoysa17wrn1swyiuda FOREIGN KEY (pedido_id)
      REFERENCES pedido (id_pedido) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fktk55mn6d6bvl5h0no5uagi3sf FOREIGN KEY (produto_id)
      REFERENCES produto (id_produto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE item_pedido
  OWNER TO postgres;

