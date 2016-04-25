package br.com.softplan.ibraimgm.desafioJava8.suporte.strategy;

public class LightsaberStrategy implements WeaponStrategy {

        @Override
        public String shoot() {
                return "pssshhew!";
        }

}
