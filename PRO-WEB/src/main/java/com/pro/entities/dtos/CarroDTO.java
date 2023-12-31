package com.pro.entities.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.pro.entities.Carro;
import com.pro.entities.enums.Status;

public class CarroDTO implements Serializable
{
   private static final long serialVersionUID = 1L;

   protected Integer id;

   @NotNull(message = "O campo MARCA é requerido")
   protected String marca;

   @NotNull(message = "O campo MODELO é requerido")
   protected String modelo;

   @NotNull(message = "O campo ANO FABRICAÇÃO é requerido")
   protected String anoFabricacao;

   @NotNull(message = "O campo ANO MODELO é requerido")
   protected String anoModelo;

   @NotNull(message = "O campo VALOR é requerido")
   protected BigDecimal valor;

   protected String descricao;

   protected Date dataInclusao = new Date();
   
   protected String flagAtivo;

   protected Set<Integer> status = new HashSet<>();

   public CarroDTO()
   {
      super();
   }

   public CarroDTO(Carro obj)
   {
      super();
      this.id = obj.getId();
      this.marca = obj.getMarca();
      this.modelo = obj.getModelo();
      this.anoFabricacao = obj.getAnoFabricacao();
      this.anoModelo = obj.getAnoModelo();
      this.valor = obj.getValor();
      this.descricao = obj.getDescricao();
      this.dataInclusao = obj.getDataInclusao();
      this.flagAtivo = obj.getFlagAtivo();
      this.status = obj.getStatus().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
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
      this.status.add(status.getCodigo());
   }

}
