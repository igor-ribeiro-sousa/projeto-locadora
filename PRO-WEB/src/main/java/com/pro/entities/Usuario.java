package com.pro.entities;

import java.util.Date;
import java.util.stream.Collectors;

import javax.persistence.Entity;

import com.pro.entities.dtos.UsuarioDTO;
import lombok.Builder;

@Entity
public class Usuario extends Pessoa
{
   private static final long serialVersionUID = 1L;

   public Usuario()
   {
      super();
   }

   public Usuario(Integer id, String nome, String cpf, String email, String telefone, Date dataNascimento, String flagAtivo)
   {
      super(id, nome, cpf, email, telefone, dataNascimento, flagAtivo);
   }
   
   public Usuario(Integer id, String nome, String cpf, String email, String senha, String telefone, Date dataNascimento,String flagAtivo)
   {
      super(id, nome, cpf, email, senha, telefone, dataNascimento, flagAtivo);
   }

   public Usuario(UsuarioDTO obj)
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

}
