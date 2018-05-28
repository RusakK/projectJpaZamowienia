package pl.sda;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.sda.model.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Zamówienia");
        primaryStage.show();
    }



    /*private static void showItemList(EntityManager entityManager) {
        List<Item> itemList = new ArrayList<Item>();
        for (int i = 0; i < 3; i++) {
            Item item = entityManager.find(Item.class, i);
            itemList.add(item);
        }
    }

    private static void addCompany(EntityManager entityManager) {
        Company company = new Company();
        company.setName("IBM");
        company.setCompanyType("S.A.");
        company.setNip("232093209302");
        company.setAddress("ul. Polna 1, Warszawa");
        entityManager.persist(company);
    }

    private static void addPerson(EntityManager entityManager) {
        Person person = new Person();
        person.setFirstName("Adam");
        person.setLastName("spadam");
        person.setPesel("912323492234");
        entityManager.persist(person);
    }

    private static void addItem(EntityManager entityManager) {
        Item item1 = new Item();
        item1.setName("Książka3");
        item1.setPrice(BigDecimal.valueOf(200));
        item1.setVat(BigDecimal.valueOf(46));
        entityManager.persist(item1);

    }*/

}
