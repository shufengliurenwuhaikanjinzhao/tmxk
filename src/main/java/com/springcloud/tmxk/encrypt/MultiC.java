import it.unisa.dia.gas.jpbc.*;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;

import java.math.BigInteger;
import java.text.NumberFormat;

public class MultiC {

    public final int d = 10;

    Field G1;
    Field Zr;
    Element g;

    byte[] IDr;
    byte[] IDs;
    byte[] ID_Rev_i;

    //byte[] rou;
    //mpk_R
    Element P;
    Element Gama;
    //mpk_S
    //Element P1;
    Element K;
    Element Theta;
    //msk_R
    Element rou;
    Element gama;
    //msk_S
    Element k;
    Element theta;

    //ek_S
    Element ek_S;

    //ek_IDs
    Element ek_1;
    Element ek_2;
    //dk_IDr
    Element dk1;
    Element dk2;
    Element dk3;

    //dk_Rev_i
    Element[] dk_1;
    Element[] dk_2;
    Element[] dk_3;

    //ciphertext
    BigInteger C1;
    Element C2;
    Element C3;
    Element[] C4;
    Element C5;

    //cipher multi
    Element T;
    Element U;
    BigInteger C_1;

    //polynomial coefficients
    BigInteger[] a = new BigInteger[d + 1];//多项式展开的系数，用大整数来表示
    BigInteger[] b = new BigInteger[d + 1];//多项式展开的系数，用大整数来表示

    private long endTime;

    public static void main(String[] args) {
        int rBits = 160;
        int qBits = 512;
        TypeACurveGenerator typeACurveGenerator = new TypeACurveGenerator(rBits, qBits);
        PairingParameters pairingParameters = typeACurveGenerator.generate();
        Pairing pairing = PairingFactory.getPairing(pairingParameters);

//        Element g = G1.newRandomElement();
//        Element a = Zr.newRandomElement();
//        Element b = Zr.newRandomElement();
//        Element g_a = g.duplicate().powZn(a);
//        Element g_b = g.duplicate().powZn(b);
//        Element egg_ab = pairing.pairing(g_a, g_b);
//        Element egg = pairing.pairing(g, g);
//        Element ab = a.duplicate().mul(b);
//        Element egg_ab_p = egg.duplicate().powZn(ab);
//        if(egg_ab.isEqual(egg_ab_p)) {
//            System.out.println("yes");
//        } else {
//            System.out.println("no");
//        }
        String message = "abc";
        String IDs = "123";
        //String IDr = "456";

        byte[] messageByte = message.getBytes();
        byte[] IDsByte = IDs.getBytes();
        //byte[] IDrByte = IDr.getBytes();
        MultiC jpbcDemo = new MultiC();
        String[] IDr = new String[jpbcDemo.d];
        for (int i = 0; i < IDr.length; i++) {
            IDr[i] = new String("123");
        }

        double averageResultInit, averageResultSKGen, averageResultRKGen, averageResultEKGen, averageResultDKGen, averageResultEnc, averageResultDec;
        double averageTenInit, averageTenSKGen, averageTenRKGen, averageTenEKGen, averageTenDKGen, averageTenEnc, averageTenDec;
        double sumResultInit = 0, sumResultSKGen = 0, sumResultRKGen = 0, sumResultEKGen = 0, sumResultDKGen = 0, sumResultEnc = 0, sumResultDec = 0;
        long sumTenInit = 0, sumTenSKGen = 0, sumTenRKGen = 0, sumTenEKGen = 0, sumTenDKGen = 0, sumTenEnc = 0, sumTenDec = 0;
        double maxAverageInit = 0, maxAverageSKGen = 0, maxAverageRKGen = 0, maxAverageEKGen = 0, maxAverageDKGen = 0, maxAverageEnc = 0, maxAverageDec = 0;
        double minAverageInit = Double.MAX_VALUE, minAverageSKGen = Double.MAX_VALUE, minAverageRKGen = Double.MAX_VALUE, minAverageEKGen = Double.MAX_VALUE,
                minAverageDKGen = Double.MAX_VALUE, minAverageEnc = Double.MAX_VALUE, minAverageDec = Double.MAX_VALUE;

        long startTime;
        long endTime;

        for (int k = 0; k < 30; k++) {
            sumTenInit = 0;
            sumTenSKGen = 0;
            sumTenRKGen = 0;
            sumTenEKGen = 0;
            sumTenDKGen = 0;
            sumTenEnc = 0;
            sumTenDec = 0;
            for (int i = 0; i < 10; i++) {

                startTime = System.currentTimeMillis();
                jpbcDemo.Setup(pairing);
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenInit += (endTime - startTime);

                System.out.println("init : " + (endTime - startTime));

                startTime = System.currentTimeMillis();
                jpbcDemo.SASetup();
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenSKGen += (endTime - startTime);


                startTime = System.currentTimeMillis();
                jpbcDemo.RASetup();
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenRKGen += (endTime - startTime);


                startTime = System.currentTimeMillis();
                jpbcDemo.EKGen(IDsByte);
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenEKGen += (endTime - startTime);


                startTime = System.currentTimeMillis();
                jpbcDemo.DKGen(IDr);
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenDKGen += (endTime - startTime);


                startTime = System.currentTimeMillis();
                jpbcDemo.Encryption(IDr, message, pairing, IDs);
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenEnc += (endTime - startTime);


                startTime = System.currentTimeMillis();
                byte[] decryptResult = jpbcDemo.Decryption(pairing, IDs);
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenDec += (endTime - startTime);

                if (message.equals(new String(decryptResult))) {
                    System.out.println("yes");
                } else {
                    System.out.println("no");
                }
                //assert message.equals(new String(decryptResult));
            }

            // 计算10次的平均
            averageTenInit = sumTenInit * 1.0 / 10.0;
            averageTenSKGen = sumTenSKGen * 1.0 / 10.0;
            averageTenRKGen = sumTenRKGen * 1.0 / 10.0;
            averageTenEKGen = sumTenEKGen * 1.0 / 10.0;
            averageTenDKGen = sumTenDKGen * 1.0 / 10.0;
            averageTenEnc = sumTenEnc * 1.0 / 10.0;
            averageTenDec = sumTenDec * 1.0 / 10.0;

            // 计算30次【10次的平均】的最大值
            maxAverageInit = Math.max(maxAverageInit, averageTenInit);
            maxAverageSKGen = Math.max(maxAverageSKGen, averageTenSKGen);
            maxAverageRKGen = Math.max(maxAverageRKGen, averageTenRKGen);
            maxAverageEKGen = Math.max(maxAverageEKGen, averageTenEKGen);
            maxAverageDKGen = Math.max(maxAverageDKGen, averageTenDKGen);
            maxAverageEnc = Math.max(maxAverageEnc, averageTenEnc);
            maxAverageDec = Math.max(maxAverageDec, averageTenDec);

            // 计算30次【10次的平均】的最小值
            minAverageInit = Math.min(minAverageInit, averageTenInit);
            minAverageSKGen = Math.min(minAverageSKGen, averageTenSKGen);
            minAverageRKGen = Math.min(minAverageRKGen, averageTenRKGen);
            minAverageEKGen = Math.min(minAverageEKGen, averageTenEKGen);
            minAverageDKGen = Math.min(minAverageDKGen, averageTenDKGen);
            minAverageEnc = Math.min(minAverageEnc, averageTenEnc);
            minAverageDec = Math.min(minAverageDec, averageTenDec);

            // 计算30次【10次的平均】的总和
            sumResultInit += averageTenInit;
            sumResultSKGen += averageTenSKGen;
            sumResultRKGen += averageTenRKGen;
            sumResultEKGen += averageTenEKGen;
            sumResultDKGen += averageTenDKGen;
            sumResultEnc += averageTenEnc;
            sumResultDec += averageTenDec;
        }

        // 计算30次【10次的平均】平均值
        averageResultInit = sumResultInit * 1.0 / 30.0;
        averageResultSKGen = sumResultSKGen * 1.0 / 30.0;
        averageResultRKGen = sumResultRKGen * 1.0 / 30.0;
        averageResultEKGen = sumResultEKGen * 1.0 / 30.0;
        averageResultDKGen = sumResultDKGen * 1.0 / 30.0;
        averageResultEnc = sumResultEnc * 1.0 / 30.0;
        averageResultDec = sumResultDec * 1.0 / 30.0;

        NumberFormat nformat = NumberFormat.getNumberInstance();
        nformat.setMaximumFractionDigits(2);

        System.out.println("init time : " + nformat.format(maxAverageInit) + ", " + nformat.format(minAverageInit) + ", " + nformat.format(averageResultInit));


        System.out.println("Sender-authority Setup time : " + nformat.format(maxAverageSKGen) + ", " + nformat.format(minAverageSKGen) + ", " + nformat.format(averageResultSKGen));


        System.out.println("Receiver-authority Setup time : " + nformat.format(maxAverageRKGen) + ", " + nformat.format(minAverageRKGen) + ", " + nformat.format(averageResultRKGen));


        System.out.println("encryption-key generation time : " + maxAverageEKGen + ", " + minAverageEKGen + ", " + averageResultEKGen);


        System.out.println("decryption-key generation time : " + maxAverageDKGen + ", " + minAverageDKGen + ", " + averageResultDKGen);


        System.out.println("encryption time : " + nformat.format(maxAverageEnc) + ", " + nformat.format(minAverageEnc) + ", " + nformat.format(averageResultEnc));


        System.out.println("decryption time : " + nformat.format(maxAverageDec) + ", " + nformat.format(minAverageDec) + ", " + nformat.format(averageResultDec));


        System.out.println("enc key : " + jpbcDemo.ek_S.getLengthInBytes() * 8);

        System.out.println("dec key : " + jpbcDemo.dk_1[1].getLengthInBytes() * 8 + jpbcDemo.dk_2[1].getLengthInBytes() * 8 + jpbcDemo.dk_3[1].getLengthInBytes() * 8);

        //System.out.println("message : " + jpbcDemo.ek_1.toBigInteger().bitCount());

        //System.out.println("ciphertext : " + jpbcDemo.ek_1.toBigInteger().bitCount());

        //System.out.println("ciphertext expansion : " + jpbcDemo.ek_1.toBigInteger().bitCount());
    }

    public void Setup(Pairing pairing) {
        G1 = pairing.getG1();
        Zr = pairing.getZr();
        g = G1.newRandomElement();
//        Element r = Zr.newRandomElement();
//        Element s = Zr.newRandomElement();
//        this.r = r;
//        this.s = s;
//        P = g.duplicate().powZn(this.r);
    }

    public void SKGen(String IDs) {
        this.IDs = IDs.getBytes();
        Element hashIDs = G1.newRandomElement();
        hashIDs.setFromHash(this.IDs, 0, this.IDs.length);
        this.ek_S = hashIDs.duplicate().powZn(gama);
    }

    public void RKGen(String[] ID_Rev) {
        this.dk_1 = new Element[this.d + 1];
        this.dk_2 = new Element[this.d + 1];
        this.dk_3 = new Element[this.d + 1];
        int i = 1;
        for (String ID_Rev_i : ID_Rev) {
            this.ID_Rev_i = ID_Rev_i.getBytes();
            Element hashID_Rev_i = G1.newRandomElement();
            hashID_Rev_i.setFromHash(this.ID_Rev_i, 0, this.ID_Rev_i.length);
            this.dk_1[i] = hashID_Rev_i.duplicate().powZn(this.theta);
            this.dk_2[i] = hashID_Rev_i.duplicate().powZn(this.gama);
            this.dk_3[i] = hashID_Rev_i.duplicate();
            i++;
        }
    }

    public void Encryption(String[] ID_Rev, String M, Pairing pairing, String IDs) {

        this.C4 = new Element[d];
        Element n_0 = Zr.newRandomElement();
        Element n_1 = Zr.newRandomElement();
        Element z = Zr.newRandomElement();
        Element v = Zr.newRandomElement();
//        Element T = g.duplicate().powZn(t);
//        Element U = g.duplicate().powZn(u);
//        this.T = T;
//        this.U = U;
        BigInteger[] k2 = new BigInteger[d];
        BigInteger[] v2 = new BigInteger[d];
        int i = 0;
        for (String ID_Rev_i : ID_Rev) {

            Element hashID_Rev_i = G1.newRandomElement();
            hashID_Rev_i.setFromHash(ID_Rev_i.getBytes(), 0, ID_Rev_i.getBytes().length);
            Element mu_Rev_i = pairing.pairing(hashID_Rev_i, this.P).duplicate().powZn(n_0);
            Element mu_Rev_i_2 = pairing.pairing(hashID_Rev_i, (this.ek_1.duplicate().mul(this.ek_2)).duplicate().mul(g.duplicate().powZn(n_1)));
            Element K_ID_Rev_i = pairing.getZr().newElement().setFromHash(mu_Rev_i.toBytes(), 0, mu_Rev_i.toBytes().length);
            Element V_ID_Rev_i = pairing.getZr().newElement().setFromHash(mu_Rev_i_2.toBytes(), 0, mu_Rev_i_2.toBytes().length);
//            PolyModElement<Element> polyModElement = new PolyModElement<Element>();
            k2[i] = Zr.getOrder().subtract(K_ID_Rev_i.negate().toBigInteger()).negate();
            v2[i] = Zr.getOrder().subtract(V_ID_Rev_i.negate().toBigInteger()).negate();

            this.C4[i] = hashID_Rev_i.duplicate().powZn(n_1).invert();
            i++;

        }
        /**
         * 计算多项式展开的参数
         */
        a[0] = k2[0];
        a[1] = BigInteger.valueOf(1);

        for (i = 1; i < d; i++) {
            a[i + 1] = BigInteger.valueOf(1);
            for (int j = i; j > 0; j--) {
                a[j] = a[j - 1].add(a[j].multiply(k2[i]));
            }
            a[0] = a[0].multiply(k2[i]);
        }
        //阶数为0的系数，即常数系数+z
        a[0] = a[0].add(z.duplicate().toBigInteger());
        /*************/

        /**
         * 计算多项式展开的参数
         */
        b[0] = v2[0];
        b[1] = BigInteger.valueOf(1);

        for (i = 1; i < d; i++) {
            b[i + 1] = BigInteger.valueOf(1);
            for (int j = i; j > 0; j--) {
                b[j] = b[j - 1].add(b[j].multiply(v2[i]));
            }
            b[0] = b[0].multiply(v2[i]);
        }
        //阶数为0的系数，即常数系数+n
        b[0] = b[0].add(v.duplicate().toBigInteger());
        /*************/


        String s1 = z.toString().concat(g.duplicate().powZn(n_0).toString());
        String s2 = v.toString().concat(g.duplicate().powZn(n_1).toString());
        BigInteger H_1_zU = new BigInteger(G1.newRandomElement().setFromHash(s1.getBytes(), 0, s1.getBytes().length).toBytes());
        BigInteger H_1_nT = new BigInteger(G1.newRandomElement().setFromHash(s2.getBytes(), 0, s2.getBytes().length).toBytes());
        BigInteger MInt = new BigInteger(M.getBytes());
        this.C1 = MInt.xor(H_1_zU).xor(H_1_nT);
        this.C2 = g.duplicate().powZn(n_0);
        this.IDs = IDs.getBytes();
        Element hashIDs = G1.newRandomElement();
        hashIDs.setFromHash(this.IDs, 0, this.IDs.length);
        this.C3 = ek_1.duplicate().mul(hashIDs.duplicate().powZn(n_1));
        this.C5 = g.duplicate().powZn(n_1);
    }

    public byte[] Decryption(Pairing pairing, String IDs) {
        Element z = Zr.newZeroElement();
        Element v = Zr.newZeroElement();
        for (int i = 1; i <= d; i++) {
            Element hashIDs = G1.newRandomElement();
            hashIDs.setFromHash(IDs.getBytes(), 0, IDs.getBytes().length);
            Element mu_Rev_i = pairing.pairing(this.C2, this.dk_1[i]);
            Element mu_Rev_i_2 = pairing.pairing(this.dk_2[i], Gama).mul(pairing.pairing(dk_3[i], C5)).mul(pairing.pairing(dk_3[i],C3)).mul(pairing.pairing(hashIDs,C4[i - 1]));
            Element K_ID_Rev_i = pairing.getZr().newElement().setFromHash(mu_Rev_i.toBytes(), 0, mu_Rev_i.toBytes().length);
            Element V_ID_Rev_i = pairing.getZr().newElement().setFromHash(mu_Rev_i_2.toBytes(), 0, mu_Rev_i_2.toBytes().length);
            z.add(K_ID_Rev_i.duplicate().powZn(Zr.newElement(i)).mul(a[i]));
            v.add(V_ID_Rev_i.duplicate().powZn(Zr.newElement(i)).mul(b[i]));
        }
        String s1 = z.toString().concat(C2.toString());
        String s2 = v.toString().concat(C5.toString());
        BigInteger H_1_zU = new BigInteger(G1.newRandomElement().setFromHash(s1.getBytes(), 0, s1.getBytes().length).toBytes());
        BigInteger H_1_nT = new BigInteger(G1.newRandomElement().setFromHash(s2.getBytes(), 0, s2.getBytes().length).toBytes());
        BigInteger MInt = this.C1.xor(H_1_zU).xor(H_1_nT);
        return MInt.toByteArray();
    }

    public void init(Pairing pairing) {
        G1 = pairing.getG1();
        Zr = pairing.getZr();
        g = G1.newRandomElement();
    }

    public void RASetup() {
        Element omega = Zr.newRandomElement();
        Element s = Zr.newRandomElement();
        this.rou = omega;
        this.gama = s;
        P = g.duplicate().powZn(this.rou);
        Gama = g.duplicate().powZn(this.gama);
    }

    public void SASetup() {
        Element k = Zr.newRandomElement();
        Element r = Zr.newRandomElement();
        this.k = k;
        this.theta = r;
        //P1 = S.duplicate().powZn(this.k);
        K = g.duplicate().powZn(this.k);
        Theta = g.duplicate().powZn(this.theta);
    }

    public void EKGen(byte[] IDs) {
        this.IDs = IDs;
        Element hashIDs = G1.newRandomElement();
        hashIDs.setFromHash(this.IDs, 0, this.IDs.length);
        ek_1 = hashIDs.duplicate().powZn(k);
        ek_2 = this.Gama.duplicate().powZn(this.theta);
    }

    public void DKGen(String[] ID_Rev) {
        this.dk_1 = new Element[this.d + 1];
        this.dk_2 = new Element[this.d + 1];
        this.dk_3 = new Element[this.d + 1];
        int i = 1;
        for (String ID_Rev_i : ID_Rev) {
            this.ID_Rev_i = ID_Rev_i.getBytes();
            Element hashID_Rev_i = G1.newRandomElement();
            hashID_Rev_i.setFromHash(this.ID_Rev_i, 0, this.ID_Rev_i.length);
            dk_1[i] = hashID_Rev_i.duplicate().powZn(rou);
            dk_2[i] = hashID_Rev_i.duplicate().powZn(gama);
            dk_3[i] = hashID_Rev_i;
            i++;
        }
    }

//    public void Encrypt(byte[] m, Pairing pairing) {
//        Element alphaIDs = Zr.newRandomElement();
//        Element gamaIDs = Zr.newRandomElement();
//
//        Element hashIDs = G1.newRandomElement();
//        hashIDs.setFromHash(this.IDs, 0, this.IDs.length);
//        Element hashIDr = G1.newRandomElement();
//        hashIDr.setFromHash(this.IDr, 0, this.IDr.length);
//
//        Element mu1 = pairing.pairing(hashIDr, P).duplicate().powZn(alphaIDs);
//        Element mu2 = pairing.pairing(hashIDr, (ek_1.duplicate().mul(ek_2)).duplicate().
//                mul((g.duplicate().powZn(gamaIDs))));
//        BigInteger H1mu1 = new BigInteger(mu1.toBytes());
//        BigInteger H1mu2 = new BigInteger(mu2.toBytes());
//        BigInteger mInt = new BigInteger(m);
//        C1 = mInt.xor(H1mu1).xor(H1mu2);
//        C2 = g.duplicate().powZn(alphaIDs);
//        C3 = ek_1.duplicate().mul(hashIDs.duplicate().powZn(gamaIDs));
//        C4 = hashIDr.duplicate().powZn(gamaIDs.negate());
//    }

//    public byte[] Decrypt(Pairing pairing) {
//        Element hashIDs = G1.newRandomElement();
//        hashIDs.setFromHash(this.IDs, 0, this.IDs.length);
//        Element rou1 = pairing.pairing(C2, dk1);
//        Element rou2 = pairing.pairing(dk2, Theta).mul(pairing.pairing(g, C4).negate()).mul(pairing.pairing(dk3, C3))
//                .mul(pairing.pairing(hashIDs, C4));
//        BigInteger mInt = C1.xor(new BigInteger(rou1.toBytes())).xor(new BigInteger(rou2.toBytes()));
//        return mInt.toByteArray();
//    }
}
