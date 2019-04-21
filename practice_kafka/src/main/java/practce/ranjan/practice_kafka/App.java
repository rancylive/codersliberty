package practce.ranjan.practice_kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableKafka
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
