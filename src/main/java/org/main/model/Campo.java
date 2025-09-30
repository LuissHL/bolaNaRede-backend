package org.main.model;

public class Campo {
    private int id;
    private String nome;       // Nome do campo
    private String tipo;       // Tipo (Society, Quadra, Fut7)
    private double precoPorHora;      // Preço por hora
    private int capacidade;    // Quantidade máxima de jogadores
    private String cidade;
    private String bairro;
    private String telefone;
    private User dono;         // Usuário que cadastrou o campo

    public Campo(int id, String nome, String tipo, double preco, int capacidade,
                 String cidade, String bairro, String telefone, User dono) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.precoPorHora = preco;
        this.capacidade = capacidade;
        this.cidade = cidade;
        this.bairro = bairro;
        this.telefone = telefone;
        this.dono = dono;
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public double getPreco() { return precoPorHora; }
    public int getCapacidade() { return capacidade; }
    public String getCidade() { return cidade; }
    public String getBairro() { return bairro; }
    public String getTelefone() { return telefone; }
    public User getDono() { return dono; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setPreco(double preco) { this.precoPorHora = preco; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setDono(User dono) { this.dono = dono; }

    @Override
    public String toString() {
        return "Campo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", preco=" + precoPorHora +
                ", capacidade=" + capacidade +
                ", cidade='" + cidade + '\'' +
                ", bairro='" + bairro + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dono=" + (dono != null ? dono.getNome() : "null") +
                '}';
    }
}
