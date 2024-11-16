package neu.edu.kafka;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class LogSendTask {
    @Inject
    KafkaLogProducer kafkaLogProducer;
    private ExecutorService executorService;
    private static final String[] USERNAMES = {"frank", "jane", "mike"};
    private static final String[] RESOURCES = {"/index.html", "/images/logo.png", "/css/style.css"};
    private static final int[] RESPONSE_SIZES = {2326, 4382, 1520};
    private static final Random RANDOM = new Random();

    @PostConstruct
    public void init() {
        executorService = Executors.newSingleThreadExecutor();
        executorService.submit(this::sendLogs);
    }

    @PreDestroy
    public void cleanup() {
        if (executorService != null) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }
        }
    }

    private void sendLogs() {
        String line;
        int i = 0;
        int maxNumLogs = 10;
        while (!Thread.currentThread().isInterrupted()) {
            if (i >= maxNumLogs) {
                break;
            }
            i++;
            line = createLogEntry();
            kafkaLogProducer.sendLogMessage(line);
        }
    }

    private static String createLogEntry() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z");
        return "127.0.0.1 - " + USERNAMES[RANDOM.nextInt(USERNAMES.length)] + " [" + sdf.format(new Date()) + "] \"" + "GET " + RESOURCES[RANDOM.nextInt(RESOURCES.length)] + " HTTP/1.0\" " + (RANDOM.nextInt(5) == 0 ? 404 : 200) + " " + RESPONSE_SIZES[RANDOM.nextInt(RESPONSE_SIZES.length)];
    }

}