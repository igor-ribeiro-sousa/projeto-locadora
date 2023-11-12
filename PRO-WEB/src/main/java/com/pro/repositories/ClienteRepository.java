package com.pro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.entities.Usuario;

public interface ClienteRepository extends JpaRepository<Usuario, Integer>
{

}
