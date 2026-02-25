package br.com.nogueiranogueira.aularefatoracao.solidproject.controller;

import br.com.nogueiranogueira.aularefatoracao.solidproject.dto.UsuarioDTO;
import br.com.nogueiranogueira.aularefatoracao.solidproject.model.Usuario;
import br.com.nogueiranogueira.aularefatoracao.solidproject.service.GerenciadorUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gerenciador/usuarios")
public class GerenciadorUsuarioController {


    private final GerenciadorUsuarioService gerenciadorUsuarioService;

    @Autowired
    public  GerenciadorUsuarioController(GerenciadorUsuarioService gerenciadorUsuarioService){
        this.gerenciadorUsuarioService = gerenciadorUsuarioService;
    }



    @PostMapping()
    public ResponseEntity<String> criarUsuario(@RequestBody UsuarioDTO dto) {
        try {
            Usuario usuario = gerenciadorUsuarioService.criarUsuario(dto);
            return ResponseEntity.ok("Usuário criado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao criar usuário");
        }
    }
}
