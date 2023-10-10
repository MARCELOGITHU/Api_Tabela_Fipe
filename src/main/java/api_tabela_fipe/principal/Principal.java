package api_tabela_fipe.principal;

import api_tabela_fipe.model.Veiculo;
import api_tabela_fipe.model.DadosTabela;
import api_tabela_fipe.model.Modelos;
import api_tabela_fipe.service.ConsumoApi;
import api_tabela_fipe.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    public void exibeMenu() {
        System.out.println("Digite o tipo de veiculo carros, motos ou caminhoes");
        var tipoDeVeiculo = leitura.nextLine();
        var json = consumo.obterDados(URL_BASE + tipoDeVeiculo + "/marcas");
        System.out.println(json);

        var marcas = conversor.obterLista(json, DadosTabela.class);
        marcas.stream()
                .sorted(Comparator.comparing(DadosTabela::codigo))
                .forEach(System.out::println);

        System.out.println("Digite o codigo da marca do veiculo");
        var codigoMarca = leitura.nextLine();
        json = consumo.obterDados(URL_BASE + tipoDeVeiculo + "/marcas/"+ codigoMarca + "/modelos/");
        System.out.println(json);
        var modeloLista = conversor.obterDados(json, Modelos.class);

        System.out.println("Modelos desta marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(DadosTabela::codigo))
                .forEach(System.out::println);

        System.out.println("Digite um trecho do modelo para visualizar");
        var trechoModelo = leitura.nextLine();
        List<DadosTabela> trechoBuscado = modeloLista.modelos().stream()
                .filter(e -> e.nome().toUpperCase().contains(trechoModelo.toUpperCase()))
                .toList();

        trechoBuscado.forEach(System.out::println);

        System.out.println("Escolha um modelo pelo codigo");
        var codigoModelo = leitura.nextLine();
        json = consumo.obterDados(URL_BASE + tipoDeVeiculo + "/marcas/"+ codigoMarca + "/modelos/" + codigoModelo + "/anos");
        List<DadosTabela> listaAnos = conversor.obterLista(json, DadosTabela.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < listaAnos.size(); i++) {
            json = consumo.obterDados(URL_BASE + tipoDeVeiculo + "/marcas/"+ codigoMarca + "/modelos/" + codigoModelo + "/anos/" + listaAnos.get(i).codigo());
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("Todos os veiculos filtrados com avaliações por ano: ");

        veiculos.forEach(System.out::println);
    }
}