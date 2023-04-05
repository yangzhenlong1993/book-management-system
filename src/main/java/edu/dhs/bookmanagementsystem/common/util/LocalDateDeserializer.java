package edu.dhs.bookmanagementsystem.common.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @program: book-management-system
 * @description:
 * @author: Zhenlong Yang
 * @create: 2023-04-05 22:59
 **/
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        String valueAsString = p.getValueAsString();
        LocalDate localDate = DateUtils.parseDateTime(valueAsString);
        return localDate;
    }
}
