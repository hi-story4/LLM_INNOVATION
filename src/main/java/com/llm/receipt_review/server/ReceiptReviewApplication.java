package com.llm.receipt_review.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ReceiptReviewApplication {



    public static void main(String[] args) {
        SpringApplication.run(ReceiptReviewApplication.class, args);

    }

}
