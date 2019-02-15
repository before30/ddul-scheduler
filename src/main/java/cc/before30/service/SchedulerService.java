package cc.before30.service;

import cc.before30.config.DdulProperties;
import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by before30 on 01/05/2017.
 */
@Slf4j
@Component
public class SchedulerService {
    @Autowired
    private Slack slack;

    @Autowired
    private DdulProperties ddulProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(cron = "0 1 7-23 * * *", zone = "Asia/Seoul")
    public void requestToWriteWhatDidUDo() {
        int hour = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).getHour();
        log.info("current hour : {}", hour);

        hour = (hour-1)>0?(hour-1)%24:23;

        Payload payload = Payload.builder()
                .channel(ddulProperties.getSlackChannelName())
                .username("coach")
                .iconEmoji(":smile_cat:")
                .text("[" + hour + ":00-" + hour + ":59]: What did you do??" )
                .build();
        try {
            WebhookResponse send = slack.send(ddulProperties.getSlackWebhookUrl(), payload);
            log.info("{}", ddulProperties.getSlackWebhookUrl());
            log.info("response {} {}", send.getCode(), send.getBody());
        } catch (IOException e) {
            log.error("exception in sending message", e.getMessage());
        }

    }

}
