package com.rezatron.mtgprice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public
class MtgPriceApplication {

    public static
    void main(String[] args) {
        SpringApplication.run( MtgPriceApplication.class,
                               args );
    }

}
