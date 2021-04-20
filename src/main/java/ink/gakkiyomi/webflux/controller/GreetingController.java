package ink.gakkiyomi.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author: fangcong
 * @description: 使用和mvc一样的注解式路由 降低学习成本
 * @create: Created by work on 2021-04-20 12:25
 **/
@RestController
public class GreetingController {

    @GetMapping("/hello3/{id}")
    public Mono<String> hello3(@PathVariable String id) {
        return Mono.just("hello3 " + id);
    }
}
