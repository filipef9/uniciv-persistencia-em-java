package br.edu.uniciv;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.uniciv.dao.InterfaceSpringDataUser;
import br.edu.uniciv.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-config.xml" })
public class AppSpringDataTest {

    @Autowired
    private InterfaceSpringDataUser dao;

    @After
    public void tearDown() {
        dao.deleteAll();
    }

    private UsuarioSpringData createAnUser() {
        final UsuarioSpringData aNewUser = new UsuarioSpringData();
        aNewUser.setEmail("javaavancacdo@javaavancado.com");
        aNewUser.setIdade(38);
        aNewUser.setLogin("filipe.fsn");
        aNewUser.setSenha("12345678");
        aNewUser.setNome("Filipe");
        return aNewUser;
    }

    private Collection<UsuarioSpringData> createListOfUsers() {
        final UsuarioSpringData filipe = new UsuarioSpringData();
        filipe.setEmail("javaavancacdo@javaavancado.com");
        filipe.setIdade(38);
        filipe.setLogin("filipe.fsn");
        filipe.setSenha("12345678");
        filipe.setNome("Filipe");

        final UsuarioSpringData miqueias = new UsuarioSpringData();
        miqueias.setEmail("javaavancacdo@javaavancado.com");
        miqueias.setIdade(38);
        miqueias.setLogin("filipe.fsn");
        miqueias.setSenha("12345678");
        miqueias.setNome("Filipe");

        final UsuarioSpringData miguel = new UsuarioSpringData();
        miguel.setEmail("javaavancacdo@javaavancado.com");
        miguel.setIdade(38);
        miguel.setLogin("filipe.fsn");
        miguel.setSenha("12345678");
        miguel.setNome("Filipe");

        return Arrays.asList(filipe, miqueias, miguel);
    }
    
    @Test
    public void testeInsert() {
        // Arrange
        final UsuarioSpringData aNewUser = createAnUser();

        // Act
        final UsuarioSpringData savedUser = dao.save(aNewUser);

        // Assert
        assertThat(savedUser, notNullValue());
    }

    @Test
    public void testeConsulta() {
        // Arrange
        final UsuarioSpringData aNewUser = createAnUser();
        final UsuarioSpringData savedUser = dao.save(aNewUser);

        // Act
        final boolean foundUser = dao.findById(savedUser.getId()).isPresent();

        // Assert
        assertThat(foundUser, is(true));
    }

    @Test
    public void testeConsultaTodos() {
        // Arrange
        final Collection<UsuarioSpringData> newUsers = createListOfUsers();
        dao.saveAll(newUsers);

        // Act
        Collection<UsuarioSpringData> allUsers = dao.findAll();

        // Assert
        assertThat(allUsers, is(not(empty())));
        assertThat(allUsers, hasSize(3));
    }

    @Test
    public void testeUpdate() {
        // Arrange
        final UsuarioSpringData aNewUser = createAnUser();
        final UsuarioSpringData savedUser = dao.save(aNewUser);
        savedUser.setNome("Filipe Updated");

        // Act
        final UsuarioSpringData updatedUser = dao.save(savedUser);

        // Assert
        assertThat(updatedUser.getNome(), is(equalTo("Filipe Updated")));
    }

}
