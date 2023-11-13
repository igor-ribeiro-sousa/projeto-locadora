package com.pro.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pro.entities.Pessoa;
import com.pro.entities.Usuario;
import com.pro.entities.dtos.UsuarioDTO;
import com.pro.repositories.ClienteRepository;
import com.pro.repositories.PessoaRepository;
import com.pro.services.exceptions.DataIntegrityViolationException;
import com.pro.services.exceptions.ObjectnotFoundException;

@Service
public class UsuarioService
{

   @Autowired
   private ClienteRepository repository;

   @Autowired
   private PessoaRepository pessoaRepository;

   @Autowired
   private BCryptPasswordEncoder encoder;

   public Usuario findById(Integer id)
   {
      Optional<Usuario> obj = repository.findById(id);
      return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
   }

   public List<Usuario> findAll()
   {
      return repository.findAll();
   }

   public Usuario inserir(UsuarioDTO objDTO)
   {
      objDTO.setId(null);
      validaPorCpfEEmail(objDTO);
      Usuario newObj = new Usuario(objDTO);
      return repository.save(newObj);
   }

   public Usuario alterar(Integer id, @Valid UsuarioDTO objDTO)
   {
      objDTO.setId(id);
      Usuario oldObj = findById(id);
      validaPorCpfEEmail(objDTO);
      oldObj = new Usuario(objDTO);
      return repository.save(oldObj);
   }

   public void delete(Integer id)
   {
      Usuario obj = findById(id);
      repository.deleteById(id);
   }

   private void validaPorCpfEEmail(UsuarioDTO objDTO)
   {
      Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
      if (obj.isPresent() && obj.get().getId() != objDTO.getId())
      {
         throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
      }

      obj = pessoaRepository.findByEmail(objDTO.getEmail());
      if (obj.isPresent() && obj.get().getId() != objDTO.getId())
      {
         throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
      }
   }

}
