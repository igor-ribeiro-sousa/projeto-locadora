package com.pro.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;

import com.pro.entities.dtos.CarroDTO;

@Entity
public class Carro extends Veiculo
{
   private static final long serialVersionUID = 1L;

   public Carro()
   {
      super();
   }

   public Carro(Integer id, String marca, String modelo, String anoFabricacao, String anoModelo, BigDecimal valor, String descricao,
         Date dataInclusao, String flagAtivo)
   {
      super(id, marca, modelo, anoFabricacao, anoModelo, valor, descricao, flagAtivo);
   }

   public Carro(CarroDTO obj)
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

}
