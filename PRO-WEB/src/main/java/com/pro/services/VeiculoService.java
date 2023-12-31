package com.pro.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.entities.Carro;
import com.pro.entities.Pessoa;
import com.pro.entities.Usuario;
import com.pro.entities.dtos.CarroDTO;
import com.pro.entities.dtos.UsuarioDTO;
import com.pro.repositories.CarroRepository;
import com.pro.services.exceptions.DataIntegrityViolationException;
import com.pro.services.exceptions.ObjectnotFoundException;

@Service
public class VeiculoService
{

   @Autowired
   private CarroRepository repository;

   public Carro findById(Integer id)
   {
      Optional<Carro> obj = repository.findById(id);
      return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
   }

   public List<Carro> findAll()
   {
      return repository.findAll();
   }

   public Carro inserir(CarroDTO objDTO)
   {
      objDTO.setId(null);
      Carro newObj = new Carro(objDTO);
      return repository.save(newObj);
   }

   public Carro alterar(Integer id, @Valid CarroDTO objDTO)
   {
      objDTO.setId(id);
      Carro oldObj = findById(id);
      oldObj = new Carro(objDTO);
      return repository.save(oldObj);
   }

   public void delete(Integer id)
   {
      Carro obj = findById(id);

      // if (obj.getChamados().size() > 0)
      // {
      // throw new DataIntegrityViolationException("Cliente possui ordens de
      // serviço e não pode ser deletado!");
      // }

      repository.deleteById(id);
   }

}
