package entidades;

import java.util.Date;

import javax.persistence.*;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

@Entity
@Table(name = "tb_ocorrencia")
public class Ocorrencia {
	
	// Atributos
	@Id
	@Column(name = "ID")
	private Integer ID;
	
	@Column(name = "classificacao")
	private String classificacao;
	
	@Column(name = "cidade")
	private String cidade;
	
	@Column(name = "estado")
	private String estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_e_horario")
	private Date dataHorario;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "aeronave", nullable = false)
	private Aeronave aeronave;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fator_contribuinte", nullable = true)
	private FatorContribuinte fatorContribuinte;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "tipoDaOcorrencia", nullable = true)
	private TipoDaOcorrencia tipoDaOcorrencia;
	
	// Construtor
	public Ocorrencia() {}

	// Setters e Getterss
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getDataHorario() {
		return dataHorario;
	}

	public void setDataHorario(Date dataHorario) {
		this.dataHorario = dataHorario;
	}

	public Aeronave getAeronave() {
		return aeronave;
	}

	public void setAeronave(Aeronave aeronave) {
		this.aeronave = aeronave;
	}

	public FatorContribuinte getFatorContribuinte() {
		return fatorContribuinte;
	}

	public void setFatorContribuinte(FatorContribuinte fatorContribuinte) {
		this.fatorContribuinte = fatorContribuinte;
	}

	public TipoDaOcorrencia getTipoDaOcorrencia() {
		return tipoDaOcorrencia;
	}

	public void setTipoDaOcorrencia(TipoDaOcorrencia tipoDaOcorrencia) {
		this.tipoDaOcorrencia = tipoDaOcorrencia;
	}
	
	@Override
	public String toString() {
		return "\n-- OCORRÊNCIA --\nID: " + ID + "\nClassificação: " + classificacao + "\nCidade: " + cidade + "\nEstado: " + estado
				+ "\nData e Horário: " + dataHorario + aeronave.toString() + (fatorContribuinte != null ? fatorContribuinte.toString() : "\n-- FATOR CONTRIBUINTE --\nNão registrado!") + tipoDaOcorrencia.toString();
	}
}
