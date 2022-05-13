package kz.pine.controller;

import kz.pine.domain.User;
import kz.pine.services.CategoryService;
import kz.pine.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String main(
            Model model,
            @AuthenticationPrincipal User user
    ) {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", user);
        data.put("categories", categoryService.findAllDetails());

        model.addAttribute("frontendData", data);
        return "index";
    }
}