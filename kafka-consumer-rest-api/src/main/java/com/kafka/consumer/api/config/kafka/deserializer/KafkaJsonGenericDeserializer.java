package com.kafka.consumer.api.config.kafka.deserializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class KafkaJsonGenericDeserializer<T> implements Deserializer<T>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJsonGenericDeserializer.class.getName());

    private ObjectMapper objectMapper;
    private Class<T> type;

    public KafkaJsonGenericDeserializer() {}

    @Override
    public void configure(Map<String, ?> props, boolean isKey)
    {
        configure(new KafkaJsonDeserializerConfig(props), isKey);
    }

    protected void configure(KafkaJsonDecoderConfig config, Class<T> type)
    {
        this.objectMapper = new ObjectMapper();
        this.type = type;

        boolean failUnknownProperties = config.getBoolean(KafkaJsonDeserializerConfig.FAIL_UNKNOWN_PROPERTIES);
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failUnknownProperties);
    }

    @SuppressWarnings("unchecked")
    private void configure(KafkaJsonDeserializerConfig config, boolean isKey)
    {
        if (isKey)
        {
            configure(config, (Class<T>) config.getClass(KafkaJsonDeserializerConfig.JSON_KEY_TYPE));
        } else
        {
            configure(config, (Class<T>) config.getClass(KafkaJsonDeserializerConfig.JSON_VALUE_TYPE));
        }
    }

    @Override
    public T deserialize(String ignored, byte[] bytes)
    {
        if (bytes == null || bytes.length == 0)
        {
            LOGGER.error("Null received at deserializing");
            return null;
        }

        try
        {
            LOGGER.info("Deserializing...");
            return objectMapper.readValue(bytes, type);
        } catch (Exception e)
        {
            throw new SerializationException("Error when deserializing byte[] to OrderItem");
        }
    }

    protected Class<T> getType()
    {
        return type;
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
