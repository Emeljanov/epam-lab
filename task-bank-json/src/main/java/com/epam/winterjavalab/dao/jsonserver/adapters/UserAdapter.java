package com.epam.winterjavalab.dao.jsonserver.adapters;

import com.epam.winterjavalab.beans.enums.Sex;
import com.epam.winterjavalab.beans.User;
import com.epam.winterjavalab.constants.Constants;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserAdapter implements JsonDeserializer<User>, JsonSerializer<User> {
    @Override
    public User deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        User user = new User();

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        user.setId(jsonObject.get(Constants.ID).getAsInt());
        user.setName(jsonObject.get(Constants.NAME).getAsString());
        user.setSecondName(jsonObject.get(Constants.SECOND_NAME).getAsString());
        user.setSex(Sex.valueOf(jsonObject.get(Constants.SEX).getAsString()));

        String date = jsonObject.get(Constants.BIRTHDAY).getAsString();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);

        user.setBirthday(LocalDate.parse(date,dateTimeFormatter));

        return user;
    }

    @Override
    public JsonElement serialize(User user, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(Constants.ID, new JsonPrimitive(user.getId()));
        jsonObject.add(Constants.NAME, new JsonPrimitive(user.getName()));
        jsonObject.add(Constants.SECOND_NAME, new JsonPrimitive(user.getSecondName()));
        jsonObject.add(Constants.SEX, new JsonPrimitive(user.getSex().toString()));
        jsonObject.add(Constants.BIRTHDAY, new JsonPrimitive(user.getBirthday().toString()));
        return jsonObject;
    }
}
