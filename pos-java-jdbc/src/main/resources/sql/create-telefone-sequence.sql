CREATE SEQUENCE telefonesequence
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;

ALTER TABLE tbl_telefone ALTER COLUMN id SET DEFAULT NEXTVAL('telefonesequence'::regclass);
