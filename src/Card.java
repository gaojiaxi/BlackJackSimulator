
public class Card {
    private final int faceValue;
    private final Suit suit;

    public Card(int faceValue, Suit s) {
        this.faceValue = faceValue;
        this.suit = s;
    }


    public int getFaceValue() {
        return faceValue;
    }

    public Suit getSuit() {
        return suit;
    }

}


