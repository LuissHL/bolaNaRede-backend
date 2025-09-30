package org.main;

import com.sun.net.httpserver.HttpServer;
import org.main.Controller.AnuncioController;
import org.main.Controller.AuthController;
import org.main.Controller.CampoController;
import org.main.Controller.UserController;
import org.main.Repository.AnuncioRepository;
import org.main.Repository.CampoRepository;
import org.main.Repository.UserRepository;
import org.main.Service.AnuncioService;
import org.main.Service.AuthService;
import org.main.Service.CampoService;
import org.main.Service.UserService;
import org.main.model.Anuncio;
import org.main.model.User;

import java.net.InetSocketAddress;

public class Program {
    public static void main(String[] args) throws Exception {
        // Criando "banco simulado"
        UserRepository userRepo = new UserRepository();
        AnuncioRepository anuncioRepo = new AnuncioRepository();
        CampoRepository campoRepository = new CampoRepository();

        // Criando serviços
        UserService userService = new UserService(userRepo);
        AuthService authService = new AuthService(userRepo);
        CampoService campoService = new CampoService(campoRepository, userRepo);
        AnuncioService anuncioService = new AnuncioService(anuncioRepo);


        // Criando servidor
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Registrando rotas
        server.createContext("/users", new UserController(userService));
        server.createContext("/login", new AuthController(authService));
        server.createContext("/campos", new CampoController(campoService));
        server.createContext("/anuncios", new AnuncioController(anuncioService, userService));


        server.start();
        System.out.println("Servidor rodando em http://localhost:8080");

        // --- Simulação de cadastro no repositório ---
        User u1 = userService.registrar("João", "joao@email.com", "123", "10371108509", "99999-9999", "Flamengo");
        User u2 = userService.registrar("Maria", "maria@email.com", "abc", "10489504322", "88888-8888", "Palmeiras");

        // --- Testando login direto ---
        User logado = authService.login("joao@email.com", "123");
        if (logado != null) {
            anuncioService.addAnuncio("Aluguel de Campo", "Campo society disponível", 150.0, logado);
            anuncioService.addAnuncio("Peneira Sub-20", "Teste para jogadores jovens", 50.0, logado);
        }

        // --- Listando anúncios ---
        for (Anuncio a : anuncioService.listarTodosOsAnuncios()) {
            System.out.println(a);
        }
    }

}