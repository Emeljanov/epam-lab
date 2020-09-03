package com.epam.winterjavalab.dao.jsonserver;

import com.epam.winterjavalab.beans.*;
import com.epam.winterjavalab.dao.SettingsCreater;
import com.epam.winterjavalab.dao.jsonserver.adapters.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonSettingsCreater implements SettingsCreater {
    @Override
    public Settings createSettings(String filePath) {
        try(JsonReader jsonReader = new JsonReader(new FileReader(filePath))) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Settings.class, new SettingsAdapter())
                    .registerTypeAdapter(ShowFor.class, new ShowForAdapter())
                    .create();

            Settings settings = gson.fromJson(jsonReader, Settings.class);
            return settings;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;

    }
}

