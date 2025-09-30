package org.main.Repository;

import org.main.model.Campo;
import org.main.model.User;

import java.util.ArrayList;
import java.util.List;

public class CampoRepository {
    private List<Campo> campos = new ArrayList<>();
    private int contadorId = 1;

    public Campo addCampo(String nome, String tipo, double preco, int qtd, String cidade, String bairro, String telefone, User dono) {
        Campo campo = new Campo(contadorId++, nome,tipo, preco, qtd, cidade, bairro, telefone, dono);
        campos.add(campo);
        return campo;
}

public List<Campo> listarTodos() {
    return campos;
    }
}
