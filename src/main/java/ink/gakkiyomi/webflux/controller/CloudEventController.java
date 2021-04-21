package ink.gakkiyomi.webflux.controller;

import com.alibaba.fastjson.JSONObject;
import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.spring.http.CloudEventHttpUtils;
import io.cloudevents.spring.webflux.CloudEventHttpMessageReader;
import io.cloudevents.spring.webflux.CloudEventHttpMessageWriter;
import org.springframework.boot.web.codec.CodecCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.CodecConfigurer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

/**
 * @author: fangcong
 * @description:
 * @create: Created by work on 2021-04-20 15:45
 **/
@RestController
@RequestMapping("/event")
public class CloudEventController {


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CloudEvent> echo(@RequestBody JSONObject object, @RequestHeader HttpHeaders headers) { //使用标准的cloudEvent来传送数据
        CloudEvent attributes = CloudEventHttpUtils.fromHttp(headers) //
                .withId(UUID.randomUUID().toString()) //
                .withSource(URI.create("https://spring.io/foos")) //
                .withType("io.spring.event.Foo") //
                .withData(JSONObject.toJSONBytes(object))
                .build();
        return Mono.just(attributes);
    }

    @PostMapping("/mono")
    public Mono<CloudEvent> event(@RequestBody Mono<CloudEvent> body) {
        return body.map(event -> {
            final CloudEventData data = event.getData();
            final JSONObject parse = (JSONObject)JSONObject.parse(data.toBytes());
            parse.put("time", new Date());
            final CloudEvent build = CloudEventBuilder.from(event) //
                    .withId(event.getId()) //
                    .withSource(event.getSource()) //
                    .withType(event.getType()) //
                    .withData(JSONObject.toJSONBytes(parse)) //
                    .build();

            return build;
        });
    }

    @Configuration
    public static class CloudEventHandlerConfiguration implements CodecCustomizer {

        @Override
        public void customize(CodecConfigurer configurer) {
            configurer.customCodecs().register(new CloudEventHttpMessageReader());
            configurer.customCodecs().register(new CloudEventHttpMessageWriter());
        }

    }
}
