package graficos;

import java.awt.Color;
import java.util.*;
import javax.swing.BorderFactory;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.AreaRendererEndType;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtils;
import arquivos.CSVOcorrencia;

public class GraficoOcorrenciasPorAno extends ConfiguracaoJFrame {
	
	private int ano2010, ano2011, ano2012, ano2013, ano2014, ano2015, ano2016, ano2017, ano2018,
	ano2019, ano2020 = 0;
	
	public GraficoOcorrenciasPorAno() {
		obterDados();
		super.configuracaoTela();
		exibirGrafico();
		setVisible(true);
	}
	
	private void exibirGrafico() {
		
		CategoryDataset dataset = criarDataset();	
		JFreeChart grafico = criarGrafico(dataset);
		ChartPanel painelDoGrafico = new ChartPanel(grafico);
		painelDoGrafico.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		painelDoGrafico.setBackground(Color.WHITE);
		add(painelDoGrafico);
		
	}
	
	private CategoryDataset criarDataset() {
		
		double[][] valores = new double[][] {
			{ano2010, ano2011, ano2012, ano2013, ano2014, ano2015, ano2016, ano2017,
			 ano2018, ano2019, ano2020}
		};
	
		CategoryDataset dataset = DatasetUtils.createCategoryDataset(
				new String[]{"OCORRÊNCIAS"}, 
				new String[] {"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"}, 
				valores);
		
		return dataset;
	}
	
	private JFreeChart criarGrafico(CategoryDataset dataset) {
		
		JFreeChart grafico = ChartFactory.createAreaChart(
				"VARIAÇÃO DAS OCORRÊNCIAS POR ANO",
				"ANO",
				"OCORRÊNCIAS",
				dataset,
				PlotOrientation.VERTICAL,
				false,
				true,
				true);
		
		CategoryPlot areaDoGrafico = (CategoryPlot) grafico.getPlot();
		areaDoGrafico.setForegroundAlpha(0.6f);
		
		AreaRenderer areaRenderer = (AreaRenderer) areaDoGrafico.getRenderer();
		areaRenderer.setEndType(AreaRendererEndType.LEVEL);
		
		return grafico;
	}
	
	private void obterDados() {
		try {
			
			CSVOcorrencia tabelaOcorrencia = CSVOcorrencia.getInstance();
			
			List<List<String>> dadosTratados = tabelaOcorrencia.obterDadosTratados();
			
			for(List<String> dado: dadosTratados) {
				
				int ano = Integer.parseInt(dado.get(4).split("/")[2]);
				
				switch(ano) {
					case 2010:
						ano2010++;
						break;
					case 2011:
						ano2011++;
						break;
					case 2012:
						ano2012++;
						break;
					case 2013:
						ano2013++;
						break;
					case 2014:
						ano2014++;
						break;
					case 2015:
						ano2015++;
						break;
					case 2016:
						ano2016++;
						break;
					case 2017:
						ano2017++;
						break;
					case 2018:
						ano2018++;
						break;
					case 2019:
						ano2019++;
						break;
					case 2020:
						ano2020++;
						break;
				}
				
			}
		} catch(Exception erro) {
			System.out.println(erro);
		}
	}
}
