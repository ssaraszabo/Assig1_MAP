import java.util.Arrays;

//ex1:
class UniNoten {

    public int[] nichtAusreichende(int[] noten) {
        return Arrays.stream(noten)
                .filter(note -> note < 40)
                .toArray();
    }

    public double Durchschnitt(int[] noten) {
        return Arrays.stream(noten)
                .average()
                .orElse(0.0);
    }

    public int[] abgerundeteNoten(int[] noten) {
        return Arrays.stream(noten)
                .map(this::rundNote)
                .toArray();
    }

    public int maxRundNote(int[] noten) {
        return Arrays.stream(noten)
                .map(this::rundNote)
                .max()
                .orElse(0);
    }

    // rotinjerea dupa regulile date:
    private int rundNote(int note) {
        if (note < 38) {
            return note;
        }
        int Vielfachen = ((note / 5) + 1) * 5;
        if (Vielfachen - note < 3) {
            return Vielfachen;
        }
        return note;
    }
}
    //ex2:
class Zahlen {
    public int maxZahl(int[] arr) {
        return Arrays.stream(arr).max().orElse(0);
    }

    public int minZahl(int[] arr) {
        return Arrays.stream(arr).min().orElse(0);
    }

    public int maxSumm(int[] arr){
        int totalSumm = Arrays.stream(arr).sum();
        int mini = minZahl(arr);
        return totalSumm - mini;
    }

    public int minSumm(int[] arr){
        int totalSumm = Arrays.stream(arr).sum();
        int maxi = maxZahl(arr);
        return totalSumm - maxi;
    }
}

class GroseZahlen{
    public int[] Summe(int[] arr1, int[] arr2){
        int n = arr1.length;
        int[] result = new int[n + 1]; //+1 für carry
        int carry = 0;
        for (int i = n - 1; i >= 0; i--) {
            int summe = arr1[i] + arr2[i] + carry;
            result[i + 1] = summe % 10;
            carry = summe / 10;
        }
        result[0] = carry;
        if (result[0] == 0) {
            return Arrays.copyOfRange(result, 1, result.length);
        }
        return result;
    }

    public int[] Differenz(int[] arr1, int[] arr2){
        int n = arr1.length;
        int[] result = new int[n];
        int carry = 0;
        for (int i = n - 1; i >= 0; i--) {
            int diff = arr1[i] - arr2[i] - carry;
            if (diff < 0) {
                diff += 10;
                carry = 1;
            } else {
                carry = 0;
            }
            result[i] = diff;
        }
        return result;
    }

    public int[] Multiplikation(int[] arr1, int x) {
        int n = arr1.length;
        int[] result = new int[n + 1];
        int carry = 0;
        for (int i = n - 1; i >= 0; i--) {
            int produkt = arr1[i] * x + carry;
            result[i + 1] = produkt % 10;
            carry = produkt / 10;
        }
        result[0] = carry;
        if (result[0] == 0) {
            return Arrays.copyOfRange(result, 1, result.length);
        }
        return result;
    }

    public int[] Division(int[] arr1, int x) {
        int n = arr1.length;
        int[] result = new int[n];
        int rest = 0;
        for (int i = 0; i < n; i++) {
            int current = rest * 10 + arr1[i];
            result[i] = current / x;
            rest = current % x;
        }
        int beginnenIndex = 0;
        while (beginnenIndex < n - 1 && result[beginnenIndex] == 0) {
            beginnenIndex++;
        }
        return Arrays.copyOfRange(result, beginnenIndex, result.length);
    }
}

class Elektronik{
    public int Billigste(int[] arr){
        return Arrays.stream(arr).min().orElse(0);
    }

    public int TeursteItem(int[] keyboard, int[] usb){
        int x = Arrays.stream(keyboard).max().getAsInt();
        int y = Arrays.stream(usb).max().getAsInt();
        return Math.max(x, y);
    }

    public int TeuersteUsb(int[] usb, int budget) {
        return Arrays.stream(usb)
                .filter(preis -> preis <= budget)
                .max()
                .orElse(0);
    }

    public int MaxGeldbetrag(int budget, int[] keyboard, int[] usb) {
        int maxBetrag = -1;
        for (int keyboardPreis : keyboard) {
            for (int usbPreis : usb) {
                int total = keyboardPreis + usbPreis;
                if (total <= budget) {
                    maxBetrag = Math.max(maxBetrag, total);
                }
            }
        }
        return maxBetrag;
    }
}

public class Main{

    public static void main(String[] args) {
        UniNoten universitatNoten = new UniNoten();
        Zahlen zahlList = new Zahlen();
        GroseZahlen groseZahlen = new GroseZahlen();
        Elektronik elektronik = new Elektronik();

        int[] noten = {84, 29, 57, 38, 62, 73, 99, 43};
        int[] arr2 = {4, 8, 3, 10, 17};
        int[] z1 = {1, 3, 0, 0, 0, 0, 0, 0, 0};
        int[] z2 = {8, 7, 0, 0, 0, 0, 0, 0, 0};
        int x = 2;
        int[] keyboards = {40, 35, 70, 15, 45};
        int[] usb = {20, 15, 40, 15, 34};
        int budget = 45;
        //1:
        System.out.println("Nicht ausreichende Noten: " + Arrays.toString(universitatNoten.nichtAusreichende(noten)));
        System.out.println("Durchschnittswert der Noten: " + universitatNoten.Durchschnitt(noten));
        System.out.println("Abgerundete Noten: " + Arrays.toString(universitatNoten.abgerundeteNoten(noten)));
        System.out.println("Maximale abgerundete Note: " + universitatNoten.maxRundNote(noten));

        System.out.println();
        //2:
        System.out.println("Großest Zahl: " + zahlList.maxZahl(arr2));
        System.out.println("Kleinest Zahl: " + zahlList.minZahl(arr2));
        System.out.println("Maximale Summe: " + zahlList.maxSumm(arr2));
        System.out.println("Maximale Summe: " + zahlList.minSumm(arr2));

        System.out.println();
        //3:
        System.out.println("Summe: " + Arrays.toString(groseZahlen.Summe(z1, z2)));
        System.out.println("Differenz: " + Arrays.toString(groseZahlen.Differenz(z1, z2)));
        System.out.println("Multiplication: " + Arrays.toString(groseZahlen.Multiplikation(z1, x)));
        System.out.println("Division: " + Arrays.toString(groseZahlen.Division(z2, x)));

        System.out.println();
        //4:
        System.out.println("Billigste Keyboard: " + elektronik.Billigste(keyboards));
        System.out.println("Teurste Item: " + elektronik.TeursteItem(keyboards, usb));
        System.out.println("Teurste USB: " + elektronik.TeuersteUsb(usb, budget));
        System.out.println("Maximale Geldbetrag: " + elektronik.MaxGeldbetrag(budget, keyboards, usb));
    }
}

