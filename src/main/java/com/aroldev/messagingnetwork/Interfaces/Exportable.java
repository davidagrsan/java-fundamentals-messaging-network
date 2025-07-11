package com.aroldev.messagingnetwork.Interfaces;

import com.aroldev.messagingnetwork.Message;

import java.util.List;

public interface Exportable<T> {
    T export();
    List<Message> filterMessages(List<Message> messages);
}
