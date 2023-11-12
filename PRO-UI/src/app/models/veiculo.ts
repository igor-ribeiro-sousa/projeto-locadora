import { Usuario } from "./usuario";

export interface Veiculo {
   id?:              any;
   marca:         string;
   modelo:        string;
   anoFabricacao: any|number;
   anoModelo:     any|number;
   valor:            any;
   descricao:     string;
   dataInclusao:     any;
   flagAtivo:     string;
   codigoUsuario:any|number;
   usuario:      Usuario;
   status:           any;

}
