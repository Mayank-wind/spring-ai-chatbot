package com.mayank.SpringAi;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class AiController {
    private final ChatClient chatClient;
    public AiController(ChatClient.Builder builder){
        this.chatClient= builder.build();
    }
    @GetMapping(value="/ask-stream",produces = "text/event-stream")
    public Flux<String> askstream(@RequestParam String message){
        return chatClient.prompt().user(message).stream().content();
    }

}
