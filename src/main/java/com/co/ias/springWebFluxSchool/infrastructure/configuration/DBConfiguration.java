package com.co.ias.springWebFluxSchool.infrastructure.configuration;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
@Configuration
@EnableR2dbcRepositories
public class DBConfiguration  extends AbstractR2dbcConfiguration {
    @Bean
    public ConnectionFactory connectionFactory(){
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration
                        .builder()
                        .host("localhost")
                        .port(5433)
                        .username("postgres")
                        .password("postgres")
                        .database("reactiveSchool")
                        .build()
        );
    }

}