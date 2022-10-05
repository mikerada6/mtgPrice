package com.rezatron.mtgprice.configuration;

import com.mongodb.client.MongoClient;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.provider.mongo.MongoLockProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public
class SchedulerConfiguration {

        @Bean
        public LockProvider lockProvider(DataSource dataSource) {
            return new JdbcTemplateLockProvider( dataSource);
        }


//    @Bean
//    public
//    LockProvider lockProvider(MongoClient mongo) {
//
//        return new MongoLockProvider( mongo,
//                                      "cards" );
//    }
}
