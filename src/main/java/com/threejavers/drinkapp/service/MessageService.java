package com.threejavers.drinkapp.service;

import com.threejavers.drinkapp.model.Message;
import com.threejavers.drinkapp.web.dto.MessageDto;

import java.util.Optional;

public interface MessageService {

    Optional<Message> get(Long id);

    void leaveMessage(Long id, String information);

    Message save(MessageDto messageDto);

    void update(Long id, String information);
}
