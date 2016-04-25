package br.com.softplan.ibraimgm.desafioJava8.suporte.flipper;

import java.util.function.Function;

@FunctionalInterface
public interface Flipper<A, B, C> {
        C execute(A a, B b);

        default Flipper<B, A, C> flip() {
                return (b, a) -> this.execute(a, b);
        }

        default Function<B, C> curry(A a) {
                return b -> this.execute(a, b);
        }
}
