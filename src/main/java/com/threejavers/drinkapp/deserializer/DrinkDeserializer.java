//package com.threejavers.drinkapp.deserializer;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.ObjectCodec;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.threejavers.drinkapp.model.Drink;
//import springfox.documentation.spring.web.json.Json;
//
//import java.io.IOException;
//
//public class DrinkDeserializer extends JsonDeserializer<Drink> {
//    @Override
//    public Drink deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//        ObjectCodec oc = jsonParser.getCodec();
//        JsonNode node = oc.readTree(jsonParser);
//
//    }
//}
