/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Alertmodel;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Lamar Cooley
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    private Button buttonCreate;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonDelete;

    @FXML
    private TableView<?> studentTable;

    @FXML
    private TableColumn<?, ?> alertID;

    @FXML
    private TableColumn<?, ?> severity;

    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private TableColumn<?, ?> studentGPA;

    @FXML
    private TableColumn<?, ?> studentName1;

    @FXML
    private Button searchbutton;

    @FXML
    void search(ActionEvent event) {
        System.out.println("Clicked!");
    }

    @FXML
    public void readAll(ActionEvent event) {

        Query query = manager.createNamedQuery("Alertmodel.findAll");
        List<Alertmodel> data = query.getResultList();

        for (Alertmodel s : data) {
            System.out.println(s.getId() + " " + s.getAccountname() + ": " + (s.getSeverity() ? "*Bad* " : "Moderate ") + s.getDate()
                    + "\n" + s.getDescription());
        }
    }

    @FXML
    void createAlert(ActionEvent event) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please Enter ID");
        int id = scnr.nextInt();

        System.out.println("Is this alert Severe? Y/N");
        boolean severity = scnr.next().toUpperCase().contains("Y");

        System.out.println("Please enter a short desc of the alert");
        String desc = scnr.nextLine();

        System.out.println("Please enter the Account name");
        String name = scnr.next();

        Alertmodel alert = new Alertmodel();

        alert.setAccountname(name);

        alert.setSeverity(severity);

        alert.setId(id);

        alert.setDescription(desc);
        create(alert);
    }

    @FXML
    void deleteAlert(ActionEvent event) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter ID");
        int id = scnr.nextInt();

        Alertmodel alert = readById(id);
        System.out.println("Deleting alert: " + id);
        deleteAlert(alert);

    }

    @FXML
    void updateAlert(ActionEvent event) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please Enter ID");
        int id = scnr.nextInt();

        System.out.println("Is this alert Severe? Y/N");
        boolean severity = scnr.next().toUpperCase().contains("Y");

        System.out.println("Please enter a short desc of the alert");
        String desc = scnr.nextLine();

        System.out.println("Please enter the Account name");
        String name = scnr.next();

        Alertmodel alert = new Alertmodel();

        alert.setAccountname(name);

        alert.setSeverity(severity);

        alert.setId(id);

        alert.setDescription(desc);
        update(alert);
    }

    //The below methods (delete, create, read, and modify were provided in demo
    @FXML
    void deleteAlert(Alertmodel alert) {
        try {
            Alertmodel existingAlert = manager.find(Alertmodel.class, alert.getId());

            // sanity check
            if (existingAlert != null) {

                // begin transaction
                manager.getTransaction().begin();

                //remove alert
                manager.remove(existingAlert);

                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    // Update operation
    public void update(Alertmodel model) {
        try {

            Alertmodel existingAlert = manager.find(Alertmodel.class, model.getId());

            if (existingAlert != null) {
                // begin transaction
                manager.getTransaction().begin();

                // update all atttributes
                existingAlert.setSeverity(model.getSeverity());
                existingAlert.setDescription(model.getDescription());
                existingAlert.setAccountname(model.getAccountname());

                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Create operation
    public void create(Alertmodel alert) {
        try {
            // begin transaction
            manager.getTransaction().begin();

            // sanity check
            if (alert != null) {

                // create student
                manager.persist(alert);

                // end transaction
                manager.getTransaction().commit();

                System.out.println(alert.toString() + " is created");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Alertmodel readById(int id) {
        Query query = manager.createNamedQuery("Alertmodel.findById");
        query.setParameter("ID", id);

        Alertmodel s = (Alertmodel) query.getSingleResult();

        if (s != null) {
            System.out.println(s.getId() + " " + s.getAccountname() + ": " + (s.getSeverity() ? "*Bad* " : "Moderate ") + s.getDate()
                    + "\n" + s.getDescription());
        }
        return s;

    }

    // Database manager
    EntityManager manager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // loading data from database
        //database reference: "IntroJavaFXPU"
        manager = (EntityManager) Persistence.createEntityManagerFactory("LamarRussFXMLPU").createEntityManager();
    }

}
