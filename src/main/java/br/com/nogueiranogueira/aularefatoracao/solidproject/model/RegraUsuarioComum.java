package br.com.nogueiranogueira.aularefatoracao.solidproject.model;

import br.com.nogueiranogueira.aularefatoracao.solidproject.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class RegraUsuarioComum implements RegraUsuario {

    @Override
    public Usuario criarUsuario(UsuarioDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setTipo(dto.tipo());
        usuario.setPontos(0);

        return usuario;

    }

}