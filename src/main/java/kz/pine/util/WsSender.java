package kz.pine.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import kz.pine.dto.EventType;
import kz.pine.dto.ObjectType;
import kz.pine.dto.WsEventDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Component
public class WsSender {
    private final SimpMessagingTemplate template; // отправка сообщ через очереди сообщ в спринге
    private final ObjectMapper mapper; // сериализация и десериализация объектов


    public WsSender(SimpMessagingTemplate template, ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public <T> BiConsumer<EventType, T> getSender(ObjectType objectType){
        ObjectWriter writer = mapper
                .setConfig(mapper.getSerializationConfig())
                .writer();

        return  (EventType eventType, T payload) -> {
            String value = null;
            try {
                value = writer.writeValueAsString(payload);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            template.convertAndSend("/topic/activity", new WsEventDto(objectType, eventType, value));

        };
    }
}
