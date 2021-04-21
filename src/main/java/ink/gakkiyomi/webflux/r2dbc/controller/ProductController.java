package ink.gakkiyomi.webflux.r2dbc.controller;

import ink.gakkiyomi.webflux.r2dbc.model.Product;
import ink.gakkiyomi.webflux.r2dbc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: fangcong
 * @description:
 * @create: Created by work on 2021-04-21 11:47
 **/
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("all")
    public Flux<Product> getAll(){
        final Flux<Product> allProducts = this.productService.getAllProducts();

        return allProducts;
    }

    @GetMapping("{productId}")
    public Mono<ResponseEntity<Product>> getProductById(@PathVariable int productId){
        return this.productService.getProductById(productId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<Product> createProduct(@RequestBody Mono<Product> productMono){

        return productMono.map(x -> x.setAsNew()).flatMap(this.productService::createProduct);
    }

    @PutMapping("{productId}")
    public Mono<Product> updateProduct(@PathVariable int productId,
                                       @RequestBody Mono<Product> productMono){
        return this.productService.updateProduct(productId, productMono);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable int id){
        return this.productService.deleteProduct(id);
    }

}
