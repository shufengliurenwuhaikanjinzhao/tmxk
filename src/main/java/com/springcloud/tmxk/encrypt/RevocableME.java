package com.springcloud.tmxk.encrypt;

import it.unisa.dia.gas.jpbc.*;
import it.unisa.dia.gas.plaf.jpbc.field.poly.PolyModElement;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.EventListener;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RevocableME {

    public final int d = 50;

    Field G1;
    Field Zr;
    Element g;
    Element P;
    // mpk
    Element P_pub;
    BigInteger orderOfG;
    // msk
    Element r;
    Element s;

    byte[] IDr;
    byte[] IDs;
    byte[] ID_Rev_i;

    byte[] rou;

    //ek_S
    Element ek_S;

    //dk_Rev_i
    Element[] dk_1;
    Element[] dk_2;
    Element[] dk_3;

    //ciphertext
    Element C0;
    Element C1;
    Element C2;
    Element C3;
    Element[] Q;
    Element[] U;
    BigInteger[] e;

    //cipher multi
//    Element T;
//    Element U;
    BigInteger C_1;

    //polynomial coefficients
    BigInteger[] a = new BigInteger[d + 1];//多项式展开的系数，用大整数来表示
    BigInteger[] b = new BigInteger[d + 1];//多项式展开的系数，用大整数来表示

    private long endTime;

    public static void main(String[] args) throws InterruptedException {
        int rBits = 160;
        int qBits = 512;
        TypeACurveGenerator typeACurveGenerator = new TypeACurveGenerator(rBits, qBits);
        PairingParameters pairingParameters = typeACurveGenerator.generate();
        Pairing pairing = PairingFactory.getPairing(pairingParameters);

        String message = "abc";
        String IDs = "123";
        //String IDr = "456";

        byte[] messageByte = message.getBytes();
        byte[] IDsByte = IDs.getBytes();
        //byte[] IDrByte = IDr.getBytes();
        RevocableME jpbcDemo = new RevocableME();

        String[] IDr = new String[jpbcDemo.d];
        String[] R = new String[2];
        for (int i = 0; i < IDr.length; i++) {
            IDr[i] = getRandomString(3);
        }
        for (int i = 0; i < R.length; i++) {
            R[i] = new String("123");
        }

        double averageResultInit, averageResultSKGen, averageResultRKGen, averageResultEKGen, averageResultDKGen, averageResultEnc, averageResultDec, averageResultRevo;
        double averageTenInit, averageTenSKGen, averageTenRKGen, averageTenEKGen, averageTenDKGen, averageTenEnc, averageTenDec, averageTenRevo;
        double sumResultInit = 0, sumResultSKGen = 0, sumResultRKGen = 0, sumResultEKGen = 0, sumResultDKGen = 0, sumResultEnc = 0, sumResultDec = 0, sumResultRevo = 0;
        long sumTenInit = 0, sumTenSKGen = 0, sumTenRKGen = 0, sumTenEKGen = 0, sumTenDKGen = 0,
                sumTenEnc = 0, sumTenDec = 0, sumTenRevo = 0;
        double maxAverageInit = 0, maxAverageSKGen = 0, maxAverageRKGen = 0, maxAverageEKGen = 0, maxAverageDKGen = 0, maxAverageEnc = 0, maxAverageDec = 0, maxAverageRevo = 0;
        double minAverageInit = Double.MAX_VALUE, minAverageSKGen = Double.MAX_VALUE, minAverageRKGen = Double.MAX_VALUE, minAverageEKGen = Double.MAX_VALUE,
                minAverageDKGen = Double.MAX_VALUE, minAverageEnc = Double.MAX_VALUE, minAverageDec = Double.MAX_VALUE, minAverageRevo = Double.MAX_VALUE;
        long sumInit = 0, sumSKGen = 0, sumRKGen = 0, sumEKGen = 0, sumDKGen = 0, sumEnc = 0, sumDec = 0, sumRevo = 0;
        long startTime;
        long endTime;
        jpbcDemo.Setup(pairing);

        for (int k = 0; k < 5; k++) {
            sumTenInit = 0;
            sumTenSKGen = 0;
            sumTenRKGen = 0;
            sumTenEnc = 0;
            sumTenRevo = 0;
            sumTenDec = 0;
            for (int i = 0; i < 10; i++) {

                startTime = System.currentTimeMillis();
                jpbcDemo.Setup(pairing);
                endTime = System.currentTimeMillis();
                //计算10次的总和
                sumTenInit += (endTime - startTime);

                System.out.println("init : " + (endTime - startTime));
                System.out.println("begin : " + i);

                startTime = System.currentTimeMillis();
                jpbcDemo.SKGen(IDs);
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenSKGen += (endTime - startTime);


                startTime = System.currentTimeMillis();
                jpbcDemo.RKGen(IDr);
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenRKGen += (endTime - startTime);

                startTime = System.currentTimeMillis();
                jpbcDemo.Encryption(IDr, message, pairing);
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenEnc += (endTime - startTime);

                startTime = System.currentTimeMillis();
                byte[] decryptResult = jpbcDemo.Decryption(pairing, IDr, IDs, R);
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenDec += (endTime - startTime);

//                if (message.equals(new String(decryptResult))) {
//                    System.out.println("yes");
//                } else {
//                    System.out.println("no");
//                }
                //assert message.equals(new String(decryptResult));

                startTime = System.currentTimeMillis();
                jpbcDemo.Revocation(pairing, R);
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenRevo += (endTime - startTime);

            }

            // 计算10次的平均
            averageTenInit = sumTenInit * 1.0 / 10.0;
            averageTenSKGen = sumTenSKGen * 1.0 / 10.0;
            averageTenRKGen = sumTenRKGen * 1.0 / 10.0;
//            averageTenEKGen = sumTenEKGen * 1.0 / 10.0;
//            averageTenDKGen = sumTenDKGen * 1.0 / 10.0;
            averageTenEnc = sumTenEnc * 1.0 / 10.0;
            averageTenDec = sumTenDec * 1.0 / 10.0;
            averageTenRevo = sumTenRevo * 1.0 / 10.0;

            // 计算30次【10次的平均】的最大值
            maxAverageInit = Math.max(maxAverageInit, averageTenInit);
            maxAverageSKGen = Math.max(maxAverageSKGen, averageTenSKGen);
            maxAverageRKGen = Math.max(maxAverageRKGen, averageTenRKGen);
//            maxAverageEKGen = Math.max(maxAverageEKGen, averageTenEKGen);
//            maxAverageDKGen = Math.max(maxAverageDKGen, averageTenDKGen);
            maxAverageEnc = Math.max(maxAverageEnc, averageTenEnc);
            maxAverageDec = Math.max(maxAverageDec, averageTenDec);
            maxAverageRevo = Math.max(maxAverageRevo, averageTenRevo);


            // 计算30次【10次的平均】的最小值
            minAverageInit = Math.min(minAverageInit, averageTenInit);
            minAverageSKGen = Math.min(minAverageSKGen, averageTenSKGen);
            minAverageRKGen = Math.min(minAverageRKGen, averageTenRKGen);
//            minAverageEKGen = Math.min(minAverageEKGen, averageTenEKGen);
//            minAverageDKGen = Math.min(minAverageDKGen, averageTenDKGen);
            minAverageEnc = Math.min(minAverageEnc, averageTenEnc);
            minAverageDec = Math.min(minAverageDec, averageTenDec);
            minAverageRevo = Math.min(minAverageRevo, averageTenRevo);


            // 计算30次【10次的平均】的总和
            sumResultInit += averageTenInit;
            sumResultSKGen += averageTenSKGen;
            sumResultRKGen += averageTenRKGen;
//            sumResultEKGen += averageTenEKGen;
//            sumResultDKGen += averageTenDKGen;
            sumResultEnc += averageTenEnc;
            sumResultDec += averageTenDec;
            sumResultRevo += averageTenRevo;

        }

        // 计算30次【10次的平均】平均值
        averageResultInit = sumResultInit * 1.0 / 5.0;
        averageResultSKGen = sumResultSKGen * 1.0 / 5.0;
        averageResultRKGen = sumResultRKGen * 1.0 / 5.0;
//        averageResultEKGen = sumResultEKGen * 1.0 / 30.0;
//        averageResultDKGen = sumResultDKGen * 1.0 / 30.0;
        averageResultEnc = sumResultEnc * 1.0 / 5.0;
        averageResultDec = sumResultDec * 1.0 / 5.0;
        averageResultRevo = sumResultRevo * 1.0 / 5.0;

        NumberFormat nformat = NumberFormat.getNumberInstance();
        nformat.setMaximumFractionDigits(2);

        System.out.println("init time : " + nformat.format(maxAverageInit) + ", " + nformat.format(minAverageInit) + ", " + nformat.format(averageResultInit));


        System.out.println("sender-key generation time : " + nformat.format(maxAverageSKGen) + ", " + nformat.format(minAverageSKGen) + ", " + nformat.format(averageResultSKGen));


        System.out.println("receiver-key generation time : " + nformat.format(maxAverageRKGen) + ", " + nformat.format(minAverageRKGen) + ", " + nformat.format(averageResultRKGen));


//        System.out.println("encryption-key generation time : " + maxAverageEKGen + ", " + minAverageEKGen + ", " + averageResultEKGen);
//
//
//        System.out.println("decryption-key generation time : " + maxAverageDKGen + ", " + minAverageDKGen + ", " + averageResultDKGen);


        System.out.println("encryption time : " + nformat.format(maxAverageEnc) + ", " + nformat.format(minAverageEnc) + ", " + nformat.format(averageResultEnc));

        System.out.println("revocation time : " + nformat.format(maxAverageRevo) + ", " + nformat.format(minAverageRevo) + ", " + nformat.format(averageResultRevo));

        System.out.println("decryption time : " + nformat.format(maxAverageDec) + ", " + nformat.format(minAverageDec) + ", " + nformat.format(averageResultDec));


        System.out.println("enc key : " + jpbcDemo.ek_S.getLengthInBytes() * 8);

        System.out.println("dec key : " + jpbcDemo.dk_1[1].getLengthInBytes() * 8 + ", " + jpbcDemo.dk_2[1].getLengthInBytes() * 8);

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("runoob.txt"));
            out.write("d = " + jpbcDemo.d + "\r\n");
            out.write("init time : " + nformat.format(maxAverageInit) + ", " + nformat.format(minAverageInit) + ", " + nformat.format(averageResultInit) + "\r\n");
            out.write("sender-key generation time : " + nformat.format(maxAverageSKGen) + ", " + nformat.format(minAverageSKGen) + ", " + nformat.format(averageResultSKGen) + "\r\n");
            out.write("receiver-key generation time : " + nformat.format(maxAverageRKGen) + ", " + nformat.format(minAverageRKGen) + ", " + nformat.format(averageResultRKGen) + "\r\n");
            out.write("encryption time : " + nformat.format(maxAverageEnc) + ", " + nformat.format(minAverageEnc) + ", " + nformat.format(averageResultEnc) + "\r\n");
            out.write("revocation time : " + nformat.format(maxAverageRevo) + ", " + nformat.format(minAverageRevo) + ", " + nformat.format(averageResultRevo) + "\r\n");
            out.write("decryption time : " + nformat.format(maxAverageDec) + ", " + nformat.format(minAverageDec) + ", " + nformat.format(averageResultDec) + "\r\n");
            out.close();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
        }
    }

    public void Setup(Pairing pairing) {
        G1 = pairing.getG1();
        Zr = pairing.getZr();
        this.P = G1.newRandomElement();
        this.orderOfG = G1.getOrder();
        Element r = Zr.newRandomElement();
        Element s = Zr.newRandomElement();
        this.P_pub = P.duplicate().mulZn(r);
        this.r = r;
        this.s = s;
    }

    public void SKGen(String IDs) {
        this.IDs = IDs.getBytes();
        Element hashIDs = G1.newRandomElement();
        hashIDs.setFromHash(this.IDs, 0, this.IDs.length);
        this.ek_S = hashIDs.duplicate().mulZn(this.s);
    }

    public void RKGen(String[] ID_Rev) {
        this.dk_1 = new Element[this.d + 1];
        this.dk_2 = new Element[this.d + 1];
        int i = 1;
        for (String ID_Rev_i : ID_Rev) {
            this.ID_Rev_i = ID_Rev_i.getBytes();
            Element hashID_Rev_i = G1.newRandomElement();
            hashID_Rev_i.setFromHash(this.ID_Rev_i, 0, this.ID_Rev_i.length);
            this.dk_1[i] = hashID_Rev_i.duplicate().mulZn(this.r);
            this.dk_2[i] = hashID_Rev_i.duplicate().mulZn(this.s);
            i++;
        }
    }

    public void Encryption(String[] ID_Rev, String Message, Pairing pairing) {
        Element M = G1.newElementFromBytes(Message.getBytes());
        this.Q = new Element[this.d + 1];
        this.U = new Element[this.d + 1];
        this.e = new BigInteger[this.d + 1];

        Element[] A = new Element[this.d + 1];
        Element[] B = new Element[this.d + 1];
        Element[] D = new Element[this.d + 1];

        Element n_1 = Zr.newRandomElement();
        Element n_2 = Zr.newRandomElement();
        Element n_3 = Zr.newRandomElement();
        Element v = G1.newRandomElement();
        Element w = Zr.newRandomElement();
        Element H_5 = G1.newRandomElement();
        H_5.setFromHash(w.toString().getBytes(), 0, w.toString().getBytes().length);
//        Element T = g.duplicate().powZn(t);
//        Element U = g.duplicate().powZn(u);
//        this.T = T;
//        this.U = U;
        Element[] x = new Element[d + 1];
        BigInteger[][] a = new BigInteger[d + 1][d + 1];
        BigInteger[] k2 = new BigInteger[d];
        BigInteger[] v2 = new BigInteger[d];
        int i = 1;
        for (String ID_Rev_i : ID_Rev) {
            Element hashID_Rev_i = Zr.newRandomElement();
            hashID_Rev_i.setFromHash(ID_Rev_i.getBytes(), 0, ID_Rev_i.getBytes().length);
            x[i] = hashID_Rev_i;
            i++;
        }

        for (i = 1; i <= d; i++) {
            Element x_iSUBx_jl = Zr.newOneElement();
            for (int j = 1; j <= d; j++) {
                if (i != j) {
                    x_iSUBx_jl.mul(x[i].duplicate().sub(x[j]));
                }
            }
            //x_iSUBx_jl.invert();
            /**
             * 计算多项式展开的参数
             */
            a[i][0] = x[1].duplicate().toBigInteger().negate();
            a[i][1] = BigInteger.valueOf(1);

            for (int j = 1; j < d; j++) {
                a[i][j + 1] = BigInteger.valueOf(1);
                if (i != j) {
                    for (int h = j; h > 0; h--) {
                        a[i][h] = a[i][h - 1].add(a[i][h].multiply(x[j].duplicate().toBigInteger()));
                    }
                    a[i][0] = a[i][0].multiply(x[j].duplicate().toBigInteger());
                }
            }
            /*************/
            for (int j = 1; j <= d; j++) {
                a[i][j] = a[i][j].divide(x_iSUBx_jl.toBigInteger());
            }

            Element hashID_Rev_i = G1.newRandomElement();
            hashID_Rev_i.setFromHash(ID_Rev[i - 1].getBytes(), 0, ID_Rev[i - 1].getBytes().length);

            A[i] = G1.newRandomElement();
            String A_i_left = pairing.pairing(hashID_Rev_i, this.P_pub.duplicate().mulZn(n_1)).toString();
            String H_2 = A_i_left.concat(ID_Rev[i - 1]);
            A[i].setFromHash(H_2.getBytes(), 0, H_2.getBytes().length);

            B[i] = G1.newRandomElement();
            String B_i_left = pairing.pairing(hashID_Rev_i, this.P_pub.duplicate().mulZn(n_2)).toString();
            String H_3 = B_i_left.concat(ID_Rev[i - 1]);
            B[i].setFromHash(H_3.getBytes(), 0, H_3.getBytes().length);
            B[i].add(v);

            D[i - 1] = Zr.newRandomElement();
            String D_i_left = pairing.pairing(hashID_Rev_i, (this.P.duplicate().mulZn(n_3)).duplicate().mul(this.ek_S)).toString();
            String H_4 = D_i_left;
            D[i - 1].setFromHash(H_4.getBytes(), 0, H_4.getBytes().length);
        }

        for (i = 1; i <= d; i++) {
            for (int j = 1; j <= d; j++) {
                this.Q[i] = G1.newZeroElement();
                this.U[i] = G1.newZeroElement();
                this.Q[i].add(A[j].duplicate().mul(a[j][i - 1]));
                this.U[i].add(B[j].duplicate().mul(a[j][i - 1]));
            }
        }


        /**
         * 计算多项式展开的参数
         */
        e[0] = D[0].duplicate().toBigInteger().negate();
        e[1] = BigInteger.valueOf(1);

        for (i = 1; i < d; i++) {
            e[i + 1] = BigInteger.valueOf(1);
            for (int j = i; j > 0; j--) {
                e[j] = e[j - 1].add(e[j].multiply(D[i].duplicate().toBigInteger().negate()));
            }
            e[0] = e[0].multiply(D[i].duplicate().toBigInteger().negate());
        }
        e[0] = e[0].add(H_5.setToZero().toBigInteger());
        /*************/

        this.C0 = v.duplicate().add(H_5.duplicate().add(M));
        this.C1 = this.P.duplicate().mulZn(n_1);
        this.C2 = this.P.duplicate().mulZn(n_2);
        this.C3 = this.P.duplicate().mulZn(n_3);

//        String s1 = z.toString().concat(U.toString());
//        String s2 = n.toString().concat(T.toString());
//        BigInteger H_1_zU = new BigInteger(G1.newRandomElement().setFromHash(s1.getBytes(), 0, s1.getBytes().length).toBytes());
//        BigInteger H_1_nT = new BigInteger(G1.newRandomElement().setFromHash(s2.getBytes(), 0, s2.getBytes().length).toBytes());
//        BigInteger MInt = new BigInteger(M.getBytes());
//        C_1 = H_1_zU.xor(H_1_nT).xor(MInt);
    }

    public void Revocation(Pairing pairing, String[] R) {
        int t = R.length;
        Element u = G1.newRandomElement();
        Element C0_pai = this.C0.duplicate().add(u);
        this.C0 = C0_pai;
        Element[] x = new Element[t + 1];
        BigInteger[] b = new BigInteger[t + 1];
        x[0] = Zr.newZeroElement();
        for (int i = 1; i < x.length; i++) {
            Element x_i = Zr.newRandomElement();
            x_i.setFromHash(R[i - 1].getBytes(), 0, R[i - 1].getBytes().length);
            x[i] = x_i;
        }
        /**
         * 计算多项式展开的参数
         */
        b[0] = x[0].duplicate().toBigInteger().negate();
        b[1] = BigInteger.valueOf(1);

        for (int i = 1; i < t; i++) {
            b[i + 1] = BigInteger.valueOf(1);
            for (int j = i; j > 0; j--) {
                b[j] = b[j - 1].add(b[j].multiply(x[i].duplicate().toBigInteger().negate()));
            }
            b[0] = b[0].multiply(x[i].duplicate().toBigInteger().negate());
        }
        /*************/
        for (int i = 1; i <= b.length; i++) {
            this.Q[i] = this.Q[i].duplicate().add(u.duplicate().mulZn(Zr.newElement(b[i - 1])));
        }

    }

    public byte[] Decryption(Pairing pairing, String[] ID_Rev, String IDs, String[] R) {
        Element[] x = new Element[d + 1];
        int i = 1;
        for (String ID_Rev_i : ID_Rev) {
            Element hashID_Rev_i = G1.newRandomElement();
            hashID_Rev_i.setFromHash(ID_Rev_i.getBytes(), 0, ID_Rev_i.getBytes().length);
            x[i] = hashID_Rev_i;
            i++;
        }
        Element x_i = G1.newOneElement();
        Element Q = G1.newZeroElement();
        Element U = G1.newZeroElement();
        for (i = 1; i <= d; i++) {
            Q.add(this.Q[i].duplicate().mul(x_i));
            U.add(this.U[i].duplicate().mul(x_i));
            x_i.mul(G1.newElement(x[i]));
        }
        Element[] x_pai = new Element[R.length + 1];
        i = 1;
        for (String ID_Rev_i : R) {
            Element hashID_Rev_i = G1.newRandomElement();
            hashID_Rev_i.setFromHash(ID_Rev_i.getBytes(), 0, ID_Rev_i.getBytes().length);
            x_pai[i] = hashID_Rev_i;
            i++;
        }
        Element u_pai;
        Element v_pai;
        // g(x_i)
        Element gx_i = G1.newOneElement();
        for (int j = 1; j < x_pai.length; j++) {
            gx_i.mul(x[1].duplicate().sub(x_pai[j]));
        }
        String H_2_left = pairing.pairing(this.dk_1[1], this.C1).toString();
        String H_2_input = H_2_left.concat(ID_Rev[1]);
        Element H_2 = G1.newZeroElement();
        H_2.setFromHash(H_2_input.getBytes(), 0, H_2_input.getBytes().length);
        u_pai = gx_i.invert().duplicate().mul(Q.duplicate().sub(H_2));

        String H_3_left = pairing.pairing(this.dk_1[1], this.C2).toString();
        String H_3_input = H_3_left.concat(ID_Rev[1]);
        Element H_3 = G1.newZeroElement();
        H_3.setFromHash(H_3_input.getBytes(), 0, H_3_input.getBytes().length);
        v_pai = U.duplicate().sub(H_3);

        Element E_i = Zr.newRandomElement();
        Element H_0 = G1.newZeroElement();
        H_0.setFromHash(IDs.getBytes(), 0, IDs.getBytes().length);
        Element H = G1.newZeroElement();
        H.setFromHash(ID_Rev[1].getBytes(), 0, ID_Rev[1].getBytes().length);
        Element H_4_left = pairing.pairing(dk_2[1], H_0);
        Element H_4_right = pairing.pairing(H, this.C3);
        String E_i_input = H_4_left.duplicate().mul(H_4_right).toString();
        E_i.setFromHash(E_i_input.getBytes(), 0, E_i_input.getBytes().length);
        Element w_pai = Zr.newZeroElement();
        for (int j = 0; j <= d; j++) {
            w_pai.add(E_i.duplicate().pow(BigInteger.valueOf(j)).mul(this.e[j]));
        }
        Element H_5 = G1.newRandomElement();
        H_5.setFromHash(w_pai.toString().getBytes(), 0, w_pai.toString().getBytes().length);
        Element M = this.C0.duplicate().sub(u_pai).sub(u_pai).sub(v_pai).sub(H_5);
        return M.toBytes();
    }

    public void init(Pairing pairing) {
        G1 = pairing.getG1();
        Zr = pairing.getZr();
        g = G1.newRandomElement();
    }


    //length用户要求产生字符串的长度
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
//    public void Encrypt(byte[] m, Pairing pairing) {
//
//    }
//
//    public byte[] Decrypt(Pairing pairing) {
//
//    }
}
