package com.tongji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class MySpringBootApplication
{

    public MySpringBootApplication()
    {
    }

    public static void main(String[] args)
    {
        SpringApplication.run(MySpringBootApplication.class);
    }
}
