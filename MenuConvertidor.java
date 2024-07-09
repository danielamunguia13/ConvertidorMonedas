import java.util.Scanner;
import java.util.HashMap;
import com.google.gson.JsonObject;

public class MenuConvertidor {
    public static void main(String[] args) {
        try {
            // Obtener las tasas de cambio desde la API
            JsonObject jsonResponse = APICliente.getExchangeRates();
            JsonObject rates = jsonResponse.getAsJsonObject("conversion_rates");

            // Mapa de monedas con sus nombres
            HashMap<Integer, String[]> currencyMap = new HashMap<>();
            currencyMap.put(1, new String[]{"ARS", "Peso argentino"});
            currencyMap.put(2, new String[]{"BOB", "Boliviano boliviano"});
            currencyMap.put(3, new String[]{"BRL", "Real brasileño"});
            currencyMap.put(4, new String[]{"CLP", "Peso chileno"});
            currencyMap.put(5, new String[]{"COP", "Peso colombiano"});
            currencyMap.put(6, new String[]{"USD", "Dólar estadounidense"});
            currencyMap.put(7, new String[]{"MXN", "Peso mexicano"});
            currencyMap.put(8, new String[]{"EUR", "Euro"});
            currencyMap.put(9, new String[]{"SALIR", "Salir"});

            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Muestra el menú de monedas de origen
                System.out.println("Seleccione la moneda de origen:");
                for (int i = 1; i <= currencyMap.size(); i++) {
                    System.out.println(i + ". " + currencyMap.get(i)[0] + " - " + currencyMap.get(i)[1]);
                }
                int baseChoice = scanner.nextInt();
                if (baseChoice == 9) {
                    System.out.println("Saliendo del programa...");
                    break;
                }
                String[] baseCurrencyInfo = currencyMap.get(baseChoice);
                String baseCurrency = baseCurrencyInfo[0];

                // Muestra el menú de monedas de destino
                System.out.println("Seleccione la moneda de destino:");
                for (int i = 1; i <= currencyMap.size(); i++) {
                    if (i != baseChoice) {
                        System.out.println(i + ". " + currencyMap.get(i)[0] + " - " + currencyMap.get(i)[1]);
                    }
                }
                int targetChoice = scanner.nextInt();
                if (targetChoice == 9) {
                    System.out.println("Saliendo del programa...");
                    break;
                }
                String[] targetCurrencyInfo = currencyMap.get(targetChoice);
                String targetCurrency = targetCurrencyInfo[0];

                // Pide el monto a convertir
                System.out.print("Ingrese el monto a convertir: ");
                double amount = scanner.nextDouble();

                // Realiza la conversión de monedas
                if (rates.has(baseCurrency) && rates.has(targetCurrency)) {
                    double baseRate = rates.get(baseCurrency).getAsDouble();
                    double targetRate = rates.get(targetCurrency).getAsDouble();
                    double convertedAmount = amount / baseRate * targetRate;
                    System.out.println(amount + " " + baseCurrency + " (" + baseCurrencyInfo[1] + ") es igual a " + convertedAmount + " " + targetCurrency + " (" + targetCurrencyInfo[1] + ")");
                } else {
                    System.out.println("Moneda de destino no válida.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
