package br.unicamp.padroescriacionais.legacy.generator;

import br.unicamp.padroescriacionais.legacy.domain.FormatoRelatorio;

public class RelatorioGeneratorFactory {

    public static IRelatorioGenerator createGenerator(FormatoRelatorio formato) {
        if (formato == null) {
            throw new IllegalArgumentException("Formato de relatorio nao pode ser nulo.");
        }

        return switch (formato) {
            case CSV -> new CsvRelatorioGenerator();
            case JSON -> new JsonRelatorioGenerator();
            case PDF -> new PdfRelatorioGenerator();
            case XML -> new XmlRelatorioGenerator();
            case HTML -> new HtmlRelatorioGenerator();
        };
    }
}