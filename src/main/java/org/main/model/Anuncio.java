package org.main.model;

public class Anuncio {
    private int id;
    private String titulo;
    private String descricao;
    private double preco;
    private User dono;

    public Anuncio(int id, String titulo, String descricao, double preco, User dono) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.dono = dono;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }
    public User getDono() { return dono; }

    @Override
    public String toString() {
        return "Anuncio{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", preco=" + preco +
                ", dono=" + dono.getNome() +
                '}';
    }
}

