package graficos;

import java.awt.Color;
import java.io.IOException;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.*;

import arquivos.CSVOcorrencia;


public class GraficoEstado extends ConfiguracaoJFrame {
	
	private int AC, AL, AP, AM, BA, CE, DF, ES, GO, MA, MT, MS, MG, PA, PB, PR, PE, PI, RJ, RN, RS,
	RO, RR, SC, SP, SE, TO = 0;
	
	private Map<String, Integer> estados;
	
	public GraficoEstado() {
		obterDados();
		super.configuracaoTela();
		exibirGrafico();
		setVisible(true);
	}
	
	private void exibirGrafico() {
		
		CategoryDataset dataset = criarDataset();
		
		JFreeChart grafico = criarGrafico(dataset);
		ChartPanel painelGrafico = new ChartPanel(grafico);
		painelGrafico.setBackground(Color.WHITE);
		painelGrafico.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		add(painelGrafico);
		
	}
	
	private CategoryDataset criarDataset() {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	
		for(Map.Entry<String, Integer> estado : estados.entrySet()) {
			dataset.setValue(estado.getValue(), "Estados", estado.getKey());
		}
		
		return dataset;
	}
	
	private JFreeChart criarGrafico(CategoryDataset dataset) {
		
		JFreeChart grafico = ChartFactory.createBarChart(
				"NÚMERO DE ACIDENTES POR ESTADO",
				"ESTADOS",
				"QUANTIDADE",
				dataset,
				PlotOrientation.VERTICAL,
				false,
				true,
				false);

		
		return grafico;
	}

	private void obterDados() {
		
		try {
			CSVOcorrencia tabelaOcorrencia = CSVOcorrencia.getInstance();
			
			List<List<String>> dadosTratados = tabelaOcorrencia.obterDadosTratados();
			
			for(List<String> dado: dadosTratados) {
				
				switch(dado.get(3)) {
					case "AC":
						AC++;
						break;
					case "AL":
						AL++;
						break;
					case "AP":
						AP++;
						break;
					case "AM":
						AM++;
						break;
					case "BA":
						BA++;
						break;
					case "CE":
						CE++;
						break;
					case "DF":
						DF++;
						break;
					case "ES":
						ES++;
						break;
					case "GO":
						GO++;
						break;
					case "MA":
						MA++;
						break;
					case "MT":
						MT++;
						break;
					case "MS":
						MS++;
						break;
					case "MG":
						MG++;
						break;
					case "PA":
						PA++;
						break;
					case "PB":
						PB++;
						break;
					case "PR":
						PR++;
						break;
					case "PE":
						PE++;
						break;
					case "PI":
						PI++;
						break;
					case "RJ":
						RJ++;
						break;
					case "RN":
						RN++;
						break;
					case "RS":
						RS++;
						break;
					case "RO":
						RO++;
						break;
					case "RR":
						RR++;
						break;
					case "SC":
						SC++;
						break;
					case "SP":
						SP++;
						break;
					case "SE":
						SE++;
						break;
					case "TO":
						TO++;
						break;
				}
				
			}
			
			estados = new HashMap<String, Integer>() {
				{
					put("AC", AC); put("AL", AL); put("AP", AP); put("AM", AM); put("BA", BA); put("CE", CE);
					put("DF", DF); put("ES", ES); put("GO", GO); put("MA", MA); put("MT", MT); put("MS", MS);
					put("MG", MG); put("PA", PA); put("PB", PB); put("PR", PR); put("PE", PE); put("PI", PI);
					put("RJ", RJ); put("RN", RN); put("RS", RS); put("RO", RO); put("RR", RR); put("SC", SC);
					put("SP", SP); put("SE", SE); put("TO", TO);
				}
			};
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
