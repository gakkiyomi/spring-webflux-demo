package ink.gakkiyomi.webflux.r2dbc.service;

import ink.gakkiyomi.webflux.r2dbc.model.Product;
import ink.gakkiyomi.webflux.r2dbc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: fangcong
 * @description:
 * @create: Created by work on 2021-04-21 11:36
 **/
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Flux<Product> getAllProducts() {
        return repository.findAll();
    }

    public Mono<Product> getProductById(final Integer id) {
        return repository.findById(id);
    }

    public Mono<Product> createProduct(final Product product) {
        return repository.save(product);
    }

    public Mono<Product> updateProduct(int productId, final Mono<Product> productMono) {
        return repository.findById(productId)
                .flatMap(p -> productMono.map(u -> {
                    p.setDescription(u.getDescription());
                    p.setPrice(u.getPrice());
                    return p;
                }))
                .flatMap(p -> repository.save(p));
    }

    public Mono<Void> deleteProduct(final Integer id) {
        return repository.deleteById(id);
    }


}
