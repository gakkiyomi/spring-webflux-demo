package ink.gakkiyomi.webflux.handler;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

/**
 * @author: fangcong
 * @description:
 * @create: Created by work on 2021-04-20 11:14
 **/


@Component
public class GreetingHandler{

    private static final Logger logger = LoggerFactory.getLogger(GreetingHandler.class);


    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Hello, Spring!"));
    }

    public Mono<ServerResponse> hello2(ServerRequest request) {
        final String id = request.pathVariable("id");
        final JSONObject object = new JSONObject();
        object.put("a", "b");
        object.put("age", 1);
        object.put("id", id);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(object));
    }

}
