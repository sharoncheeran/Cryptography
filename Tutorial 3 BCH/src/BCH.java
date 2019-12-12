
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import javax.swing.JOptionPane;
/**
 *
 * @author sharo
 */
public class BCH extends javax.swing.JFrame {

    /**
     * Creates new form BCH
     */
    public BCH() {
        initComponents();
    }
    
    int Mod11(int x) //Modulous function
    {
        int y;
        if (x < 0){
            y = -x; //converting to positive - and - equals +
            x = y % 11; //do mod
            x = -x + 11; //converting the positive to negative number then adding 11
        }
        else
        {
            x = x % 11; //do this if integer already positive
        }
        return x;
    }
    
    int Inverse(int y) //Inverse function
    {
        y = Mod11(y); //Check Mod11
        switch(y)
        {
            case 1:
                return 1;
            case 2:
                return 6;
            case 3:
                return 4;
            case 4:
                return 3;
            case 5:
                return 9;
            case 6:
                return 2;
            case 7:
                return 8;
            case 8:
                return 7;
            case 9:
                return 5;
            case 10:
                return 10;
        }
        return y;
    }
    
    int sqRoot(int y) //square root function
    {
        y = Mod11(y); //check mod11
        switch(y)
        {
            case 1:
                return 1;
            case 2:
                return -1;
            case 3:
                return 5;
            case 4:
                return 2;
            case 5:
                return 4;
            case 6:
                return -1;
            case 7:
                return -1;
            case 8:
                return -1;
            case 9:
                return 3;
            case 10:
                return -1;
            default:
                return -1;
        }
    }
    
    int[] BCHGenerator(String s) //BCH generation, getting 6 and adding 4 digits using parity bit 
    {
        int[] nm = new int[10];
        String num = s.trim(); //get string and trim 
        if (num.length() != 6) //check string length 
        {
            JOptionPane.showMessageDialog(null, "Only 6 digits allowed");  //error message 
        }
        else 
        {
            jTextArea2.setText("");
            int d7, d8, d9, d10;
            for (int i = 0; i < num.length(); i++)
            {
                nm[i] = Integer.parseInt(String.valueOf(num.charAt(i))); //passing string as integer into array
            }
            
            d7 = ((nm[0] * 4) + (10 * nm[1]) + (9 * nm[2]) + (2 * nm[3]) + nm[4] + (7 * nm[5])) % 11; //d7 parity calculation 
            d8 = ((7 * nm[0]) + (8 * nm[1]) + (7 * nm[2]) + nm[3] + (9 * nm[4]) + (6 * nm[5])) % 11; //d8 parity calculation 
            d9 = ((9 * nm[0]) + nm[1] + (7 * nm[2]) + (8 * nm[3]) + (7 * nm[4]) + (7 * nm[5])) % 11; //d9 parity calculation 
            d10 = (nm[0] + (2 * nm[1]) + (9 * nm[2]) + (10 * nm[3]) + (4 * nm[4]) + nm[5]) % 11; //d10 parity calculation 
            
            if (d7 > 9) //if condition met return error else store into array
            {
                JOptionPane.showMessageDialog(null, "Unusable number d7");
            }
            else
            {
                nm[6] = d7;
            }
            
            if (d8 > 9) //if condition met return error else store into array
            {
                JOptionPane.showMessageDialog(null, "Unusable number d8");
            }
            else
            {
                nm[7] = d8;
            }
            
            if (d9 > 9) //if condition met return error else store into array
            {
                JOptionPane.showMessageDialog(null, "Unusable number d9");
            }
            else
            {
                nm[8] = d9;
            }
            
            if (d10 > 9) //if condition met return error else store into array
            {
                JOptionPane.showMessageDialog(null, "Unusable number d10");
            }
            else
            {
                nm[9] = d10;
            }
            for (int i = 0; i < 10; i++)
            {
                System.out.println(nm[i] + " D"); //printing out the whole array after wole result 
            }
        }
        return nm; //return array as result 
    }
    
    void BCHDecoder(String g) //Function for decoding BCH using syndrome 
    {
        int[] nm = new int[10];
        String num = g.trim();//triming string of any spaces 
        if (num.length() != 10) //checking for input length
        {
            jTextArea2.setText("Only 10 digits allowed for Syndrome"); //error message if input more than 10
        }
        else 
        {
            jTextArea2.setText("");
            int s1, s2, s3, s4, p, q, r, i, a, b, j, d;
            for (int k = 0; k < num.length(); k++)
            {
                nm[k] = Integer.parseInt(String.valueOf(num.charAt(k))); //passing from string to integer
            }
            s1 = (nm[0] + nm[1] + nm[2] + nm[3] + nm[4] + nm[5] + nm[6] + nm[7] + nm[8] + nm[9]); //doing syndrome 
            s1 = Mod11(s1);//modding the syndrome 
            s2 = (nm[0] + (2 * nm[1]) + (3 * nm[2]) + (4 * nm[3]) + (5 * nm[4]) + (6 * nm[5]) + (7 * nm[6]) + (8 * nm[7]) + (9 * nm[8]) + (10 * nm[9]));//doing syndrome
            s2 = Mod11(s2);//modding the syndrome
            s3 = (nm[0] + (4 * nm[1]) + (9 * nm[2]) + (5 * nm[3]) + (3 * nm[4]) + (3 * nm[5]) + (5 * nm[6]) + (9 * nm[7]) + (4 * nm[8]) + nm[9]);//doing syndrome
            s3 = Mod11(s3);//modding the syndrome
            s4 = (nm[0] + (8 * nm[1]) + (5 * nm[2]) + (9 * nm[3]) + (4 * nm[4]) + (7 * nm[5]) + (2 * nm[6]) + (6 * nm[7]) + (3 * nm[8]) + (10 * nm[9]));//doing syndrome
            s4 = Mod11(s4);//modding the syndrome
            System.out.println("S1 " + s1 + ", S2 " + s2 + " ,S3 " + s3 + " ,S4 " + s4); //printing out result 
            
            if (s1+s2+s3+s4 == 0000) //if syndrome is all 0's then no error 
            {
                jTextArea2.setText("No errors in BCH"); //error message 
            }
            else  
            {
                p = (Mod11((s2 * s2)) - Mod11((s1 * s3))); //finding pqr
                p = Mod11(p);
                q = (Mod11((s1 * s4)) - Mod11((s2 * s3)));
                q = Mod11(q);
                r = (Mod11((s3 * s3)) - Mod11((s2 * s4)));
                r = Mod11(r);
                System.out.println("P " + p + ",Q " + q + ",R " + r); //printing result 
                
                if(p == 0 && q == 0 && r == 0) //single error check
                {
                    i = s2 * (Inverse(s1)); //correcting single error
                    i = Mod11(i);
                    a = s1;
                    
                    if (i == 0) //if i is found to be 0 then return error message, if not, print error position and magnitude, then correct it in the array 
                    {
                        System.out.println("At least 2 or more errors (1)");
                    }
                    else
                    {
                        System.out.println("Error Pos i: " + i); 
                        System.out.println("Error Mag a: " + a);
                        
                        i = i - 1; //replacing the incorrect digits
                        nm[i] = Mod11(nm[i] - a);
                  
                    }
                }
                else if (!(p == 0 && q == 0 && r == 0)) //double error check
                {
                    i = ((Mod11(-q) + sqRoot(Mod11((q * q) - (4 * p * r))))) * Mod11(Inverse((2 * p)));//quadratic calculation
                    j = ((Mod11(-q) - sqRoot(Mod11((q * q) - (4 * p * r))))) * Mod11(Inverse((2 * p)));//quadratic calculation 
                    i = Mod11(i);
                    j = Mod11(j);
                    
                    d = sqRoot(Mod11((q * q) - (4 * p * r))); //2 or more error check
                    
                    if (d == -1 || i == 0 || j == 0) //if these conditions met, print error message 
                    {
                        System.out.println("At least 2 or more errors (2)"); //error message 
                    } 
                    else 
                    {
                        b = (Mod11(((i * s1) - s2))) * Mod11(Inverse(i - j)); //if it hasn't met then does these calculations 
                        a = s1 - b;
                        a = Mod11(a);
                        b = Mod11(b);
                        
                        if (a == 10 || b == 10) //if these conditions met, print error message
                        {
                            System.out.println("At least 2 or more errors (3)");//error message 
                        }
                        else
                        {
                            System.out.println("Error Pos i: " + i);//print error position and magnitude, then correct it in the array
                            System.out.println("Error Mag a: " + a);
                            System.out.println("Error Pos j: " + j);
                            System.out.println("Error Mag b: " + b);
                            
                            i = i - 1; //replacing the incorrect digits
                            nm[i] = Mod11(nm[i] - a);
                            j = j - 1;
                            nm[j] = Mod11(nm[j] - b);
                            
                        }
                    }   //i and j == Position
                }       //a and b == Magnitutde
            }
        }
        System.out.println("Replaced incorrect array (2)" + nm[0]+ nm[1]+ nm[2]+ nm[3]+ nm[4]+ nm[5]+ nm[6]+ nm[7]+ nm[8]+ nm[9]); //print out end result after calculations 
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Input 6");

        jTextField1.setText("888888");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Calculate");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane1.setViewportView(jTextArea2);

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Input 10");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jButton1)
                            .addGap(18, 18, 18)
                            .addComponent(jButton2))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(29, 29, 29)
                                    .addComponent(jLabel1)
                                    .addGap(20, 20, 20))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField2)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String s = jTextField1.getText(); //getting string from input field 
        String g = jTextField2.getText(); //getting string from input field
        BCHGenerator(s); //calling the function
        BCHDecoder(g);  //calling function 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

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
            java.util.logging.Logger.getLogger(BCH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BCH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BCH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BCH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new BCH().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
