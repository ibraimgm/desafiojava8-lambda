package br.com.softplan.ibraimgm.desafioJava8.exemplos;

import java.util.function.Consumer;

public class E03Scope {

        public static void main(String[] args) {
                new E03Scope().doMain();
        }

        private void doMain() {
                // classe anonima
                Consumer<String> anon = new Consumer<String>() {
                        @Override
                        public void accept(String t) {
                                System.out.println("'this' na " + t + ": " + toString());

                        }
                };

                // lambda
                Consumer<String> lambda = s -> System.out.println("'this' na " + s + ": " + toString());

                // você consegue adivinhar qual o resultado?
                anon.accept("Classe anônima");
                lambda.accept("Lambda");
        }

        @Override
        public String toString() {
                return "E04Scope";
        }
}
