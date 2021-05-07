package recursos;

import java.io.IOException;
import java.util.*;

import com.opencsv.CSVReader;

public abstract class TratadorDados {
	
	public static List<List<String>> tratarDados(ArrayList<Integer> colunasRemover, CSVReader tabela) throws IOException {
		
		List<List<String>> dadosTratados = new ArrayList<List<String>>();

		String[] linha;
		int numeroLinha = 0;
	
		while((linha = tabela.readNext()) != null) {
			
			if(numeroLinha != 0) {
				
				ArrayList<String> dadosProcessados = new ArrayList<String>(Arrays.asList(linha[0].split(";")));
				
				for(int i = dadosProcessados.size() - 1; i > 0; i--) {
					if(colunasRemover.contains(i)) {
						dadosProcessados.remove(i);
					}
				}
				dadosTratados.add(dadosProcessados);
			}							
			numeroLinha++;
		}
		return dadosTratados;
	}

}
