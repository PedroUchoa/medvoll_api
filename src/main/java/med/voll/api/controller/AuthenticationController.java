package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.user.DadosAutenticacao;
import med.voll.api.domain.user.User;
import med.voll.api.infra.security.TokeDataJWT;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity makeLogin(@RequestBody @Valid DadosAutenticacao dados) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.tokenGenerator((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokeDataJWT(tokenJWT));

    }

}
