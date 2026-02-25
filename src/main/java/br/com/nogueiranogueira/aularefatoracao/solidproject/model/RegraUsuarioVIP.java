package br.com.nogueiranogueira.aularefatoracao.solidproject.model;

import br.com.nogueiranogueira.aularefatoracao.solidproject.dto.UsuarioDTO;
import jakarta.persistence.DiscriminatorValue;
import org.springframework.stereotype.Component;

@Component
@DiscriminatorValue("VIP")
public class RegraUsuarioVIP implements  RegraUsuario{

    @Override
    public Usuario criarUsuario(UsuarioDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setTipo(dto.tipo());
        usuario.setPontos(100);

        return usuario;

    }

}
