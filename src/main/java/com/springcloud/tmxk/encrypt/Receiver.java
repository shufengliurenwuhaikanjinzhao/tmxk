import java.io.File;
import java.util.Random;
import it.unisa.dia.gas.jpbc.*;
import it.unisa.dia.gas.jpbc.Polynomial;
import it.unisa.dia.gas.plaf.jpbc.field.poly.PolyModElement;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;

public class Receiver {
    Pairing pairing;
    Field Zr;
    public final int K = 2;         //多项式的数量，最高阶数
    Element f2[] = new Element[K];
    Element f2ratio[] = new Element[K + 1];       //各阶系数b
    //Random r = new Random();

    public void initialF() {            //n为a能取的上限，此处a的值都是随机的，可改为固定值
        for (int i = 0; i < f2.length; i++) {
            f2[i] = Zr.newZeroElement().add(Zr.newElement(-2));
            f2ratio[i] = Zr.newZeroElement();
        }
        f2ratio[f2ratio.length - 1] = Zr.newZeroElement();
    }

    int tempLayer = 0;
    Element tempRatio;
    int tempX = 0;                           //当前层x的阶

    void dealF2Ratio(boolean flag) {
        if (tempLayer == K - 1) {
            if (flag == true) {
                //f2ratio[tempX + 1] += tempRatio;
                f2ratio[tempX + 1].add(tempRatio);
            } else {
                //f2ratio[tempX] += tempRatio * f2[tempLayer];
                f2ratio[tempX].add(tempRatio.duplicate().mul(f2[tempLayer]));
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
            tempRatio.mul(f2[tempLayer - 1]);
            dealF2Ratio(true);
            dealF2Ratio(false);
            //tempRatio = tempRatio / f2[tempLayer - 1];
            tempRatio.div(f2[tempLayer - 1]);
        }
        tempLayer--;
    }

    public static void main(String[] args) {
        int rBits = 160;
        int qBits = 512;
        TypeACurveGenerator typeACurveGenerator = new TypeACurveGenerator(rBits, qBits);
        PairingParameters pairingParameters = typeACurveGenerator.generate();
        Pairing pairing = PairingFactory.getPairing(pairingParameters);
        Field f = pairing.getZr();

        Element test = f.newZeroElement();
        System.out.println(f.newElement(-1).sign());
        System.out.println(f.getOrder());

        Receiver receiver = new Receiver();
        receiver.pairing = pairing;
        receiver.Zr = pairing.getZr();
        receiver.tempRatio = receiver.Zr.newOneElement();
        receiver.initialF();
        receiver.dealF2Ratio(true);
        receiver.dealF2Ratio(false);
        for (Element a :
                receiver.f2) {
            System.out.println(a);
        }
        int i = 0;
        for (Element a : receiver.f2ratio
        ) {
            System.out.println("x的阶数为" + (i++) + "的系数为:" + a);
        }
    }
}