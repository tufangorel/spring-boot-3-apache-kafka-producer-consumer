package com.kafka.producer.api.config.kafka.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Serialize objects to UTF-8 JSON. This works with any object which is serializable with Jackson.
 */
public class KafkaJsonGenericSerializer<T> implements Serializer<T>
{

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJsonGenericSerializer.class.getName());

    private ObjectMapper objectMapper;

    public KafkaJsonGenericSerializer() {}

    @Override
    public void configure(Map<String, ?> config, boolean isKey)
    {
        configure(new KafkaJsonSerializerConfig(config));
    }

    protected void configure(KafkaJsonSerializerConfig config)
    {
        boolean prettyPrint = config.getBoolean(KafkaJsonSerializerConfig.JSON_INDENT_OUTPUT);
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT, prettyPrint);
    }

    @Override
    public byte[] serialize(String topic, T data) {

        try {
            if (data == null){
                LOGGER.error("Null received at serializing");
                return null;
            }
            LOGGER.info("Serializing orderItem ...");
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing OrderItem to byte[]");
        }
    }

    @Override
    public void close() {
        Serializer.super.close();
    }

}
