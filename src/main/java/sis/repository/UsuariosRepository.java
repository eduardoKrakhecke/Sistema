package sis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sis.model.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

    @Query(value="Select u from Usuarios u where u.login  = :parametroLogin")
    public Usuarios buscarPorLogin(@Param("parametroLogin") String usuarios);
}
