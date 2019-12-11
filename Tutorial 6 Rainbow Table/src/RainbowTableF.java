/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author sharo
 */
public class RainbowTableF extends javax.swing.JFrame {

    /**
     * Creates new form RainbowTableF
     */
    public RainbowTableF() {
        initComponents();
    }
    
    private static String convertToHex(byte[] data) { //coverting to hex
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
    
    public static String SHA1(String text)  //hashing 
        throws NoSuchAlgorithmException, UnsupportedEncodingException  { 
	MessageDigest md; 
	md = MessageDigest.getInstance("SHA-1"); 
	byte[] sha1hash = new byte[40]; 
	md.update(text.getBytes("iso-8859-1"), 0, text.length()); 
	sha1hash = md.digest(); 
	return convertToHex(sha1hash); 
    } 
    
    String[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9"};   //Storing letters into an array 
    String pass;
    int sizePass = 4;
    int chainLength = 100;
    int numPass = 1727605; //36^4 + 36^3 + 36^2 + 36^1 + 36^0 + 36 is the total characters in the array
    int chainNumber = numPass / chainLength;
    HashMap <String, String> rainTable = new HashMap<>();
    Random rand = new Random();
    
    void rainTable()
    throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        int count = 0;
        for(int i = 0; i < chainNumber; i++)
        {
            BigInteger firstN = new BigInteger (rand.nextInt(numPass) + "");
            String firstStr = intToString(firstN, letters);
            String lastStr = chainReduce(firstStr, 0);
            
            if(rainTable.containsValue(lastStr))
            {
                System.out.println("Unusable " + firstStr);
            }
            
            if(rainTable.containsKey(lastStr))
            {
                count++;
            }
            else
            {
                rainTable.put(lastStr, firstStr);
                System.out.println("LS: " + lastStr + "  " + "FS: " + firstStr);
            }
        }
    }
    
    String reduceFunc(String hashes, int pos)
    throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        BigInteger val = new BigInteger(hashes, 16);
        String conversion = intToString(val, letters);
        
        return conversion;
    }
    
    String intToString(BigInteger n, String[] alpha)
    {
        BigInteger chr = new BigInteger("0");
        BigInteger base = new BigInteger(alpha.length + "");
        int xy = 0;
        int num;
        String wrd = "";
        
        while(n.compareTo(chr) != 0 && xy < sizePass)
        {
            num = n.mod(base).intValue();
            n = n.divide(base);
            wrd = getLetter((int)num, letters) + wrd;
            n = n.subtract(new BigInteger("1"));
            xy++;
        }
        
        return wrd;
    }
    
    String Decrypt()
    throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        int position = chainLength;
        String userHash = jTextField1.getText().trim();
        String userHash2 = jTextField1.getText().trim();
        String result = reduceFunc(userHash, chainLength);
        String start = rainTable.get(result);
        System.out.println(userHash + "=======" + result);
        
        while(start == null && position > 0)
        {
            userHash = SHA1(start);
            start = reduceFunc(userHash, chainLength);
            position--;
        }
        
        if (position == 0)
        {
            return null;
        }
        else
        {
            position = 0;
            String newStart = start;
            
            while(position <= chainLength)
            {
                userHash = SHA1(newStart).trim();
                
                if(userHash.compareTo(userHash2) == 0)
                {
                    return newStart;
                }
                else
                {
                    position++;
                    newStart = reduceFunc(userHash, position);
                }
            }
            return null;
        }
    }
    String chainReduce(String hash, int pos) 
    throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        String hash1 = SHA1(hash);
        String pwd = reduceFunc(hash1, pos);
        while(pos != chainLength)
        {
            pos++;
            hash1 = SHA1(pwd);
            pwd = reduceFunc(hash1, pos);
        }
        System.out.println(pwd + "-------" + hash1);
        return pwd;
    }
    
    String getLetter(int num, String[] wrd)
    {
        return wrd[num];
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Generate RT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Decrypt");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
            .addGroup(layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            rainTable();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(RainbowTableF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String r = Decrypt();
            if (r == null)
            {
                System.out.println("Unavailable");
            }
            else
            {
                System.out.println("Your password is:  " + r);
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(RainbowTableF.class.getName()).log(Level.SEVERE, null, ex);
        }    // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(RainbowTableF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RainbowTableF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RainbowTableF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RainbowTableF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RainbowTableF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
