package com.SeromSb.dajuva.app.demodajuva.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SeromSb.dajuva.app.demodajuva.modelo.Venta;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Integer>{

}
