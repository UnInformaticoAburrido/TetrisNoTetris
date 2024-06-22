package practicaFinal;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConfigurarTiempoVentana extends JDialog {

    public ConfigurarTiempoVentana(JFrame padre) {
        super(padre);

        setTitle("Cambiar tiempo partida");
        setDefaultCloseOperation(ConfigVentana.DISPOSE_ON_CLOSE);
        setBackground(TetrisUIB.COLOR_FONDOS);

        GridLayout gridCentral = new GridLayout(2, 1);

        JPanel central = new JPanel(gridCentral);

        JPanel botones = new JPanel(gridCentral);
        botones.setBackground(TetrisUIB.COLOR_FONDOS);

        // Insertamos los imputs
        JPanel panelIn = new JPanel(new GridLayout(1, 2));
        panelIn.setBackground(TetrisUIB.COLOR_FONDOS);

        JLabel text = new JLabel(
                "MODIFICAR TIEMPO PARTIDA [ " + TetrisUIB.getConfiguracion().getTiempoPartida() + " segundos ]");
        text.setForeground(TetrisUIB.COLOR_TERCIARIO);
        panelIn.add(text);

        JTextField tiempoField = new JTextField();
        panelIn.add(tiempoField);

        central.add(panelIn);

        // Insertamos los botones
        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.setBackground(TetrisUIB.COLOR_SECUNDARIO);
        confirmarButton.setForeground(TetrisUIB.COLOR_TERCIARIO);
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tiempoField.getText().isEmpty()) {
                    try {
                        int tiempo = Integer.parseInt(tiempoField.getText());
                        TetrisUIB.getConfiguracion().setTiempoPartida(tiempo);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Debes insertar un numero", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    dispose();
                }
            }
        });

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setBackground(TetrisUIB.COLOR_SECUNDARIO);
        cancelarButton.setForeground(TetrisUIB.COLOR_TERCIARIO);
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        botones.add(confirmarButton);
        botones.add(cancelarButton);
        central.add(botones);

        add(central);

        pack();

        setLocationRelativeTo(padre);
        setVisible(true);
    }
}
