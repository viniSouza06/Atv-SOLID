package br.com.nogueiranogueira.aularefatoracao.solidproject.controller;

import br.com.nogueiranogueira.aularefatoracao.solidproject.dto.UsuarioDTO;
import br.com.nogueiranogueira.aularefatoracao.solidproject.model.Usuario;
import br.com.nogueiranogueira.aularefatoracao.solidproject.service.GerenciadorUsuarioService;
import br.com.nogueiranogueira.aularefatoracao.solidproject.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private GerenciadorUsuarioService usuarioService;

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody UsuarioDTO dto) {
        try{
            Usuario usuario = usuarioService.criarUsuario(dto);
            notificationService.enviarEmailBoasVindas(usuario.getEmail(), usuario.getNome());
            return ResponseEntity.ok("Usuário criado com sucesso: " + usuario.getNome());
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
