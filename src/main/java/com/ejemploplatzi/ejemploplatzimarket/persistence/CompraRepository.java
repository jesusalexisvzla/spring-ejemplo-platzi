package com.ejemploplatzi.ejemploplatzimarket.persistence;

import com.ejemploplatzi.ejemploplatzimarket.domain.Purchase;
import com.ejemploplatzi.ejemploplatzimarket.domain.repository.PurchaseRepository;
import com.ejemploplatzi.ejemploplatzimarket.persistence.crud.CompraCrudRepository;
import com.ejemploplatzi.ejemploplatzimarket.persistence.entity.Compra;
import com.ejemploplatzi.ejemploplatzimarket.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.getByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
