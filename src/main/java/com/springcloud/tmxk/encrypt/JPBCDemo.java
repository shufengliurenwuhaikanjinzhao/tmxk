import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;

import java.math.BigInteger;

/**
 * @author 12551
 */
public class JPBCDemo {

    Field G1;
    Field Zr;
    Element g;

    byte[] IDr;
    byte[] IDs;

    byte[] rou;
    //mpk_R
    Element P;
    Element S;
    //mpk_S
    //Element P1;
    Element K;
    Element R;
    //msk_R
    Element omega;
    Element s;
    //msk_S
    Element k;
    Element r;

    //ek_IDs
    Element ek_1;
    Element ek_2;
    //dk_IDr
    Element dk1;
    Element dk2;
    Element dk3;

    //ciphertext
    BigInteger C1;
    Element C2;
    Element C3;
    Element C4;
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
        String IDr = "456";
        byte[] messageByte = message.getBytes();
        byte[] IDsByte = IDs.getBytes();
        byte[] IDrByte = IDr.getBytes();
        JPBCDemo jpbcDemo = new JPBCDemo();

        double averageResultInit, averageResultRASetup, averageResultSASetup, averageResultEKGen, averageResultDKGen, averageResultEnc, averageResultDec;
        double averageTenInit, averageTenRASetup, averageTenSASetup, averageTenEKGen, averageTenDKGen, averageTenEnc, averageTenDec;
        double sumResultInit = 0, sumResultRASetup = 0, sumResultSASetup = 0, sumResultEKGen = 0, sumResultDKGen = 0, sumResultEnc = 0, sumResultDec = 0;
        long sumTenInit = 0, sumTenRASetup = 0, sumTenSASetup = 0, sumTenEKGen = 0, sumTenDKGen = 0, sumTenEnc = 0, sumTenDec = 0;
        double maxAverageInit = 0, maxAverageRASetup = 0, maxAverageSASetup = 0, maxAverageEKGen = 0, maxAverageDKGen = 0, maxAverageEnc = 0, maxAverageDec = 0;
        double minAverageInit = Double.MAX_VALUE, minAverageRASetup = Double.MAX_VALUE, minAverageSASetup = Double.MAX_VALUE, minAverageEKGen = Double.MAX_VALUE,
                minAverageDKGen = Double.MAX_VALUE, minAverageEnc = Double.MAX_VALUE, minAverageDec = Double.MAX_VALUE;

        long startTime;
        long endTime;

        for(int k = 0 ; k < 30 ; k++) {
            sumTenInit = 0;
            sumTenRASetup = 0;
            sumTenSASetup = 0;
            sumTenEKGen = 0;
            sumTenDKGen = 0;
            sumTenEnc = 0;
            sumTenDec = 0;
            for(int i = 0; i < 10; i++) {

                startTime = System.currentTimeMillis();
                jpbcDemo.init(pairing);
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenInit += (endTime - startTime);

                System.out.println("init : " + (endTime - startTime));

                startTime = System.currentTimeMillis();
                jpbcDemo.RASetup();
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenRASetup += (endTime - startTime);


                startTime = System.currentTimeMillis();
                jpbcDemo.SASetup();
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenSASetup += (endTime - startTime);


                startTime = System.currentTimeMillis();
                jpbcDemo.EKGen(IDsByte);
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenEKGen += (endTime - startTime);


                startTime = System.currentTimeMillis();
                jpbcDemo.DKGen(IDrByte);
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenDKGen += (endTime - startTime);


                startTime = System.currentTimeMillis();
                jpbcDemo.Encrypt(messageByte, pairing);
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenEnc += (endTime - startTime);


                startTime = System.currentTimeMillis();
                byte[] decryptResult = jpbcDemo.Decrypt(pairing);
                endTime = System.currentTimeMillis();
                // 计算10次的总和
                sumTenDec += (endTime - startTime);

                if (message.equals(new String(decryptResult))) {
                    System.out.println("yes");
                } else {
                    System.out.println("no");
                }
                assert message.equals(new String(decryptResult));
            }

            // 计算10次的平均
            averageTenInit = sumTenInit * 1.0 / 10;
            averageTenRASetup = sumTenRASetup * 1.0 / 10;
            averageTenSASetup = sumTenSASetup * 1.0 / 10;
            averageTenEKGen = sumTenEKGen * 1.0 / 10;
            averageTenDKGen = sumTenDKGen * 1.0 / 10;
            averageTenEnc = sumTenEnc * 1.0 / 10;
            averageTenDec = sumTenDec * 1.0 / 10;

            // 计算30次【10次的平均】的最大值
            maxAverageInit = Math.max(maxAverageInit, averageTenInit);
            maxAverageRASetup = Math.max(maxAverageRASetup, averageTenRASetup);
            maxAverageSASetup = Math.max(maxAverageSASetup, averageTenSASetup);
            maxAverageEKGen = Math.max(maxAverageEKGen, averageTenEKGen);
            maxAverageDKGen = Math.max(maxAverageDKGen, averageTenDKGen);
            maxAverageEnc = Math.max(maxAverageEnc, averageTenEnc);
            maxAverageDec = Math.max(maxAverageDec, averageTenDec);

            // 计算30次【10次的平均】的最小值
            minAverageInit = Math.min(minAverageInit, averageTenInit);
            minAverageRASetup = Math.min(minAverageRASetup, averageTenRASetup);
            minAverageSASetup = Math.min(minAverageSASetup, averageTenSASetup);
            minAverageEKGen = Math.min(minAverageEKGen, averageTenEKGen);
            minAverageDKGen = Math.min(minAverageDKGen, averageTenDKGen);
            minAverageEnc = Math.min(minAverageEnc, averageTenEnc);
            minAverageDec = Math.min(minAverageDec, averageTenDec);

            // 计算30次【10次的平均】的总和
            sumResultInit += averageTenInit;
            sumResultRASetup += averageTenRASetup;
            sumResultSASetup += averageTenSASetup;
            sumResultEKGen += averageTenEKGen;
            sumResultDKGen += averageTenDKGen;
            sumResultEnc += averageTenEnc;
            sumResultDec += averageTenDec;
        }

        // 计算30次【10次的平均】平均值
        averageResultInit = sumResultInit * 1.0 / 30;
        averageResultRASetup = sumResultRASetup * 1.0 / 30;
        averageResultSASetup = sumResultSASetup * 1.0 / 30;
        averageResultEKGen = sumResultEKGen * 1.0 / 30;
        averageResultDKGen = sumResultDKGen * 1.0 / 30;
        averageResultEnc = sumResultEnc * 1.0 / 30;
        averageResultDec = sumResultDec * 1.0 / 30;

        System.out.println("init time : " + maxAverageInit + ", " + minAverageInit + ", " + averageResultInit);


        System.out.println("receiver-authority setup time : " + maxAverageRASetup + ", " + minAverageRASetup + ", " + averageResultRASetup);


        System.out.println("sender-authority setup time : " + maxAverageSASetup + ", " + minAverageSASetup + ", " + averageResultSASetup);


        System.out.println("encryption-key generation time : " + maxAverageEKGen + ", " + minAverageEKGen + ", " + averageResultEKGen);


        System.out.println("decryption-key generation time : " + maxAverageDKGen + ", " + minAverageDKGen + ", " + averageResultDKGen);


        System.out.println("encryption time : " + maxAverageEnc + ", " + minAverageEnc + ", " + averageResultEnc);


        System.out.println("decryption time : " + maxAverageDec + ", " + minAverageDec + ", " + averageResultDec);


        System.out.println("enc key : " + jpbcDemo.ek_1.getLengthInBytes() * 8);

        System.out.println("dec key : " + jpbcDemo.dk1.getLengthInBytes() * 8 + jpbcDemo.dk2.getLengthInBytes() * 8 + jpbcDemo.dk3.getLengthInBytes() * 8);

        //System.out.println("message : " + jpbcDemo.ek_1.toBigInteger().bitCount());

        //System.out.println("ciphertext : " + jpbcDemo.ek_1.toBigInteger().bitCount());

        //System.out.println("ciphertext expansion : " + jpbcDemo.ek_1.toBigInteger().bitCount());
    }

    public void init(Pairing pairing) {
        G1 = pairing.getG1();
        Zr = pairing.getZr();
        g = G1.newRandomElement();
    }

    public void RASetup() {
        Element omega = Zr.newRandomElement();
        Element s = Zr.newRandomElement();
        this.omega = omega;
        this.s = s;
        P = g.duplicate().powZn(this.omega);
        S = g.duplicate().powZn(this.s);
    }

    public void SASetup() {
        Element k = Zr.newRandomElement();
        Element r = Zr.newRandomElement();
        this.k = k;
        this.r = r;
        //P1 = S.duplicate().powZn(this.k);
        K = g.duplicate().powZn(this.k);
        R = g.duplicate().powZn(this.r);
    }

    public void EKGen(byte[] IDs) {
        this.IDs = IDs;
        Element hashIDs = G1.newRandomElement();
        hashIDs.setFromHash(this.IDs, 0, this.IDs.length);
        ek_1 = hashIDs.duplicate().powZn(k);
        ek_2 = this.S.duplicate().powZn(this.r);
    }

    public void DKGen(byte[] IDr) {
        this.IDr = IDr;
        Element t = Zr.newRandomElement();
        Element hashIDr = G1.newRandomElement();
        hashIDr.setFromHash(this.IDr, 0, this.IDr.length);
        dk1 = hashIDr.duplicate().powZn(omega);
        dk2 = hashIDr.duplicate().powZn(s);
        dk3 = hashIDr;
    }

    public void Encrypt(byte[] m, Pairing pairing) {
        Element alphaIDs = Zr.newRandomElement();
        Element gamaIDs = Zr.newRandomElement();

        Element hashIDs = G1.newRandomElement();
        hashIDs.setFromHash(this.IDs, 0, this.IDs.length);
        Element hashIDr = G1.newRandomElement();
        hashIDr.setFromHash(this.IDr, 0, this.IDr.length);

        Element mu1 = pairing.pairing(hashIDr, P).duplicate().powZn(alphaIDs);
        Element mu2 = pairing.pairing(hashIDr, (ek_1.duplicate().mul(ek_2)).duplicate().
                mul((g.duplicate().powZn(gamaIDs))));
        BigInteger H1mu1 = new BigInteger(mu1.toBytes());
        BigInteger H1mu2 = new BigInteger(mu2.toBytes());
        BigInteger mInt = new BigInteger(m);
        C1 = mInt.xor(H1mu1).xor(H1mu2);
        C2 = g.duplicate().powZn(alphaIDs);
        C3 = ek_1.duplicate().mul(hashIDs.duplicate().powZn(gamaIDs));
        C4 = hashIDr.duplicate().powZn(gamaIDs.negate());
    }

    public byte[] Decrypt(Pairing pairing) {
        Element hashIDs = G1.newRandomElement();
        hashIDs.setFromHash(this.IDs, 0, this.IDs.length);
        Element rou1 = pairing.pairing(C2, dk1);
        Element rou2 = pairing.pairing(dk2, R).mul(pairing.pairing(g, C4).negate()).mul(pairing.pairing(dk3, C3))
                .mul(pairing.pairing(hashIDs, C4));
        BigInteger mInt = C1.xor(new BigInteger(rou1.toBytes())).xor(new BigInteger(rou2.toBytes()));
        return mInt.toByteArray();
    }
}
