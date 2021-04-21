package ink.gakkiyomi.webflux;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@SpringBootApplication
public class SpringWebfluxDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxDemoApplication.class, args);

    }

    /**
     * 初始化数据库
     * r2dbc 不支持像JPA一样可以通过配置来实现通过实体类来生成数据库表
     * @param connectionFactory
     * @return
     */
    @Bean
    ConnectionFactoryInitializer initializer(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ByteArrayResource((
                "DROP TABLE IF EXISTS product;"
                + "CREATE TABLE product (id SERIAL PRIMARY KEY, description VARCHAR(100) NOT NULL, price float(8) NOT NULL);")
                .getBytes())));

        return initializer;
    }

}
