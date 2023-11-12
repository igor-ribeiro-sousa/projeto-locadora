import { Veiculo } from 'src/app/models/veiculo';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NavComponent } from './components/nav/nav.component';
import { UsuarioListComponent } from './components/usuario/usuario-list/usuario-list.component';
import { UsuarioCreateComponent } from './components/usuario/usuario-create/usuario-create.component';
import { UsuarioDeleteComponent } from './components/usuario/usuario-delete/usuario-delete.component';
import { UsuarioUpdateComponent } from './components/usuario/usuario-update/usuario-update.component';
import { VeiculoListComponent } from './components/veiculo/veiculo-list/veiculo-list.component';
import { VeiculoCreateComponent } from './components/veiculo/veiculo-create/veiculo-create.component';
import { VeiculoUpdateComponent } from './components/veiculo/veiculo-update/veiculo-update.component';
import { VeiculoDeleteComponent } from './components/veiculo/veiculo-delete/veiculo-delete.component';
import { RecuperarLoginComponent } from './components/login/recuperar/recuperar.component';
import { AlugarListComponent } from './components/alugar/alugar-list/alugar-list.component';
import { AlugarCreateComponent } from './components/alugar/alugar-create/alugar-create.component';
import { AlugarDeleteComponent } from './components/alugar/alugar-delete/alugar-delete.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'recuperar', component: RecuperarLoginComponent }, // Adicione esta linha

  {
    path: '', component: NavComponent, canActivate: [AuthGuard], children: [
      { path: 'home', component: HomeComponent },

      { path: 'usuarios',            component:   UsuarioListComponent },
      { path: 'usuarios/create',     component: UsuarioCreateComponent },
      { path: 'usuarios/update/:id', component: UsuarioUpdateComponent },
      { path: 'usuarios/delete/:id', component: UsuarioDeleteComponent },

      { path: 'veiculos',            component:   VeiculoListComponent },
      { path: 'veiculos/create',     component: VeiculoCreateComponent },
      { path: 'veiculos/update/:id', component: VeiculoUpdateComponent },
      { path: 'veiculos/delete/:id', component: VeiculoDeleteComponent },
      
      { path: 'locacoes',            component:   AlugarListComponent },
      { path: 'locacoes/create',     component: AlugarCreateComponent },
      { path: 'locacoes/delete/:id', component: AlugarDeleteComponent },

    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
