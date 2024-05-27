package online.gemfpt.BE.Service;

import online.gemfpt.BE.Entity.Account;
import online.gemfpt.BE.Repository.AuthenticationRepository;
import online.gemfpt.BE.model.AccountResponse;
import online.gemfpt.BE.model.LoginRequest;
import online.gemfpt.BE.model.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service

public class AuthenticationService implements UserDetailsService {
// xu ly logic
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AuthenticationRepository authenticationRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Account register(RegisterRequest registerRequest){
        Account account = new Account();
        account.setPhone(registerRequest.getPhone());
        account.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        //xu ly logic register

        // nho repo set data xuong db
        return authenticationRepository.save(account);
    }
    public Account login (LoginRequest loginRequest) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getPhone(),
                    loginRequest.getPassword()
            ));
        // data qua cacs try =>> acc chinh xac
        Account account = authenticationRepository.findAccountByPhone(loginRequest.getPhone());
        String token = tokenService.generateToken(account);

        AccountResponse accountResponse= new AccountResponse();
        accountResponse.setPhone(account.getPhone());
        accountResponse.setToken(token);
        return  accountResponse;
    }
    public List<Account> all() {
        return authenticationRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        return authenticationRepository.findAccountByPhone(phone) ;
    }

}
