package com.javageeks.curdexamples.service;

import com.javageeks.curdexamples.model.Product;
import com.javageeks.curdexamples.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public void saveProduct(Product product){
        productRepository.save(product);
    }

    public List<Product> showProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product product){
        return productRepository.findById(id).map( prd -> {
            prd.setName(product.getName());
            prd.setCost(product.getCost());
            prd.setDescription(product.getDescription());
            prd.setQuantity(product.getQuantity());
            return productRepository.save(prd);
        }).orElseThrow(() -> new RuntimeException("product not found"+id));
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
