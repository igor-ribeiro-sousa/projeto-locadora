import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Usuario } from 'src/app/models/usuario';
import { Veiculo } from 'src/app/models/veiculo';
import { UsuarioService } from 'src/app/services/usuario.service';
import { UtilService } from 'src/app/services/util.service';
import { VeiculoService } from 'src/app/services/veiculo.service';


@Component({
  selector: 'app-veiculo-update',
  templateUrl: './veiculo-update.component.html',
  styleUrls: ['./veiculo-update.component.css']
})
export class VeiculoUpdateComponent implements OnInit {
  hoje:Date = new Date();
  ano: number = this.hoje.getFullYear();

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
  }

  constructor(
    private service: VeiculoService,
    private util:    UtilService,
    private toast: ToastrService,
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.veiculo.id = this.route.snapshot.paramMap.get('id');
    this.findById();
  }

  findById(): void {
    this.service.findById(this.veiculo.id).subscribe(resposta => {
      resposta.status = []
      this.veiculo = resposta;
    })
  }

  alterar(): void {
    if (this.validarAlterar()) {
      this.completarAlterar();
      this.service.alterar(this.veiculo).subscribe(() => {
        this.toast.success('Veículo atualizado com sucesso', 'Alterar');
        this.router.navigate(['veiculos'])
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

  completarAlterar(): void {
    this.veiculo.marca = this.veiculo.marca.toUpperCase().trim();
    this.veiculo.modelo = this.veiculo.modelo.toUpperCase().trim();
    this.veiculo.anoFabricacao = this.veiculo.anoFabricacao.trim();
    this.veiculo.anoModelo = this.veiculo.anoModelo.trim();
    this.veiculo.status = [];
    this.veiculo.status.push(0);
    if(this.veiculo.descricao){
      this.veiculo.descricao = this.veiculo.descricao.toUpperCase().trim();
    }
  }

  formatarMoeda(event) {
    if (event.target.value !== '' || typeof event.target.value === 'number' ) {
      const filterValor = event.target.value.replace(/\D/g, '');
      event.target.value = new Intl.NumberFormat('pt-BR', {
        style: 'currency',
        currency: 'BRL'
      }).format(parseFloat(filterValor) / 100);

      this.veiculo.valor = +filterValor / 100;

    } else {
      event.target.value = '';
      this.veiculo.valor = null;

    }
  }

  validarAlterar(): boolean {
    if(this.util.comparaCampo(this.veiculo.marca)){
      this.toast.error("O campo Marca é obrigatório.");
      return false
    }
    if(this.util.comparaCampo(this.veiculo.modelo)){
      this.toast.error("O campo Modelo é obrigatório.");
      return false
    }
    if(this.util.comparaCampo(this.veiculo.anoFabricacao)){
      this.toast.error("O campo Ano de fabricação é obrigatório.");
      return false
    }
    if(this.veiculo.anoFabricacao > this.ano){
      this.toast.error("O campo Ano de fabricação não pode ser futura.");
      return false
    }
    if(this.util.comparaCampo(this.veiculo.anoModelo)){
      this.toast.error("O campo Ano do modelo é obrigatório.");
      return false
    }
    if(this.veiculo.anoModelo < this.veiculo.anoFabricacao){
      this.toast.error("O campo Ano do modelo não pode ser menor que o de fabricação.");
      return false
    }
    if (this.veiculo.anoModelo < this.veiculo.anoFabricacao
      || this.veiculo.anoModelo > Number(this.veiculo.anoFabricacao) + 1) {
      this.toast.error("O campo Ano do modelo inválido.");
      return false
    }
    if(this.util.comparaCampo(this.veiculo.valor)){
      this.toast.error("O campo Valor é obrigatório.");
      return false
    }
    return true;
  }
}
