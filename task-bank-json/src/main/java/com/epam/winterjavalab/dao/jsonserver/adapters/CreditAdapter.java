package com.epam.winterjavalab.dao.jsonserver.adapters;

import com.epam.winterjavalab.beans.Credit;
import com.epam.winterjavalab.beans.enums.Period;
import com.epam.winterjavalab.constants.Constants;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreditAdapter implements JsonDeserializer<Credit>, JsonSerializer<Credit> {
    @Override
    public Credit deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Credit credit = new Credit();

        credit.setId(jsonObject.get(Constants.ID).getAsInt());
        credit.setUserId(jsonObject.get(Constants.USER_ID).getAsInt());

        String date = jsonObject.get(Constants.DATE).getAsString();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);
        credit.setDate(LocalDate.parse(date,dateTimeFormatter));

        credit.setPeriod(Period.valueOf(jsonObject.get(Constants.PERIOD).getAsString()));
        credit.setMoney(jsonObject.get(Constants.MONEY).getAsInt());
        credit.setRate(jsonObject.get(Constants.RATE).getAsDouble());

        return credit;
    }

    @Override
    public JsonElement serialize(Credit credit, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(Constants.ID,new JsonPrimitive(credit.getId()));
        jsonObject.add(Constants.USER_ID, new JsonPrimitive(credit.getUserId()));
        jsonObject.add(Constants.DATE, new JsonPrimitive(credit.getDate().toString()));
        jsonObject.add(Constants.PERIOD, new JsonPrimitive(credit.getPeriod().toString()));
        jsonObject.add(Constants.MONEY, new JsonPrimitive(credit.getMoney()));
        jsonObject.add(Constants.RATE, new JsonPrimitive(credit.getRate()));
        return jsonObject;
    }
}
