import { Usuario } from '../models/usuario';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';

@Injectable({
  providedIn: 'root'
})
export class RecuperaService {

  constructor(private http: HttpClient) { }

  recuperarSenha(login: string, dataNascimento: string): Observable<any> {
    const body = { login, dataNascimento };
    return this.http.post<any>(`${API_CONFIG.baseUrl}/recuperar-senha`, body);
  }
}
