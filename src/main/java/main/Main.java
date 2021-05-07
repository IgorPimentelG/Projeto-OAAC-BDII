package main;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import arquivos.CSVAeronave;
import arquivos.CSVFatorContribuinte;
import arquivos.CSVOcorrencia;
import arquivos.CSVTipoDaOcorrencia;
import entidades.Aeronave;
import entidades.FatorContribuinte;
import entidades.Ocorrencia;
import entidades.TipoDaOcorrencia;
import graficos.GraficoEstado;
import graficos.GraficoFatalidadePorAeronave;
import graficos.GraficoOcorrenciasPorAno;
import persistencia.OcorrenciaDAO;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		// Menu 
		System.out.println("-------------------- CENIPA --------------------");
		System.out.println(">> OCORRÊNCIAS AERONÁUTICAS NA AVIAÇÃO CIVIL <<");
		
		boolean flag = true;
		while(flag) {
			System.out.println("\n[1] - PESQUISAR DADOS");
			System.out.println("[2] - TAXA DE OCORRÊNCIAS TOTAL POR ESTADOS");
			System.out.println("[3] - TAXA DE OCORRÊNCIAS POR ANO");
			System.out.println("[4] - TAXA DE FATALIDADE POR TIPO DE AERONAVE NO ÚLTIMO ANO");
			System.out.println("[0] - SAIR");
			System.out.print("OPÇÃO: ");
			int opcao = Integer.parseInt(input.nextLine());
			
			switch(opcao) {
				case 0:
					flag = false;
					break;
				case 1:
					System.out.print("ID da Ocorrência: ");
					String ID = input.nextLine();
					
					try {
						pesquisarOcorrencia(ID);
					} catch(Exception erro) {
						System.out.println(erro);
					}

					break;
				case 2:
					new GraficoEstado();
					break;
				case 3:
					new GraficoOcorrenciasPorAno();
					break;
				case 4:
					new GraficoFatalidadePorAeronave();
					break;
				default:
					System.out.println("OPÇÃO INVÁLIDA!");
			}
			
		}
		input.close();
		System.exit(0);	
	}
	
	private static void pesquisarOcorrencia(String ID) throws IOException, ParseException {
		
		CSVOcorrencia csvOcorrencia = CSVOcorrencia.getInstance(); 
		csvOcorrencia.obterDadosTratados();
		
		CSVAeronave csvAeronave = CSVAeronave.getInstance();
		csvAeronave.obterDadosTratados();
		
		CSVFatorContribuinte csvFatorContribuinte = CSVFatorContribuinte.getInstance();
		csvFatorContribuinte.obterDadosTratados();
		
		CSVTipoDaOcorrencia csvTipoDaOcorrencia = CSVTipoDaOcorrencia.getInstance();
		csvTipoDaOcorrencia.obterDadosTratados();
		
		List<String> dadosOcorrencia = csvOcorrencia.pesquisarOcorrencia(ID);
		List<String> dadosAeronave = csvAeronave.pesquisarAeronave(ID);
		List<String> dadosFatorContribuinte = csvFatorContribuinte.pesquisarFatorContribuinte(ID);
		List<String> dadosTipoDaOcorrencia = csvTipoDaOcorrencia.pesquisarTipoDaOcorrencia(ID);
		
		Aeronave aeronave = new Aeronave(
				dadosAeronave.get(1), 
				dadosAeronave.get(2), 
				dadosAeronave.get(3), 
				dadosAeronave.get(4), 
				Integer.parseInt(dadosAeronave.get(5)),
				dadosAeronave.get(6),
				Integer.parseInt(dadosAeronave.get(7)));
		
		TipoDaOcorrencia tipoDaOcorrencia = new TipoDaOcorrencia(dadosTipoDaOcorrencia.get(1));
		
		FatorContribuinte fatorContribuinte = null;
		if(dadosFatorContribuinte != null) {
			fatorContribuinte = new FatorContribuinte(
					dadosFatorContribuinte.get(1),
					dadosFatorContribuinte.get(2),
					dadosFatorContribuinte.get(3));
		}
		
		
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setID(Integer.parseInt(dadosOcorrencia.get(0)));
		ocorrencia.setClassificacao(dadosOcorrencia.get(1));
		ocorrencia.setCidade(dadosOcorrencia.get(2));
		ocorrencia.setEstado(dadosOcorrencia.get(3));
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		ocorrencia.setDataHorario(formato.parse(dadosOcorrencia.get(4) + " " + dadosOcorrencia.get(5)));
		ocorrencia.setAeronave(aeronave);
		ocorrencia.setTipoDaOcorrencia(tipoDaOcorrencia);
		ocorrencia.setFatorContribuinte(fatorContribuinte);
		
		System.out.println(ocorrencia.toString());
		
		OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
		if(ocorrenciaDAO.pesquisar(Integer.parseInt(ID)) == null) {
			ocorrenciaDAO.salvar(ocorrencia);
		}
		
	}
}
