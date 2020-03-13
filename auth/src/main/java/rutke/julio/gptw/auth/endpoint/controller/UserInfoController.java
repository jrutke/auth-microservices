package rutke.julio.gptw.auth.endpoint.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rutke.julio.gptw.auth.endpoint.service.DAOService;
import rutke.julio.gptw.auth.endpoint.service.MailService;
import rutke.julio.gptw.auth.endpoint.service.SMSService;
import rutke.julio.gptw.core.model.ApplicationUser;
import rutke.julio.gptw.core.repository.ApplicationUserRepository;

import javax.xml.bind.ValidationException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserInfoController {

    private final ApplicationUserRepository applicationUserRepository;

    @Autowired
    private MailService mailService;
    @Autowired
    private SMSService smsService;
    @Autowired
    private DAOService daoService;

    @GetMapping(path = "info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationUser> getUserInfo(Principal principal) {
        ApplicationUser applicationUser = (ApplicationUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return new ResponseEntity<>(applicationUser, HttpStatus.OK);
    }

    @SneakyThrows
    @PostMapping("/add")
    public Boolean create(@RequestBody Map<String, String> body) throws NoSuchAlgorithmException {
        String username = body.get("username");
        if (applicationUserRepository.existsByUsername(username)){
            throw new ValidationException("Username already existed");
        }

        String name = body.get("name");
        String email = body.get("email");
        String telefone = body.get("telefone");
        String password = body.get("password");
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        String role = "USER";
        applicationUserRepository.save(new ApplicationUser(username, encodedPassword, name, email, telefone, role));
        return true;
    }

    @PostMapping("/email")
    public ResponseEntity<Object> sendEmail(@RequestBody Map<String, String> body, Principal principal) {
        ApplicationUser applicationUser = (ApplicationUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        String code = String.valueOf(new Random().nextInt(9999)+1000);

        mailService.sendEmail(body.get("email"), code);
        daoService.updateFAProperties(applicationUser.getId().toString(), code);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/sms")
    public ResponseEntity<Object> smsSend(@RequestBody Map<String, String> body) {
        String code = String.valueOf(new Random().nextInt(9999)+1000);

        smsService.sendSMS(body.get("mobile"), code);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}