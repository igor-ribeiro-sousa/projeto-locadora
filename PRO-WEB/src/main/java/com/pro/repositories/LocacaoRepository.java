package com.pro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.entities.Locacao;

public interface LocacaoRepository extends JpaRepository<Locacao, Integer>
{

}
