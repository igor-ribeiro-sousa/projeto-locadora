import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Usuario } from 'src/app/models/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.css']
})
export class UsuarioListComponent implements OnInit {

  listaResultado: Usuario[] = []

  displayedColumns: string[] = ['id', 'nome', 'cpf', 'email', 'telefone', 'dataNascimento', 'dataInclusao', 'flagAtivo', 'perfis', 'acoes'];
  dataSource = new MatTableDataSource<Usuario>(this.listaResultado);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private service: UsuarioService
  ) { }

  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.service.findAll().subscribe(resposta => {
      this.listaResultado = resposta
      this.dataSource = new MatTableDataSource<Usuario>(resposta);
      this.dataSource.paginator = this.paginator;
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  retornaPerfil(perfis: any[]): string {
    const perfilEnum: Map<number, string> = new Map([
      [0, "ROLE_ADMIN"],
      [1, "ROLE_CLIENTE"]
    ]);
    
    const perfisMapeados = perfis.map(perfil => perfilEnum.get(perfil) || perfil);
    return perfisMapeados.join(', ');
  }


}
