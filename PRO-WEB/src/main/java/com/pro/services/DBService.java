package com.pro.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pro.entities.Carro;
import com.pro.entities.Locacao;
import com.pro.entities.Usuario;
import com.pro.entities.Veiculo;
import com.pro.entities.enums.Perfil;
import com.pro.entities.enums.Status;
import com.pro.repositories.CarroRepository;
import com.pro.repositories.LocacaoRepository;
import com.pro.repositories.PessoaRepository;
import com.pro.repositories.VeiculoRepository;

@Service
public class DBService
{

   @Autowired
   private PessoaRepository pessoaRepository;
   
   @Autowired
   private CarroRepository carroRepository;
   
   @Autowired
   private VeiculoRepository veiculoRepository;
   
   @Autowired
   private LocacaoRepository locacaoRepository;

   @Autowired
   private BCryptPasswordEncoder encoder;

   public void instanciaDB() throws ParseException
   {
      SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

      Usuario user1 = new Usuario(null, "Seu Zezinho", "11166189074", "ze@mail.com", encoder.encode("123"), "85985521245", fmt.parse("17/08/1996"), "S");
      user1.addPerfil(Perfil.ADMIN);
      
      Usuario user2 = new Usuario(null, "Dona Raimunda", "43362147003", "rai@mail.com", "85988202312", fmt.parse("22/09/1985"), "S");
      user2.addPerfil(Perfil.CLIENTE);
      
      Usuario user3 = new Usuario(null, "Francisco", "57262943006", "francisco@mail.com", "85954487445", fmt.parse("05/01/1972"), "S");
      user3.addPerfil(Perfil.CLIENTE);
      
      List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
      pessoaRepository.saveAll(usuarios);
      
      
      Veiculo carro1 = new Carro(null, "Chevrolet", "Onix", "2019", "2020", new BigDecimal("56990.00"), null, null, "S");
      carro1.addStatus(Status.DISPONIVEL);
      
      Veiculo carro2 = new Carro(null, "Volkswagen", "Gol", "2017", "2017", new BigDecimal("35990.00"), null, null, "S");
      carro2.addStatus(Status.DISPONIVEL);
      
      Veiculo carro3 = new Carro(null, "Toyota", "Corola", "2019", "2020", new BigDecimal("120990.00"), "Um Belo Carro", null, "S");
      carro3.addStatus(Status.DISPONIVEL);
      
      Veiculo carro4 = new Carro(null, "Toyota", "Etius", "2019", "2019", new BigDecimal("81990.00"), "Carro bom", null, "S");
      carro4.addStatus(Status.DISPONIVEL);
      
      List<Veiculo> carros = Arrays.asList(carro1, carro2, carro3, carro4);
      veiculoRepository.saveAll(carros);

      
//      Locacao locacao1 = new Locacao(null, usuarios.get(1).getId(), usuarios.get(1), 
//            carros.get(1).getId(), carros.get(1),fmt.parse("17/08/1996"),fmt.parse("17/08/1997"),fmt.parse("17/08/1998"));
//      
//      List<Locacao> locacoes = Arrays.asList(locacao1);
//      locacaoRepository.saveAll(locacoes);
      
   }
}
