package com.platzi.platzi.market.persitencia;

import com.platzi.platzi.market.domain.Product;
import com.platzi.platzi.market.domain.respository.ProductRepository;
import com.platzi.platzi.market.persitencia.crud.ProductoCrusRepository;
import com.platzi.platzi.market.persitencia.entities.Producto;
import com.platzi.platzi.market.persitencia.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRespositorio implements ProductRepository {
    @Autowired
    private ProductMapper mapper;

    @Autowired
    private ProductoCrusRepository productoCrusRepository;

    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrusRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrusRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> gerScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrusRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrusRepository.findById(productId).map(prod -> mapper.toProduct(prod));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrusRepository.save(producto));
    }

    @Override
    public void delete(int productId){
        productoCrusRepository.deleteById(productId);
    }
}
