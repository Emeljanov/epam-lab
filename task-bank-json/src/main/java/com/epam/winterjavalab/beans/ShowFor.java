package com.epam.winterjavalab.beans;

import com.epam.winterjavalab.beans.enums.ShowType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ShowFor {
    private ShowType type;
    private List<String> users;

    public ShowFor() {
    }

    public ShowFor(ShowType type, List<String> users) {
        this.type = type;
        this.users = users;
    }

    public ShowType getType() {
        return type;
    }

    public List<String> getUsers() {
        return users;
    }

    public List<User> getUsersObj(Bank bank) {
        List<User> result = new ArrayList<>();
        if(type == ShowType.ID) {
            for(String usName : users) {
                Optional<User> usertmp = bank.getUsers()
                        .stream()
                        .filter(user -> user.getId()== Integer.valueOf(usName))
                        .findFirst();
                result.add(usertmp.orElse(new User()));

            }
        }
        if(type == ShowType.NAME) {
            for(String usName : users) {

                Optional<User> usertmp = bank.getUsers()
                        .stream()
                        .filter(user -> (user.getSecondName() + " " + user.getName()).equals(usName))
                        .findFirst();
                result.add(usertmp.orElse(new User()));
            }
        }

        return result;
    }

    public void setType(ShowType type) {
        this.type = type;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

}
