package br.cesed.facisa.repository;

import org.springframework.data.repository.CrudRepository;
import br.cesed.facisa.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    
}