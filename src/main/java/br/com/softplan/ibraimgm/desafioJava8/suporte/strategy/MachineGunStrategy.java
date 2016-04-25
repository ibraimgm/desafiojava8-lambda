package br.com.softplan.ibraimgm.desafioJava8.suporte.strategy;

public class MachineGunStrategy implements WeaponStrategy {

        @Override
        public String shoot() {
                return "ra-ta-ta-ta-ta-ta-ta-ta-ta-ta!";
        }

}
