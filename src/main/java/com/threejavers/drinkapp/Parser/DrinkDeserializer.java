package com.threejavers.drinkapp.Parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DrinkDeserializer extends JsonDeserializer<DrinkAPI> {
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
    }
}
