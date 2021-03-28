package com.example.demo.Controllers;

import com.example.demo.Entities.User;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin("*") //Allow All ports :)
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserRepository UR;
    @Autowired
    private PasswordEncoder Bcrypt ;
    @GetMapping("getusers")
    public List<User> getAllusers(){
         return UR.findAll();
    }
    @PostMapping("saveuser")
    public Boolean saveUser(@RequestBody User u){
        try{
            System.out.println(u.getPassword());
            System.out.println(""+Bcrypt.encode(u.getPassword()));
            //EXP
            // old password="Guedri"//
            u.setPassword(Bcrypt.encode(u.getPassword()));
            //SAVED password =" CRYPTED ONE"
            u.setRole("CLIENT");
            UR.save(u);
            return true;
        }catch (Exception E ){
            System.out.println(E.getMessage());
            return  false;
        }
    }
    @GetMapping("login")
    public Principal login(Principal p){
        System.out.println("User Logged"+p);
        return p ;
    }
}
