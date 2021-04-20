package ink.gakkiyomi.webflux.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: fangcong
 * @description: 使用和mvc一样的注解式路由 降低学习成本
 * @create: Created by work on 2021-04-20 12:25
 **/
@RestController
public class GreetingController {

    public static List<String> strings;

    static {
         strings = new ArrayList<String>() {{
            add("a");
            add("b");
            add("c");
            add("d");
            add("e");
            add("f");
            add("g");
            add("h");
        }};
    }

    @GetMapping("/hello3/{id}")
    public Mono<String> hello3(@PathVariable String id) {
        return Mono.just("hello3 " + id);
    }

    @GetMapping(value = "list", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> list() {
        return Flux.fromStream(strings.stream());
    }

    @GetMapping(value = "list2", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<String>> list2() {
        return Mono.just(strings);
    }

}
