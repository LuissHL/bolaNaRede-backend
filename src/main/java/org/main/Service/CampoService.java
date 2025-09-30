package org.main.Service;

import org.main.Repository.CampoRepository;
import org.main.Repository.UserRepository;
import org.main.model.Campo;
import org.main.model.User;

import java.util.List;

public class CampoService {
    private CampoRepository campoRepo;
    private UserRepository userRepo;

    public CampoService(CampoRepository campoRepo, UserRepository userRepo) {
        this.campoRepo = campoRepo;
        this.userRepo = userRepo;
    }

    public Campo cadastrarCampo(String nome, String tipo, double preco, int capacidade,
                                String cidade, String bairro, String telefone, int userId) {
        // buscar o usuário dono pelo id
        User dono = userRepo.findById(userId);
        if (dono == null) {
            throw new IllegalArgumentException("Usuário não encontrado para id: " + userId);
        }

        // mudar o papel do usuário para "DONO"
        dono.setRole("DONO");

        // cadastrar o campo
        return campoRepo.addCampo(nome, tipo, preco, capacidade, cidade, bairro, telefone, dono);
    }

    public List<Campo> listarTodos() {
        return campoRepo.listarTodos();
    }
}
