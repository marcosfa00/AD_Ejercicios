/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package poker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import javax.swing.Timer;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 *
 * @author marcosfa
 */
public class Poker_interface extends javax.swing.JFrame {

    private boolean pausado = false;
private static final String NIVEL_UP_SOUND_PATH = "./src/audio/Nuevo_cambio_de_nivel.wav";

private int minutos;
    private int segundos;
    private Timer timer;
    private boolean primerNivel;
    private int nivel;
 public Poker_interface() {
        initComponents();

         try {
            final Robot robot = new Robot();
            final int mouseMovementInterval = 10000; // Mover el ratón cada 10 segundos (puedes ajustar este valor)
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            // Simula un pequeño movimiento del ratón
                            robot.mouseMove(0, 1);
                            robot.mouseMove(0, 0);
                            Thread.sleep(mouseMovementInterval);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    
 
        minutos = 15;
        segundos = 0;
        nivel = 1;

        timer = new Timer(1000, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!pausado) { // Si no está en pausa
            if (minutos == 0 && segundos == 0) {
                // Cuando los minutos y segundos llegan a 0, incrementa el nivel y restablece el tiempo.
                nivel++;
                if (nivel == 1) {
                    // El primer nivel es de 15 minutos.
                    minutos = 15;
                } else {
                    // Los niveles posteriores son de 10 minutos.
                    minutos = 12;
                }
                segundos = 0;
                playSound(NIVEL_UP_SOUND_PATH);
            } else if (segundos == 0) {
                // Si los segundos llegan a 0, disminuye los minutos en uno y restablece los segundos a 59.
                minutos--;
                segundos = 59;
            } else {
                segundos--;
            }
            // Actualiza la cuenta atrás y el nivel
            CUENTA_ATRAS.setText(String.format("%02d:%02d", minutos, segundos)); // Formato MM:SS
            CONTADOR_NIVELES.setText(String.valueOf(nivel));
            actualizarCiegas(nivel);
        }
    }
});

        timer.start(); // Inicia el Timer
        
        
        // Agregar ActionListener para el botón "Descanso"
    BTN_DESCANSO.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            pausado = true; // Establecer el estado en pausa
            timer.stop(); // Detener el temporizador
        }
    });

    // Agregar ActionListener para el botón "Continuar"
    CONTINUAR.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pausado) {
                pausado = false; // Establecer el estado en no pausa
                timer.start(); // Reanudar el temporizador
            }
        }
    });
    }
 
 private void playSound(String soundPath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
 
 
private void actualizarCiegas(int nivel) {
    int ciegaPequena;
    int ciegaGrande;
    int ante;

    if (nivel <= 1) {
        ciegaPequena = 100;
        ciegaGrande = 100;
        ante = 100;
    } else if (nivel == 2) {
        ciegaPequena = 100;
        ciegaGrande = 200;
        ante = 200;
    } else if (nivel <= 10) {
        ciegaPequena = 100 + (nivel - 2) * 100;
        ciegaGrande = 200 + (nivel - 2) * 100;
        ante = ciegaGrande;
    } else if (nivel <= 25) {
        ciegaPequena = 1000 + (nivel - 10) * 200;
        ciegaGrande = 2000 + (nivel - 10) * 400;
        ante = ciegaGrande;
    } else {
        ciegaPequena = 6000 + (nivel - 25) * 1000;
        ciegaGrande = 12000 + (nivel - 25) * 2000;
        ante = ciegaGrande;
    }

    Label_CIEGAS.setText(String.format("%d/%d %d", ciegaPequena, ciegaGrande, ante));

    int proximoNivel = nivel + 1;
    int proximaCiegaPequena;
    int proximaCiegaGrande;
    int proximoAnte;

    if (proximoNivel <= 1) {
        proximaCiegaPequena = 100;
        proximaCiegaGrande = 100;
        proximoAnte = 100;
    } else if (proximoNivel == 2) {
        proximaCiegaPequena = 100;
        proximaCiegaGrande = 200;
        proximoAnte = 200;
    } else if (proximoNivel <= 10) {
        proximaCiegaPequena = 100 + (proximoNivel - 2) * 100;
        proximaCiegaGrande = 200 + (proximoNivel - 2) * 100;
        proximoAnte = proximaCiegaGrande;
    } else if (proximoNivel <= 25) {
        proximaCiegaPequena = 1000 + (proximoNivel - 10) * 200;
        proximaCiegaGrande = 2000 + (proximoNivel - 10) * 400;
        proximoAnte = proximaCiegaGrande;
    } else {
        proximaCiegaPequena = 6000 + (proximoNivel - 25) * 1000;
        proximaCiegaGrande = 12000 + (proximoNivel - 25) * 2000;
        proximoAnte = proximaCiegaGrande;
    }

    PROXIMAS_CIEGAS.setText(String.format("%d/%d %d", proximaCiegaPequena, proximaCiegaGrande, proximoAnte));
}





    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background_panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Label_contador1 = new javax.swing.JLabel();
        Label_contador2 = new javax.swing.JLabel();
        Label_contador3 = new javax.swing.JLabel();
        Label_CIEGAS = new javax.swing.JLabel();
        Label_contador5 = new javax.swing.JLabel();
        CUENTA_ATRAS = new javax.swing.JLabel();
        CONTADOR_NIVELES = new javax.swing.JLabel();
        PROXIMAS_CIEGAS = new javax.swing.JLabel();
        BTN_DESCANSO = new javax.swing.JButton();
        Label_contador7 = new javax.swing.JLabel();
        Label_contador8 = new javax.swing.JLabel();
        Label_contador9 = new javax.swing.JLabel();
        CONTINUAR = new javax.swing.JButton();
        Label_contador6 = new javax.swing.JLabel();
        Label_contador10 = new javax.swing.JLabel();
        Label_contador11 = new javax.swing.JLabel();
        Label_contador12 = new javax.swing.JLabel();
        Label_contador13 = new javax.swing.JLabel();
        Label_contador14 = new javax.swing.JLabel();
        BTN_NEXT_LEVEL = new javax.swing.JButton();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Label_contador1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        Label_contador1.setForeground(new java.awt.Color(255, 255, 255));
        Label_contador1.setText("SIGUIENTE NIVEL");
        jPanel1.add(Label_contador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, -1, -1));

        Label_contador2.setFont(new java.awt.Font("Helvetica Neue", 1, 70)); // NOI18N
        Label_contador2.setForeground(new java.awt.Color(255, 255, 255));
        Label_contador2.setText("CIEGAS");
        jPanel1.add(Label_contador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 280, 70));

        Label_contador3.setFont(new java.awt.Font("Helvetica Neue", 1, 48)); // NOI18N
        Label_contador3.setForeground(new java.awt.Color(255, 255, 255));
        Label_contador3.setText("ANTE");
        jPanel1.add(Label_contador3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 310, -1, -1));

        Label_CIEGAS.setFont(new java.awt.Font("Helvetica Neue", 1, 100)); // NOI18N
        Label_CIEGAS.setForeground(new java.awt.Color(255, 255, 255));
        Label_CIEGAS.setText("100/200 200");
        jPanel1.add(Label_CIEGAS, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, -1, -1));

        Label_contador5.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        Label_contador5.setForeground(new java.awt.Color(255, 0, 51));
        Label_contador5.setText("ULTIMA RECOMPRA");
        jPanel1.add(Label_contador5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, -1, -1));

        CUENTA_ATRAS.setFont(new java.awt.Font("Helvetica Neue", 1, 65)); // NOI18N
        CUENTA_ATRAS.setForeground(new java.awt.Color(255, 255, 255));
        CUENTA_ATRAS.setText("15:00");
        jPanel1.add(CUENTA_ATRAS, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 140, -1, -1));

        CONTADOR_NIVELES.setFont(new java.awt.Font("Helvetica Neue", 1, 48)); // NOI18N
        CONTADOR_NIVELES.setForeground(new java.awt.Color(255, 255, 255));
        CONTADOR_NIVELES.setText("0");
        jPanel1.add(CONTADOR_NIVELES, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        PROXIMAS_CIEGAS.setFont(new java.awt.Font("Helvetica Neue", 1, 48)); // NOI18N
        PROXIMAS_CIEGAS.setForeground(new java.awt.Color(255, 255, 255));
        PROXIMAS_CIEGAS.setText("100/300 300");
        jPanel1.add(PROXIMAS_CIEGAS, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, -1, -1));

        BTN_DESCANSO.setText("DESCANSO");
        BTN_DESCANSO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DESCANSOActionPerformed(evt);
            }
        });
        jPanel1.add(BTN_DESCANSO, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 550, -1, -1));

        Label_contador7.setFont(new java.awt.Font("Helvetica Neue", 1, 48)); // NOI18N
        Label_contador7.setForeground(new java.awt.Color(255, 255, 255));
        Label_contador7.setText("SMALL");
        jPanel1.add(Label_contador7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, -1, -1));

        Label_contador8.setFont(new java.awt.Font("Helvetica Neue", 1, 48)); // NOI18N
        Label_contador8.setForeground(new java.awt.Color(255, 255, 255));
        Label_contador8.setText("BIG");
        jPanel1.add(Label_contador8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 310, -1, -1));

        Label_contador9.setFont(new java.awt.Font("Helvetica Neue", 1, 48)); // NOI18N
        Label_contador9.setForeground(new java.awt.Color(255, 0, 51));
        Label_contador9.setText("NIVEL 11");
        jPanel1.add(Label_contador9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 580, -1, -1));

        CONTINUAR.setText("REANUDAR");
        CONTINUAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CONTINUARActionPerformed(evt);
            }
        });
        jPanel1.add(CONTINUAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 580, 100, -1));

        Label_contador6.setFont(new java.awt.Font("Helvetica Neue", 1, 48)); // NOI18N
        Label_contador6.setForeground(new java.awt.Color(255, 255, 255));
        Label_contador6.setText("TIEMPO");
        jPanel1.add(Label_contador6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, -1, -1));

        Label_contador10.setFont(new java.awt.Font("Helvetica Neue", 1, 48)); // NOI18N
        Label_contador10.setForeground(new java.awt.Color(255, 255, 255));
        Label_contador10.setText("RESTANTE");
        jPanel1.add(Label_contador10, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 70, -1, -1));

        Label_contador11.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        Label_contador11.setForeground(new java.awt.Color(255, 0, 51));
        Label_contador11.setText("ADD-ON");
        jPanel1.add(Label_contador11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 610, -1, -1));

        Label_contador12.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        Label_contador12.setForeground(new java.awt.Color(255, 0, 51));
        Label_contador12.setText("+");
        jPanel1.add(Label_contador12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, -1, -1));

        Label_contador13.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        Label_contador13.setForeground(new java.awt.Color(255, 0, 51));
        Label_contador13.setText("CHIP RAISE ");
        jPanel1.add(Label_contador13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, -1, -1));

        Label_contador14.setFont(new java.awt.Font("Helvetica Neue", 1, 48)); // NOI18N
        Label_contador14.setForeground(new java.awt.Color(255, 255, 255));
        Label_contador14.setText("NIVEL");
        jPanel1.add(Label_contador14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        BTN_NEXT_LEVEL.setText("SIGUIENTE NIVEL");
        BTN_NEXT_LEVEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NEXT_LEVELActionPerformed(evt);
            }
        });
        jPanel1.add(BTN_NEXT_LEVEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 610, -1, -1));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tarjeta_java.png"))); // NOI18N
        jPanel1.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 650));

        javax.swing.GroupLayout Background_panelLayout = new javax.swing.GroupLayout(Background_panel);
        Background_panel.setLayout(Background_panelLayout);
        Background_panelLayout.setHorizontalGroup(
            Background_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Background_panelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Background_panelLayout.setVerticalGroup(
            Background_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Background_panelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(Background_panel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_DESCANSOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DESCANSOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_DESCANSOActionPerformed

    private void CONTINUARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CONTINUARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CONTINUARActionPerformed

    private void BTN_NEXT_LEVELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NEXT_LEVELActionPerformed
        // TODO add your handling code here:
        minutos = 12; // Restablece los minutos a 10
    segundos = 0; // Restablece los segundos a 0
    CUENTA_ATRAS.setText("12:00"); // Actualiza la etiqueta de la cuenta atrás
    nivel++; // Incrementa el nivel manualmente
    CONTADOR_NIVELES.setText(String.valueOf(nivel)); // Actualiza la etiqueta del nivel en la interfaz gráfica
    actualizarCiegas(nivel); // Actualiza las ciegas para el nuevo nivel
    }//GEN-LAST:event_BTN_NEXT_LEVELActionPerformed

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
            java.util.logging.Logger.getLogger(Poker_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Poker_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Poker_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Poker_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Poker_interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_DESCANSO;
    private javax.swing.JButton BTN_NEXT_LEVEL;
    private javax.swing.JLabel Background;
    private javax.swing.JPanel Background_panel;
    private javax.swing.JLabel CONTADOR_NIVELES;
    private javax.swing.JButton CONTINUAR;
    private javax.swing.JLabel CUENTA_ATRAS;
    private javax.swing.JLabel Label_CIEGAS;
    private javax.swing.JLabel Label_contador1;
    private javax.swing.JLabel Label_contador10;
    private javax.swing.JLabel Label_contador11;
    private javax.swing.JLabel Label_contador12;
    private javax.swing.JLabel Label_contador13;
    private javax.swing.JLabel Label_contador14;
    private javax.swing.JLabel Label_contador2;
    private javax.swing.JLabel Label_contador3;
    private javax.swing.JLabel Label_contador5;
    private javax.swing.JLabel Label_contador6;
    private javax.swing.JLabel Label_contador7;
    private javax.swing.JLabel Label_contador8;
    private javax.swing.JLabel Label_contador9;
    private javax.swing.JLabel PROXIMAS_CIEGAS;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
