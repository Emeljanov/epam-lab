package com.epam.winterjavalab.dao;

import com.epam.winterjavalab.beans.Settings;

public interface SettingsCreater {
    Settings createSettings(String filepath);
}
