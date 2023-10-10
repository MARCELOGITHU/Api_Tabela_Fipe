package api_tabela_fipe.teste;

import api_tabela_fipe.model.DadosTabela;
import api_tabela_fipe.service.ConsumoApi;
import api_tabela_fipe.service.ConverteDados;

public class teste {
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    public void exibeMenu() {
//        var json = consumo.obterDados("https://parallelum.com.br/fipe/api/v1/carros/marcas");
//        System.out.println(json);

//        var marcas = conversor.obterLista(json, DadosTabela.class);
//        marcas.stream()
//                .sorted(Comparator.comparing(DadosTabela::codigo))
//                .forEach(System.out::println);

//        var json = consumo.obterDados("https://parallelum.com.br/fipe/api/v1/carros/marcas/21/modelos/560/anos");
//        var listaModelos = conversor.obterLista(json, DadosTabela.class);


//        System.out.println("Todos os veiculos filtrados com avaliações por ano: ");
//
//        listaModelos.stream()
//                .map(m -> m.codigo() + " "+  m.nome())
//                .forEach(System.out::println);

        var json1 = consumo.obterDados("https://parallelum.com.br/fipe/api/v1/carros/marcas");
        var listaCarros = conversor.obterLista(json1, DadosTabela.class);
        System.out.println(listaCarros);
        //NÃO CONSIGO RECEBER OS DADOS JSON PARA DADOVEICULO
    }

    public static void main(String[] args) {
        teste teste = new teste();
        teste.exibeMenu();
    }
}
