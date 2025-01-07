import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Arrays;

public class MainAppController {
    private Lure tempLure = null;

    @FXML
    private TextField brandTextField;

    @FXML
    private TextField colorTextField;

    @FXML
    private AnchorPane deepDiverPane;

    @FXML
    private TextField divesToTextField;

    @FXML
    private CheckBox floatCheckBox;
    @FXML
    private AnchorPane editLureCountPane;
    @FXML
    private TextField lureNameCountTextField;
    @FXML
    private TextField amountLostTextField;


    @FXML
    private MenuButton hoverMenuButton;

    @FXML
    private ListView<Lure> inventoryShower;
    private LinkedList<Lure> lureLinkedList = new LinkedList<>();
    private ObservableList<Lure> lureObservableList = FXCollections.observableArrayList();


    @FXML
    private AnchorPane jigPane;

    @FXML
    private TextField jigTypeTextField;

    @FXML
    private TextField lengthTextField;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField packQuantTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private TextField sizeTextField;

    @FXML
    private AnchorPane softPlasticPane;
    @FXML
    private AnchorPane topWaterPane;
    @FXML
    private TextField topWaterTypeTextField;

    @FXML
    private TextField usesTextField;
    @FXML
    private AnchorPane findLurePane;
    @FXML
    private TextField lureNameTextField;

    @FXML
    private void initialize() {
        lureLinkedList = LinkedListPersistence.loadLinkedList();
        Collections.sort(lureLinkedList);
        lureObservableList.addAll(lureLinkedList);
        inventoryShower.setItems(lureObservableList);
        mainPane.setVisible(true);
        mainPane.setManaged(true);
        jigPane.setManaged(false);
        softPlasticPane.setManaged(false);
        deepDiverPane.setManaged(false);
        topWaterPane.setManaged(false);
        topWaterPane.setVisible(false);
        findLurePane.setManaged(false);
        findLurePane.setVisible(false);
        editLureCountPane.setManaged(false);
        editLureCountPane.setVisible(false);
    }

    @FXML
    private void addDeepDiver(ActionEvent event) {
        try {
            DeepDiver diver = new DeepDiver(tempLure, Integer.parseInt(divesToTextField.getText()));
            clearFields();
            deepDiverPane.setVisible(false);
            deepDiverPane.setManaged(false);
            mainPane.setVisible(true);
            mainPane.setManaged(true);
            updateList(lureLinkedList, diver);
            tempLure = null;
        } catch (InvalidInputException e) {
            showAlert("Invalid Input", e.getMessage());
        } catch (Exception e) {
            showAlert("Invalid Input", "Unkown Error");
        }
    }

    @FXML
    private void topWaterAdder(ActionEvent event) {
        try {
            // Attempt to add a Lure
            tempLure = addHelper();
            // If successful, proceed to switch panes
            mainPane.setVisible(false);
            mainPane.setManaged(false);
            topWaterPane.setManaged(true);
            topWaterPane.setVisible(true);
        } catch (InvalidInputException e) {
            showAlert("Invalid Input", e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Invalid number for weight, quantity, or length.");
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred.");
        }
    }
    @FXML
    private void editLureCount(ActionEvent event) {
        mainPane.setVisible(false);
        mainPane.setManaged(false);
        editLureCountPane.setManaged(true);
        editLureCountPane.setVisible(true);
    }
    @FXML
    private void editQuantity(ActionEvent event){
        try {
            if (lureNameCountTextField.getText().isEmpty())
                throw new InvalidInputException("No Name Given");
            if(amountLostTextField.getText().isEmpty())
                throw new InvalidInputException("No Quantity Given");
            else {
                boolean contains = false;
                boolean completelyRemoved = false;
                String lureName = lureNameCountTextField.getText();
                for (int i = 0; i < lureLinkedList.size(); i++) {
                    if (lureName.equals(lureLinkedList.get(i).getName())) {
                        lureLinkedList.get(i).updateQuantity(Integer.parseInt(amountLostTextField.getText()));
                        if(lureLinkedList.get(i).getQuantity() <= 0) {
                            lureLinkedList.remove(i);
                            lureObservableList.remove(i);
                            completelyRemoved = true;
                        }
                        contains = true;
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Lure Quantity");
                        if(completelyRemoved) {
                            alert.setContentText("New Quantity: " + 0);
                            alert.showAndWait();
                        }
                        else {
                            alert.setContentText("New Quantity: " + lureLinkedList.get(i).getQuantity());
                            alert.showAndWait();
                        }
                    }
                }
                if (!contains)
                    throw new InvalidInputException("Lure Not in List");
                editLureCountPane.setManaged(false);
                editLureCountPane.setVisible(false);
                mainPane.setVisible(true);
                mainPane.setManaged(true);
                clearFields();
            }
        } catch (InvalidInputException e) {
            showAlert("Error", e.getMessage());
        } catch (Exception e) {
            showAlert("Error", "Unkown Error");
        }
    }

    private void saveData() {
        LinkedListPersistence.saveLinkedList(lureLinkedList);
    }

    @FXML
    private void addJig(ActionEvent event) {
        try {
            Jig jig = new Jig(tempLure, jigTypeTextField.getText());
            clearFields();
            jigPane.setVisible(false);
            jigPane.setManaged(false);
            mainPane.setVisible(true);
            mainPane.setManaged(true);
            updateList(lureLinkedList, jig);
            tempLure = null;
        } catch (InvalidInputException e) {
            showAlert("Invalid Input", e.getMessage());
        } catch (Exception e) {
            showAlert("Invalid Input", "Unkown Error");
        }
    }

    @FXML
    private void addSoftPlastic(ActionEvent event) {
        try {
            String stringOfUses = usesTextField.getText();
            ArrayList<String> uses = new ArrayList<>(Arrays.asList(stringOfUses.split(",")));
            SoftPlastic softPlastic = new SoftPlastic(tempLure, uses, floatCheckBox.isSelected(),
                    Integer.parseInt(packQuantTextField.getText()));
            clearFields();
            softPlasticPane.setVisible(false);
            softPlasticPane.setManaged(false);
            mainPane.setVisible(true);
            mainPane.setManaged(true);
            updateList(lureLinkedList, softPlastic);
            tempLure = null;
        } catch (InvalidInputException e) {
            showAlert("Invalid Input", e.getMessage());
        } catch (Exception e) {
            showAlert("Invalid Input", "Unkown Error");
        }

    }

    @FXML
    private void addTopWater(ActionEvent event) {
        try {
            TopWater topWater = new TopWater(tempLure, topWaterTypeTextField.getText());
            clearFields();
            topWaterPane.setVisible(false);
            topWaterPane.setManaged(false);
            mainPane.setVisible(true);
            mainPane.setManaged(true);
            updateList(lureLinkedList, topWater);
            tempLure = null;
        } catch (InvalidInputException e) {
            showAlert("Invalid Input", e.getMessage());
        } catch (Exception e) {
            showAlert("Invalid Input", "Unkown Error");
        }
    }

    @FXML
    private void clearFields(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        brandTextField.clear();
        nameTextField.clear();
        colorTextField.clear();
        quantityTextField.clear();
        sizeTextField.clear();
        lengthTextField.clear();
        packQuantTextField.clear();
        divesToTextField.clear();
        floatCheckBox.setSelected(false);
        jigTypeTextField.clear();
        usesTextField.clear();
        topWaterTypeTextField.clear();
        lureNameTextField.clear();
        amountLostTextField.clear();
        lureNameCountTextField.clear();
    }

    @FXML
    private void closeApp(ActionEvent event) {
        saveData();
        Platform.exit();
    }

    @FXML
    private void crankbaitAdder(ActionEvent event) {
        try {
            // Attempt to add a Lure
            tempLure = addHelper();
            // If successful, proceed to switch panes
            mainPane.setVisible(false);
            mainPane.setManaged(false);
            deepDiverPane.setManaged(true);
            deepDiverPane.setVisible(true);
        } catch (InvalidInputException e) {
            showAlert("Invalid Input", e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Invalid number for weight, quantity, or length.");
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred.");
        }
    }

    @FXML
    private void handleFindLure(ActionEvent event) {
        mainPane.setVisible(false);
        mainPane.setManaged(false);
        findLurePane.setVisible(true);
        findLurePane.setManaged(true);
    }

    @FXML
    void jigAdder(ActionEvent event) {
        try {
            // Attempt to add a Lure
            tempLure = addHelper();
            // If successful, proceed to switch panes
            mainPane.setVisible(false);
            mainPane.setManaged(false);
            jigPane.setManaged(true);
            jigPane.setVisible(true);
        } catch (InvalidInputException e) {
            showAlert("Invalid Input", e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Invalid number for weight, quantity, or length.");
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred.");
        }
    }

    @FXML
    void saveToFile(ActionEvent event) {
        saveData();
    }

    @FXML
    void softPlasticAdder(ActionEvent event) {
        try {
            // Attempt to add a Lure
            tempLure = addHelper();
            // If successful, proceed to switch panes
            mainPane.setVisible(false);
            mainPane.setManaged(false);
            softPlasticPane.setManaged(true);
            softPlasticPane.setVisible(true);
        } catch (InvalidInputException e) {
            showAlert("Invalid Input", e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Invalid number for weight, quantity, or length.");
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred.");
        }
    }

    private Lure addHelper() throws InvalidInputException {
        String brand = brandTextField.getText();
        String color = colorTextField.getText();
        String name = nameTextField.getText();
        double weight;
        double length;
        if (sizeTextField.getText().isEmpty())
            weight = 0.0;
        else {
            weight = Double.parseDouble(sizeTextField.getText());
            if (weight < 0)
                throw new InvalidInputException("Size must be above zero");
        }
        if (lengthTextField.getText().isEmpty())
            length = 0.0;
        else {
            length = Double.parseDouble(lengthTextField.getText());
            if (length < 0)
                throw new InvalidInputException("Length must be above zero");
        }
        int quantity = Integer.parseInt(quantityTextField.getText());
        if (quantity < 1)
            throw new InvalidInputException("Quantity must be greater than 0");
        return new Lure(quantity, brand, weight, length, color, name);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait(); // Use showAndWait for blocking behavior if needed
    }

    private void updateList(LinkedList<Lure> lureList, Lure lure) {
        for (Lure lureAlreadyInList : lureList) {
            if (lureAlreadyInList.equals(lure)) {
                lureAlreadyInList.updateQuantity(lure.getQuantity());
                Collections.sort(lureList);
                inventoryShower.refresh();
                return;
            }
        }
        lureList.add(lure);
        Collections.sort(lureList);
        lureObservableList.add(lure);
        Collections.sort(lureObservableList);

    }

    @FXML
    private void findAndDisplayLure(ActionEvent event) {
        try {
            if (lureNameTextField.getText().isEmpty())
                throw new InvalidInputException("No Name Given");
            else {
                boolean contains = false;
                String lureName = lureNameTextField.getText();
                for (int i = 0; i < lureLinkedList.size(); i++) {
                    if (lureName.equals(lureLinkedList.get(i).getName())) {
                        inventoryShower.scrollTo(lureLinkedList.get(i));
                        contains = true;
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Lure Quantity");
                        alert.setContentText("Quantity: " + lureLinkedList.get(i).getQuantity());
                        alert.showAndWait();
                    }
                }
                if (!contains)
                    throw new InvalidInputException("Lure Not in List");
                findLurePane.setManaged(false);
                findLurePane.setVisible(false);
                mainPane.setVisible(true);
                mainPane.setManaged(true);
                clearFields();
            }
        } catch (InvalidInputException e) {
            showAlert("Error", e.getMessage());
        } catch (Exception e) {
            showAlert("Error", "Unkown Error");
        }
    }
}