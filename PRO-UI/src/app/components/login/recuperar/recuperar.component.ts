import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Credenciais } from 'src/app/models/credenciais';
import { UsuarioTO } from 'src/app/models/usuarioTO';
import { AuthService } from 'src/app/services/auth.service';
import { RecuperaService } from 'src/app/services/loginRecuperar.service';

@Component({
  selector: 'app-recuperar',
  templateUrl: './recuperar.component.html',
  styleUrls: ['./recuperar.component.css']
})
export class RecuperarLoginComponent implements OnInit {

  usuario: UsuarioTO = {
    email:          '',
    dataNascimento: ''
  }

  constructor(
    private toast: ToastrService,
    private service: AuthService,
    private recoveryService: RecuperaService,
    private router: Router) { }

  ngOnInit(): void { 
    
  }

  recuperarSenha(): void {
    this.recoveryService.recuperarSenha(this.usuario.email, this.usuario.dataNascimento)
      .subscribe(
        response => {
          this.toast.success("Deu certo.");
        },
        error => {
          this.toast.error("Nao deu certo.");
        }
      );
  }


}
