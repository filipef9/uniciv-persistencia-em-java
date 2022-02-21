package br.edu.uniciv.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.uniciv.model.UsuarioSpringData;

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long> {
    
    Collection<UsuarioSpringData> findAll();

    @Query(value = "select p from UsuarioSpringData p where p.nome like %?1%")
    Collection<UsuarioSpringData> buscaPorNome(final String nome);

}
