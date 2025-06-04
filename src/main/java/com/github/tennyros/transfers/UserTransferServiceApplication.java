package com.github.tennyros.transfers;

import com.github.tennyros.transfers.util.DotenvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserTransferServiceApplication {

    public static void main(String[] args) {
        DotenvLoader.load();
        SpringApplication.run(UserTransferServiceApplication.class, args);
    }

}
