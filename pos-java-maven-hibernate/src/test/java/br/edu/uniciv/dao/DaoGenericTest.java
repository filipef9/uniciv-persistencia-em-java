package br.edu.uniciv.dao;

import br.edu.uniciv.model.UsuarioPessoa;
import org.junit.Test;

public class DaoGenericTest {
    
    @Test
    public void testeDaoGeneric() {
        // arrange
        final UsuarioPessoa pessoa = new UsuarioPessoa();
        pessoa.setIdade(38);
        pessoa.setLogin("teste");
        pessoa.setNome("Filipe");
        pessoa.setSenha("123");
        pessoa.setSobrenome("dos Santos Nascimento");
        pessoa.setEmail("filipe.fsn@uniciv.edu.br");

        // act
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<>();
        daoGeneric.salvar(pessoa);
    }

    @Test
    public void testeBuscar() {
        final DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        final UsuarioPessoa pessoa = new UsuarioPessoa();
        pessoa.setId(2);
    }

}
