package br.edu.uniciv.dao;

import br.edu.uniciv.model.UsuarioPessoa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class DaoGenericTest {

    private DaoGeneric<UsuarioPessoa> dao;

    @Before
    public void setUp() {
        dao = new DaoGeneric<>();
    }
    
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
        dao.salvar(pessoa);
    }

    @Test
    public void testeBuscar() {
        // arrange
        final UsuarioPessoa personToFind = new UsuarioPessoa();
        personToFind.setId(1L);

        // act
        final UsuarioPessoa personFound = dao.pesquisar(personToFind);

        // assert
        assertNotNull(personFound);
        assertEquals(Long.valueOf(1), personFound.getId());
        assertEquals("filipe.fsn@uniciv.edu.br", personFound.getEmail());
        assertEquals(Integer.valueOf(38), personFound.getIdade());
        assertEquals("Filipe", personFound.getNome());
        assertEquals("dos Santos Nascimento", personFound.getSobrenome());
        assertEquals("teste", personFound.getLogin());
        assertEquals("123", personFound.getSenha());
    }

    @Test
    public void testeBuscarPorId() {
        // arrange
        final Long personIdToFind = 1L;

        // act
        final UsuarioPessoa personFound = dao.pesquisarPorId(UsuarioPessoa.class, personIdToFind);

        // assert
        assertNotNull(personFound);
        assertEquals(Long.valueOf(1), personFound.getId());
        assertEquals("filipe.fsn@uniciv.edu.br", personFound.getEmail());
        assertEquals(Integer.valueOf(38), personFound.getIdade());
        assertEquals("Filipe", personFound.getNome());
        assertEquals("dos Santos Nascimento", personFound.getSobrenome());
        assertEquals("teste", personFound.getLogin());
        assertEquals("123", personFound.getSenha());
    }

    @Test
    public void testeUpdateMerge() {
        // arrange
        final UsuarioPessoa personToUpdate = dao.pesquisarPorId(UsuarioPessoa.class, 1L);
        personToUpdate.setNome("Filipe Updated");


        // act
        final UsuarioPessoa personUpdated = dao.updateMerge(personToUpdate);

        // assert
        assertNotNull(personToUpdate);
        assertEquals(Long.valueOf(1), personToUpdate.getId());
        assertEquals("filipe.fsn@uniciv.edu.br", personToUpdate.getEmail());
        assertEquals(Integer.valueOf(38), personToUpdate.getIdade());
        assertEquals("Filipe Updated", personToUpdate.getNome());
        assertEquals("dos Santos Nascimento", personToUpdate.getSobrenome());
        assertEquals("teste", personToUpdate.getLogin());
        assertEquals("123", personToUpdate.getSenha());
    }

    @Test
    public void testeDelete() {
        // arrange
        final UsuarioPessoa personToDelete = dao.pesquisarPorId(UsuarioPessoa.class, 9L);

        // act
        dao.deletarPorId(personToDelete);
        final UsuarioPessoa personDeleted = dao.pesquisarPorId(UsuarioPessoa.class, personToDelete.getId());

        // assert
        assertNull(personDeleted);
    }

}
