
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*COTADOR POR NOMBRE DE MASCOTAS*/
public class PetCounter {

    private List<Pet> petsSold;

    public PetCounter(List<Pet> petsSold) {
        this.petsSold = petsSold;
    }

    public Map<String, Integer> countPetsByName() {
        Map<String, Integer> petCount = new HashMap<>();
        for (Pet pet : petsSold) {
            String petName = pet.getName();
            if (petCount.containsKey(petName)) {
                int count = petCount.get(petName);
                petCount.put(petName, count + 1);
            } else {
                petCount.put(petName, 1);
            }
        }
        return petCount;
    }

}
