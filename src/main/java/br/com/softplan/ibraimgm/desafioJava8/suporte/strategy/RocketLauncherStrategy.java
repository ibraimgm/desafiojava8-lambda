package br.com.softplan.ibraimgm.desafioJava8.suporte.strategy;

public class RocketLauncherStrategy implements WeaponStrategy {

        @Override
        public String shoot() {
                return "Fwoooshhhh... BOOOM!!!";
        }

}
