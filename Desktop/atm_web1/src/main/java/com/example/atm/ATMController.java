package com.example.atm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ATMController {
    private ATM atm = new ATM(10000);
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "ATMへようこそ");
        model.addAttribute("balance", atm.checkBalance());
        return "index";
    }
    
    @GetMapping("/balance")
    public String showBalance(Model model) {
        model.addAttribute("balance", atm.checkBalance());
        return "balance";
    }
    
    @PostMapping("/deposit")
    public String deposit(@RequestParam int amount, Model model) {
        if (amount <= 0) {
            model.addAttribute("message", "入金金額は1円以上で入力してください");
            model.addAttribute("balance", atm.checkBalance());
            return "index";
        }
        model.addAttribute("message", atm.deposit(amount));
        model.addAttribute("balance", atm.checkBalance());
        return "index";
    }
    
    @PostMapping("/withdraw")
    public String withdraw(@RequestParam int amount, Model model) {
        String message;
        if (amount <= 0) {
            message = "出金金額は1円以上で入力してください";
        } else {
            message = atm.withdraw(amount);
        }
        model.addAttribute("message", message);
        model.addAttribute("balance", atm.checkBalance());
        return "index";
    }
    

}
