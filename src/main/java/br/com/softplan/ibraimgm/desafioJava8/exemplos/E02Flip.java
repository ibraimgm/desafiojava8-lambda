package br.com.softplan.ibraimgm.desafioJava8.exemplos;

import java.util.function.Function;

import br.com.softplan.ibraimgm.desafioJava8.suporte.flipper.Flipper;

public class E02Flip {

        public static void main(String[] args) {
                /*
                 * 'Flipper' é uma interface funciona simples, representando uma
                 * funcao que recebe dois argumentos.
                 *
                 * Apesar de já existir uma interfave em java.util.function para
                 * funcoes com dois argumentos, criamos a nossa propria versao apenas
                 * para mostrar como é simples definir interfaces funcionais.
                 *
                 * Para deixar o exemplo mais interessante, criamos também os
                 * métodos 'flip' e 'curry' na nossa interface
                 */

                // primeiro, uma lambda normal
                Flipper<Integer, String, String> f = (i, s) -> i + " " + s;

                //execucao normal
                System.out.println(f.execute(12, "macacos"));

                // invertendo os parametros
                System.out.println(f.flip().execute("coisas que eu odeio em você", 10));

                // inverter duas vezes é o mesmo que nao fazer nada
                System.out.println(f.flip().flip().execute(17, "outra vez"));

                // 'curry' deixa o primeiro parâmetro da funcao com valor fixo
                Function<String, String> curried = f.curry(3);
                System.out.println(curried.apply("mosqueteiros"));
                System.out.println(curried.apply("patetas"));

                // é claro que podemos combinar as duas coisas!
                Function<Integer, String> garrafas = f.flip().curry("garrafas de bebida...");
                System.out.println(garrafas.apply(99));
                System.out.println(garrafas.apply(98));
                System.out.println(garrafas.apply(97));
        }
}
