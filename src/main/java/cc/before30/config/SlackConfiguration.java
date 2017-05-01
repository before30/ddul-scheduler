package cc.before30.config;

import com.github.seratch.jslack.Slack;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by before30 on 01/05/2017.
 */
@Configuration
public class SlackConfiguration {

    @Bean
    public Slack slack() {
        return Slack.getInstance();
    }

}
