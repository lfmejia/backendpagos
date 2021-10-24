package com.devappron.backendjava.shared;

import java.io.Serializable;
import java.util.Date;


public class PartidoDto implements Serializable {

    private static final long serialVersionUID= 1L;
    
    private long id;
    private String partidoId;
    private Date fecha;
    private String golesLocal;
    private String golesVisitante;
    private Date createdAt;
    private UsuarioDto usuarioDto;
    private EquipoDto equipoDtoLocal;
    private EquipoDto equipoDtoVisitante;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPartidoId() {
        return partidoId;
    }

    public void setPartidoId(String partidoId) {
        this.partidoId = partidoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(String golesLocal) {
        this.golesLocal = golesLocal;
    }

    public String getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(String golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioEntity(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public EquipoDto getEquipoDtoLocal() {
        return equipoDtoLocal;
    }

    public void setEquipoEntityLocal(EquipoDto equipoDtoLocal) {
        this.equipoDtoLocal = equipoDtoLocal;
    }

    public EquipoDto getEquipoDtoVisitante() {
        return equipoDtoVisitante;
    }

    public void setEquipoEntityVisitante(EquipoDto equipoDtoVisitante) {
        this.equipoDtoVisitante = equipoDtoVisitante;
    }

    
}