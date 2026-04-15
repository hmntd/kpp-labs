package Models;

import java.util.List;

public class Buildings {
    public static abstract class AbstractBuilding extends Building {
        public AbstractBuilding(int addr) {
            super(addr);
        }
    }

    public static class Res extends Residential {
        public Res(int a, int r) {
            super(a, r);
        }
    }

    public static class Shp extends Shop {
        public Shp(int a, Enums.ShopType t, List<String> d) {
            super(a, t, d);
        }
    }

    public static class Sch extends School {
        public Sch(int a, Enums.SchoolLevel l) {
            super(a, l);
        }
    }

    public static class Hos extends Hospital {
        public Hos(int a, int b) {
            super(a, b);
        }
    }
}
