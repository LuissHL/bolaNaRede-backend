package org.main.model;

public class User {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String timeCoracao;

    public User(int id, String nome, String email, String senha, String telefone, String timeCoracao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.timeCoracao = timeCoracao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getTimeCoracao() {
        return timeCoracao;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", timeCoracao='" + timeCoracao + '\'' +
                '}';
    }
}