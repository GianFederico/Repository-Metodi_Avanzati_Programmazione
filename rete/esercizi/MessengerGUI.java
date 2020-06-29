/*
 * Copyright (C) 2020 pierpaolo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package di.uniba.map.b.lab.rete.esercizi;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 * Facendo riferimento al codice presente in di.uniba.map.b.lab.rete.esercizi
 * Realizzare il Messenger client tramite un’applicazione Java SWING con i 
 * seguenti componenti:
 * - TextArea con scroll dove vengono stampati i messaggi ricevuti
 * - Button per invio comandi utenti al server
 * - TextField dove vengono inseriti i comandi dell’utente
 * 
 * Caratteristiche facoltative da implementare:
 * - i comandi vengono inviati direttamente alla pressione del tasto INVIO
 * - il server oltre ad inviare il messaggio al client invia anche il nome del mittente
 * 
 * @author pierpaolo
 */
public class MessengerGUI extends javax.swing.JFrame {

    /**
     * Creates new form MessengerGUI
     */
    public MessengerGUI() {
        initComponents();
        init();
    }

    private void init() {
        try {
            sendButton.setEnabled(false);
            // indirizzo riservato al localhost 127.0.0.1
            InetAddress addr = InetAddress.getByName("localhost");
            System.out.println("addr = " + addr);
            socket = new Socket(addr, 6666);
            // Pone tutto in un blocco try-finally per assicurarsi che
            // il socket sia chiuso:
            System.out.println("socket = " + socket);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Flush automatico con PrintWriter:
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            Thread t = new ClientGUIThread(in);
            t.start();
            sendButton.setEnabled(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
            System.exit(1);
        }
    }

    private BufferedReader in;

    private PrintWriter out;

    private Socket socket;

    protected class ClientGUIThread extends Thread {

        private final BufferedReader in;

        public ClientGUIThread(BufferedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    textArea.setText(textArea.getText() + "\n" + in.readLine());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        textField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Messenger Ver.1.00");

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        textField.setColumns(20);
        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldKeyReleased(evt);
            }
        });
        jPanel1.add(textField);

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });
        jPanel1.add(sendButton);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        String cmd = textField.getText().trim();
        if (cmd.equals("#exit")) {
            out.println("#remove");
            out.println(cmd);
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println(ex);
            }
            this.dispose();
            System.exit(0);
        } else {
            out.println(cmd);
        }
        textField.setText("");
    }//GEN-LAST:event_sendButtonActionPerformed

    private void textFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String cmd = textField.getText().trim();
            if (cmd.equals("#exit")) {
                out.println("#remove");
                out.println(cmd);
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
                this.dispose();
                System.exit(0);
            } else {
                out.println(cmd);
            }
            textField.setText("");
        }
    }//GEN-LAST:event_textFieldKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MessengerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MessengerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MessengerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MessengerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MessengerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextArea textArea;
    private javax.swing.JTextField textField;
    // End of variables declaration//GEN-END:variables
}