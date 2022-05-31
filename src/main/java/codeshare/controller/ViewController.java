package codeshare.controller;

import codeshare.code.Code;
import codeshare.service.AppService;
import codeshare.service.ServiceResponse;
import codeshare.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.util.List;


@Controller
public class ViewController {

    @Autowired
    private AppService service;

    @GetMapping("/")
    private String homePage() {
        return "home";
    }

    @GetMapping("/post")
    private String postPage(Model model) {
        model.addAttribute("code", new Code());
        return "post";
    }

    @PostMapping("/post")
    private RedirectView postCode(@ModelAttribute Code code, Principal principal, RedirectAttributes attributes) {
        ServiceResponse serviceResponse = service.saveCode(code, principal.getName());
        attributes.addFlashAttribute("serviceResponse", serviceResponse);
        return new RedirectView("/post");
    }

    @GetMapping("/register")
    private String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    private RedirectView registerUser(@ModelAttribute User user, RedirectAttributes attributes) {
        ServiceResponse serviceResponse = service.saveUser(user);
        attributes.addFlashAttribute("serviceResponse", serviceResponse);
        return new RedirectView("/register");
    }

    @PostMapping("/update")
    private RedirectView updateCode(@ModelAttribute Code code, Principal principal, RedirectAttributes attributes) {
        ServiceResponse serviceResponse = service.updateCode(code, principal.getName());
        attributes.addAttribute("uuid", code.getUuid());
        attributes.addFlashAttribute("serviceResponse", serviceResponse);
        return new RedirectView("access");
    }

    @GetMapping("/my-code")
    private String accountPage() {
        return "redirect:/my-code/page=1";
    }

    @GetMapping("/my-code/page={pageNumber}")
    private String accountPage(@PathVariable int pageNumber, Principal principal, Model model) {
        int pageSize = 13;
        Page<Code> page = service.getUserCodePaginated(pageNumber, pageSize, principal.getName());
        List<Code> codeList = page.getContent();
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("codeList", codeList);
        return "my-code";
    }

    @PostMapping("/delete")
    private RedirectView deleteCode(@RequestParam(name = "uuid") String uuid, Principal principal, RedirectAttributes attributes) {
        ServiceResponse serviceResponse = service.deleteCode(uuid, principal.getName());
        attributes.addFlashAttribute("serviceResponse", serviceResponse);
        return new RedirectView("/my-code");
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/access-form")
    private String accessDialogPage() {
        return "access-form";
    }

    @GetMapping("/access")
    private String accessPage(@RequestParam(name = "uuid") String uuid, Model model, Principal principal) {
        ServiceResponse serviceResponse = service.getCode(uuid, principal.getName());
        if (serviceResponse.getResult().equals("Failure!")) {
            model.addAttribute("serviceResponse", serviceResponse);
            return "access-form";
        } else {
            model.addAttribute("code", serviceResponse.getCode());
            return "access";
        }
    }
}
