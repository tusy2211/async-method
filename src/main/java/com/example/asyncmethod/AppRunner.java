package com.example.asyncmethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Component
public class AppRunner implements CommandLineRunner {

    @Qualifier("taskExecutor")
    @Autowired
    Executor executor;

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final GitHubLookupService gitHubLookupService;

    public AppRunner(GitHubLookupService gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();

        // Use Async no return result
        logger.info("Use Async no return result!");
        // Kick of multiple, asynchronous lookups
        CompletableFuture<User> page1 = gitHubLookupService.findUser(1L);
        CompletableFuture<User> page2 = gitHubLookupService.findUser(2L);
        CompletableFuture<User> page3 = gitHubLookupService.findUser(3L);

        // Wait until they are all done
        CompletableFuture<Void> future = CompletableFuture.allOf(page1, page2, page3);
//		CompletableFuture.allOf(page1,page2,page3).join();
        future.thenRunAsync(() -> {
            logger.info("Merge all page success");
        }).join();

        logger.info("Done!");
        // Print results, including elapsed time
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        logger.info("page1 --> " + page1.get());
        logger.info("page2 --> " + page2.get());
        logger.info("page3 --> " + page3.get());
        page1.thenRun(() -> {
            logger.info("page 1 completed");
        });


        // Use supplyAsync return result
        logger.info("Use supplyAsync return result!");
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return gitHubLookupService.findUser1(1L);
        }, executor);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return gitHubLookupService.findUser1(2L);
        }, executor);
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            return gitHubLookupService.findUser1(3L);
        }, executor);
        logger.info("Done calculator!");
        future1.thenRun(() -> {
            logger.info("future1 completed!");
        });
        future2.thenAccept(result -> {
            logger.info("future2 completed, result = " + result);
        });
        future3.handle((data, error) -> {
            if (error != null) {
                logger.info("future3 error: " + error);
                return null;
            } else {
                logger.info("future3 completed, result = " + data);
                return data;
            }

        });

    }

}
