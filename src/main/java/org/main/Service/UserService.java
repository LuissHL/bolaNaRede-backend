package org.main.Service;

import org.main.Repository.UserRepository;
import org.main.model.User;

import java.util.List;

//vai ser a classe que gerencia usuários
public class UserService {
    private UserRepository repo;
    public UserService(UserRepository repo) { this.repo = repo; }

    public User registrar(String nome, String email, String senha, String cpf,String telefone, String time) {
        return repo.addUser(nome, email, senha, cpf, telefone, time);
    }

    public User buscarPorEmail(String email) {
        return repo.getByEmail(email);
    }

    public List<User> listarTodos() {
        return repo.getAll();
    }
}
