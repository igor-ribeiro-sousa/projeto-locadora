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

import com.pro.entities.Usuario;
import com.pro.entities.dtos.UsuarioDTO;
import com.pro.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController
{

   @Autowired
   private UsuarioService service;

   @GetMapping(value = "/{id}")
   public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id)
   {
      Usuario obj = service.findById(id);
      return ResponseEntity.ok().body(new UsuarioDTO(obj));
   }

   @GetMapping
   public ResponseEntity<List<UsuarioDTO>> findAll()
   {
      List<Usuario> list = service.findAll();
      List<UsuarioDTO> listDTO = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
      return ResponseEntity.ok().body(listDTO);
   }

   @PostMapping
   public ResponseEntity<UsuarioDTO> inserir(@Valid @RequestBody UsuarioDTO objDTO)
   {
      Usuario newObj = service.inserir(objDTO);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
      return ResponseEntity.created(uri).build();
   }

   @PutMapping(value = "/{id}")
   public ResponseEntity<UsuarioDTO> alterar(@PathVariable Integer id, @Valid @RequestBody UsuarioDTO objDTO)
   {
      Usuario obj = service.alterar(id, objDTO);
      return ResponseEntity.ok().body(new UsuarioDTO(obj));
   }

   @DeleteMapping(value = "/{id}")
   public ResponseEntity<UsuarioDTO> delete(@PathVariable Integer id)
   {
      service.delete(id);
      return ResponseEntity.noContent().build();
   }

}
