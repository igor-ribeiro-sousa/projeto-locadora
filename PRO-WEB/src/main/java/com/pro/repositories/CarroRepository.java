package com.pro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.entities.Carro;

public interface CarroRepository extends JpaRepository<Carro, Integer>
{

}
