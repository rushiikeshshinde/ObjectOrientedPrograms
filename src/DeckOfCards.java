import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DeckOfCards {
    private static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private static final int NUM_PLAYERS = 4;
    private static final int CARDS_PER_PLAYER = 9;

    private List<String> deck;

    public DeckOfCards() {
        initializeDeck();
    }

    private void initializeDeck() {
        deck = new ArrayList<>();
        for (String suit : SUITS) {
            for (String rank : RANKS) {
                deck.add(rank + " of " + suit);
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(deck, new Random());
    }

    public String[][] distributeCards() {
        String[][] playersCards = new String[NUM_PLAYERS][CARDS_PER_PLAYER];
        int cardIndex = 0;

        for (int i = 0; i < CARDS_PER_PLAYER; i++) {
            for (int j = 0; j < NUM_PLAYERS; j++) {
                playersCards[j][i] = deck.get(cardIndex++);
            }
        }

        return playersCards;
    }

    public void printPlayersCards(String[][] playersCards) {
        for (int i = 0; i < NUM_PLAYERS; i++) {
            System.out.println("Player " + (i + 1) + "'s cards:");
            for (int j = 0; j < CARDS_PER_PLAYER; j++) {
                System.out.println("  " + playersCards[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        DeckOfCards game = new DeckOfCards();
        game.shuffleDeck();
        String[][] distributedCards = game.distributeCards();
        game.printPlayersCards(distributedCards);
    }
}