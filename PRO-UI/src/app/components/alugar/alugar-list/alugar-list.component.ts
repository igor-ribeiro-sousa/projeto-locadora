import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Alugar } from 'src/app/models/alugar';
import { saveAs } from 'file-saver';
import { Usuario } from 'src/app/models/usuario';
import { AlugarService } from 'src/app/services/alugar.service';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-alugar-list',
  templateUrl: './alugar-list.component.html',
  styleUrls: ['./alugar-list.component.css']
})
export class AlugarListComponent implements OnInit {
  listaResultado: Alugar[] = []

  displayedColumns: string[] = ['id', 'nome', 'marca', 'ano', 'valor', 'status', 'dataReserva', 'dataLocacao', 'dataEntrega', 'acoes'];
  dataSource = new MatTableDataSource<Alugar>(this.listaResultado);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private service: AlugarService
  ) { }

  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.service.findAll().subscribe(resposta => {
      this.listaResultado = resposta
      this.dataSource = new MatTableDataSource<Alugar>(resposta);
      this.dataSource.paginator = this.paginator;
    })
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


  baixarRelatorioPDF(): void {
    this.service.gerarRelatorioPDF().subscribe(
      (pdf: Blob) => {
        saveAs(pdf, 'relatorio.pdf');
      },
      error => {
        console.error('Erro ao baixar o relatório PDF', error);
      }
    );
  }


}
