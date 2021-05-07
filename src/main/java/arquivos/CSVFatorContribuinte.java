package arquivos;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.opencsv.CSVReader;

import recursos.TratadorDados;

public class CSVFatorContribuinte {
	
	// Atributos
	private static CSVFatorContribuinte tabelaFatorContribuinte;
	private Reader arquivoTabelaFatorContibuinte;
	private final String PATH_TABELA_FATOR_CONTRIBUINTE = "D:\\eclipse-workspace\\OAAC\\dados\\fator_contribuinte.csv"; 
	private List<List<String>> dadosFatorContribuinte;
	
	// Singleton
	private CSVFatorContribuinte() throws IOException {
		arquivoTabelaFatorContibuinte = Files.newBufferedReader(Paths.get(PATH_TABELA_FATOR_CONTRIBUINTE));
	}
	
	public static synchronized CSVFatorContribuinte getInstance() throws IOException {
		if(tabelaFatorContribuinte == null) {
			tabelaFatorContribuinte = new CSVFatorContribuinte();
		}
		return tabelaFatorContribuinte;
	}
	// -- Singleton --
	
	// Métodos
	public CSVReader getTabelaFatorContribuinte() throws Exception {
		return new CSVReader(arquivoTabelaFatorContibuinte);
	}
	
	public List<List<String>> obterDadosTratados() {
		
		if(dadosFatorContribuinte == null) {
			
			try {
		
				ArrayList<Integer> colunasRemover = new ArrayList<Integer>() {
					{
						add(4);
					}
				};
				
				dadosFatorContribuinte = TratadorDados.tratarDados(colunasRemover, getTabelaFatorContribuinte());
				
			} catch(Exception erro) {
				System.out.println(erro);
			}
		} // end if
		return dadosFatorContribuinte;
	}
	
	public void fecharArquivo() throws Exception {
		arquivoTabelaFatorContibuinte.close();
	}
	
	public List<String> pesquisarFatorContribuinte(String ID) {
		for(List<String> dado : dadosFatorContribuinte) {
			if(dado.contains(ID)) {
				return dado;
			}
		}
		return null;
	}

}
