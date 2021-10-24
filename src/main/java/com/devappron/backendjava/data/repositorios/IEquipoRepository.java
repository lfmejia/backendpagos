package com.devappron.backendjava.data.repositorios;

import com.devappron.backendjava.data.entidades.EquipoEntity;

import org.springframework.data.repository.CrudRepository;

public interface IEquipoRepository extends CrudRepository<EquipoEntity, Long>{
    
    EquipoEntity findById(long id);
}
