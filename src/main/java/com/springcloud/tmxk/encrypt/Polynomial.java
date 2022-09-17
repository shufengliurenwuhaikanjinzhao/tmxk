package com.springcloud.tmxk.encrypt;

import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;

import java.math.BigInteger;
import java.util.Random;

public class Polynomial {
    public final int K = 5;         //多项式的数量，最高阶数
    BigInteger f2[] = new BigInteger[K];
    BigInteger f2ratio[] = new BigInteger[K + 1];       //各阶系数b
    Random r = new Random();

    public void initialF(BigInteger[] f) {            //n为a能取的上限，此处a的值都是随机的，可改为固定值
        for (int i = 0; i < f2.length; i++) {
            f2[i] = f[i];
            f2ratio[i] = BigInteger.valueOf(0);
        }
        f2ratio[f2ratio.length - 1] = BigInteger.valueOf(0);
    }

    int tempLayer = 0;
    BigInteger tempRatio = BigInteger.valueOf(1);
    int tempX = 0;                           //当前层x的阶

    void dealF2Ratio(boolean flag) {
        if (tempLayer == K - 1) {
            if (flag == true) {
                //f2ratio[tempX + 1] += tempRatio;
                f2ratio[tempX + 1] = f2ratio[tempX + 1].add(tempRatio);
            } else {
                //f2ratio[tempX] += tempRatio * f2[tempLayer];
                f2ratio[tempX] = f2ratio[tempX].add(tempRatio.multiply(f2[tempLayer]));
            }
            return;
        }
        tempLayer++;
        if (flag == true) {
            tempX++;
            dealF2Ratio(true);
            dealF2Ratio(false);
            tempX--;
        } else {
            //tempRatio = tempRatio * f2[tempLayer - 1];
            tempRatio = tempRatio.multiply(f2[tempLayer - 1]);
            dealF2Ratio(true);
            dealF2Ratio(false);
            //tempRatio = tempRatio / f2[tempLayer - 1];
            tempRatio = tempRatio.divide(f2[tempLayer - 1]);
        }
        tempLayer--;
    }

    public static void main(String[] args) {
        int rBits = 160;
        int qBits = 512;
        TypeACurveGenerator typeACurveGenerator = new TypeACurveGenerator(rBits, qBits);
        PairingParameters pairingParameters = typeACurveGenerator.generate();
        Pairing pairing = PairingFactory.getPairing(pairingParameters);
        Field field = pairing.getZr();
        BigInteger[] f2 = new BigInteger[2];
        for(int i = 0; i < f2.length; i++) {
            f2[i] = field.getOrder().subtract(field.newElement(10).negate().toBigInteger()).negate();
            //System.out.println(field.getOrder().subtract(field.newElement(10).negate().toBigInteger()).negate());
        }
        Polynomial receiver = new Polynomial();
        receiver.initialF(f2);
        receiver.dealF2Ratio(true);
        receiver.dealF2Ratio(false);
        for (BigInteger a :
                receiver.f2) {
            System.out.println(a);
        }
        int i = 0;
        for (BigInteger a : receiver.f2ratio
        ) {
            System.out.println("x的阶数为" + (i++) + "的系数为:" + a);
        }
    }
}