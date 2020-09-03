package com.epam.winterjavalab.dao.jsonserver.adapters;

import com.epam.winterjavalab.beans.ShowFor;
import com.epam.winterjavalab.beans.enums.ShowType;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ShowForAdapter implements JsonDeserializer<ShowFor> {
    @Override
    public ShowFor deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        ShowFor showFor = new ShowFor();
        List<String> usersList = new ArrayList<>();
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        showFor.setType(ShowType.valueOf(jsonObject.get("type").getAsString()));

        JsonArray users = jsonObject.getAsJsonArray("users");
        for(JsonElement user : users) {
            usersList.add(user.getAsString());
        }
        showFor.setUsers(usersList);
        return showFor;
    }
}
