package com.SeromSb.dajuva.app.demodajuva.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SeromSb.dajuva.app.demodajuva.modelo.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{

	Usuario findByCorreo(String correo);
		
}
