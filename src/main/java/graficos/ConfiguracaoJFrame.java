package graficos;

import javax.swing.JFrame;

public abstract class ConfiguracaoJFrame extends JFrame {
	
	protected void configuracaoTela() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("GRAFÍCO DE ACIDENTES POR ESTADOS");
		setLocationRelativeTo(null);
	}

}
