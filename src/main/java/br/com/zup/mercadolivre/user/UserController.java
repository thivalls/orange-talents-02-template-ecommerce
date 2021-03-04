package br.com.zup.mercadolivre.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    @PostMapping
    public String store(@Valid @RequestBody UserRequest request) {
        return "route mathced!!!";
    }

}
