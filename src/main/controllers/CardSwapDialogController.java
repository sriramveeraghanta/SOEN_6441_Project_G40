package main.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.helpers.ReinforcementPhase;
import main.models.CardModel;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * This class is the reinforcement phase controller
 */
public class CardSwapDialogController implements Observer {

    public Label cards,playerUnitsInHand;
    private GameModel gameModel;
    private CountryModel selectedCountry = null;
    private PlayerModel playerModel;
    private ArrayList<CardModel> selectedCards;
    private ReinforcementPhase reinforcePhase=null;
    private CheckBox[] checkBoxes;

    @FXML
    private VBox cardsView;
    @FXML
    private Button SwapCardsButton, CalculateArmyButton;
    @FXML
    public AnchorPane ReinforcementPanel;
    @FXML
    private ListView<CardModel> PlayerCardsList;
    @FXML
    private TextField ArmyCountToPlace;

    /**
     * Getter method to get the game model object
     * @return object of game model
     */
    public GameModel getGameModel() {
        return gameModel;
    }

    /**
     * Setter method to setting the game model
     * @param gameModel object of game model
     */
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
        this.initializeCardsViewer();
    }

    /**
     * Initializing the Reinforment phase required data
     *
     * */
    public void initializeCardsViewer() {
        playerModel = gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex());
        ArrayList<CardModel>  playerCardsList = getPlayerModel().getDeck();
            SwapCardsButton.setDisable(true);
        int numberOfCards = playerCardsList.size();
        checkBoxes = new CheckBox[numberOfCards];
        for (int i = 0; i < numberOfCards; i++) {
            checkBoxes[i] = new CheckBox(
                    playerCardsList.get(i).getCardType().toString());
        }
        cardsView.getChildren().addAll(checkBoxes);
        cards.setText(""+playerCardsList.size());
    }

    @FXML
    public void calculateArmy() {
            int counter = 0;
        ArrayList<CardModel>  playerCardsList = gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex()).getDeck();
         selectedCards = new ArrayList<>();
            for (CheckBox check : checkBoxes) {
                if (check.isSelected()) {
                    selectedCards.add(playerCardsList.get(counter));
                }
                counter++;
            }
            if(selectedCards!=null && selectedCards.size()>=0) {
                ReinforcementPhase reinforcementPhase=new ReinforcementPhase(gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex())
                ,this.gameModel);
                setReinforcePhase(reinforcementPhase);
              int armyUnits=reinforcementPhase.swapCardsForArmyUnits(selectedCards);
                playerUnitsInHand.setText(Integer.toString(armyUnits));
                if(armyUnits ==0){
                    SwapCardsButton.setDisable(true);

                }else{
                    SwapCardsButton.setDisable(false);
                    playerModel.setSuccessfulCardSwapCounter(playerModel.getSuccessfulCardSwapCounter()-1);
                }


            }

    }

    /**
     * SwapCards Action Listener.
     * */
    @FXML
    public void swapCardAction() {
        int armyUnitCount=0;
        getReinforcePhase().setUpdatePlayerDeck(true);
            armyUnitCount=getReinforcePhase().swapCardsForArmyUnits(selectedCards);
            if(armyUnitCount!=0){
                getPlayerModel().setArmyInHand(getPlayerModel().getArmyInHand()+armyUnitCount);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Army Units Added:"+armyUnitCount);
                alert.showAndWait();
            }
        }



    /**
     * Getter method for getting the current Selected Country;
     * @return Country Model
     * */
    public CountryModel getSelectedCountry() {
        return selectedCountry;
    }

    /**
     * Setter method for getting the current Selected Country;
     * @param selectedCountry of Country Model which is selected by the user.
     * */
    public void setSelectedCountry(CountryModel selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    /**
     * Getter method for player model
     * @return player model
     * */
    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    /**
     * Setter method for getting the current Selected Country;
     * @param playerModel Player model
     * */
    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }



    public ReinforcementPhase getReinforcePhase() {
        return reinforcePhase;
    }

    public void setReinforcePhase(ReinforcementPhase reinforcePhase) {
        this.reinforcePhase = reinforcePhase;
    }

    /**
     * this method is to update the view
     */
    public void update(Observable o, Object arg) {

        System.out.println("player Cards:"+ getPlayerModel().getDeck().size());
        String phase = (String) arg;
          if(phase.equalsIgnoreCase("cardSwap")) {
            cards.setText(""+getPlayerModel().getDeck().size());
              int numberOfCards = getPlayerModel().getDeck().size();
              checkBoxes = new CheckBox[numberOfCards];
              for (int i = 0; i < numberOfCards; i++) {
                  checkBoxes[i] = new CheckBox(
                          getPlayerModel().getDeck().get(i).getCardType().toString());
              }
              cardsView.getChildren().clear();
              cardsView.getChildren().addAll(checkBoxes);
              CalculateArmyButton.setDisable(true);
              SwapCardsButton.setDisable(true);

        }
      }
}
