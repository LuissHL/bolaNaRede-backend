package org.main.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.main.Service.AnuncioService;
import org.main.Service.UserService;
import org.main.model.Anuncio;
import org.main.model.User;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AnuncioController  implements HttpHandler {
    private AnuncioService anuncioService;
    private UserService userService;

    public AnuncioController(AnuncioService anuncioService, UserService userService) {
        this.anuncioService = anuncioService;
        this.userService = userService;
    }


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();

        if ("POST".equals(method)) {
            String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            String[] parts = body.split(",");

            // Exemplo: titulo,descricao,preco,Dono
            User dono = userService.buscarPorEmail(parts[3]);
            Anuncio anuncio = anuncioService.addAnuncio(parts[0], parts[1], Double.parseDouble(parts[2]), dono);

            String response = "{ \"id\": " + anuncio.getId() + ", \"titulo\": \"" + anuncio.getTitulo() + "\" }";
            exchange.sendResponseHeaders(201, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
        else if ("GET".equals(method)) {
            List<Anuncio> anuncios = anuncioService.listarTodosOsAnuncios();
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < anuncios.size(); i++) {
                Anuncio a = anuncios.get(i);
                sb.append("{\"id\":").append(a.getId())
                        .append(",\"titulo\":\"").append(a.getTitulo())
                        .append("\",\"preco\":").append(a.getPreco())
                        .append("}");
                if (i < anuncios.size() - 1) sb.append(",");
            }
            sb.append("]");

            byte[] response = sb.toString().getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, response.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response);
            }
        }
        else {
            exchange.sendResponseHeaders(405, -1);
        }
    }
    }
}
