package br.edu.uniciv;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    private UsuarioSpringData createNewUser() {
        final UsuarioSpringData aNewUser = new UsuarioSpringData();
        aNewUser.setEmail("javaavancacdo@javaavancado.com");
        aNewUser.setIdade(38);
        aNewUser.setLogin("filipe.fsn");
        aNewUser.setSenha("12345678");
        aNewUser.setNome("Filipe");
        return aNewUser;
    }
    
    @Test
    public void testeInsert() {
        // Arrange
        final UsuarioSpringData aNewUser = createNewUser();

        // Act
        final UsuarioSpringData savedUser = dao.save(aNewUser);

        // Assert
        assertThat(savedUser, notNullValue());
    }

    @Test
    public void testeConsulta() {
        // Arrange
        final UsuarioSpringData aNewUser = createNewUser();
        final UsuarioSpringData savedUser = dao.save(aNewUser);

        // Act
        final boolean foundUser = dao.findById(savedUser.getId()).isPresent();

        // Assert
        assertThat(foundUser, is(true));
    }

}
