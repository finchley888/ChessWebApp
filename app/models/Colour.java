package models;

public enum Colour {
    WHITE{
        @Override
        Colour updateColour() {
            return BLACK;
        };
    },
    BLACK{
        @Override
        Colour updateColour() {
            return WHITE;
        };
    },
    NILL{
        @Override
        Colour updateColour() {
            return NILL;
        };
    };

    abstract Colour updateColour();
}
