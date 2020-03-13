package rutke.julio.gptw.funcionario.endpoint.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rutke.julio.gptw.core.model.Example;
import rutke.julio.gptw.core.model.Funcionario;
import rutke.julio.gptw.core.repository.FuncionarioRepository;
import rutke.julio.gptw.funcionario.endpoint.service.FuncionarioService;

import javax.xml.bind.ValidationException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FuncionarioController {
    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioService funcionarioService;

    @GetMapping(path = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Funcionario>> list(Pageable pageable){
        return new ResponseEntity<>(funcionarioService.list(pageable), HttpStatus.OK);
    }

    @SneakyThrows
    @PostMapping(path = "/add")
    public Funcionario create(@RequestBody Funcionario contact){
        return funcionarioRepository.save(contact);
    }
    /*@PostMapping("/add")
    public Boolean create(@RequestBody Map<String, String> body) throws NoSuchAlgorithmException {
        String nome = body.get("nome");
        String cpf = body.get("cpf");
        if (funcionarioRepository.existsByNomeAndCpf(nome, cpf)){
            throw new ValidationException("Funcionário já cadastrado!");
        }

        String email = body.get("email");
        String telefone = body.get("telefone");
        String funcao = body.get("funcao");
        Double salario = Double.parseDouble(body.get("salario"));
        String role = "USER";
        funcionarioRepository.save(new Funcionario(nome, cpf, email, telefone, funcao, salario));
        return true;
    }*/


    @GetMapping
    public List findAll(){
        return (List) funcionarioRepository.findAll();
    }

    @PutMapping(value="/edit/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Funcionario funcionario) {
        return funcionarioRepository.findById(id)
                .map(record -> {
                    record.setNome(funcionario.getNome());
                    record.setEmail(funcionario.getEmail());
                    record.setTelefone(funcionario.getTelefone());
                    record.setCpf(funcionario.getCpf());
                    record.setFuncao(funcionario.getFuncao());
                    record.setSalario(funcionario.getSalario());
                    Funcionario updated = funcionarioRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"busca/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return funcionarioRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"excluir/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        return funcionarioRepository.findById(id)
                .map(record -> {
                    funcionarioRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}
