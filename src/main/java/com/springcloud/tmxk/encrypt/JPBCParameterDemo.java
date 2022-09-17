package com.springcloud.tmxk.encrypt;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;

public class JPBCParameterDemo {

    public static void main(String[] args) {
        int rBits = 160;
        int qBits = 512;
        TypeACurveGenerator typeACurveGenerator = new TypeACurveGenerator(rBits, qBits);
        PairingParameters pairingParameters = typeACurveGenerator.generate();
        Pairing pairing = PairingFactory.getPairing(pairingParameters);

        Field G1 = pairing.getG1();;
        Field Zr = pairing.getZr();
        Element element = G1.newRandomElement();
        System.out.println(element.getLengthInBytes());
    }
}
