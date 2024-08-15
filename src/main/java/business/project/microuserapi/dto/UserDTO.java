package business.project.microuserapi.dto;

import business.project.microuserapi.helper.CPFGenerator;
import business.project.microuserapi.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "Sobrenome é obrigatório")
    private String sobrenome;
    private String cpf;
    private LocalDateTime dataCadastro;

    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.id = user.getId();
        userDTO.setNome(user.getNome());
        userDTO.setSobrenome(user.getSobrenome());
        userDTO.setCpf(user.getCpf());
        userDTO.setDataCadastro(user.getDataCadastro());
        return userDTO;
    }


}
