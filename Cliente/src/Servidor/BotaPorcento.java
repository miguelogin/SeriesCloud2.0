package Servidor;

import java.io.Serializable;

public class BotaPorcento implements Serializable{
    
    public String BotaPorcento(String NomeBusca) {
        String[] Anular = {" "};
        if (NomeBusca.contains(" ")) {
            for (String n : Anular) {
                NomeBusca = NomeBusca.replaceAll(n, "%20");
            }
        } else {
            NomeBusca = NomeBusca;
        }
        return NomeBusca;
    }
}
