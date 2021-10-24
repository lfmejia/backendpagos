package com.devappron.backendjava.models.respuestas;

import java.util.List;

public class UsuarioRestModel {

    private String userId; 
    private String nombre;
    private String correo;
    private String userName;
    private List<PartidoRestModel> partidoRestModelList;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public List<PartidoRestModel> getPartidoRestModelList() {
        return partidoRestModelList;
    }
    public void setPartidoRestModelList(List<PartidoRestModel> partidoRestModelList) {
        this.partidoRestModelList = partidoRestModelList;
    }

      
   
}
