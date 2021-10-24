package com.devappron.backendjava.services;

import java.util.List;

import com.devappron.backendjava.shared.PartidoActualizarDto;
import com.devappron.backendjava.shared.PartidoCrearDto;
import com.devappron.backendjava.shared.PartidoDto;

public interface IPartidoService {

    public PartidoDto crearPartido(PartidoCrearDto partidoCreacionDto);

    public List<PartidoDto> obtenerUltimos();

    public PartidoDto obtenerPartido(String id);

    public void eliminarPartido(String partidoId, long usuarioId);

    public PartidoDto actualizarPartido(String partidoId, PartidoActualizarDto partidoActualizarDto);
    
}
