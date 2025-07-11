package com.aroldev.messagingnetwork;

import java.util.LinkedList;
import java.util.Queue;

public class MessageServer implements Runnable {
    private final Queue<Message> messageQueue = new LinkedList<>();

    public MessageServer() {
    }

    public synchronized void addMessage(Message message) {
        synchronized (this.messageQueue) {
            messageQueue.offer(message);
        }
    }

    @Override
    public void run() {
        while(true) {
            Message message = null;

            synchronized (messageQueue) {
                if (!messageQueue.isEmpty()) {
                    message = messageQueue.poll();
                }
            }

            if (message != null) System.out.println("Processing... " + message.getContent());

            try { Thread.sleep(1000); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); break; }
        }
    }
}
