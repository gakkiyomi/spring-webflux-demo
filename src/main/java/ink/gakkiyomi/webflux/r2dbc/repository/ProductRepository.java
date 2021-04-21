package ink.gakkiyomi.webflux.r2dbc.repository;

import ink.gakkiyomi.webflux.r2dbc.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: fangcong
 * @description:
 * @create: Created by work on 2021-04-21 11:35
 **/
@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {

}
