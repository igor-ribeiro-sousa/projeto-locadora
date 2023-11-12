import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Veiculo } from 'src/app/models/veiculo';
import { VeiculoService } from 'src/app/services/veiculo.service';

@Component({
  selector: 'app-veiculo-list',
  templateUrl: './veiculo-list.component.html',
  styleUrls: ['./veiculo-list.component.css']
})
export class VeiculoListComponent implements OnInit {

  listaResultado: Veiculo[] = []

  displayedColumns: string[] = ['id','marca', 'modelo','anoFabricacao','anoModelo' , 'valor', 'descricao','dataInclusao', 'flagAtivo', 'status', 'acoes'];
  dataSource = new MatTableDataSource<Veiculo>(this.listaResultado);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private service: VeiculoService,
  ) { }
 
  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.service.findAll().subscribe(resposta => {
      this.listaResultado = resposta.filter(item => item.status.includes('DISPONIVEL'));
      this.dataSource = new MatTableDataSource<Veiculo>(this.listaResultado);
      this.dataSource.paginator = this.paginator;
    });
  }

  

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  retornaStatus(status: any[]): string {
    const statusEnum: Map<number, string> = new Map([
      [0, "DISPONIVEL"],
      [1, "RESERVADO"],
      [2, "LOCADO"]
    ]);
    
    const statusMapeados = status.map(status => statusEnum.get(status) || status);
    return statusMapeados.join(', ');
  }

}
