package com.platzi.platzi.market.persitencia.crud;

import com.platzi.platzi.market.persitencia.entities.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {

    Optional<List<Compra>> findByIdCliente(String idCliente);


}
