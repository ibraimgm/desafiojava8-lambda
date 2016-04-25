package br.com.softplan.ibraimgm.desafioJava8.suporte.roman;

public final class RomanNumeralConverter {

        public static int romanoParaInteiro(String romanNumber) {
                if (romanNumber.isEmpty())
                        return 0;

                // um ou dois digitos de uma vez
                int value = 0;
                String one = romanNumber.substring(0, 1);
                String two = romanNumber.substring(0, Math.min(2, romanNumber.length()));

                // primeiro, resolvemos os dois digitos
                switch (two) {
                        case "CM": value = 900; break;
                        case "CD": value = 400; break;
                        case "XC": value = 90; break;
                        case "XL": value = 40; break;
                        case "IX": value = 9; break;
                        case "IV": value = 4; break;
                }

                if (value > 0)
                        return value + romanoParaInteiro(romanNumber.substring(2));

                // se chegamos ate aqui, é pra ser apenas de um digito
                switch (one) {
                        case "M": value = 1000; break;
                        case "D": value = 500; break;
                        case "C": value = 100; break;
                        case "L": value = 50; break;
                        case "X": value = 10; break;
                        case "V": value = 5; break;
                        case "I": value = 1; break;
                }

                if (value > 0)
                        return value + romanoParaInteiro(romanNumber.substring(1));

                // se chegamos ate este ponto, tem alguma coisa muito errada...
                throw new RuntimeException("Número romano em formato incorreto!");
        }

        public static String inteiroParaRomano(int numero) {

                if ((numero <= 0) || (numero >= 5000))
                        throw new RuntimeException("Número fora dos limites suportados");

                StringBuilder sb = new StringBuilder();

                while (numero > 0) {
                        if (numero >= 1000) {
                                numero -= 1000;
                                sb.append("M");
                        } else if (numero >= 900) {
                                numero -= 900;
                                sb.append("CM");
                        } else if (numero >= 500) {
                                numero -= 500;
                                sb.append("D");
                        } else if (numero >= 400) {
                                numero -= 400;
                                sb.append("CD");
                        } else if (numero >= 100) {
                                numero -= 100;
                                sb.append("C");
                        } else if (numero >= 90) {
                                numero -= 90;
                                sb.append("XC");
                        } else if (numero >= 50) {
                                numero -= 50;
                                sb.append("L");
                        } else if (numero >= 40) {
                                numero -= 40;
                                sb.append("XL");
                        } else if (numero >= 10) {
                                numero -= 10;
                                sb.append("X");
                        } else if (numero >= 9) {
                                numero -= 9;
                                sb.append("IX");
                        } else if (numero >= 5) {
                                numero -= 5;
                                sb.append("V");
                        } else if (numero >= 4) {
                                numero -= 4;
                                sb.append("IV");
                        } else if (numero >= 1) {
                                numero -= 1;
                                sb.append("I");
                        };
                }

                return sb.toString();
        }

}
