package br.com.softplan.ibraimgm.desafioJava8.suporte.strategy;

public class RegularActionHero {

        private String name;
        private WeaponStrategy weapon;

        public RegularActionHero(String name) {
                super();
                this.name = name;
        }

        public void shoot() {
                System.out.println("[" + name + "] " + weapon.shoot());
        }

        public void setWeapon(WeaponStrategy weapon) {
                this.weapon = weapon;
        }
}
