package com.mayank.SpringAi;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class AiController {

    private final ChatClient chatClient;

    public AiController(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    // STREAMING WITH SESSION MEMORY
    @GetMapping(value="/ask-stream", produces = "text/event-stream")
    public Flux<String> askStream(@RequestParam String message,
                                  @RequestParam String sessionId){

        // 1. Get memory for this user
        var history = ChatMemoryStore.getMessages(sessionId);

        // 2. Add user message
        history.add("User: " + message);

        // 3. Create full prompt
        String fullPrompt = String.join("\n", history);

        // 4. Prepare response collector
        StringBuilder aiResponse = new StringBuilder();

        // 5. Call AI
        Flux<String> response = chatClient.prompt()
                .user(fullPrompt)
                .stream()
                .content()
                .doOnNext(token -> aiResponse.append(token))
                .doOnComplete(() -> {
                    ChatMemoryStore.addMessage(sessionId, "AI: " + aiResponse.toString());
                });

        return response;
    }

    // RESET CHAT
    @GetMapping("/reset")
    public String reset(@RequestParam String sessionId){
        ChatMemoryStore.getMessages(sessionId).clear();
        return "Chat cleared!";
    }
}