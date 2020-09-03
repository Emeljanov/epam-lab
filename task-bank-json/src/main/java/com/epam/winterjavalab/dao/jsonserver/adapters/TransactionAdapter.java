package com.epam.winterjavalab.dao.jsonserver.adapters;

import com.epam.winterjavalab.beans.Transaction;
import com.epam.winterjavalab.constants.Constants;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionAdapter implements JsonDeserializer<Transaction>, JsonSerializer<Transaction> {

    @Override
    public Transaction deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Transaction transaction = new Transaction();

        transaction.setId(jsonObject.get(Constants.ID).getAsInt());

        String date = jsonObject.get(Constants.DATE).getAsString();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);
        transaction.setDate(LocalDate.parse(date,dateTimeFormatter));

        transaction.setCreditId(jsonObject.get(Constants.CREDIT_ID).getAsInt());
        transaction.setMoney(jsonObject.get(Constants.MONEY).getAsInt());

        return transaction;
    }

    @Override
    public JsonElement serialize(Transaction transaction, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(Constants.ID, new JsonPrimitive(transaction.getId()));
        jsonObject.add(Constants.DATE, new JsonPrimitive(transaction.getDate().toString()));
        jsonObject.add(Constants.CREDIT_ID, new JsonPrimitive((transaction.getCreditId())));
        jsonObject.add(Constants.MONEY, new JsonPrimitive(transaction.getMoney()));

        return jsonObject;
    }
}
