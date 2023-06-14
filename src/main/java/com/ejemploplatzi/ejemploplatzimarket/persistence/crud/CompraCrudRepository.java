package com.ejemploplatzi.ejemploplatzimarket.persistence.crud;

import com.ejemploplatzi.ejemploplatzimarket.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {
    Optional<List<Compra>> getByIdCliente(String idCliente);
}
