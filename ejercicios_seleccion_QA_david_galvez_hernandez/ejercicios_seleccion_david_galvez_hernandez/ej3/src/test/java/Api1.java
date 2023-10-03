import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Api1 {

    private static final String BASE_URL = "https://petstore.swagger.io";

    public static void main(String[] args) {

        Scanner captura = new Scanner(System.in);

        System.out.println("RELLENE LOS CAMPOS: ");

        System.out.println("Usuario: ");
        String username = captura.nextLine();

        System.out.println("Email: ");
        String email = captura.nextLine();

        createUser(username, email);
        getUserByUsername(username);

        captura.close();
    }

    /*FUNCION PARA CREAR EL USUARIO*/
    private static void createUser(String username, String email) {
        try {
            URL url = new URL(BASE_URL + "/v2/user");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String requestBody = "{\"username\": \"" + username + "\", \"email\": \"" + email + "\"}";
            conn.getOutputStream().write(requestBody.getBytes());

        int statusCode = conn.getResponseCode();
        if (statusCode == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            System.out.println("///Usuario creado///");
        } else {
            System.err.println("Error al crear el usuario - Código de estado HTTP: " + statusCode);
        }
        } catch (Exception e) {
        System.err.println("Usuario no creado - error: " + e.getMessage());
        }
    }


    /*FUNCION PARA MOSTRAR EL USUARIO*/
    private static void getUserByUsername(String username) {
        try {
            URL url = new URL(BASE_URL + "/v2/user/" + username);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            Gson gson = new Gson();
            JsonObject user = gson.fromJson(response.toString(), JsonObject.class);
            System.out.println("~ DATOS DEL USUARIO ~");
            System.out.println("NOMBRE: " + user.get("username").getAsString());
            System.out.println("EMAIL: " + user.get("email").getAsString());
        } catch (Exception e) {
            System.err.println("Error al obtener los datos del usuario: " + e.getMessage());
        }
    }

}
