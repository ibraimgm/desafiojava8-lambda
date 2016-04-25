package br.com.softplan.ibraimgm.desafioJava8.suporte.strategy;

public class LaserStrategy implements WeaponStrategy {

        @Override
        public String shoot() {
                return "pew pew pew!";
        }

}
