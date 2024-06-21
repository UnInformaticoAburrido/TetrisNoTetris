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

        GridLayout gridCentral = new GridLayout(2, 1);
        JPanel central = new JPanel(gridCentral);
        JPanel botones = new JPanel(gridCentral);
        //Insertamos los imputs
        GridLayout grid = new GridLayout(1, 2);
        JPanel panelIn = new JPanel(grid);
        JLabel text = new JLabel(
                "MODIFICAR TIEMPO PARTIDA [ " + TetrisUIB.getConfiguracion().getTiempoPartida() + " ]");
        JTextField tiempoField = new JTextField();
        panelIn.add(text);
        panelIn.add(tiempoField);
        this.add(panelIn);
        //Insertamos los central
        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tiempoField.getText().isEmpty()) {
                    try {
                        TetrisUIB.getConfiguracion().setTiempoPartida(Integer.parseInt(tiempoField.getText()));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Debes insertar un numero", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    dispose();
                }
            }
        });
        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        central.add(panelIn);
        botones.add(confirmarButton);
        botones.add(cancelarButton);
        central.add(botones);
        add(central);
        pack();

        setLocationRelativeTo(padre);
        setVisible(true);
    }

}
