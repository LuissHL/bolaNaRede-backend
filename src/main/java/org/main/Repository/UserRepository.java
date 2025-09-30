package org.main.Repository;

import org.main.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private List<User> users = new ArrayList();
    private int nextId = 1;

    public User addUser(String nome, String email, String senha, String cpf, String telefone, String timeCoracao ) {
        User user = new User(nextId++,nome, email, senha, cpf, telefone, timeCoracao);
        users.add(user);
        return user;
    }

    public List<User> getAll() {
        return users;
    }

    public User getByEmail(String email) {
        for (User u : users) {
           if(u.getEmail().equals(email)) return u;


        }
        return null;
    }

    public List<User> listarTodos() {
        return users;
    }

    public User findById(int id) {
        for (User u : users) {
            if (u.getId() == id) return u;
        }
        return null;
    }

}
