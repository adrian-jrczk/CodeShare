package codeshare.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncoderProvider {

    @Bean
    PasswordEncoder get() {
        return new BCryptPasswordEncoder();
    }
}
