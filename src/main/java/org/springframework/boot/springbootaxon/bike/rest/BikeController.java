package org.springframework.boot.springbootaxon.bike.rest;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.springbootaxon.bike.domain.commands.CreateBikeCommand;
import org.springframework.boot.springbootaxon.bike.domain.commands.ModifyTitleBikeCommand;
import org.springframework.boot.springbootaxon.bike.rest.requests.CreateBikeRequest;
import org.springframework.boot.springbootaxon.bike.rest.requests.ModifyBikeRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class BikeController {
    private final CommandGateway commandGateway;
    private final static Logger logger = LoggerFactory.getLogger(BikeController.class);

    public BikeController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }


//    @Async
//    @PostMapping(value = "/bikes")
//    public CompletableFuture<String> createBike(@RequestBody final CreateBikeRequest request) {
//        logger.info("createBike controller start");
////		String id = UUID.randomUUID().toString();
//        CompletableFuture<String> future = commandGateway
//                .send(new CreateBikeCommand(UUID.randomUUID().toString(), request.getTitle()));
//
//        logger.info("createBike controller end");
//        return future;
//    }

    @RequestMapping(value = "/bikes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBike(@RequestBody CreateBikeRequest request) {
        logger.info("createBike controller start");

//        new Thread(() -> {
//            for(int i = 0; i < 1; i++) {
//                commandGateway
//                        .send(new CreateBikeCommand(UUID.randomUUID().toString(), request.getTitle()))
//                        .whenComplete((Object o, Throwable throwable) -> {
//                            if(throwable == null) {
//                                logger.info("commandGateway successful");
//                            } else {
//                                logger.error("commandGateway error handling command", throwable);
//                            }
//                        });
//            }
//        }).start();


        commandGateway.send(new CreateBikeCommand(UUID.randomUUID().toString(), request.getTitle()))
                .exceptionally(Throwable::getMessage);

        // Asenkron
//        CompletableFuture<String> future = commandGateway
//                .send(new CreateBikeCommand(UUID.randomUUID().toString(), request.getTitle()));
//
//        future.whenComplete((Object o, Throwable throwable) -> {
//            if(throwable == null) {
//                logger.info("commandGateway successful");
//            } else {
//                logger.error("commandGateway error handling command", throwable);
//            }
//        });


//        commandGateway
//                .send(new CreateBikeCommand(UUID.randomUUID().toString(), request.getTitle()))
//                .whenComplete((Object o, Throwable throwable) -> {
//                    if(throwable == null) {
//                        logger.info("commandGateway successful");
//                    } else {
//                        logger.error("commandGateway error handling command", throwable);
//                    }
//                });

//        commandGateway
//                .sendAndWait(new CreateBikeCommand(UUID.randomUUID().toString(), request.getTitle()));

        logger.info("createBike controller end");
//        commandGateway.sendAndWait()
    }
//    public void addPost(@RequestBody Post post) {
//        postService.addPost(post);
//    }

    @RequestMapping(method = RequestMethod.PUT, value = "/bikes/{id}")
    public void updateTopic(@RequestBody ModifyBikeRequest request, @PathVariable String id) {
        commandGateway.send(new ModifyTitleBikeCommand(id, request.getTitle()));
    }

    @ExceptionHandler
    public void handleException(Principal principal, Throwable exception) {
        logger.error("Exception :" + exception.getMessage());
    }

    @GetMapping("/result")
    public String getResult() throws ExecutionException, InterruptedException {
        // Create a CompletableFuture
        logger.info("result start");
        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        });

// Attach a callback to the Future using thenApply()
        CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
            return "Hello " + name;
        }).thenApplyAsync(s -> {
            System.out.println("kkk");
            return "Kemal";
        });
        logger.info("result end");

// Block and get the result of the future.
//        System.out.println(greetingFuture.get()); // Hello Rajeev
        return "Test";
    }
}
