package sis.module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sis.module.model.Municipios;

import java.util.List;

@Repository
public interface MunicipiosRepository extends JpaRepository<Municipios, Long> {

    @Query( value="select mun from Municipios mun where mun.idUf = :idUf ")
    public List<Municipios> buscarPorUf(@Param("idUf") long idUf);
}
