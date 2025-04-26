package ua.opnu.practice1_template.mvs;

import ch.qos.logback.core.model.Model;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ua.opnu.practice1_template.user.User;
import ua.opnu.practice1_template.user.UserRepository;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

  public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {

      this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
  }
   @GetMapping("/register")
    public String registerForm(Model model) {
      model.addText("user");
      return "register";
   }
}
