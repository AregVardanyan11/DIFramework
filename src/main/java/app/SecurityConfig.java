package app;

import core.annotations.Bean;
import core.annotations.Configuration;
import core.annotations.Inject;

@Configuration
public class SecurityConfig {
    @Inject
    UserRepository  userRepository;

    @Bean
    public User user(){
        return new User();
    }
}
