import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Alugar } from 'src/app/models/alugar';
import { Usuario } from 'src/app/models/usuario';
import { Veiculo } from 'src/app/models/veiculo';
import { AlugarService } from 'src/app/services/alugar.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import { UtilService } from 'src/app/services/util.service';
import { VeiculoService } from 'src/app/services/veiculo.service';

@Component({
  selector: 'app-alugar-create',
  templateUrl: './alugar-create.component.html',
  styleUrls: ['./alugar-create.component.css']
})
export class AlugarCreateComponent implements OnInit {
  hoje: Date = new Date();

  usuario: Usuario = {
    id:             '',
    nome:           '',
    cpf:            '',
    email:          '',
    senha:          '',
    telefone:       '',
    dataNascimento: '',
    dataInclusao:   '',
    flagAtivo:      '',
    perfis:         []
  };

  veiculo: Veiculo = {
    id:             '',
    marca:          '',
    modelo:         '',
    anoFabricacao:  '',
    anoModelo:      '',
    valor:          '',
    descricao:      '',
    dataInclusao:   '',
    flagAtivo:      '',
    codigoUsuario:  '',
    usuario: undefined,
    status:         []
  };

  locacao: Alugar = {
    id:                       '',
    codigoUsuario:            '',
    usuario: { ...this.usuario },
    codigoVeiculo:            '',
    veiculo: { ...this.veiculo },
    dataReserva:              '',
    dataLocacao:              '',
    dataEntrega:              ''
  };

  usuarios: Usuario[] = []
  veiculos: Veiculo[] = []

  constructor(
    private serviceUsuario: UsuarioService,
    private serviceVeiculo: VeiculoService,
    private service: AlugarService,
    private util: UtilService,
    private toast: ToastrService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.findAllUsuarios();
    this.findAllVeiculos();
  }

  findAllUsuarios(): void {
    this.serviceUsuario.findAll().subscribe(resposta => {
      this.usuarios = resposta.filter(usuario => usuario.perfis.includes('CLIENTE'));
    })
  }

  findAllVeiculos(): void {
    this.serviceVeiculo.findAll().subscribe(resposta => {
      this.veiculos = resposta.filter(usuario => usuario.status.includes('DISPONIVEL'));
    })
  }

  inserir(): void {
    if (this.validarInserir()) {
      this.completarInserir();
      this.service.inserir(this.locacao).subscribe(() => {
        this.toast.success('Carro locado com sucesso', 'Cadastro');
        this.router.navigate(['locacoes'])
      }, ex => {
        if (ex.error.errors) {
          ex.error.errors.forEach(element => {
            this.toast.error(element.message);
          });
        } else {
          this.toast.error(ex.error.message);
        }
      })
    }
  }

  completarInserir(): void {
    this.locacao.usuario = null;
    this.locacao.veiculo = null;
    if (this.locacao.dataLocacao != this.hoje) {
      this.locacao.dataReserva = new Date(this.locacao.dataLocacao);
    }
    else {
      this.locacao.dataReserva = new Date(this.hoje);
    }
  }
  

  validarInserir(): boolean {
    const hoje = new Date();
    hoje.setHours(0, 0, 0, 0); // Zera as horas, minutos, segundos e milissegundos para obter apenas a data

    if (this.util.comparaCampo(this.locacao.codigoUsuario)) {
      this.toast.error("O campo nome é obrigatório.");
      return false
    }
    if (this.util.comparaCampo(this.locacao.codigoVeiculo)) {
      this.toast.error("O campo veiculo é obrigatório.");
      return false
    }
    if (this.util.comparaCampo(this.locacao.dataLocacao)) {
      this.toast.error("O campo Data da locação é obrigatório.");
      return false
    }
    const dataLocacao = new Date(this.locacao.dataLocacao);
    dataLocacao.setHours(0, 0, 0, 0);
    if (dataLocacao < hoje) {
      this.toast.error("O campo Data da locação não pode ser uma data passada.");
      return false
    }
    if (this.util.comparaCampo(this.locacao.dataEntrega)) {
      this.toast.error("O campo Data da entrega é obrigatório.");
      return false
    }
    const dataEntrega = new Date(this.locacao.dataEntrega);
    dataLocacao.setHours(0, 0, 0, 0);
    if (dataEntrega < dataLocacao) {
      this.toast.error("O campo Data da locação não pode ser menor que a data da locação.");
      return false
    }
    if (dataEntrega < hoje) {
      this.toast.error("O campo Data da locação não pode ser uma data passada.");
      return false
    }
    return true;
  }
}
