package danya.chat;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsersOnline {
    List<String> users = new ArrayList<>();

    public UsersOnline(){
    }

    public void addUser(String user){
        users.add(user);
    }public void disconnected(String user){
        users.remove(user);
    }
}
