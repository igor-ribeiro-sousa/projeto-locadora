package com.pro.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.pro.entities.dtos.LocacaoDTO;

@Entity
public class Locacao implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected Integer id;

   protected Integer codigoUsuario;

   @ManyToOne
   @JoinColumn(name = "codigoUsuario", insertable = false, updatable = false)
   protected Usuario usuario;

   protected Integer codigoVeiculo;

   @ManyToOne
   @JoinColumn(name = "codigoVeiculo", insertable = false, updatable = false)
   protected Veiculo veiculo;

   protected Date dataReserva;

   protected Date dataLocacao;

   protected Date dataEntrega;
   
   public Locacao()
   {
   }

   
   public Locacao(Integer id, Integer codigoUsuario, Usuario usuario, Integer codigoVeiculo, Veiculo veiculo, Date dataReserva,
         Date dataLocacao, Date dataEntrega)
   {
      super();
      this.id = id;
      this.codigoUsuario = codigoUsuario;
      this.usuario = usuario;
      this.codigoVeiculo = codigoVeiculo;
      this.veiculo = veiculo;
      this.dataReserva = dataReserva;
      this.dataLocacao = dataLocacao;
      this.dataEntrega = dataEntrega;
   }
   
   public Locacao(LocacaoDTO objDTO)
   {
      super();
      this.id = objDTO.getId();
      this.codigoUsuario = objDTO.getCodigoUsuario();
      this.usuario = objDTO.getUsuario();
      this.codigoVeiculo = objDTO.getCodigoVeiculo();
      this.veiculo = objDTO.getVeiculo();
      this.dataReserva = objDTO.getDataReserva();
      this.dataLocacao = objDTO.getDataLocacao();
      this.dataEntrega = objDTO.getDataEntrega();
   }

   public Integer getId()
   {
      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
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

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Locacao other = (Locacao) obj;
      return Objects.equals(id, other.id);
   }

}
