import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Usuario } from 'src/app/models/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-usuario-create',
  templateUrl: './usuario-create.component.html',
  styleUrls: ['./usuario-create.component.css']
})
export class UsuarioCreateComponent implements OnInit {
  hoje:Date = new Date();

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
  }

  constructor(
    private service: UsuarioService,
    private util:       UtilService,
    private toast:    ToastrService,
    private router:          Router,
    ) { }

  ngOnInit(): void { 
  }

  inserir(): void {
    if(this.validarInserir()){
      this.completarInserir();
      this.service.inserir(this.usuario).subscribe(() => {
        this.toast.success('Usuario cadastrado com sucesso', 'Cadastro');
        this.router.navigate(['usuarios'])
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

  completarInserir(): void {
    this.usuario.cpf = this.usuario.cpf.toUpperCase().trim();
    this.usuario.email = this.usuario.email.toUpperCase().trim();
    this.usuario.nome =  this.usuario.nome.toUpperCase().trim();
    this.usuario.perfis = [];
    this.usuario.perfis.push(1);
    this.usuario.cpf = this.usuario.cpf.toUpperCase().trim();
    this.usuario.flagAtivo = "S"
    this.usuario.dataInclusao = new Date();
  }
  
  validarInserir(): boolean {
    if(this.util.comparaCampo(this.usuario.nome)){
      this.toast.error("O campo nome é obrigatório.");
      return false
    }
    if(this.util.comparaCampo(this.usuario.cpf)){
      this.toast.error("O campo CPF é obrigatório.");
      return false
    }
    if(!this.util.isValidCPF(this.usuario.cpf)){
      this.toast.error("O campo CPF não é válido.");
      return false
    }
    if(this.util.comparaCampo(this.usuario.email)){
      this.toast.error("O campo telefone é obrigatório.");
      return false
    }
    if(!this.util.isValidEmail(this.usuario.email)){
      this.toast.error("O campo E-mail não é válido.");
      return false
    }
    if(this.util.comparaCampo(this.usuario.telefone)){
      this.toast.error("O campo telefone é obrigatório.");
      return false
    }
    if(this.usuario.telefone.length != 11){
      this.toast.error("O campo telefone não é válido.");
      return false
    }
    if(this.util.comparaCampo(this.usuario.dataNascimento)){
      this.toast.error("O campo Data de nascimento é obrigatório.");
      return false
    }
    if(this.usuario.dataNascimento > this.hoje){
      this.toast.error("O campo Data de Nascimento não pode ser uma data futura.");
      return false
    }
    return true;
  }
}
