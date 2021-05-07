package arquivos;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.opencsv.CSVReader;

import recursos.TratadorDados;

public class CSVTipoDaOcorrencia {
	
	// Atributos
	private static CSVTipoDaOcorrencia tabelaTipoDeOcorrencia;
	private Reader arquivoTabelaTipoOcorrencia;
	private final String PATH_TABELA_TIPO_OCORRENCIA = "D:\\eclipse-workspace\\OAAC\\dados\\ocorrencia_tipo.csv"; 
	private List<List<String>> dadosTipoDeOcorrencia;
	
	// Singleton
	private CSVTipoDaOcorrencia() throws IOException {
		arquivoTabelaTipoOcorrencia = Files.newBufferedReader(Paths.get(PATH_TABELA_TIPO_OCORRENCIA));
	}
	
	public static synchronized CSVTipoDaOcorrencia getInstance() throws IOException {
		if(tabelaTipoDeOcorrencia == null) {
			tabelaTipoDeOcorrencia = new CSVTipoDaOcorrencia();
		}
		return tabelaTipoDeOcorrencia;
	}
	// -- Singleton --
	
	// Métodos
	public CSVReader getTabelaTipoDeOcorrencia() {
		return new CSVReader(arquivoTabelaTipoOcorrencia);
	}
	
	public void fecharArquivo() throws Exception {
		arquivoTabelaTipoOcorrencia.close();
	}
	
	public List<List<String>> obterDadosTratados() {
		
		if(dadosTipoDeOcorrencia == null) {
			try {

				ArrayList<Integer> colunasRemover = new ArrayList<Integer>() {
					{
						add(3); add(2);
					}
				};

				dadosTipoDeOcorrencia = TratadorDados.tratarDados(colunasRemover, getTabelaTipoDeOcorrencia());
				
			} catch(Exception erro) {
				System.out.println(erro);
			}
		} // end if
		return dadosTipoDeOcorrencia;
	}

	public List<String> pesquisarTipoDaOcorrencia(String ID) {
		for(List<String> dado : dadosTipoDeOcorrencia) {
			if(dado.contains(ID)) {
				return dado;
			}
		}
		return null;
	}
}
