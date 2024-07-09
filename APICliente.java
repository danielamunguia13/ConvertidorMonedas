import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class APICliente {
    // URL de la API con la clave de API
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/d62997d51e33b77b7f0d2dcf/latest/USD";

    // Método para obtener las tasas de cambio desde la API
    public static JsonObject getExchangeRates() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Convertir la respuesta JSON en un JsonObject
        return JsonParser.parseString(response.body()).getAsJsonObject();
    }

    // Método main para probar la conexión a la API
    public static void main(String[] args) {
        try {
            JsonObject rates = getExchangeRates();
            System.out.println(rates);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
