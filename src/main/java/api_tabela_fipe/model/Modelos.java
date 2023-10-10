package api_tabela_fipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)//ultilizando para ignorar o restante das propriedades, neste caso a propriedade "ano" e trabalhando apenas com a propriedade abaixo no caso modelos
public record Modelos(List<DadosTabela> modelos) {
}
