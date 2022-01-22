package br.edu.uniciv;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.uniciv.dao.InterfaceSpringDataUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-config.xml" })
public class AppSpringDataTest {

    @Autowired
    private InterfaceSpringDataUser dao;
    
    @Test
    public void iniciouSprintTesteComSucesso() {
        System.out.println("Iniciou Spring com sucesso!");
    }

}
