package arquivos;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import com.opencsv.CSVReader;

import recursos.TratadorDados;


public class CSVOcorrencia {
	
	// Atributos
	private static CSVOcorrencia tabelaOcorrencia;
	private Reader arquivoTabelaOcorrencia;
	private final String PATH_TABELA_OCORRENCIA = "D:\\eclipse-workspace\\OAAC\\dados\\ocorrencia.csv";
	private List<List<String>> dadosOcorrencia;

	// Singleton
	private CSVOcorrencia() throws IOException {
		arquivoTabelaOcorrencia = Files.newBufferedReader(Paths.get(PATH_TABELA_OCORRENCIA));
	}
	
	public static synchronized CSVOcorrencia getInstance() throws IOException {
		if(tabelaOcorrencia == null) {
			tabelaOcorrencia = new CSVOcorrencia();
		}
		return tabelaOcorrencia;
	}
	// -- Singleton --
	
	// Métodos
	public CSVReader getTabelaOcorrencia() {
		return new CSVReader(arquivoTabelaOcorrencia);
	}
	
	public void fecharArquivo() throws Exception {
		arquivoTabelaOcorrencia.close();
	}
	
	public List<List<String>> obterDadosTratados() {
		
		if(dadosOcorrencia == null) {
			try {
							
				ArrayList<Integer> colunasRemover = new ArrayList<Integer>() {
					{
						add(22); add(21); add(20); add(19); add(18); add(17); add(16);
						add(15); add(14); add(11); add(10); add(7); add(6);
						add(4); add(3); add(2); add(1);
					}
				};
				
				dadosOcorrencia = TratadorDados.tratarDados(colunasRemover, getTabelaOcorrencia());
				 
			} catch(Exception erro) {
				System.out.println(erro);
			}
		} // end if
		return dadosOcorrencia;
	}

	public List<String> pesquisarOcorrencia(String ID) {
		for(List<String> dado : dadosOcorrencia) {
			if(dado.contains(ID)) {
				return dado;
			}
		}
		return null;
	}
}
