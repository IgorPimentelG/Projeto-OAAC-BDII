package entidades;

import javax.persistence.*;

@Entity
@Table(name = "tb_Fator_Contribuinte")
public class FatorContribuinte {
	
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Integer ID;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "aspecto")
	private String aspecto;
	
	@Column(name = "area")
	private String area;

	// Construtores
	public FatorContribuinte() {}
	
	public FatorContribuinte(String nome, String aspecto, String area) {
		this.nome = nome;
		this.aspecto = aspecto;
		this.area = area;
	}

	// Setters e Getters
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAspecto() {
		return aspecto;
	}

	public void setAspecto(String aspecto) {
		this.aspecto = aspecto;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Override
	public String toString() {
		return "\n-- FATOR CONTRIBUINTE --\nNome: " + nome + "\nAspecto: " + aspecto + "\nÁrea: " + area;
	}
}
