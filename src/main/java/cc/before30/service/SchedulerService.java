package cc.before30.service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

    @Value("${slack.webhook.url}")
    private String webhookUrl;

    @Value("${slack.channel.name}")
    private String channelName;

    @Value("${ddulscheduler.url}")
    private String ddulSchedulerUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(cron = "0 1 0,9-23 * * *", zone = "Asia/Seoul")
    public void requestToWriteWhatDidUDo() {
        int hour = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).getHour();
        log.info("current hour : {}", hour);

        hour = (hour-1)>0?(hour-1)%24:23;

        Payload payload = Payload.builder()
                .channel(channelName)
                .username("coach")
                .iconEmoji(":smile_cat:")
                .text("[" + hour + ":00-" + hour + ":59]: What did you do?" )
                .build();
        try {
            slack.send(webhookUrl,payload);
        } catch (IOException e) {
            log.error("exception in sending message", e.getMessage());
        }

    }

    @Scheduled(cron = "0 * 8-23 * * *", zone = "Asia/Seoul")
    public void requestToDdulSchedulerInHeroku() {
        ResponseEntity<String> response = restTemplate.getForEntity(ddulSchedulerUrl, String.class);
        log.info("response {} ", response.getBody());
    }

}
