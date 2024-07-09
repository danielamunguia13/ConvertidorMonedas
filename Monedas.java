import java.util.Arrays;
import java.util.List;
import com.google.gson.JsonObject;

public class Monedas {
    public static void main(String[] args) {
        try {
            JsonObject jsonResponse = APICliente.getExchangeRates();
            JsonObject rates = jsonResponse.getAsJsonObject("rates");

            List<String> currencies = Arrays.asList("ARS", "BOB", "BRL", "CLP", "COP", "USD", "MXN", "EUR");
            for (String currency : currencies) {
                if (rates.has(currency)) {
                    System.out.println(currency + ": " + rates.get(currency).getAsDouble());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
