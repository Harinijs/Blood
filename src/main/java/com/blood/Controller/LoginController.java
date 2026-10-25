package com.blood.Controller;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blood.Domain.Donor;
import com.blood.Repository.DonorRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final DonorRepository repo;

    public LoginController(DonorRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/signin")
    public String showLoginPage() {
        return "redirect:/signin.html";
    }

    @PostMapping("/signin")
    public String Login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {

        Donor entity = repo.findByEmailAndPassword(email, password);

        if (entity == null) {
            return "redirect:/signin.html";
        }

        session.setAttribute("donor", entity);

        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "redirect:/dashboard.html";
    }

    @GetMapping("/donor-info")
    @ResponseBody
    public Donor donorInfo(HttpSession session) {

        Donor donor = (Donor) session.getAttribute("donor");

        return donor;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/index.html";
    }
}
