package com.devappron.backendjava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.devappron.backendjava.data.entidades.PartidoEntity;
import com.devappron.backendjava.data.entidades.UsuarioEntity;
import com.devappron.backendjava.data.repositorios.IPartidoRepository;
import com.devappron.backendjava.data.repositorios.IUsuarioRepository;
import com.devappron.backendjava.errors.UsuarioExisteException;
import com.devappron.backendjava.shared.PartidoDto;
import com.devappron.backendjava.shared.UsuarioCrearDto;
import com.devappron.backendjava.shared.UsuarioDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService{ 

    @Autowired
    ModelMapper modelmapper;

    @Autowired
    IUsuarioRepository iUsuarioRepositorio; 

    @Autowired
    BCryptPasswordEncoder bcryptPasswordEncoder;

    
    @Autowired
    IPartidoRepository iPartidoRepository; 


    @Override
    public UsuarioDto crearUsuario(UsuarioCrearDto usuarioCrearDto) {

        if(iUsuarioRepositorio.findByCorreo(usuarioCrearDto.getCorreo()) != null){
            throw new UsuarioExisteException("Este correo ya se encuentra registrado");
        }

        if(iUsuarioRepositorio.findByUserName(usuarioCrearDto.getUserName()) != null){
            throw new UsuarioExisteException("Este nombre de usuario ya esta en uso");
        }
         
         
        UsuarioEntity usuarioEntityDto= modelmapper.map(usuarioCrearDto, UsuarioEntity.class);
        usuarioEntityDto.setEncryptedPassword(bcryptPasswordEncoder.encode(usuarioCrearDto.getPassword()));
        usuarioEntityDto.setUserId(UUID.randomUUID().toString());

        UsuarioEntity usuarioEntitySave=iUsuarioRepositorio.save(usuarioEntityDto);
        
        UsuarioDto usuarioDto= modelmapper.map(usuarioEntitySave, UsuarioDto.class);
      
        return usuarioDto;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        
        UsuarioEntity usuarioEntity= iUsuarioRepositorio.findByUserName(userName);

        if(usuarioEntity==null) {
            throw new UsernameNotFoundException(userName);
        }
        
        return new User(usuarioEntity.getUserName(), usuarioEntity.getEncryptedPassword(), new ArrayList<>());
    }

    @Override
    public UsuarioDto obtenerUsuario(String userName) {

        UsuarioEntity usuarioEntity= iUsuarioRepositorio.findByUserName(userName);
        
        if(usuarioEntity==null){
            throw new UsernameNotFoundException(userName);
        }

        UsuarioDto usuarioDtoObtenido= modelmapper.map(usuarioEntity, UsuarioDto.class);

        return usuarioDtoObtenido;
    }


    @Override
    public List<PartidoDto> obtenerUsuarioPartidos(String userName) {

        UsuarioEntity usuarioEntity= iUsuarioRepositorio.findByUserName(userName);
        
        List<PartidoEntity> PartidoEntityList=iPartidoRepository.getByUsuarioEntityIdOrderByCreatedAtDesc(usuarioEntity.getId());

        List<PartidoDto> partidoDtoList = new ArrayList<PartidoDto>();

        for(PartidoEntity partidoEntity : PartidoEntityList){
            PartidoDto partidoDto= modelmapper.map(partidoEntity, PartidoDto.class);
            partidoDtoList.add(partidoDto);
        }

        return partidoDtoList;
    }
    
}
