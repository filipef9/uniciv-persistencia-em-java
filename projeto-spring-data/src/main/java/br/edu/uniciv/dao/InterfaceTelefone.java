package br.edu.uniciv.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.uniciv.model.Telefone;

@Repository
public interface InterfaceTelefone extends CrudRepository<Telefone, Long> {
    
}
