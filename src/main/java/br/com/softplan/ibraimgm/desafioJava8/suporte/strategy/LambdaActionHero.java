package br.com.softplan.ibraimgm.desafioJava8.suporte.strategy;

import java.util.function.Function;

public class LambdaActionHero {

        private String name;
        private Function<String, String> weapon;

        public LambdaActionHero(String name) {
                super();
                this.name = name;
        }

        public void shoot() {
                System.out.println("[" + name + "] " + weapon.apply(""));
        }

        public void setWeapon(Function<String, String> weapon) {
                this.weapon = weapon;
        }

}
