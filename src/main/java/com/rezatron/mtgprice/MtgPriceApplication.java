package com.rezatron.mtgprice;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableRabbit
@SpringBootApplication
@EnableScheduling
@EnableSchedulerLock( defaultLockAtMostFor = "PT30S" )
public
class MtgPriceApplication {

    public static
    void main(String[] args) {
        SpringApplication.run( MtgPriceApplication.class,
                               args );
    }

}
