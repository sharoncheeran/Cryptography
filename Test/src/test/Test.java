/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author sharo
 */
public class Test {
    static int Mod(int x) //Modulous function
    {
        int y;
        int n = 17;
        if (x < 0){
            y = -x; //converting to positive - and - equals +
            x = y % n; //do mod
            x = -x + n; //converting the positive to negative number then adding 11
        }
        else
        {
            x = x % n; //do this if integer already positive
        }
        return x;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] num1 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}; //(1)
        int[] num = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16}; //(2)
        int[] num2 = new int[16]; //(3)
        int[] num3 = new int[16]; //(4)
        int[] num4 = new int[16]; //(5)
        int[] num5 = new int[16]; //(6)
        int[] num6 = new int[16]; //(7)
        int[] num7 = new int[16]; //(8)
        int[] num8 = new int[16]; //(9)
        int[] num9 = new int[16]; //(10)
        int[] num10 = new int[16]; //(11)
        int[] num11 = new int[16]; //(12)
        int[] num12 = new int[16]; //(13)
        int[] num13 = new int[16]; //(14)
        int[] num14 = new int[16]; //(15)
        int[] num15 = new int[16]; //(16)
        int[] num16 = new int[16]; //(17)
        int[] num17 = new int[16]; //(18)
        
        for(int i = 0; i < num.length; i++)
        {
            System.out.print(num1[i] + "\t"); //power of 0
        }
        System.out.print("\n");
        
        for(int i = 0; i < num.length; i++) //power of 1
        {
            System.out.print(num[i] + "\t");
        }
        System.out.print("\n");
        
        for(int i = 0; i < num.length; i++) //power of 2
        {
            num2[i] = num[i] * num[i];
            num2[i] = Mod(num2[i]);
            System.out.print(num2[i] + "\t");
        }
        System.out.print("\n");
        
        for(int i = 0; i < num.length; i++) //power of 3
        {
            num3[i] = num[i] * num[i] * num[i];
            num3[i] = Mod(num3[i]);
            System.out.print(num3[i] + "\t");
        }
        System.out.print("\n");
        System.out.print("\n");
        //--------------------------------------------------------------------- generate syndrome ^^
        for(int i = 0; i < num.length; i++)
        {
            num4[i] = num1[i] + num[i]; //(1) + (2) = (5)
            num4[i] = Mod(num4[i]);
            System.out.print(num4[i] + "\t");
        }
        System.out.print("\n");
        
        for(int i = 0; i < num.length; i++)
        {
            num5[i] = num1[i] + num3[i]; //(1) + (4) = (6)
            num5[i] = Mod(num5[i]);
            System.out.print(num5[i] + "\t");
        }
        System.out.print("\n");
        
        for(int i = 0; i < num.length; i++)
        {
            num6[i] = num2[i] + num3[i]; //(3) + (4) = (7)
            num6[i] = Mod(num6[i]);
            System.out.print(num6[i] + "\t");
        }
        System.out.print("\n");
        for(int i = 0; i < num.length; i++)
        {
            num7[i] = num2[i] + num[i]; //(2) + (3) = (8)
            num7[i] = Mod(num7[i]);
            System.out.print(num7[i] + "\t");
        }
        System.out.print("\n");
        for(int i = 0; i < num.length; i++)
        {
            num8[i] = num5[i] + num6[i]; //(6) + (7) = (9) <--- Not ending in 0 0 0 
            num8[i] = Mod(num8[i]);
            System.out.print(num8[i] + "\t");
        }
        System.out.print("<---" + "\n");
        for(int i = 0; i < num.length; i++)
        {
            num9[i] = -1 * num8[i]; //-1 x (9) = (10)
            num9[i] = Mod(num9[i]);
            System.out.print(num9[i] + "\t");
        }
        System.out.print("\n");
        for(int i = 0; i < num.length; i++)
        {
            num10[i] = num4[i] - (5 * num7[i]); //(5) - 5 x (8) = (11)<-- This one could cause problems - need checking
            num10[i] = Mod(num10[i]);
        }
        System.out.print("\n");
        for(int i = 0; i < num.length; i++)
        {
            num11[i] = num10[i] - (3 * num9[i]); //(11) - 3 x (10) = (12) <-- This one could cause problems - need checking
            num11[i] = Mod(num11[i]);
        }
        System.out.print("\n");
        for(int i = 0; i < num.length; i++)
        {
            num12[i] = num4[i] + num7[i]; //(5) + (8) = (13)
            num12[i] = Mod(num12[i]);
        }
        System.out.print("\n");
        for(int i = 0; i < num.length; i++)
        {
            num13[i] = num12[i] - (4 * num11[i]); //(13) -4 x (12) = (14) <-- This one could cause problems - need checking
            num13[i] = Mod(num13[i]);
        }
        System.out.print("\n");
        for(int i = 0; i < num.length; i++)
        {
            num14[i] = num13[i] - (9 * num9[i]); //(14) - 9 x (10) = (15) <-- This one could cause problems - need checking
            num14[i] = Mod(num14[i]);
        }
        System.out.print("\n");
        for(int i = 0; i < num.length; i++)
        {
            num15[i] = num1[i] - num14[i]; //(1) - (15) = (16)
            num15[i] = Mod(num15[i]);
        }
        System.out.print("\n");
        for(int i = 0; i < num.length; i++)
        {
            num16[i] = num15[i] - num11[i]; //(16) - (12) = (17)
            num16[i] = Mod(num16[i]);
        }
        System.out.print("\n");
        for(int i = 0; i < num.length; i++)
        {
            num17[i] = num16[i] - num9[i]; //(17) - (10) = (18)
            num17[i] = Mod(num17[i]);
        }
        System.out.print("\n");
        //------------------------------------------------------------------------------generate the other shit^^
    }
}
    
