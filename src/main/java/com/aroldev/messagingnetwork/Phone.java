package com.aroldev.messagingnetwork;

import com.aroldev.messagingnetwork.Enum.MessageType;
import com.aroldev.messagingnetwork.Exceptions.InvalidMessageException;
import com.aroldev.messagingnetwork.Interfaces.Exportable;

import java.util.ArrayList;
import java.util.List;

public class Phone extends Device implements Exportable<List<String>> {
    private int capacity;
    private Message[] history;
    public int messageCount = 0;

    public Phone(String name, int id, int capacity) {
        super(name, id);
        this.capacity = capacity;
        this.history = new Message[this.capacity];
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Message[] getHistory() {
        int historySize = Math.min(messageCount, capacity);

        int start = (messageCount >= capacity) ? (messageCount % capacity) : 0;

        Message[] orderedHistory = new Message[historySize];

        for (int i = 0; i < historySize; i++) {
            int index = (start + i) % capacity;
            orderedHistory[historySize - 1 - i] = history[index];
        }
        return orderedHistory;
    }

    public void setHistory(Message[] history) {
        this.history = history;
    }

    public Message[] getMessages() {
        if (messageCount == 0) {
            return new Message[capacity];
        }
        return getHistory();
    }

    @Override
    public void sendMessage(Message msg) {
       receiveMessage(msg);
    }

    @Override
    public void receiveMessage(Message msg) {
        int index = messageCount % capacity;
        if (msg.getContent() == null || msg.getContent().trim().length() < 3) {
            throw new InvalidMessageException("The content of the message is invalid: " +
                    "It's either too short, null or empty");
        }
        if (msg.getType() == MessageType.SYSTEM) {
            if ("SYSTEM".equals(msg.getSender())) {
                history[index] = msg;
                messageCount++;
            }
            return;
        }
        else {
            history[index] = msg;
            messageCount++;
        }
    }

    @Override
    public List<String> export() {
        List<String> exportedMessages = new ArrayList<>();
        Message[] messages = getHistory();

        for (Message msg : messages) {
            if (msg != null && msg.getContent() != null) {
                exportedMessages.add(msg.getContent());
            } else {
                exportedMessages.add(null);
            }
        }

        return exportedMessages;
    }

    @Override
    public List<Message> filterMessages(List<Message> messages) {
        List<Message> filtered = new ArrayList<>();
        for (Message msg : messages) {
            if (msg.getType() == MessageType.TEXT) {
                filtered.add(msg);
            }
        }
        return filtered;
    }
}
