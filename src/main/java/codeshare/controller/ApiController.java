package codeshare.controller;

import codeshare.code.Code;
import codeshare.service.AppService;
import codeshare.service.ServiceResponse;
import codeshare.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private AppService service;

    @GetMapping("/code")
    private ServiceResponse getCode(@RequestParam(name = "uuid") String uuid, Principal principal) {
        return service.getCode(uuid, principal.getName());
    }

    @PostMapping("/post")
    private ServiceResponse postCode(@RequestBody Code code, Principal principal) {
        return service.saveCode(code, principal.getName());
    }

    @DeleteMapping("/delete")
    private ServiceResponse deleteCode(@RequestParam(name = "uuid") String uuid, Principal principal) {
        return service.deleteCode(uuid, principal.getName());
    }

    @PutMapping("/update")
    private ServiceResponse updateCode(@RequestBody Code code, Principal principal) {
        return service.updateCode(code, principal.getName());
    }

    @GetMapping("/my-code")
    private Map<String, String> getMyCode(Principal principal) {
        return service.getUserCodeList(principal.getName());
    }

    @PostMapping("/register")
    private ServiceResponse registerUser(@RequestBody User user) {
        return service.saveUser(user);
    }
}
