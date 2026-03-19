package com.mayank.SpringAi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatMemoryStore {
    private static final Map<String, List<String>> memory = new HashMap<>();

    public static List<String>getMessages(String sessionID){
      return   memory.computeIfAbsent(sessionID, k ->new ArrayList<>());
    }

    public static void addMessage(String sessionID, String message){
        getMessages(sessionID).add(message);
    }

    public static List<String> get(String sessionId){
        return memory.getOrDefault(sessionId, new ArrayList<>());
    }
}
