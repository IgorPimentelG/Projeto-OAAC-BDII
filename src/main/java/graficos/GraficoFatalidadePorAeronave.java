package graficos;

import java.awt.Color;
import java.util.*;
import javax.swing.BorderFactory;
import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;

import arquivos.CSVAeronave;
import arquivos.CSVOcorrencia;

public class GraficoFatalidadePorAeronave extends ConfiguracaoJFrame {
	
	private Map<String, Integer> dadosFatalidade;
	private int aviao = 0;
	private int helicoptero = 0;
	private int ultraleve = 0;
	
	public GraficoFatalidadePorAeronave() {
		obterDados();
		super.configuracaoTela();
		exibirGrafico();
		setVisible(true);
	}
	
	private void exibirGrafico() {
		
		DefaultPieDataset dataset = criarDataset();
		
		JFreeChart grafico = criarGrafico(dataset);
		ChartPanel painelGrafico = new ChartPanel(grafico);
		painelGrafico.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		painelGrafico.setBackground(Color.WHITE);
		add(painelGrafico);
		
	}
	
	private DefaultPieDataset criarDataset() {
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		for(Map.Entry<String, Integer> dado : dadosFatalidade.entrySet()) {
			dataset.setValue(dado.getKey(), dado.getValue());
		}
		
		return dataset;
	}
	
	private JFreeChart criarGrafico(DefaultPieDataset dataset) {
		
		JFreeChart grafico = ChartFactory.createPieChart(
				"TAXA DE FATALIDADE POR TIPO DE AERONAVE EM 2020",
				dataset,
				false,
				true,
				false
				);		
		
		return grafico;
	}
	
	private void obterDados() {
		try {
			
			CSVOcorrencia csvOcorrencia = CSVOcorrencia.getInstance();
			CSVAeronave csvAeronave = CSVAeronave.getInstance();
			
			for(List<String> dadoOcorrencia : csvOcorrencia.obterDadosTratados()) {
				
				if(dadoOcorrencia.get(4).split("/")[2].equals("2020")) {
					for(List<String> dadoAeronave : csvAeronave.obterDadosTratados()) {
						if(dadoOcorrencia.get(0).equals(dadoAeronave.get(0))) {
							if(dadoAeronave.get(2).equals("AVIÃO")) {
								aviao += Integer.parseInt(dadoAeronave.get(7));
							} else if(dadoAeronave.get(2).equals("HELICÓPTERO")) {
								helicoptero += Integer.parseInt(dadoAeronave.get(7));
							} else {
								ultraleve +=  Integer.parseInt(dadoAeronave.get(7));
							}
						}	
					}
				}
			}
			
			dadosFatalidade = new HashMap<String, Integer>() {
					{
						put("AVIÃO", aviao); put("HELICÓPTERO", helicoptero); put("ULTRALEVE", ultraleve);
					}
				};
			
		} catch(Exception erro) {
			System.out.println(erro);
		}
	}
}
