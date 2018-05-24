package pl.sda.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.sda.model.Item;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Controller {

    @FXML
    private ListView<Item> itemList;
    @FXML
    private Button addOrderButton;
    @FXML
    private ListView<Item> yourItemList;
    @FXML
    private Label amount;
    @FXML
    private CheckBox checkboxPerson;
    @FXML
    private CheckBox checkboxCompany;
    @FXML
    private TextField labelName;
    @FXML
    private TextField labelSurnameOrKindOfCompany;
    @FXML
    private TextField labelAddress;
    @FXML
    private TextField labelPeselNIP;




    private BigDecimal valueOfAmount = BigDecimal.valueOf(0.00);
    private BigDecimal valueOfVat = BigDecimal.valueOf(0.00);

    public  void initialize(){
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("JPA");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

            itemList.getItems().addAll(showItemList(entityManager));
            itemList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            addOrderButton.setOnAction(event -> {
                    addItemsToOrder();
                    addingPricesWithVat();
                });

            checkboxPerson.selectedProperty().addListener((observable, oldValue, newValue) ->
                {if(checkboxPerson.isSelected()){
                    checkboxCompany.setSelected(false);
                    labelName.setText("Imie");
                    labelSurnameOrKindOfCompany.setText("Nazwisko");
                    labelAddress.setText("ulica, nr bud/ lok, miasto");
                    labelPeselNIP.setText("PESEL");
                }});
            checkboxCompany.selectedProperty().addListener((observable, oldValue, newValue) ->
                {if(checkboxCompany.isSelected()){
                    checkboxPerson.setSelected(false);
                    labelName.setText("Nazwa firmy");
                    labelSurnameOrKindOfCompany.setText("Rodzaj firmay, np. Sp.z o.o., IDG, S.A., S.C");
                    labelAddress.setText("ulica, nr bud/ lok, miasto");
                    labelPeselNIP.setText("NIP");
                }});




        entityManager.getTransaction().commit();

        entityManager.close();
        emf.close();


    }

    private void addingPricesWithVat() {
        valueOfAmount = BigDecimal.valueOf(0.00);
        valueOfVat = BigDecimal.valueOf(0.00);
        if(yourItemList.getItems().isEmpty()){
            amount.setText("Kwota zamówienia");
        } else
            amount.setText("Kwota do zapłaty z VAT to: " + showAmount() + " zł");
    }

    private String showAmount() {
        yourItemList.getItems().forEach(item -> {
                valueOfAmount = valueOfAmount.add(item.getPrice());
                valueOfVat = valueOfVat.add(item.getVat());
        });
        BigDecimal brutto = valueOfAmount.add(valueOfVat);
        return brutto.toString();
    }

    private void addItemsToOrder() {
        ObservableList<Item> items;
        items = itemList.getSelectionModel().getSelectedItems();

        List<Item> itemList = new ArrayList<>();
        itemList.addAll(items);
        yourItemList.getItems().addAll(itemList);

    }

    private  List<Item> showItemList(EntityManager entityManager) {
        List<Item> itemList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Item item = entityManager.find(Item.class, i);
            itemList.add(item);
        }
        return itemList;

    }


}
