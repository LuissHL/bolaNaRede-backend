package org.main.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.main.Service.CampoService;
import org.main.model.Campo;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CampoController implements HttpHandler {
    private CampoService campoService;

    public CampoController(CampoService campoService) {
        this.campoService = campoService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("📩 Requisição recebida em /campos - Método: " + exchange.getRequestMethod());

        // 🔹 CORS (permitir o front chamar o back)
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        // 🔹 Tratar pré-flight OPTIONS
        if ("OPTIONS".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        String method = exchange.getRequestMethod();

        if ("POST".equals(method)) {
            // corpo esperado: nome,tipo,preco,capacidade,cidade,bairro,telefone,userId
            String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println("📥 Corpo recebido: " + body);

            String[] parts = body.split(",");

            if (parts.length < 8) {
                String erro = "{ \"erro\": \"Dados insuficientes para cadastrar campo\" }";
                exchange.sendResponseHeaders(400, erro.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(erro.getBytes());
                }
                return;
            }

            String nome = parts[0];
            String tipo = parts[1];
            double preco = Double.parseDouble(parts[2]);
            int capacidade = Integer.parseInt(parts[3]);
            String cidade = parts[4];
            String bairro = parts[5];
            String telefone = parts[6];
            int userId = Integer.parseInt(parts[7]);

            Campo campo = campoService.cadastrarCampo(nome, tipo, preco, capacidade, cidade, bairro, telefone, userId);

            String response = "{ \"id\": " + campo.getId() + ", \"nome\": \"" + campo.getNome() + "\" }";
            exchange.sendResponseHeaders(201, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
        else if ("GET".equals(method)) {
            List<Campo> campos = campoService.listarTodos();

            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < campos.size(); i++) {
                Campo c = campos.get(i);
                sb.append("{\"id\":").append(c.getId())
                        .append(",\"nome\":\"").append(c.getNome()).append("\"")
                        .append(",\"tipo\":\"").append(c.getTipo()).append("\"")
                        .append(",\"cidade\":\"").append(c.getCidade()).append("\"")
                        .append(",\"bairro\":\"").append(c.getBairro()).append("\"")
                        .append("}");
                if (i < campos.size() - 1) sb.append(",");
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
