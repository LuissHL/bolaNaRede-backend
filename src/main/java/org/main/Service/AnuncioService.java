package org.main.Service;

import org.main.Repository.AnuncioRepository;
import org.main.model.Anuncio;
import org.main.model.User;

import java.util.List;

public class AnuncioService {
    private AnuncioRepository repo;

    public AnuncioService(AnuncioRepository repo) {
        this.repo = repo;
    }

    public Anuncio addAnuncio(String titulo, String descricao, double preco, User dono) {
        return repo.addAnuncio(titulo, descricao, preco, dono);
    }

    public List<Anuncio> listarTodosOsAnuncios() {
        return repo.getAll();
    }

    public List<Anuncio> listarPorDono(User dono) {
        return repo.getByOwner(dono);
    }
}
