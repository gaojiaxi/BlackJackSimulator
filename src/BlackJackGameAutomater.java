import java.util.ArrayList;
import java.util.List;

public class BlackJackGameAutomater {
    // for simplicity, every user will keep asking cards if the total score of
    // their cards at hand is less than HIT_UNTIL = 16.
    private Deck deck;
    private BlackJackHandCards[] hands;
    private static final int HIT_UNTIL = 16;

    public BlackJackGameAutomater(int numPlayers) {
        hands = new BlackJackHandCards[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            hands[i] = new BlackJackHandCards();
        }
    }

    public void initializeDeck() {
        deck = new Deck();
        deck.shuffle();
    }

    boolean dealInitial() {
        for (BlackJackHandCards hand : hands) {
            Card[] cards = deck.dealHand(2);
            if (cards == null) {
                return false;
            }
            hand.addCards(cards);
        }
        return true;
    }

    List<Integer> getBlackJacks() {
        List<Integer> winners = new ArrayList<>();
        for (int i = 0; i < hands.length; i++) {
            if (hands[i].isBackJack()) {
                winners.add(i);
            }
        }
        return winners;
    }

    boolean playHand(BlackJackHandCards hand) {
        while (hand.score() < HIT_UNTIL) {
            Card card = deck.dealCard();
            if (card == null) {
                return false;
            }
            hand.addCards(new Card[]{card});
        }
        return true;
    }

    boolean playAllHands() {
        for (BlackJackHandCards hand : hands) {
            if (playHand(hand) == false) {
                return false;
            }
        }
        return true;
    }

    List<Integer> getWinners() {
        List<Integer> winners = new ArrayList<>();
        int winnerScore = 0;
        for (int i = 0; i <hands.length; i++) {
            BlackJackHandCards hand = hands[i];
            if (!hand.isBusted()) {
                if (hand.score() > winnerScore) {
                    winners.clear();
                    winners.add(i);
                }
                else if (hand.score() == winnerScore) {
                    winners.add(i);
                }
            }
        }
        return winners;
    }

    void printHandsAndScore() {
        for (int i = 0; i < hands.length; i++) {
            System.out.print("Hand " + i + "(" + hands[i].score() + "):");
            System.out.println();
        }
    }






}
