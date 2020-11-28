package com.threejavers.drinkapp.Parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

@Slf4j
public class DrinkDeserializer extends JsonDeserializer<DrinkAPI> {
    private static final String SETTINGS_FILE_NAME = "settings.properties";
    private static final String DATE_FORMAT = "date.format";

    @Override
    public DrinkAPI deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        Map<String, String> ingredients = new HashMap<>();
        JsonNode tree = jsonParser.readValueAsTree();
        DrinkAPI drinkAPI = new DrinkAPI();

        String[] errors = {"null"};

        for (int index = 1; index < 16; index++) {
            index = (char) index;
            JsonNode ingredientNode = tree.get("strIngredient" + index);

            if (ingredientNode == null) {
                break;
            }
            String trim = ingredientNode.asText().trim();
            for (String error : errors) {
                if (!trim.equals(error) && !trim.isEmpty()) {
                    ingredients.put(tree.get("strIngredient" + index).asText().trim(),
                            tree.get("strMeasure" + index).asText().trim());
                }
            }

        }
        drinkAPI.setId(tree.get("idDrink").asLong());
        drinkAPI.setName(tree.get("strDrink").asText());
        drinkAPI.setRecipe(tree.get("strInstructions").asText());
        drinkAPI.setCategory(tree.get("strCategory").asText());
        drinkAPI.setDrinkType(tree.get("strAlcoholic").asText());
        drinkAPI.setGlassType(tree.get("strGlass").asText());
        if ((tree.get("dateModified")).isNull()) {
            String datePattern = getNewDatePattern();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            drinkAPI.setModificationDate(LocalDateTime.now().format(formatter));
        } else {
            drinkAPI.setModificationDate(tree.get("dateModified").asText());
        }
        drinkAPI.setImageUrl(tree.get("strDrinkThumb").asText());
        drinkAPI.setIngredients(ingredients);
        log.info("Deserialization data from file");
        return drinkAPI;
    }

    private String getNewDatePattern() throws IOException {
        Properties settings = new Properties();
        settings.load(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(SETTINGS_FILE_NAME)).openStream());
        String dateFormat = settings.getProperty(DATE_FORMAT);
        log.info("Date Time format is: " + dateFormat);
        return dateFormat;
    }
}
