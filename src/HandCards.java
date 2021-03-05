import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class HandCards {
    protected final List<Card> cards = new ArrayList<>();


    // default implementation
    public abstract int score();

    public void addCards(Card[] c) {
        Collections.addAll(cards, c);
    }

    public int size() {
        return cards.size();
    }

}
