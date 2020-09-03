package com.epam.winterjavalab.dao.jsonserver.adapters;

import com.epam.winterjavalab.beans.Settings;
import com.epam.winterjavalab.beans.ShowFor;
import com.epam.winterjavalab.beans.enums.SortType;
import com.epam.winterjavalab.constants.Constants;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SettingsAdapter implements JsonDeserializer<Settings> {

    @Override
    public Settings deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);

        Settings settings = new Settings();
        String dateFrom = jsonObject.get(Constants.DATE_FROM).getAsString();
        String dateTo = jsonObject.get(Constants.DATE_TO).getAsString();
        settings.setDateFrom(LocalDate.parse(dateFrom,dateTimeFormatter));
        settings.setDateTo(LocalDate.parse(dateTo,dateTimeFormatter));
        settings.setShowFor(jsonDeserializationContext.deserialize(jsonObject.get(Constants.SHOW_FOR), ShowFor.class));

        settings.setSortType(SortType.valueOf(jsonObject.get(Constants.SORT_BY).getAsString()));
        List<String> departments = null;

        if(jsonObject.get(Constants.USE_DEPARTMENTS).isJsonArray()) {
            JsonArray departsJS = jsonObject.getAsJsonArray(Constants.USE_DEPARTMENTS);
            departments = new ArrayList<>();
            for(JsonElement departJson : departsJS) {
                departments.add(departJson.getAsString());
            }
        }

        settings.setUseDepartsment(departments);
        return settings;
    }
}
