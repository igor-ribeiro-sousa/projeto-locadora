import { Usuario } from '../models/usuario';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { Alugar } from '../models/alugar';

@Injectable({
  providedIn: 'root'
})
export class AlugarService {

  constructor(private http: HttpClient) { }

  findById(id: any): Observable<Alugar> {
    return this.http.get<Alugar>(`${API_CONFIG.baseUrl}/locacoes/${id}`);
  }

  findAll(): Observable<Alugar[]> {
    return this.http.get<Alugar[]>(`${API_CONFIG.baseUrl}/locacoes`);
  }

  inserir(locacao: Alugar): Observable<Alugar> {
    return this.http.post<Alugar>(`${API_CONFIG.baseUrl}/locacoes`, locacao);
  }

  alterar(locacao: Alugar): Observable<Alugar> {
    return this.http.put<Alugar>(`${API_CONFIG.baseUrl}/locacoes/${locacao.id}`, locacao);
  }

  delete(id: any): Observable<Alugar> {
    return this.http.delete<Alugar>(`${API_CONFIG.baseUrl}/locacoes/${id}`);
  }
  
  gerarRelatorioPDF(): Observable<Blob> {
    return this.http.get(`${API_CONFIG.baseUrl}/locacoes/pdf`, { responseType: 'blob' });
  }
  
}
