package com.pro.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.entities.Pessoa;
import com.pro.entities.Usuario;
import com.pro.entities.dtos.RecuperaDTO;
import com.pro.repositories.PessoaRepository;
import com.pro.services.UsuarioService;

@RestController
@RequestMapping(value = "/recuperar-senha")
public class RecoveryController
{

   @Autowired
   private UsuarioService usuarioService;

   @Autowired
   private PessoaRepository pessoaRepository;

   @PostMapping("/recuperar-senha")
   public ResponseEntity<String> recuperarSenha(@RequestBody RecuperaDTO recuperaDTO)
   {
      Optional<Pessoa> pessoaOptional = pessoaRepository.findByEmail(recuperaDTO.getEmail());

      if (pessoaOptional.isPresent() && pessoaOptional.get().getDataNascimento().equals(recuperaDTO.getDataNascimento()))
      {
         Usuario usuario = usuarioService.findById(pessoaOptional.get().getId());

         // Lógica para gerar e enviar um token de recuperação de senha (por
         // exemplo, via e-mail)

         return ResponseEntity.ok("Instruções de recuperação de senha enviadas com sucesso.");
      }
      else
      {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas para recuperação de senha.");
      }
   }

}
