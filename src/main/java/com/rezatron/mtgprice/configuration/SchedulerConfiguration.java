package com.rezatron.mtgprice.configuration;


import com.mongodb.client.MongoClient;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.mongo.MongoLockProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public
class SchedulerConfiguration {
    @Value( "spring.data.mongodb.database" )
    private String databaseName;

    @Bean
    public
    LockProvider lockProvider(MongoClient mongo) {
        return new MongoLockProvider( mongo.getDatabase( "mtgPrice_dev" ) );
    }
}
