package br.com.alura.clientelo.leitor;

import br.com.alura.clientelo.modal.PedidoCSVDTO;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class ProcessadorDeCsv implements ProcessadorArquivo{
        @Override
    public List<PedidoCSVDTO> lerArquivo(URL url) throws IOException, URISyntaxException {
        File file = Paths.get(url.toURI()).toFile();
        try(Reader reader = new FileReader(file)) {
            return new CsvToBeanBuilder<PedidoCSVDTO>(reader)
                    .withType(PedidoCSVDTO.class)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(',')
                    .build()
                    .parse();
        }
    }
}
