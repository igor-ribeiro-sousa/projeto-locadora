package com.pro.services;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pro.entities.Locacao;
import com.pro.entities.Usuario;
import com.pro.entities.Veiculo;
import com.pro.entities.dtos.LocacaoDTO;
import com.pro.entities.enums.Status;
import com.pro.repositories.LocacaoRepository;
import com.pro.repositories.VeiculoRepository;
import com.pro.services.exceptions.ObjectnotFoundException;

@Service
public class LocacaoService
{

   @Autowired
   private UsuarioService usuarioService;

   @Autowired
   private VeiculoService veiculoService;

   @Autowired
   private VeiculoRepository veiculoRepository;

   @Autowired
   private LocacaoRepository repository;

   public Locacao findById(Integer id)
   {
      Optional<Locacao> obj = repository.findById(id);
      return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
   }

   public List<Locacao> findAll()
   {
      return repository.findAll();
   }

   public Locacao inserir(LocacaoDTO objDTO)
   {
      objDTO.setId(null);
      atualizaStatusVeiculo(objDTO);
      Locacao newObj = new Locacao(objDTO);
      return repository.save(newObj);
   }

   private void atualizaStatusVeiculo(LocacaoDTO objDTO)
   {
      Veiculo veiculo = veiculoService.findById(objDTO.getCodigoVeiculo());

      if (!Objects.isNull(veiculo))
      {
         LocalDate dataReserva = objDTO.getDataReserva().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         LocalDate dataLocacao = objDTO.getDataLocacao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         LocalDate hoje = LocalDate.now();

         veiculo.addStatus(
               dataReserva.isEqual(hoje) ? Status.LOCADO : (dataReserva.isEqual(dataLocacao) ? Status.RESERVADO : Status.LOCADO));

         veiculoRepository.save(veiculo);
      }
   }

   public Locacao alterar(Integer id, @Valid LocacaoDTO objDTO)
   {
      objDTO.setId(id);
      Locacao oldObj = findById(id);
      oldObj = new Locacao(objDTO);
      return repository.save(oldObj);
   }

   public void delete(Integer id)
   {
      Locacao obj = findById(id);
      Veiculo veiculo = veiculoService.findById(obj.getCodigoVeiculo());
      veiculo.addStatus(Status.DISPONIVEL);
      veiculoRepository.save(veiculo);
      repository.deleteById(id);
   }

   public byte[] gerarRelatorioPDF(List<Locacao> locacao)
   {
      try
      {
         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         Document document = new Document();
         PdfWriter.getInstance(document, outputStream);

         document.open();
         for (Locacao item : locacao)
         {
            adicionarConteudo(document, item);
         }
         document.close();

         return outputStream.toByteArray();
      }
      catch (DocumentException e)
      {
         e.printStackTrace();
         return new byte[0];
      }
   }

   private void adicionarConteudo(Document document, Locacao locacao) throws DocumentException
   {
      SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

      Usuario usuario = usuarioService.findById(locacao.getCodigoUsuario());
      Veiculo veiculo = veiculoService.findById(locacao.getCodigoVeiculo());
      String cpfNumerico = usuario.getCpf().replaceAll("\\D", "");
      String cpfFormatado = cpfNumerico.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");

      String descricao = (veiculo.getDescricao() != null) ? veiculo.getDescricao() : "";
      
      Font fontNegrito = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

      document.add(new Paragraph(" "));
      document.add(new Paragraph("Relatório de Locação", fontNegrito));
      document.add(new Paragraph(" "));

      document.add(new Paragraph("ID: " + locacao.getId()));
      document.add(new Paragraph("Cliente: " + usuario.getNome().toUpperCase()));
      document.add(new Paragraph("CPF: " + cpfFormatado));
      document.add(new Paragraph("Email: " + usuario.getEmail().toUpperCase()));
      document.add(new Paragraph("Data de Nascimento: " + formatoData.format(usuario.getDataNascimento())));
      document.add(new Paragraph("Telefone: " + usuario.getTelefone()));
      document.add(new Paragraph("      Veículo: " + veiculo.getMarca().toUpperCase() + " " + veiculo.getModelo().toUpperCase()));
      document.add(new Paragraph("      Ano: " + veiculo.getAnoFabricacao()+ "/" + veiculo.getAnoModelo() ));
      document.add(new Paragraph("      Valor: " +"RS "+ veiculo.getValor()));
      document.add(new Paragraph("      Descrição: " + descricao.toUpperCase()));
      document.add(new Paragraph("      Staus: " + veiculo.getStatus()));
      document.add(new Paragraph("      Data de Reserva: " + formatoData.format(locacao.getDataReserva())));
      document.add(new Paragraph("      Data de Locação: " + formatoData.format(locacao.getDataLocacao())));
      document.add(new Paragraph("      Data de Entrega: " + formatoData.format(locacao.getDataEntrega())));
   }

}
