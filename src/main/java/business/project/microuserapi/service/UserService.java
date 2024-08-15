package business.project.microuserapi.service;

import business.project.microuserapi.dto.UserDTO;
import business.project.microuserapi.helper.CPFGenerator;
import business.project.microuserapi.model.User;
import business.project.microuserapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import business.project.microuserapi.helper.CPFGenerator.*;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> getAll(){
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long userId){
        return UserDTO.convert(userRepository
                .findById(userId).orElseThrow(() -> new RuntimeException()));
    }

    public UserDTO save(UserDTO userDTO){
        userDTO.setDataCadastro(LocalDateTime.now());
        userDTO.setCpf(CPFGenerator.generateCPF());
        User user = userRepository.save(User.convert(userDTO));
        return UserDTO.convert(user);
    }

    public UserDTO delete(long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
        userRepository.delete(user);
        return UserDTO.convert(user);
    }

    public UserDTO findByCpf(String cpf){
        User user = userRepository.findByCpf(cpf);
        if (user != null){
            return UserDTO.convert(user);
        }
        return null;
    }

    public List<UserDTO> queryByName(String name){
        List<User> usuarios = userRepository.queryByNomeLike(name);
        return usuarios
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    public UserDTO editUser(Long Id, UserDTO userDTO){
        User user = userRepository.findById(Id).orElseThrow(() -> new RuntimeException());

        if (userDTO.getSobrenome() != null && !user.getSobrenome().equals(userDTO.getSobrenome())){
            user.setSobrenome(userDTO.getSobrenome());
        }
        if (userDTO.getCpf() != null && !user.getCpf().equals(userDTO.getCpf())){
            user.setCpf(userDTO.getCpf());
        }
        user = userRepository.save(user);
        return UserDTO.convert(user);
    }

    public Page<UserDTO> getAllPages(Pageable page){
        Page<User> users = userRepository.findAll(page);
        return users.map(UserDTO::convert);
    }
}
