package com.pro.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.pro.entities.enums.Status;

@Entity
public abstract class Veiculo implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected Integer id;

   protected String marca;

   protected String modelo;

   protected String anoFabricacao;

   protected String anoModelo;

   protected BigDecimal valor;

   protected String descricao;

   protected Date dataInclusao = new Date();

   protected String flagAtivo;
   
   @ManyToOne
   @JoinColumn(name = "cod_usuario")
   private Usuario usuario;

   @ElementCollection(fetch = FetchType.EAGER)
   @CollectionTable(name = "STATUS")
   protected Set<Integer> status = new HashSet<>();

   public Veiculo()
   {
      super();
   }

   public Veiculo(Integer id, String marca, String modelo, String anoFabricacao, String anoModelo, BigDecimal valor, String descricao,
          String flagAtivo)
   {
      super();
      this.id = id;
      this.marca = marca;
      this.modelo = modelo;
      this.anoFabricacao = anoFabricacao;
      this.anoModelo = anoModelo;
      this.valor = valor;
      this.descricao = descricao;
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

   public String getMarca()
   {
      return marca;
   }

   public void setMarca(String marca)
   {
      this.marca = marca;
   }

   public String getModelo()
   {
      return modelo;
   }

   public void setModelo(String modelo)
   {
      this.modelo = modelo;
   }

   public String getAnoFabricacao()
   {
      return anoFabricacao;
   }

   public void setAnoFabricacao(String anoFabricacao)
   {
      this.anoFabricacao = anoFabricacao;
   }

   public String getAnoModelo()
   {
      return anoModelo;
   }

   public void setAnoModelo(String anoModelo)
   {
      this.anoModelo = anoModelo;
   }

   public BigDecimal getValor()
   {
      return valor;
   }

   public void setValor(BigDecimal valor)
   {
      this.valor = valor;
   }

   public String getDescricao()
   {
      return descricao;
   }

   public void setDescricao(String descricao)
   {
      this.descricao = descricao;
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

   public Set<Status> getStatus()
   {
      return status.stream().map(x -> Status.toEnum(x)).collect(Collectors.toSet());
   }

   public void addStatus(Status status)
   {
      this.status.clear();
      this.status.add(status.getCodigo());
   }

//   public Integer getCodigoUsuario()
//   {
//      return codigoUsuario;
//   }
//   
//   public void setCodigoUsuario(Integer codigoUsuario)
//   {
//      this.codigoUsuario = codigoUsuario;
//   }
   
   @Override
   public int hashCode()
   {
      return Objects.hash(id);
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Veiculo other = (Veiculo) obj;
      return Objects.equals(id, other.id);
   }

   public Usuario getUsuario()
   {
      return usuario;
   }

   public void setUsuario(Usuario usuario)
   {
      this.usuario = usuario;
   }


}
