package com.blood.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blood.Domain.Donor;
import com.blood.Repository.DonorRepository;

@RequestMapping("/signup")
@Controller
public class RegisterController {

    private final DonorRepository repo;

    public RegisterController(DonorRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String DisplaySignup() {
        return "register.html";
    }

    @PostMapping
    public String Signup(@ModelAttribute Donor donor) {
        repo.save(donor);
        return "redirect:/index.html";
    }
}