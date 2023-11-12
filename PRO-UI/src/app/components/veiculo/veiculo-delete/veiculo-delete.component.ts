import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { VeiculoService } from 'src/app/services/veiculo.service';
import { Veiculo } from './../../../models/veiculo';

@Component({
  selector: 'app-veiculo-delete',
  templateUrl: './veiculo-delete.component.html',
  styleUrls: ['./veiculo-delete.component.css']
})
export class VeiculoDeleteComponent implements OnInit {

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
    private toast:    ToastrService,
    private router:          Router,
    private route:   ActivatedRoute,
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

  delete(): void {
    this.service.delete(this.veiculo.id).subscribe(() => {
      this.toast.success('Veículo deletado com sucesso', 'Delete');
      this.router.navigate(['veiculos'])
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
