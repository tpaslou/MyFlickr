
import java.util.HashMap;
import java.util.Map;

public class Client_Cart {

    public static HashMap<String, Client> c_cart;
    public static String username;

    public Client_Cart() {
        c_cart = new HashMap<>();
    }

    public boolean CheckEmail(String email) {
        for (Map.Entry<String, Client> entry : c_cart.entrySet()) {
            
            if(entry.getValue().getEmail().equals(email)){
                return true;
            }
           
        }
        return false;
    }

    public boolean add(Client cl) {
        

        if (c_cart.containsKey(cl.getUname()) || CheckEmail(cl.getEmail())) {
            return false;
        } else {
            c_cart.put(cl.getUname(), cl);
        }

        return true;
    }
}
