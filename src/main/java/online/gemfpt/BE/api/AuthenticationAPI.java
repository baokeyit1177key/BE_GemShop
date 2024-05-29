package online.gemfpt.BE.api;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.gemfpt.BE.Entity.Account;
import online.gemfpt.BE.Repository.AuthenticationRepository;
import online.gemfpt.BE.Service.AuthenticationService;
import online.gemfpt.BE.Service.EmailService;
import online.gemfpt.BE.model.EmailDetail;
import online.gemfpt.BE.model.LoginRequest;
import online.gemfpt.BE.model.RegisterRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// nhan request tu fontend
@RestController
@SecurityRequirement(name="api")
@CrossOrigin("*")
public class AuthenticationAPI {
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    EmailService emailService;

    @GetMapping("test")
    public ResponseEntity test() {
        return ResponseEntity.ok("test");
    }


    @GetMapping("Admin_only")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity getAdmin(){return  ResponseEntity.ok("ok");}

    @PostMapping("Register")
    public ResponseEntity Register (@RequestBody RegisterRequest responseRequest){
        Account  account = authenticationService.register(responseRequest);
        return  ResponseEntity.ok(account);
    }
    @GetMapping("send-mail")
    public void sendMail(){
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setRecipient("baoyasuohoang@gmail.com");
        emailDetail.setSubject("test123");
        emailDetail.setMsgBody("aaa");
        emailService.sendMailTemplate(emailDetail);
    }
    @GetMapping("GetAll")
    public ResponseEntity Getallaccount (){
        List<Account> account = authenticationService.all();
        return  ResponseEntity.ok(account);
    }
    @PostMapping("login")
    public ResponseEntity login (@RequestBody LoginRequest loginRequest){

        Account account = authenticationService.login(loginRequest);
        return ResponseEntity.ok(account);
    }





}
