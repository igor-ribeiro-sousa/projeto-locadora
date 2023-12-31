package com.pro.entities.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.pro.entities.Usuario;
import com.pro.entities.Veiculo;
import com.pro.entities.enums.Perfil;

public class UsuarioDTO implements Serializable
{
   private static final long serialVersionUID = 1L;

   private Integer id;

   @NotNull(message = "O campo NOME é requerido")
   protected String nome;

   @NotNull(message = "O campo CPF é requerido")
   @CPF
   protected String cpf;

   @NotNull(message = "O campo EMAIL é requerido")
   protected String email;

   protected String senha;

   @NotNull(message = "O campo TELEFONE é requerido")
   protected String telefone;

   @NotNull(message = "O campo DATA DE NASCIMENTO é requerido")
   protected Date dataNascimento;

   protected Date dataInclusao = new Date();

   protected String flagAtivo;

   protected Set<Integer> perfis = new HashSet<>();

   public UsuarioDTO()
   {
      super();
   }

   public UsuarioDTO(Usuario obj)
   {
      super();
      this.id = obj.getId();
      this.nome = obj.getNome();
      this.cpf = obj.getCpf();
      this.email = obj.getEmail();
      this.senha = obj.getSenha();
      this.telefone = obj.getTelefone();
      this.dataNascimento = obj.getDataNascimento();
      this.dataInclusao = obj.getDataInclusao();
      this.flagAtivo = obj.getFlagAtivo();
      this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
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

   public Set<Perfil> getPerfis()
   {
      return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
   }

   public void addPerfil(Perfil perfil)
   {
      this.perfis.add(perfil.getCodigo());
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

}
