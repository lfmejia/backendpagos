package com.devappron.backendjava.services;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import com.devappron.backendjava.data.entidades.EquipoEntity;
import com.devappron.backendjava.data.entidades.PartidoEntity;
import com.devappron.backendjava.data.entidades.UsuarioEntity;
import com.devappron.backendjava.data.repositorios.IEquipoRepository;
import com.devappron.backendjava.data.repositorios.IPartidoRepository;
import com.devappron.backendjava.data.repositorios.IUsuarioRepository;
import com.devappron.backendjava.shared.PartidoActualizarDto;
import com.devappron.backendjava.shared.PartidoCrearDto;
import com.devappron.backendjava.shared.PartidoDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidoService implements IPartidoService{

    @Autowired
    ModelMapper modelmapper;

    @Autowired
    IUsuarioRepository iUsuarioRepository;

    @Autowired
    IEquipoRepository iEquipoRepository;

    @Autowired
    IPartidoRepository iPartidoRepository;

    @Override
    public PartidoDto crearPartido(PartidoCrearDto partidoCrearDto) {

        UsuarioEntity usuarioEntity = iUsuarioRepository.findByUserName(partidoCrearDto.getUserName());

        EquipoEntity equipoEntityLocal= iEquipoRepository.findById(partidoCrearDto.getEquipoLocal());

        EquipoEntity equipoEntityVisitante= iEquipoRepository.findById(partidoCrearDto.getEquipoVisitante());

        PartidoEntity partidoEntity= new PartidoEntity();
        partidoEntity.setPartidoId(UUID.randomUUID().toString());
        partidoEntity.setFecha(partidoCrearDto.getFecha());
        partidoEntity.setGolesLocal("0");
        partidoEntity.setGolesVisitante("0");
        partidoEntity.setUsuarioEntity(usuarioEntity);
        partidoEntity.setEquipoEntityLocal(equipoEntityLocal);
        partidoEntity.setEquipoEntityVisitante(equipoEntityVisitante);

        PartidoEntity partidoCreado= iPartidoRepository.save(partidoEntity);
     
        PartidoDto partidoDto= modelmapper.map(partidoCreado, PartidoDto.class);            
        
        return partidoDto;
    }

    @Override
    public List<PartidoDto> obtenerUltimos() {
        
        List<PartidoEntity> partidoEntityList= iPartidoRepository.getUltimosPartidos();

        List<PartidoDto> partidoDtoList=new ArrayList<>();

        for(PartidoEntity partidoEntity: partidoEntityList){
            PartidoDto partidoDto=modelmapper.map(partidoEntity, PartidoDto.class);
            partidoDtoList.add(partidoDto);
        }

        return partidoDtoList;
    }

    @Override
    public PartidoDto obtenerPartido(String id) {

        PartidoEntity partidoEntity= iPartidoRepository.findByPartidoId(id);

        PartidoDto postDto= modelmapper.map(partidoEntity, PartidoDto.class);

        return postDto;
    }

    @Override
    public void eliminarPartido(String partidoId, long usuarioId) {
       
        PartidoEntity partidoEntity= iPartidoRepository.findByPartidoId(partidoId);

        if(partidoEntity.getUsuarioEntity().getId() != usuarioId){
            throw new  RuntimeException("No se puede realizar esta accion");
        }

        iPartidoRepository.delete(partidoEntity);        
    }

    @Override
    public PartidoDto actualizarPartido(String partidoId, PartidoActualizarDto partidoActualizarDto) {
        
        PartidoEntity partidoEntity = iPartidoRepository.findByPartidoId(partidoId);

        UsuarioEntity usuarioEntity= iUsuarioRepository.findByUserName(partidoActualizarDto.getUserName());

        if(partidoEntity.getUsuarioEntity().getId() != usuarioEntity.getId()){
            throw new RuntimeException("No se puede realizar esta acccion");
        }

        partidoEntity.setGolesLocal(partidoActualizarDto.getGolesLocal());
        partidoEntity.setGolesVisitante(partidoActualizarDto.getGolesVisitante());

        PartidoEntity partidoEntityActualizado= iPartidoRepository.save(partidoEntity);

        PartidoDto partidoDto=modelmapper.map(partidoEntityActualizado, PartidoDto.class);

        return partidoDto;
    }
    
}
