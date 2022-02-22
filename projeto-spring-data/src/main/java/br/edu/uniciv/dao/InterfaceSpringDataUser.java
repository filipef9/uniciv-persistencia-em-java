package br.edu.uniciv.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.uniciv.model.UsuarioSpringData;

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long> {
    
    Collection<UsuarioSpringData> findAll();

    @Query(value = "select p from UsuarioSpringData p where p.nome like %?1%")
    Collection<UsuarioSpringData> buscaPorNome(final String nome);

    @Query(value = "select p from UsuarioSpringData p where p.nome = :paramnome")
    UsuarioSpringData buscaPorNomeParam(@Param("paramnome") String paramnome);

    @Transactional
    @Modifying
    @Query("delete from UsuarioSpringData u where u.nome = :nome")
    void deletePorNome(@Param("nome") String nome);

}
