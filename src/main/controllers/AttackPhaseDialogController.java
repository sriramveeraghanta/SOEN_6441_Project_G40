package main.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import main.helpers.AttackPhase;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.GameCommons;

import java.util.ArrayList;

/**
 *This class is a controller for attack phase dialog
 */
public class AttackPhaseDialogController {
    private GameModel gameModel;
    private CountryModel attackingCountry = null;
    private CountryModel defendingCountry = null;

    @FXML
    private ListView<CountryModel> attackingCountryListView, defendingCountryListView;

    /**
     * this method do all action that should be done in attack phase
     */
    public void attackAction() {
        System.out.println(attackingCountry.getCountryName());
        System.out.println(defendingCountry.getCountryName());
        if(attackingCountry != null && defendingCountry != null) {
            AttackPhase attackPhase = new AttackPhase(gameModel, attackingCountry, defendingCountry);
            if(attackingCountry.getArmyInCountry() > 2) {
                String message = attackPhase.attackCountry();
                System.out.println(message);
                if(message != null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(message);
                    alert.setHeaderText(null);
                    alert.setContentText("Attacker won and occupied the defender country");
                    alert.showAndWait();
                }
            }
        } else {
            System.out.println("IN else");
        }
    }

    /**
     * Getter method to get game model object
     * @return gameModel object
     */
    public GameModel getGameModel() {
        return gameModel;
    }

    /**
     * Setter method to set the game model according to the parameter it gets and initialize the attack phase
     * @param gameModel game model object
     */
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
        this.initializeAttack();
    }

    /**
     * this method initialize attack phase by selecting the attacker and defender country according to the map file
     * and graph of countries
     */
    private void initializeAttack() {
        GameCommons gameCommons = new GameCommons();

        PlayerModel currentPlayer = gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex());
        ArrayList<CountryModel> playerCountries = currentPlayer.getCountries();
        ObservableList<CountryModel> playerCountriesObservable = FXCollections.observableArrayList(playerCountries);
        attackingCountryListView.setItems(playerCountriesObservable);
        attackingCountryListView.setCellFactory(lv -> new ListCell<CountryModel>() {
            @Override
            public void updateItem(CountryModel item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getCountryName());
                }
            }
        });
        attackingCountryListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CountryModel>() {
            @Override
            public void changed(ObservableValue<? extends CountryModel> observable, CountryModel oldValue, CountryModel newValue) {
                //System.out.println("Selected Country: " + newValue.getCountryName());
                setAttackingCountry(newValue);

                ArrayList<CountryModel> defendingCountyModels = gameCommons.getAttackerAdjcentCountires(newValue.getAdjacentCountries(), playerCountries);
                ObservableList<CountryModel> defendingCountries = FXCollections.observableArrayList(defendingCountyModels);
                defendingCountryListView.setItems(defendingCountries);
                defendingCountryListView.setCellFactory(lv -> new ListCell<CountryModel>() {
                    @Override
                    public void updateItem(CountryModel item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(item.getCountryName());
                        }
                    }
                });
                defendingCountryListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CountryModel>(){
                    @Override
                    public void changed(ObservableValue<? extends CountryModel> observable, CountryModel oldValue, CountryModel newValue) {
                        setDefendingCountry(newValue);
                    }
                });
            }
        });
    }

    /**
     * Getter method to get the attacking country
     * @return the attacking country object
     */
    public CountryModel getAttackingCountry() {
        return attackingCountry;
    }

    /**
     * Setter method to set the attacker country object
     * @param attackingCountry which setted for attacker country
     */
    public void setAttackingCountry(CountryModel attackingCountry) {
        this.attackingCountry = attackingCountry;
    }

    /**
     * Getter method to get defender country object
     * @return the defender country object
     */
    public CountryModel getDefendingCountry() {
        return defendingCountry;
    }
    /**
     * Setter method to set the defender country object
     * @param defendingCountry which setted for defender country
     */
    public void setDefendingCountry(CountryModel defendingCountry) {
        this.defendingCountry = defendingCountry;
    }
}
