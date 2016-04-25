package br.com.softplan.ibraimgm.desafioJava8.exemplos;

import java.util.function.Function;

import br.com.softplan.ibraimgm.desafioJava8.suporte.strategy.LambdaActionHero;
import br.com.softplan.ibraimgm.desafioJava8.suporte.strategy.LaserStrategy;
import br.com.softplan.ibraimgm.desafioJava8.suporte.strategy.LightsaberStrategy;
import br.com.softplan.ibraimgm.desafioJava8.suporte.strategy.MachineGunStrategy;
import br.com.softplan.ibraimgm.desafioJava8.suporte.strategy.RegularActionHero;
import br.com.softplan.ibraimgm.desafioJava8.suporte.strategy.RocketLauncherStrategy;
import br.com.softplan.ibraimgm.desafioJava8.suporte.strategy.WeaponStrategy;

public class E01Strategy {

        public static void main(String[] args) {
                /*
                 * Antes de mais nada, vamos criar um novo herói de cinema (RegularActionHero),
                 * que, na melhor tradição dos anos 80, só sabe atirar.
                 *
                 * Como qualquer herói da época, uma vasta seleção de armas está disponível,
                 * e para simular o arsenal usamos uma pattern clássica: Strategy.
                 *
                 * Temos uma interface que define o que é uma arma (WeaponStrategy) e várias
                 * armas implementadas (LaserStrategy, MachineGunStrategy e RocketLauncherStrategy),
                 * então é só mudar a arma para mudar o comportamento do herói.
                 *
                 * Vamos lá!!!
                 */
                RegularActionHero stallone = new RegularActionHero("Stallone");

                // vamos testar o laser...
                stallone.setWeapon(new LaserStrategy());
                stallone.shoot();

                // ...e a metralhadora
                stallone.setWeapon(new MachineGunStrategy());
                stallone.shoot();

                // mas a diversão mesmo é explodir tudo!!!
                stallone.setWeapon(new RocketLauncherStrategy());
                stallone.shoot();

                // podemos até criar uma arma ad-hoc!
                stallone.setWeapon(new WeaponStrategy() {
                        @Override
                        public String shoot() {
                                return "*thump*"; // hehe, silenciador!
                        }
                });
                stallone.shoot();

                /*
                 * Apesar de funcionar bem, em certos casos (como este) a interface é muito
                 * simples, mas mesmo assim somos obrigados a criar uma estrutura muito grande
                 * (1 interface, 3 implementacoes, além da classe anônima). Além disso, se
                 * quisermos combinar armas ou efeitos, teremos que ou criar novas classes ou
                 * aumentar bastante a complexidade das existentes.
                 *
                 * Solução: lambdas!
                 */
                LambdaActionHero arnold = new LambdaActionHero("Arnold");

                // nada melhor do que uma shotgun para 'exterminar' os inimigos!
                // repare no som ao recarregar a arma e no fato de que nem precisamos usar
                // o parâmetro
                Function<String, String> shotgun = s -> s + "BAM! *schklikt, klikt* ";
                arnold.setWeapon(shotgun);
                arnold.shoot();

                // como robos do futuro sao bem resistentes, o ideal seria que nosso
                // heroi aproveitasse os dois canos da shotgun e atirasse duas vezes.
                // é simples: basta combinar duas funcoes! (no caso, com ela mesma!)
                //
                // Repare a entrada de uma funcao tem que bater com a saida de outra,
                // o que nao é um problema no nosso caso
                shotgun = shotgun.andThen(shotgun);
                arnold.setWeapon(shotgun);
                arnold.shoot();

                // uma caracteristica essencial de um heroi é lancar uma frase de
                // efeito antes de 'exterminar' seu inimigo de uma vez por todas.
                //
                // como a frase deve ser dita ANTES do tiro, combinamos duas funcoes,
                // mas desta vez usando 'compose':
                shotgun = shotgun.compose(s -> "Hasta la vista, baby! ");
                arnold.setWeapon(shotgun);
                arnold.shoot();

                /*
                 * Não é só a pattern 'strategy' que em alguns casos pode ser substituída por
                 * expressões lambda. Na verdade, uma grande parte das patterns existe apenas
                 * para passar um comportamento (ou seja, uma funcao) de um lado a outro do
                 * sistema, o que faz com que muitos casos possam ser simplificados com a
                 * inclusão das lambdas.
                 *
                 * Como exemplo, vejamos um caso em que a pattern 'Adapter' simplesmente
                 * desaparece (precisamos de uma Function<String, String>, mas temos uma
                 * WeaponStrategy em mãos):
                 */
                LambdaActionHero luke = new LambdaActionHero("Luke");

                // assim como no filme, nosso heroi tem que montar a propria arma!
                final WeaponStrategy oldSaber = new LightsaberStrategy();
                Function<String, String> lightsaber = s -> oldSaber.shoot();

                //o imperio nao tem chance!
                luke.setWeapon(lightsaber);
                luke.shoot();
        }
}
