CREATE TABLE tbl_telefone (
	id BIGINT NOT NULL,
	numero CHARACTER VARYING(255) NOT NULL,
	tipo CHARACTER VARYING(255) NOT NULL,
	idUser BIGINT NOT NULL,
	CONSTRAINT telefone_id PRIMARY KEY (id)
);

ALTER TABLE tbl_telefone
ADD CONSTRAINT tbl_telefone_iduser_fk
FOREIGN KEY (idUser)
REFERENCES tbl_user (id);
