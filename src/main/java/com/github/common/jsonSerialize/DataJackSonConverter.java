package com.github.common.jsonSerialize;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * http://tutorials.jenkov.com/java-internationalization/simpledateformat.html
 */
public class DataJackSonConverter extends JsonDeserializer<Timestamp> {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static String[] pattern =
            new String[]{"yyyy-MM-dd",
                    "yyyy-MM-dd HH:mm",
                    "yyyy-MM-dd HH:mm:ss",
                    "yyyy-MM-dd HH:mm:ss.S",
                    "yyyy.MM.dd",
                    "yyyy.MM.dd HH:mm",
                    "yyyy.MM.dd HH:mm:ss",
                    "yyyy.MM.dd HH:mm:ss.S",
                    "yyyy/MM/dd",
                    "yyyy/MM/dd HH:mm",
                    "yyyy/MM/dd HH:mm:ss",
                    "yyyy/MM/dd HH:mm:ss.S"};

    @Override
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        String text = jsonParser.getText();
        if (StringUtils.isNotBlank(text)) {
            try {
                //            LocalDateTime dateTime = LocalDateTime.parse(text);
                Date date = sdf.parse(text);
                Timestamp timestamp = new Timestamp(date.getTime());
//            Timestamp timestamp = Timestamp.from(Instant.parse(text));
                return timestamp;
            } catch (ParseException parseException) {
                throw new RuntimeException(parseException);
            }

        }
        return null;
    }

    @Override
    public Class<?> handledType() {
        return Timestamp.class;
    }
}
