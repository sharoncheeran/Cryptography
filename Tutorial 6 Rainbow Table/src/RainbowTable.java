
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;
import java.math.BigInteger;

/**
 *
 * @author sharo
 */
public class RainbowTable extends javax.swing.JFrame {

    /**
     * Creates new form RainbowTable
     */
    public RainbowTable() {
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
    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    String[] letters = {"", "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};   //Storing letters into an array 
    String pass = null;
    int chainLength = 100;
    int chainNumber = 100;
    HashMap<String, String> rainTable = new HashMap<String, String>();
    long sizePass = 101;
    Random rand = new Random(sizePass);
    
    void rainTable()
    throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        for(int i = 0; i < chainNumber; i++)
        {
            String lastStr;
            long firstN = rand.nextInt();
            String firstStr = intToString(firstN, letters);
            lastStr = chainReduce(firstStr, 0);
            if(rainTable.containsKey(lastStr))
            {
                i++;
            }
            else
            {
                rainTable.put(firstStr, lastStr);
            }
        }
    }
    
    String reduceFunc(String hash, int pos)
    throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        BigInteger val = new BigInteger(hash);
        long len = val.longValue();
        
        String conversion = intToString(len, letters);
        return conversion;
    }
    
    String intToString(long n, String[] alpha)
    {
        int base = alpha.length;
        long num;
        String wrd = "";
        while(n>=0)
        {
            num = n % base;
            n = n % base;
            wrd = getLetter((int)num, letters) + wrd;
            n = n - 1;
        }
        return wrd;
    }
    
    String chainReduce(String hash, int pos) 
    throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        String pwd;
        String hash1 = SHA1(hash);
        pwd = reduceFunc(hash, pos);
        while(pos != chainLength)
        {
            pos++;
            hash1 = SHA1(pwd);
            pwd = reduceFunc(hash, pos);
        }
        
        return pwd;
    }
    
    String getLetter(int num, String[] wrd)
    {
        return wrd[num];
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        
        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new RainbowTable().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
