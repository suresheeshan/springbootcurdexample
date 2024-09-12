package com.javageeks.curdexamples.controller;

import com.javageeks.curdexamples.model.Product;
import com.javageeks.curdexamples.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/V1")
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping("/createProduct")
    public void createProduct(@RequestBody Product product){
        productService.saveProduct(product);
    }

    @GetMapping("/showProducts")
    public List<Product> showAllProducts(){
        return productService.showProducts();
    }
    @GetMapping("/{id}")
    public Optional<Product> displayByProduct(@RequestParam Long id){
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@RequestParam Long id, @RequestBody Product product){
      try{
            Product prd = productService.updateProduct(id,product);
            return ResponseEntity.ok(prd);
      }catch (Exception e){
          return ResponseEntity.notFound().build();
      }
    }

    public void deleteProduct(@RequestParam Long id){
        productService.deleteProduct(id);
    }
}
