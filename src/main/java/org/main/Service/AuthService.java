package org.main.Service;

import org.main.Repository.UserRepository;
import org.main.model.User;

//esse service é onde valida o login dos usuarios(a parte de authenticação) obs: em breve adicionar o logout
public class AuthService {

    private UserRepository repo;

    public AuthService(UserRepository repo) {
        this.repo = repo;
    }

    public User login(String email, String senha) {
        User u = repo.getByEmail(email);
        if(u != null && u.getSenha().equals(senha)) {
            System.out.println("Login bem sucedido! Bem-vindo, " + u.getNome());
            return u;
        }
        System.out.println("Credenciais inválidas.");
        return null;
    }

}
