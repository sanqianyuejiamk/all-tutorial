package com.mengka.springboot.injecting_mocks_in_spring;

import com.mengka.springboot.manager.NoteManager;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 *  The @Profile annotation tells Spring to apply this configuration only when the “test” profile is active.
 *
 * @author mengka
 * @version 2021/4/18
 * @since
 */
@Profile("test")
@Configuration
public class TestConfiguration {

    @Bean
    @Primary
    public NoteManager nameService() {
        return Mockito.mock(NoteManager.class);
    }
}
