import com.google.gson.JsonObject;

public class TasaCambio {
    public static void main(String[] args) {
        try {
            JsonObject jsonResponse = APICliente.getExchangeRates();
            JsonObject rates = jsonResponse.getAsJsonObject("rates");

            System.out.println(rates.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
