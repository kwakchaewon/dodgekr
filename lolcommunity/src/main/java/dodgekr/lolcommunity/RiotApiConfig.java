package dodgekr.lolcommunity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RiotApiConfig {
    @Value("${RIOT_API_KEY}")
    private String RIOT_API_KEY;

    public String getRIOT_API_KEY() {
        return RIOT_API_KEY;
    }

    @Override
    public String toString() {
        return "RIOT_API_KEY = " + RIOT_API_KEY;
    }
}
