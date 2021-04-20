package ink.gakkiyomi.webflux.route;

import ink.gakkiyomi.webflux.handler.GreetingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


/**
 * @author: fangcong
 * @description: 使用函数式的路径路由，将路径路由和处理方法分开
 * @create: Created by work on 2021-04-20 11:17
 **/


@Configuration
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {

        return RouterFunctions
                .route(RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), greetingHandler::hello);
    }

    @Bean
    public RouterFunction<ServerResponse> route2(GreetingHandler greetingHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello2/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        request -> greetingHandler.hello2(request));
    }

}
