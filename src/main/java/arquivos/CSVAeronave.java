package arquivos;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.opencsv.CSVReader;

import recursos.TratadorDados;

public class CSVAeronave {
	
	// Atributos
	private static CSVAeronave tabelaAeronave;
	private Reader arquivoTabelaAeronave;
	private final String PATH_TABELA_AERONAVE = "D:\\eclipse-workspace\\OAAC\\dados\\aeronave.csv";
	private List<List<String>> dadosAeronave;
	
	// Singleton
	private CSVAeronave() throws IOException {
		arquivoTabelaAeronave = Files.newBufferedReader(Paths.get(PATH_TABELA_AERONAVE));
	};
	
	public static synchronized CSVAeronave getInstance() throws IOException {
		
		if(tabelaAeronave == null) {
			tabelaAeronave = new CSVAeronave();
		}
		
		return tabelaAeronave;	
	}
	// -- Singleton --
	
	public CSVReader getTabelaAeronave()  {
		return new CSVReader(arquivoTabelaAeronave);
	}
	
	public List<List<String>> obterDadosTratados() {
		
		if(dadosAeronave == null) {
			
			try {
				ArrayList<Integer> colunasRemover = new ArrayList<Integer>() {
					{
						add(21); add(19); add(18); add(17); add(16); add(15); add(14); add(13); 
						add(12); add(10); add(9); add(8); add(7); add(6); add(2); 
					}
				};
				
				dadosAeronave = TratadorDados.tratarDados(colunasRemover, getTabelaAeronave());
				
			} catch(Exception erro) {
				System.out.println(erro);
			}
		} // end if
		return dadosAeronave;
	}
	
	public void fecharArquivo() throws Exception {
		arquivoTabelaAeronave.close();
	}
	
	public List<String> pesquisarAeronave(String ID) {
		for(List<String> dado : dadosAeronave) {
			if(dado.contains(ID)) {
				return dado;
			}
		}
		return null;
	}
}
