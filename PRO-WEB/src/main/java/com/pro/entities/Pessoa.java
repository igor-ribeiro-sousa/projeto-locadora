package com.pro.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public abstract class Pessoa implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected Integer id;

   protected String nome;

   @Column(unique = true)
   protected String cpf;

   @Column(unique = true)
   protected String email;

   protected String senha;

   protected String telefone;

   protected Date dataNascimento;

   protected Date dataInclusao = new Date();

   protected String flagAtivo;
   
   @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
   protected List<Veiculo> veiculos = new ArrayList<>();

   @ElementCollection(fetch = FetchType.EAGER)
   @CollectionTable(name = "PERFIS")
   protected Set<Integer> perfis = new HashSet<>();
}
