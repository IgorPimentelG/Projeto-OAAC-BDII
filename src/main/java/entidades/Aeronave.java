package entidades;

import javax.persistence.*;

@Entity
@Table(name = "tb_aeronave")
public class Aeronave {

	// Atributos
	@Id
	@Column(name = "matricula")
	private String matricula;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "fabricante")
	private String fabricante;
	
	@Column(name = "modelo")
	private String modelo;
	
	@Column(name = "quantidade_assentos")
	private Integer quantidadeAssentos;
	
	@Column(name = "operacaos")
	private String operacao;
	
	@Column(name = "fatalidade_total")
	private Integer fatalidadeTotal;
	
	// Construtor
	public Aeronave() {}
	
	public Aeronave(String matricula, String tipo, String fabricante, String modelo, Integer quantidaedAssentos, String operacao, Integer fatalidadeTotal) {
		this.matricula = matricula;
		this.tipo = tipo;
		this.fabricante = fabricante;
		this.modelo = modelo;
		this.quantidadeAssentos = quantidaedAssentos;
		this.operacao = operacao;
		this.fatalidadeTotal = fatalidadeTotal;
	}

	// Setter e Getters
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getQuantidadeAssentos() {
		return quantidadeAssentos;
	}

	public void setQuantidadeAssentos(Integer quantidadeAssentos) {
		this.quantidadeAssentos = quantidadeAssentos;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	@Override
	public String toString() {
		return "\n-- AERONAVE --\nMatricula: " + matricula + "\nTipo: " + tipo + "\nFabricante: " + fabricante + "\nModelo: " + modelo + 
			"\nQuantidade de Assentos: " + quantidadeAssentos + "\nTipo Operação: " + operacao;
	}
	
	
}
