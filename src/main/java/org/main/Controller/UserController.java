package org.main.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.main.Service.UserService;
import org.main.model.User;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class UserController implements HttpHandler {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("📩 Requisição recebida em /users com método: " + exchange.getRequestMethod());

        // 🔹 CORS - permite chamadas do front
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        // 🔹 Tratar OPTIONS (pré-flight do navegador)
        if ("OPTIONS".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1); // sem conteúdo
            return;
        }

        String method = exchange.getRequestMethod();

        if ("POST".equals(method)) {

            // cadastrar usuário
            String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            String[] parts = body.split(",");

            User user = userService.registrar(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);

            String response = "{ \"id\": " + user.getId() + ", \"nome\": \"" + user.getNome() + "\" }";
            exchange.sendResponseHeaders(201, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
            System.out.println("📥 Corpo recebido: " + body);

        }
        else if ("GET".equals(method)) {
            // listar usuários
            List<User> usuarios = userService.listarTodos();
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < usuarios.size(); i++) {
                User u = usuarios.get(i);
                sb.append("{\"id\":").append(u.getId())
                        .append(",\"nome\":\"").append(u.getNome()).append("\"}");
                if (i < usuarios.size() - 1) sb.append(",");
            }
            sb.append("]");

            byte[] response = sb.toString().getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, response.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response);
            }
        }
        else {
            exchange.sendResponseHeaders(405, -1); // método não permitido
        }
    }


}
