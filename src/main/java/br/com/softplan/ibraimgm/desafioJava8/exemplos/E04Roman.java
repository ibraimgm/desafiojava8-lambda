package br.com.softplan.ibraimgm.desafioJava8.exemplos;

import java.util.function.Function;

import br.com.softplan.ibraimgm.desafioJava8.suporte.roman.RomanNumeralConverter;

public class E04Roman {

        private static final int PAGTO_A_VISTA = 5;
        private static final Double PERCENTUAL_DESCONTO = 25.0;
        private static final int CUMPOM_DESCONTO = 24;
        private static final int FRETE = 42;

        public static void main(String[] args) {
                /*
                 * Paulus Augustus, pretor romano, fez uma compra de alguns ebooks
                 * em uma famosa livraria online.
                 *
                 * Sabendo que o total dos itens é MCMLXXXV, ajude Paulus a calcular
                 * o total da compra, que deverá seguir a fórmula (nesta ordem):
                 * - Paulus pagará a vista, entao será descontado um valor to total (PAGTO_A_VISTA)
                 * - Por ser cliente VIP MasterPlus Ancient Gold, Paulus terá desconto (PERCENTUAL_DESCONTO)
                 * - Paulus usará na compra um cumpom de desconto que ganhou de presente (CUMPOM_DESCONTO)
                 * - Por fim, o valor do frete expresso para roma será incluído no total (FRETE)
                 *
                 * Como bem sabemos, 'em Roma, faça como os romanos', então é essencial formatar o número
                 * em uma notação familiar a Paulus - numerais romanos.
                 */

                // Para resolver o problema, vamos compor várias funcoes.
                // Nao é necessário usar variaveis neste caso, usaremos apenas para facilitar a leitura

                // por sorte, as funcoes de conversao ja existem
                Function<String, Integer> romanoParaInt = RomanNumeralConverter::romanoParaInteiro;
                Function<Integer, String> inteiroParaRomano = RomanNumeralConverter::inteiroParaRomano;

                // mas os passos do calculo precisamos criar
                Function<Integer, Integer> descontoAVista = i -> i - PAGTO_A_VISTA;
                Function<Integer, Integer> descontoVIP = i -> (int)(i * (1.0 - PERCENTUAL_DESCONTO / 100));
                Function<Integer, Integer> cupomDesconto = i -> i - CUMPOM_DESCONTO;
                Function<Integer, Integer> frete = i -> i + FRETE;

                // Opção 1: Usando compose
                Function<String, String> f1 = inteiroParaRomano        // converto o valor para romano
                                              .compose(frete)          // que teve o frete acrescido
                                              .compose(cupomDesconto)  // ao valor descontado pelo cupom
                                              .compose(descontoVIP)    // que teve desconto vip
                                              .compose(descontoAVista) // no pagamento à vista
                                              .compose(romanoParaInt); // e foi convertido de numeral romano

                // Opção 2: Usando andThen
                Function<String, String> f2 = romanoParaInt                // converto para inteiro
                                              .andThen(descontoAVista)     // subtraio o valor de pagamento à vista
                                              .andThen(descontoVIP)        // aplico o desconto vip
                                              .andThen(cupomDesconto)      // desconto o valor do cupom
                                              .andThen(frete)              // adiciono o frete
                                              .andThen(inteiroParaRomano); // e converto de volta para romano

                /*
                 * Qual das duas é mais legível?
                 *
                 * Em casos (como desse exemplo), que a ideia é seguir um passo a passo, geralmente o
                 * 'andThen' fica mais simples de entender. No entanto, existem casos em que o compose
                 * fica mais proximo da linguagem natural.
                 * Exemplo:
                 * Function<Boolean, Boolean> not = b -> !b; // vamos fingir que o 'negate' nao existe
                 * Function<Integer, Boolean> even = i -> i % 2 == 0;
                 * Function<Integer, Boolean> odd = not.compose(even); // leia as funcoes em voz alta
                 *
                 * No fim das contas, é uma boa dose de preferência pessoal e bom senso.
                 */
                final String totalItens = "MCMLXXXV";
                System.out.println(f1.apply(totalItens));
                System.out.println(f2.apply(totalItens));
        }

}
