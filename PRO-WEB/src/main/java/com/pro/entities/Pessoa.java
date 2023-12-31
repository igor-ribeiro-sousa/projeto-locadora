package com.pro.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.pro.entities.enums.Perfil;

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

   public Pessoa()
   {
      super();
   }

   public Pessoa(Integer id, String nome, String cpf, String email, String telefone, Date dataNascimento, String flagAtivo)
   {
      super();
      this.id = id;
      this.nome = nome;
      this.cpf = cpf;
      this.email = email;
      this.telefone = telefone;
      this.dataNascimento = dataNascimento;
      this.flagAtivo = flagAtivo;
   }

   public Pessoa(Integer id, String nome, String cpf, String email, String senha, String telefone, Date dataNascimento, String flagAtivo)
   {
      super();
      this.id = id;
      this.nome = nome;
      this.cpf = cpf;
      this.email = email;
      this.senha = senha;
      this.telefone = telefone;
      this.dataNascimento = dataNascimento;
      this.flagAtivo = flagAtivo;
   }

   public Integer getId()
   {
      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public String getNome()
   {
      return nome;
   }

   public void setNome(String nome)
   {
      this.nome = nome;
   }

   public String getCpf()
   {
      return cpf;
   }

   public void setCpf(String cpf)
   {
      this.cpf = cpf;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getSenha()
   {
      return senha;
   }

   public void setSenha(String senha)
   {
      this.senha = senha;
   }

   public String getTelefone()
   {
      return telefone;
   }

   public void setTelefone(String telefone)
   {
      this.telefone = telefone;
   }

   public Date getDataNascimento()
   {
      return dataNascimento;
   }

   public void setDataNascimento(Date dataNascimento)
   {
      this.dataNascimento = dataNascimento;
   }

   public Date getDataInclusao()
   {
      return dataInclusao;
   }

   public void setDataInclusao(Date dataInclusao)
   {
      this.dataInclusao = dataInclusao;
   }

   public String getFlagAtivo()
   {
      return flagAtivo;
   }

   public void setFlagAtivo(String flagAtivo)
   {
      this.flagAtivo = flagAtivo;
   }

   public Set<Perfil> getPerfis()
   {
      return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
   }

   public void addPerfil(Perfil perfil)
   {
      this.perfis.add(perfil.getCodigo());
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Pessoa other = (Pessoa) obj;
      if (cpf == null)
      {
         if (other.cpf != null) return false;
      }
      else if (!cpf.equals(other.cpf)) return false;
      if (id == null)
      {
         if (other.id != null) return false;
      }
      else if (!id.equals(other.id)) return false;
      return true;
   }
}
