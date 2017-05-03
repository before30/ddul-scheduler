package cc.before30.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by before30 on 03/05/2017.
 */

@ConfigurationProperties(prefix = "ddul")
@Getter
@Setter
public class DdulProperties {

    private String slackWebhookUrl;

    private String slackChannelName;

    private String serviceUrl;

}
