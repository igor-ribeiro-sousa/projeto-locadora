import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { VeiculoService } from 'src/app/services/veiculo.service';
import { Alugar } from 'src/app/models/alugar';
import { Usuario } from 'src/app/models/usuario';
import { Veiculo } from 'src/app/models/veiculo';
import { UsuarioService } from 'src/app/services/usuario.service';
import { AlugarService } from 'src/app/services/alugar.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-alugar-delete',
  templateUrl: './alugar-delete.component.html',
  styleUrls: ['./alugar-delete.component.css']
})
export class AlugarDeleteComponent implements OnInit {

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

  veiculoMarcaModelo: any;

  constructor(
    private serviceUsuario: UsuarioService,
    private serviceVeiculo: VeiculoService,
    private service: AlugarService,
    private util: UtilService,
    private toast: ToastrService,
    private router: Router,
    private route:   ActivatedRoute,

  ) { }

  ngOnInit(): void {
    this.locacao.id = this.route.snapshot.paramMap.get('id');
    this.findById();
    console.log(this.locacao);
   }

   findById(): void {
    this.service.findById(this.locacao.id).subscribe(resposta => {
      this.locacao = resposta;
    })
  }
  delete(): void {
    this.service.delete(this.locacao.id).subscribe(() => {
      this.toast.success('Locação deletada com sucesso', 'Delete');
      this.router.navigate(['locacoes'])
    }, ex => {
      if(ex.error.errors) {
        ex.error.errors.forEach(element => {
          this.toast.error(element.message);
        });
      } else {
        this.toast.error(ex.error.message);
      }
    })
  }

}
