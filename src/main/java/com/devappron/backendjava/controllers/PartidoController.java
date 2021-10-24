package com.devappron.backendjava.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.devappron.backendjava.models.peticiones.PartidoActualizarRequestModel;
import com.devappron.backendjava.models.peticiones.PartidoCrearRequestModel;
import com.devappron.backendjava.models.respuestas.MensajeOperacionModel;
import com.devappron.backendjava.models.respuestas.PartidoRestModel;
import com.devappron.backendjava.services.IPartidoService;
import com.devappron.backendjava.services.IUsuarioService;
import com.devappron.backendjava.shared.PartidoActualizarDto;
import com.devappron.backendjava.shared.PartidoCrearDto;
import com.devappron.backendjava.shared.PartidoDto;
import com.devappron.backendjava.shared.UsuarioDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

    @Autowired
    ModelMapper modelmapper;

    @Autowired
    IPartidoService iPartidoService;

    @Autowired
    IUsuarioService iUsuarioService;

    
    @PostMapping
    public PartidoRestModel crearPartido(@RequestBody @Valid PartidoCrearRequestModel partidoCrearRequestModel){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getPrincipal().toString();

        PartidoCrearDto partidoCrearDto = modelmapper.map(partidoCrearRequestModel, PartidoCrearDto.class);
        partidoCrearDto.setUserName(username);

        PartidoDto partidoDto= iPartidoService.crearPartido(partidoCrearDto);

        PartidoRestModel partidoRestModel= modelmapper.map(partidoDto, PartidoRestModel.class);

        // if(partidoRestModel.getFecha().compareTo(new Date(System.currentTimeMillis())) < 0 ){
        //     partidoRestModel.setJugado(true);
        // }

        return partidoRestModel;
    }

    @GetMapping(path="/ultimos")
    public List<PartidoRestModel> ultimosPartidos(){
        
        List<PartidoDto> patidoDtoList=iPartidoService.obtenerUltimos();

        List<PartidoRestModel> partidoRestModelList=new ArrayList<>();

        for(PartidoDto partidoDto : patidoDtoList){
            PartidoRestModel partidoRestModel= modelmapper.map(partidoDto, PartidoRestModel.class);
            partidoRestModelList.add(partidoRestModel);
        }

        return partidoRestModelList;
    }

    @GetMapping(path="/{id}")
    public PartidoRestModel obtenerPartido(@PathVariable String id){

        PartidoDto partidoDto= iPartidoService.obtenerPartido(id);

        PartidoRestModel partidoRestModel = modelmapper.map(partidoDto, PartidoRestModel.class);

        // if(partidoRestModel.getFecha().compareTo(new Date(System.currentTimeMillis())) < 0 ){
        //     partidoRestModel.setJugado(true);
        // }

        return partidoRestModel;
    }

    @DeleteMapping(path="/{id}")
    public MensajeOperacionModel eliminarPartido(@PathVariable String id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getPrincipal().toString();

        UsuarioDto usuarioDto= iUsuarioService.obtenerUsuario(username);

        MensajeOperacionModel mensajeOperacionModel= new MensajeOperacionModel();
        mensajeOperacionModel.setNombre("Eliminar");

        iPartidoService.eliminarPartido(id, usuarioDto.getId());

        mensajeOperacionModel.setResultado("Exitoso");

        return mensajeOperacionModel;
    }

    @PutMapping(path = "/{id}")
    public PartidoRestModel actualizarPartido(@RequestBody @Valid PartidoActualizarRequestModel partidoActualizarRequestModel, @PathVariable String id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getPrincipal().toString();

        PartidoActualizarDto partidoActualizarDto= modelmapper.map(partidoActualizarRequestModel, PartidoActualizarDto.class);
        partidoActualizarDto.setUserName(username);

        PartidoDto partidoDto= iPartidoService.actualizarPartido(id, partidoActualizarDto);

        PartidoRestModel partidoRestModel = modelmapper.map(partidoDto, PartidoRestModel.class);

        return partidoRestModel;

    }

}

