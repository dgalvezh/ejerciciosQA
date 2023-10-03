import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Map;


public class Api23 {

    private static final String BASE_URL = "https://petstore.swagger.io";

    public static void main(String[] args) {

        //EJERCICIO 3.2

        List<Pet> soldPets = getPetsByStatus("sold");

        System.out.println("ID Y NOMBRE DE LAS MASCOTAS VENDIDAS: \n");

        for (int i = 0; i < soldPets.size(); i++) {
            Pet pet = soldPets.get(i);
            System.out.println("**** ID: " + pet.getId() + " **** NOMBRE: " + pet.getName()  + " **** \n");
        }

        //EJERCICIO 3.3

        System.out.println("CANTIDAD DE MASCOTAS VENDIDAS CON MISMO NOMBRE:\n");

        //mascotas vendidas
        PetCounter contadorPet = new PetCounter(soldPets);

        //un map donde se inserta las mascotas vendidas y numero de veces
        Map<String, Integer> petCount = contadorPet.countPetsByName();

        //imprimir por pantalla los datos de mascotas duplicadas
        for (Map.Entry<String, Integer> entry : petCount.entrySet()) {
            String nombre = entry.getKey();
            Integer cantidad = entry.getValue();
            System.out.println(nombre + " === " + cantidad);
        }

    }

    private static List<Pet> getPetsByStatus(String status) {
        List<Pet> soldPets = new ArrayList<>();

        try {
            URL url = new URL(BASE_URL + "/v2/pet/findByStatus?status=" + status);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int statusCode = conn.getResponseCode();

            if (statusCode == 200) {

                //conversion a caracteres para la lectura
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // Parsear la respuesta JSON
                Gson gson = new Gson();
                JsonArray jsonArray = gson.fromJson(response.toString(), JsonArray.class);

                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject petJson = jsonArray.get(i).getAsJsonObject();
                    Pet pet = gson.fromJson(petJson, Pet.class);
                    soldPets.add(pet);
                }
        } else {
            System.err.println("Error en la solicitud: Código de estado " + statusCode);
        }

        } catch(Exception e){
            System.err.println("Error al obtener las mascotas vendidas: " + e.getMessage());
        }

        return soldPets;
    }

}
