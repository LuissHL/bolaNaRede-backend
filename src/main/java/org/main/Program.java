package org.main;

import org.main.Repository.AnuncioRepository;
import org.main.Repository.UserRepository;
import org.main.Service.AnuncioService;
import org.main.Service.AuthService;
import org.main.model.Anuncio;
import org.main.model.User;

public class Program {
    public static void main(String[] args) {
        // Criando "banco simulado"
        UserRepository userRepo = new UserRepository();
        AnuncioRepository anuncioRepo = new AnuncioRepository();

        // Criando serviços
        AuthService auth = new AuthService(userRepo);
        AnuncioService anuncioService = new AnuncioService(anuncioRepo);


        //cadastro de usuarios aq
        User u1 = userRepo.addUser("João", "joao@email.com", "123", "99999-9999", "Flamengo");
        User u2 = userRepo.addUser("Maria", "maria@email.com", "abc", "88888-8888", "Palmeiras");

        User logado = auth.login("joao@email.com", "123");

        if(logado != null) {
            Anuncio anuncio1 = anuncioService.addAnuncio("Aluguel de Campo", "Campo society disponível", 150.0, logado);
            Anuncio anuncio2 = anuncioService.addAnuncio("Peneira Sub-20", "Teste para jogadores jovens", 50.0, logado);
        }

        for(Anuncio a : anuncioService.listarTodosOsAnuncios()) {
            System.out.println(a);
        }



    }
}