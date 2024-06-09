package com.farabbit.springboot;

import com.farabbit.springboot.domain.Book;
import com.farabbit.springboot.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
public class Demo2Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }

    @Bean
    CommandLineRunner start(BookRepository bookRepository){
        return args -> {

            Flux.just(
                    new Book(35L,"josdem"),
                    new Book(36L,"tgrip"),
                    new Book(37L,"edzero"),
                    new Book(38L,"siedrix"),
                    new Book(39L,"mkheck"))
                    //.flatMap(bookRepository::save)
                    .subscribe(person -> log.info("person: {}", person));

        };
    }
}
