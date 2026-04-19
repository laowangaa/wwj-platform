import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.wwj.auth.model.User;
import com.wwj.auth.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(User user) {
        if (user == null || StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return "Username and password cannot be empty";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Logic to create and return a token using Sa-Token
            return "Token for user: " + username;
        }
        return "Invalid username or password";
    }

    public String logout(String token) {
        // Logic to handle logout using Sa-Token
        return "Logged out successfully";
    }

    public String refresh(String token) {
        // Logic to refresh token using Sa-Token
        return "Refreshed token";
    }
}