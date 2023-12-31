package com.pro.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import com.pro.entities.Locacao;
import com.pro.entities.dtos.LocacaoDTO;
import com.pro.services.LocacaoService;

@RestController
@RequestMapping(value = "/locacoes")
public class LocacaoController
{
   @Autowired
   private LocacaoService service;

   @GetMapping(value = "/{id}")
   public ResponseEntity<LocacaoDTO> findById(@PathVariable Integer id)
   {
      Locacao obj = service.findById(id);
      return ResponseEntity.ok().body(new LocacaoDTO(obj));
   }

   @GetMapping
   public ResponseEntity<List<LocacaoDTO>> findAll()
   {
      List<Locacao> list = service.findAll();
      List<LocacaoDTO> listDTO = list.stream().map(obj -> new LocacaoDTO(obj)).collect(Collectors.toList());
      return ResponseEntity.ok().body(listDTO);
   }

   @PostMapping
   public ResponseEntity<LocacaoDTO> inserir(@Valid @RequestBody LocacaoDTO objDTO)
   {
      Locacao newObj = service.inserir(objDTO);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
      return ResponseEntity.created(uri).build();
   }

   @PutMapping(value = "/{id}")
   public ResponseEntity<LocacaoDTO> alterar(@PathVariable Integer id, @Valid @RequestBody LocacaoDTO objDTO)
   {
      Locacao obj = service.alterar(id, objDTO);
      return ResponseEntity.ok().body(new LocacaoDTO(obj));
   }

   @DeleteMapping(value = "/{id}")
   public ResponseEntity<LocacaoDTO> delete(@PathVariable Integer id)
   {
      service.delete(id);
      return ResponseEntity.noContent().build();
   }

   @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
   public ResponseEntity<byte[]> gerarRelatorioPDF()
   {
      List<Locacao> locacoes = service.findAll();
      if (locacoes.isEmpty())
      {
         return ResponseEntity.notFound().build();
      }
      byte[] relatorioPDF = service.gerarRelatorioPDF(locacoes);
      return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=relatorio.pdf").body(relatorioPDF);
   }

}
