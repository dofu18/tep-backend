package com.main.timeshareexchangeplatform_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})

public class TimeshareExchangePlatformBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeshareExchangePlatformBackendApplication.class, args);
    }

}
