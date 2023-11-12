package com.pro.entities.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.pro.entities.Locacao;
import com.pro.entities.Usuario;
import com.pro.entities.Veiculo;

public class LocacaoDTO implements Serializable
{
   private static final long serialVersionUID = 1L;

   protected Integer id;

   @NotNull(message = "O campo CODIGO USUARIO é requerido")
   protected Integer codigoUsuario;

   protected Usuario usuario;

   @NotNull(message = "O campo CODIGO VEICULO é requerido")
   protected Integer codigoVeiculo;

   protected Veiculo veiculo;

   protected Date dataReserva;

   protected Date dataLocacao;

   protected Date dataEntrega;

   public LocacaoDTO()
   {
   }

   public LocacaoDTO(Locacao obj)
   {
      this.id = obj.getId();
      this.codigoUsuario = obj.getCodigoUsuario();
      this.usuario = obj.getUsuario();
      this.codigoVeiculo = obj.getCodigoVeiculo();
      this.veiculo = obj.getVeiculo();
      this.dataReserva = obj.getDataReserva();
      this.dataLocacao = obj.getDataLocacao();
      this.dataEntrega = obj.getDataEntrega();
   }

   public Integer getId()
   {
      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public Veiculo getVeiculo()
   {
      return veiculo;
   }

   public void setVeiculo(Veiculo veiculo)
   {
      this.veiculo = veiculo;
   }

   public Date getDataReserva()
   {
      return dataReserva;
   }

   public void setDataReserva(Date dataReserva)
   {
      this.dataReserva = dataReserva;
   }

   public Date getDataLocacao()
   {
      return dataLocacao;
   }

   public void setDataLocacao(Date dataLocacao)
   {
      this.dataLocacao = dataLocacao;
   }

   public Date getDataEntrega()
   {
      return dataEntrega;
   }

   public void setDataEntrega(Date dataEntrega)
   {
      this.dataEntrega = dataEntrega;
   }

   public Integer getCodigoUsuario()
   {
      return codigoUsuario;
   }

   public void setCodigoUsuario(Integer codigoUsuario)
   {
      this.codigoUsuario = codigoUsuario;
   }

   public Usuario getUsuario()
   {
      return usuario;
   }

   public void setUsuario(Usuario usuario)
   {
      this.usuario = usuario;
   }

   public Integer getCodigoVeiculo()
   {
      return codigoVeiculo;
   }

   public void setCodigoVeiculo(Integer codigoVeiculo)
   {
      this.codigoVeiculo = codigoVeiculo;
   }

}
