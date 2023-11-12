import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { Veiculo } from '../models/veiculo';

@Injectable({
  providedIn: 'root'
})
export class VeiculoService {

  constructor(private http: HttpClient) { }

  findById(id: any): Observable<Veiculo> {
    return this.http.get<Veiculo>(`${API_CONFIG.baseUrl}/veiculos/${id}`);
  }

  findAll(): Observable<Veiculo[]> {
    return this.http.get<Veiculo[]>(`${API_CONFIG.baseUrl}/veiculos`);
  }

  inserir(veiculo: Veiculo): Observable<Veiculo> {
    return this.http.post<Veiculo>(`${API_CONFIG.baseUrl}/veiculos`, veiculo);
  }

  alterar(veiculo: Veiculo): Observable<Veiculo> {
    return this.http.put<Veiculo>(`${API_CONFIG.baseUrl}/veiculos/${veiculo.id}`, veiculo);
  }

  delete(id: any): Observable<Veiculo> {
    return this.http.delete<Veiculo>(`${API_CONFIG.baseUrl}/veiculos/${id}`);
  }
}
