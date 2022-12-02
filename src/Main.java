import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger count3 = new AtomicInteger(0);
        AtomicInteger count4 = new AtomicInteger(0);
        AtomicInteger count5 = new AtomicInteger(0);

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread aaa = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                boolean isaAa = true;
                char firstChar = texts[i].charAt(0);
                for (int j = 1; j < texts[i].length(); j++) {
                    if (texts[i].charAt(j) != firstChar) {
                        isaAa = false;
                        break;
                    }
                }
                if (isaAa) {
                    switch (texts[i].length()) {
                        case 3:
                            count3.getAndIncrement();
                            break;
                        case 4:
                            count4.getAndIncrement();
                            break;
                        case 5:
                            count5.getAndIncrement();

                    }
                }
            }

        });

        Thread palindrom = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                boolean isPalindrome = true;
                for (int j = 0; j < texts[i].length() / 2; j++) {
                    if (texts[i].charAt(j) != texts[i].charAt(texts[i].length() - j - 1)) {
                        isPalindrome = false;
                        break;
                    }

                }
                if (isPalindrome) {
                    switch (texts[i].length()) {
                        case 3:
                            count3.getAndIncrement();
                            break;
                        case 4:
                            count4.getAndIncrement();
                            break;
                        case 5:
                            count5.getAndIncrement();

                    }
                }
            }
        });


        Thread aBc = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                boolean isAbC = true;
                for (int j = 0; j < texts[i].length() - 1; j++) {
                    if (texts[i].charAt(j) > texts[i].charAt(j + 1)) {
                        isAbC = false;
                        break;
                    }
                }
                if (isAbC) {
                    switch (texts[i].length()) {
                        case 3:
                            count3.getAndIncrement();
                            break;
                        case 4:
                            count4.getAndIncrement();
                            break;
                        case 5:
                            count5.getAndIncrement();

                    }
                }
            }
        });
        aBc.start();
        aaa.start();
        palindrom.start();
        aBc.join();
        aaa.join();
        palindrom.join();


        System.out.println("Красивых слов с длиной 3: " + count3);
        System.out.println("Красивых слов с длиной 4: " + count4);
        System.out.println("Красивых слов с длиной 5: " + count5);
        ;


    }


    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}