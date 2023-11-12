package com.pro.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pro.entities.Carro;
import com.pro.entities.dtos.CarroDTO;
import com.pro.services.VeiculoService;

@RestController
@RequestMapping(value = "/veiculos")
public class VeiculoController
{

   @Autowired
   private VeiculoService service;

   @GetMapping(value = "/{id}")
   public ResponseEntity<CarroDTO> findById(@PathVariable Integer id)
   {
      Carro obj = service.findById(id);
      return ResponseEntity.ok().body(new CarroDTO(obj));
   }

   @GetMapping
   public ResponseEntity<List<CarroDTO>> findAll()
   {
      List<Carro> list = service.findAll();
      List<CarroDTO> listDTO = list.stream().map(obj -> new CarroDTO(obj)).collect(Collectors.toList());
      return ResponseEntity.ok().body(listDTO);
   }

   @PostMapping
   public ResponseEntity<CarroDTO> inserir(@Valid @RequestBody CarroDTO objDTO)
   {
      Carro newObj = service.inserir(objDTO);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
      return ResponseEntity.created(uri).build();
   }

   @PutMapping(value = "/{id}")
   public ResponseEntity<CarroDTO> alterar(@PathVariable Integer id, @Valid @RequestBody CarroDTO objDTO)
   {
      Carro obj = service.alterar(id, objDTO);
      return ResponseEntity.ok().body(new CarroDTO(obj));
   }

   @DeleteMapping(value = "/{id}")
   public ResponseEntity<CarroDTO> delete(@PathVariable Integer id)
   {
      service.delete(id);
      return ResponseEntity.noContent().build();
   }

}
