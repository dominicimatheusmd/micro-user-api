package business.project.microuserapi.helper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;
public class CPFGenerator {


    public static String generateCPF(){
        Random random = new Random();
        StringBuilder cpf = new StringBuilder();
        for (int i = 0; i < 11; i++){
            int randomBuilder = random.nextInt(10);
            cpf.append(randomBuilder);
        }
        return cpf.toString();
    }
}
