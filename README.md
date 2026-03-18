Spring Boot AI Chatbot (Local LLM)

A simple AI chatbot built using Spring Boot + Spring AI + Ollama.

This chatbot runs fully locally using open-source models like Llama3 / Phi3 without requiring OpenAI API credits.

---

Features

- Local AI chatbot
- Spring Boot REST API
- Llama3 / Phi3 integration
- ChatGPT-style UI
- No paid API required

  Advanced Features

- Real-time streaming responses (SSE)
- Chatmemory (content-aware coonversation)
- PDF Q&A (basic document understanding)

---

Tech Stack

- Java
- Spring Boot
- Spring AI
- Ollama
- Llama3 / Phi3
- HTML / JavaScript

---

Architecture

Browser UI
↓
Spring Boot REST API
↓
Spring AI
↓
Ollama
↓
Llama3 / Phi3 Model

---

Running the Project

1 Install Ollama

https://ollama.com

2 Download model

ollama pull llama3

3 Start model

ollama run llama3

4 Run Spring Boot

Run:

SpringAiApplication

5 Open chatbot

http://localhost:8085

---

Example Prompt

Explain Spring Boot

---

Future Improvements

- Session-based memory (multi-user support)
- Vector database (RAG for large PDFs
- Chat history UI (like ChatGPT sidebar)
- Voice chatbot
