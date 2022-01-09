package br.edu.uniciv.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.uniciv.model.UsuarioPessoa;

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
    public void testeBuscarPorNome() {
        // arrange
        final String personNameToFind = "Filipe";

        // act
        final List<UsuarioPessoa> pessoas = dao.getEntityManager().createQuery("from UsuarioPessoa where nome = :nome")
            .setParameter("nome", personNameToFind)
            .getResultList();
        final UsuarioPessoa personFound = pessoas.get(0);

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
        assertNotNull(personUpdated);
        assertEquals(Long.valueOf(1), personUpdated.getId());
        assertEquals("filipe.fsn@uniciv.edu.br", personUpdated.getEmail());
        assertEquals(Integer.valueOf(38), personUpdated.getIdade());
        assertEquals("Filipe Updated", personUpdated.getNome());
        assertEquals("dos Santos Nascimento", personUpdated.getSobrenome());
        assertEquals("teste", personUpdated.getLogin());
        assertEquals("123", personUpdated.getSenha());
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

    @Test
    public void testeListar() {
        // arrange

        // act
        final List<UsuarioPessoa> pessoas = dao.listar(UsuarioPessoa.class);
        final UsuarioPessoa primeiraPessoa = pessoas.get(0); 

        // assert
        assertNotNull(pessoas);
        assertEquals(1, pessoas.size());
        assertEquals(Long.valueOf(1), primeiraPessoa.getId());
        assertEquals("filipe.fsn@uniciv.edu.br", primeiraPessoa.getEmail());
        assertEquals(Integer.valueOf(38), primeiraPessoa.getIdade());
        assertEquals("Filipe Updated", primeiraPessoa.getNome());
        assertEquals("dos Santos Nascimento", primeiraPessoa.getSobrenome());
        assertEquals("teste", primeiraPessoa.getLogin());
        assertEquals("123", primeiraPessoa.getSenha());
    }

    @Test
    public void testeQuerySomaIdades() {
        // arrange

        // act
        final Long somaIdades = (Long) dao.getEntityManager()
            .createQuery("select sum(u.idade) from UsuarioPessoa u")
            .getSingleResult();

        // assert
        assertEquals(Long.valueOf(76), somaIdades);
    }

    @Test
    public void testeQueryMediaIdades() {
        // arrange

        // act
        final Double somaIdades = (Double) dao.getEntityManager()
            .createQuery("select avg(u.idade) from UsuarioPessoa u")
            .getSingleResult();

        // assert
        assertEquals(Double.valueOf(38), somaIdades);
    }

    @Test
    public void testeUsuarioPessoaFindAll() {
        // arrange

        // act
        final List<UsuarioPessoa> users = dao.getEntityManager()
            .createNamedQuery("UsuarioPessoa.findAll")
            .getResultList();

        // assert
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    public void testeUsuarioPessoaFindByName() {
        // arrange
        final String nameToFind = "Filipe";

        // act
        final List<UsuarioPessoa> users = dao.getEntityManager()
            .createNamedQuery("UsuarioPessoa.findByName")
            .setParameter("nome", nameToFind)
            .getResultList();

        // assert
        assertNotNull(users);
        assertEquals(2, users.size());
    }

}
