package com.mayank.SpringAi;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AiController {

    private final ChatClient chatClient;

    // 🔥 MEMORY
    private final List<String> conversation = new ArrayList<>();

    public AiController(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    // ✅ NORMAL API (for testing)
    @GetMapping("/ask")
    public String ask(@RequestParam String message){

        conversation.add("User: " + message);

        String fullPrompt = String.join("\n", conversation);

        String response = chatClient.prompt()
                .user(fullPrompt)
                .call()
                .content();

        conversation.add("AI: " + response);

        return response;
    }

    // ✅ STREAMING API (UPDATED WITH MEMORY)
    @GetMapping(value="/ask-stream", produces = "text/event-stream")
    public Flux<String> askStream(@RequestParam String message){

        conversation.add("User: " + message);

        String fullPrompt = String.join("\n", conversation);

        return chatClient.prompt()
                .user(fullPrompt)
                .stream()
                .content();
    }

    // ✅ RESET CHAT
    @GetMapping("/reset")
    public String reset(){
        conversation.clear();
        return "Chat cleared!";
    }
}