package com.devappron.backendjava.data.repositorios;

import java.util.List;

import com.devappron.backendjava.data.entidades.PartidoEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPartidoRepository extends PagingAndSortingRepository<PartidoEntity, Long> {
    
    List<PartidoEntity> getByUsuarioEntityIdOrderByCreatedAtDesc(long usuarioEntityId);

    @Query(value = "SELECT * FROM partidos ORDER BY created_at DESC LIMIT 20",
            nativeQuery = true)
    List<PartidoEntity> getUltimosPartidos();

    PartidoEntity findByPartidoId(String id);

}
