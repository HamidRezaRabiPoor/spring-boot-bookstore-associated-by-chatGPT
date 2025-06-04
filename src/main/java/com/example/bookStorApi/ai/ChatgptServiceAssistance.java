package com.example.bookStorApi.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatgptServiceAssistance {
    private final ChatClient chatClient;
    public ChatgptServiceAssistance(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }
    public String chat(String prompt){
        return chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();
    }
}
