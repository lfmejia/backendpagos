package com.devappron.backendjava.services;

import java.util.List;

import com.devappron.backendjava.shared.PartidoDto;
import com.devappron.backendjava.shared.UsuarioCrearDto;
import com.devappron.backendjava.shared.UsuarioDto;

import org.springframework.security.core.userdetails.UserDetailsService;


public interface IUsuarioService extends UserDetailsService{

    public UsuarioDto crearUsuario(UsuarioCrearDto usuarioCrearDto);
    public UsuarioDto obtenerUsuario(String userName);
    public List<PartidoDto> obtenerUsuarioPartidos(String userName);
    
}
