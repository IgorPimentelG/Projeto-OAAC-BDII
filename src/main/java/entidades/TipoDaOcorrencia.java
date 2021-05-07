package entidades;

import javax.persistence.*;

@Entity
@Table(name = "tb_tipo_da_ocorrencia")
public class TipoDaOcorrencia {

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Integer ID;
	
	@Column(name = "tipo")
	private String tipo;
	
	// Construtores
	public TipoDaOcorrencia() {}
	
	public TipoDaOcorrencia(String tipo) {
		this.tipo = tipo;
	}

	// Setters e Getters
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}
		
	@Override
	public String toString() {
		return "\n-- TIPO OCORRÊNCIA --\nTipo:" + tipo;
	}
}
