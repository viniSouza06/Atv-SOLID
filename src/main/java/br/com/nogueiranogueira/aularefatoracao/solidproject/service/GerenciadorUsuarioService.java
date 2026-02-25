package br.com.nogueiranogueira.aularefatoracao.solidproject.service;

import br.com.nogueiranogueira.aularefatoracao.solidproject.dto.UsuarioDTO;
import br.com.nogueiranogueira.aularefatoracao.solidproject.model.RegraUsuario;
import br.com.nogueiranogueira.aularefatoracao.solidproject.model.RegraUsuarioComum;
import br.com.nogueiranogueira.aularefatoracao.solidproject.model.RegraUsuarioVIP;
import br.com.nogueiranogueira.aularefatoracao.solidproject.model.Usuario;
import br.com.nogueiranogueira.aularefatoracao.solidproject.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerenciadorUsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final List<RegraUsuario> regrasUsuario;


    GerenciadorUsuarioService(UsuarioRepository usuarioRepository,List<RegraUsuario> regrasUsuario) {
        this.usuarioRepository = usuarioRepository;
        this.regrasUsuario = regrasUsuario;
    }

    public Usuario criarUsuario(UsuarioDTO dto) {
        validarNome(dto.nome());
        validarEmail(dto.email());
        validarIdade(dto.idade());

        RegraUsuario regra = regrasUsuario.stream()
                .filter(r -> r.criarUsuario(dto).getTipo().equals(dto.tipo()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de usuário inválido"));

        Usuario usuario = regra.criarUsuario(dto);
        return usuarioRepository.save(usuario);
    }

    private void validarEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido");
        }
    }

    private void validarIdade(int idade){
        if (idade < 18) {
            throw new IllegalArgumentException("Usuário deve ser maior de idade");
        }
    }

    private  void validarNome(String nome){
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
    }

}
