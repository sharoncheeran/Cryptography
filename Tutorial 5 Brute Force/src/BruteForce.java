/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Arrays;
import java.security.MessageDigest;
import java.io.UnsupportedEncodingException; 
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author sharo
 */
public class BruteForce extends javax.swing.JFrame {

    String BCHGenerator(String s)//BCH generation, getting 6 and adding 4 digits using parity bit
    {
        int[] nm = new int[10]; //creating array
        String num = s.trim(); //get string and trim
        int d7, d8, d9, d10; //check string length
            
        for (int i = 0; i < num.length(); i++)
        {
            nm[i] = Integer.parseInt(String.valueOf(num.charAt(i))); //converting to integer
            
        }
        d7 = ((nm[0] * 4) + (10 * nm[1]) + (9 * nm[2]) + (2 * nm[3]) + nm[4] + (7 * nm[5])) % 11; //d7 parity calculation
        d8 = ((7 * nm[0]) + (8 * nm[1]) + (7 * nm[2]) + nm[3] + (9 * nm[4]) + (6 * nm[5])) % 11; //d8 parity calculation 
        d9 = ((9 * nm[0]) + nm[1] + (7 * nm[2]) + (8 * nm[3]) + (7 * nm[4]) + (7 * nm[5])) % 11; //d9 parity calculation 
        d10 = (nm[0] + (2 * nm[1]) + (9 * nm[2]) + (10 * nm[3]) + (4 * nm[4]) + nm[5]) % 11; //d10 parity calculation 
        
        if (d7 > 9)//if condition met return error else store into array
        {
            System.out.println("Unusable number d7"); //error message
            return "";
        }
        else
        {
            nm[6] = d7; //storing to array
        }
        if (d8 > 9)//if condition met return error else store into array
        { 
            System.out.println("Unusable number d8");//error message
            return "";
        }
        else
        {
            nm[7] = d8;//storing to array
        }
        if (d9 > 9)//if condition met return error else store into array
        {
            System.out.println("Unusable number d9");//error message
            return "";
        }
        else
        {
            nm[8] = d9;//storing to array
        }
        if (d10 > 9)//if condition met return error else store into array
        {
            System.out.println("Unusable number d10");//error message
            return "";
        }
        else
        {
            nm[9] = d10;//storing to array
        }
        return Arrays.toString(nm).replaceAll("\\[|\\]|,|\\s", ""); //converting int array to string with regex to replace commas and brackets
    }
    
    private static String convertToHex(byte[] data) { //coverting to hex - code from leturer
        StringBuffer buf = new StringBuffer(); 
        for (int i = 0; i < data.length; i++) { 
        	int halfbyte = (data[i] >>> 4) & 0x0F; 
        	int two_halfs = 0; 
        	do { 
	            if ((0 <= halfbyte) && (halfbyte <= 9)) 
	                buf.append((char) ('0' + halfbyte)); 
	            else 
	            	buf.append((char) ('a' + (halfbyte - 10))); 
	            halfbyte = data[i] & 0x0F; 
        	} while(two_halfs++ < 1); 
        } 
        return buf.toString(); 
    } 
    
    public static String SHA1(String text)  //hashing - code from lecturer 
        throws NoSuchAlgorithmException, UnsupportedEncodingException  { 
	MessageDigest md; 
	md = MessageDigest.getInstance("SHA-1"); 
	byte[] sha1hash = new byte[40]; 
	md.update(text.getBytes("iso-8859-1"), 0, text.length()); 
	sha1hash = md.digest(); 
	return convertToHex(sha1hash); 
    } 
    /**
     * Creates new form BruteForce
     */
    public BruteForce() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Calculate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Text");

        jLabel2.setText("Result");

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Time");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton4.setText("BCH");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                            .addComponent(jTextField2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton4)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String[] letters = {"", "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9"};   // TODO add your handling code here
        String pass, hash = null;
        
        long startTime = System.currentTimeMillis(); //timer function
        long total = 0;
        for (int i = 0; i < 10000000; i++) { //loop for timer
            total += i;
        }
      
        for (int i = 0; i < letters.length; i++) //6 nested loop
        {
            for (int j = 0; j < letters.length; j++)
            {
                for (int k = 0; k < letters.length; k++)
                {
                    for (int l = 0; l < letters.length; l++)
                    {
                        for (int m = 0; m < letters.length; m++)
                        {
                            for (int n = 0; n < letters.length; n++)
                            {
                                pass = letters[i] + letters[j] + letters[k] + letters[l] + letters[m] + letters[n]; //running through all the characters
                                try {
                                    //System.out.println(pass + " <-- Generated"); //print 
                                    hash = SHA1(pass); //runs hashing function
                                    //System.out.println(hash); //prints hash result 
                                } catch (NoSuchAlgorithmException e) { //catch check
                                    e.printStackTrace(); 
                                } catch (UnsupportedEncodingException e) {  
                                    e.printStackTrace(); 
                                } 
                                String input = jTextField1.getText().trim();//gets input
                                //System.out.println(input + "\n"); //prints given input 
                                if (hash.equals(input)) //if given hash matches the one that has been generated 
                                {
                                    System.out.println(pass);
                                    System.out.println("MATCHED!!!"); //result message 
                                    long stopTime = System.currentTimeMillis();//get current millisecond 
                                    long elapsedTime = stopTime - startTime;//do time calculation 
                                    System.out.println("Time: " + elapsedTime + " ms"); //printing elapsed time for function run
                                    System.exit(0); //exit after if statement is true
                                }
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        String[] numbers = {"0","1","2","3","4","5","6","7","8","9"};//SET B 
        String pass, hash = null, p;
        long startTime = System.currentTimeMillis(); //timer function
        long total = 0;
        
        for (int i = 0; i < 10000000; i++) { //loop for timer
            total += i;
        }
        
        for (int i = 0; i < numbers.length; i++) //6 nested loop 
        {
            for (int j = 0; j < numbers.length; j++)
            {
                for (int k = 0; k < numbers.length; k++)
                {
                    for (int l = 0; l < numbers.length; l++)
                    {
                        for (int m = 0; m < numbers.length; m++)
                        {
                            for (int n = 0; n < numbers.length; n++)
                            {
                                pass = numbers[i] + numbers[j] + numbers[k] + numbers[l] + numbers[m] + numbers[n]; //running through all the characters
                                try {
                                    System.out.println(pass + " <-- First 6"); //print
                                    p = BCHGenerator(pass); //passing through BCH Generator with 6 digits generated 
                                    System.out.println(p + " <-- Full set"); //print
                                    hash = SHA1(p); //runs hashing function
                                    System.out.println(hash); //prints hash 
                                } catch (NoSuchAlgorithmException e) { //catch check 
                                    e.printStackTrace(); 
                                } catch (UnsupportedEncodingException e) {  
                                    e.printStackTrace(); 
                                } 
                                
                                String input = jTextField1.getText().trim();//gets input
                                System.out.println(input + "\n"); //prints given input 
                                
                                if (hash.equals(input)) //if hash generated matches given hash 
                                {
                                    System.out.println("MATCHED!!!");//result message 
                                    long stopTime = System.currentTimeMillis(); //get current time
                                    long elapsedTime = stopTime - startTime;//time calculation 
                                    System.out.println("Time: " + elapsedTime + " ms"); //print elapsed time
                                    System.exit(0); //exit when done
                                }
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(BruteForce.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BruteForce.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BruteForce.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BruteForce.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BruteForce().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
