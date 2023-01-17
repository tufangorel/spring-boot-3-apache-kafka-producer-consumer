package com.kafka.consumer.api.service.kafka.topic.listener;

import com.kafka.consumer.api.model.OrderItem;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class KafkaOrderItemTopicListenerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaOrderItemTopicListenerService.class.getName());

    @KafkaListener(topics = "orders", groupId = "group_id", containerFactory = "kafkaListener")
    public void consumeOrderItem(ConsumerRecord consumerRecord) {

        LOGGER.info("topic = %s, partition = %d, offset = %d, customer = %s, country = %s\n",
            consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(),
                    consumerRecord.key(), consumerRecord.value());

        LOGGER.info("OrderItem received from Kafka broker = " + consumerRecord.value());

        try {

            Map<String, Object> passedValues = (LinkedHashMap<String, Object>) consumerRecord.value();
            OrderItem orderItem = new OrderItem();
            for (Map.Entry<String, Object> mapTemp : passedValues.entrySet()) {
                if (mapTemp.getKey().equalsIgnoreCase("id")) {
                    if( mapTemp.getValue() != null )
                        orderItem.setId((String) mapTemp.getValue());
                }
                else if (mapTemp.getKey().equalsIgnoreCase("quantity")) {
                    if( mapTemp.getValue() != null ) {
                        Integer quantity = (Integer) mapTemp.getValue();
                        orderItem.setQuantity( quantity );
                    }
                }
            }

            LOGGER.info( "OrderItem received from Kafka broker as Java Object =" + orderItem );
        }
        catch( Exception e ) {
            LOGGER.info(e.getMessage());
        }

    }

}