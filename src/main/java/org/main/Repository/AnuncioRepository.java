package org.main.Repository;

import org.main.model.Anuncio;
import org.main.model.User;

import java.util.ArrayList;
import java.util.List;

public class AnuncioRepository {
    private List<Anuncio> anuncios = new ArrayList<>();
    private int nextId = 1;

    public Anuncio addAnuncio(String titulo, String descricao, double preco, User dono) {
        Anuncio a = new Anuncio(nextId++, titulo, descricao, preco, dono);
        anuncios.add(a);
        return a;
    }

    public List<Anuncio> getAll() {
        return anuncios;
    }

    public List<Anuncio> getByOwner(User dono) {
        List<Anuncio> result = new ArrayList<>();
        for (Anuncio a : anuncios) {
            if (a.getDono().equals(dono)) result.add(a);
        }
        return result;
    }
}
