\connect posjava

CREATE TABLE tbl_user (
	id BIGINT NOT NULL,
	nome CHARACTER VARYING(255),
	email CHARACTER VARYING(255),
	CONSTRAINT user_pk PRIMARY KEY (id)
);
