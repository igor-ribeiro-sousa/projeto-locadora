import { Usuario } from "./usuario";
import { Veiculo } from "./veiculo";

export interface Alugar {
   id?:                   any;
   codigoUsuario:  any|number;
   usuario:           Usuario;
   codigoVeiculo:  any|number;
   veiculo:           Veiculo;
   dataReserva:           any;
   dataLocacao:           any;
   dataEntrega:           any;
 }
 