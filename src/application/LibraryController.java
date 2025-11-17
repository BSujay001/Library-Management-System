package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LibraryController {

    @FXML private TextField idField, titleField, authorField;
    @FXML private TableView<Book> table;
    @FXML private TableColumn<Book, Integer> idCol;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private TableColumn<Book, String> statusCol;

    @FXML private TextField searchField;

    ObservableList<Book> bookList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        titleCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
        authorCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAuthor()));
        statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));

        table.setItems(bookList);
    }

    @FXML
    public void addBook() {
        int id = Integer.parseInt(idField.getText());
        String title = titleField.getText();
        String author = authorField.getText();

        bookList.add(new Book(id, title, author));

        idField.clear();
        titleField.clear();
        authorField.clear();
    }

    @FXML
    public void searchBook() {
        int id = Integer.parseInt(searchField.getText());
        for (Book b : bookList) {
            if (b.getId() == id) {
                table.getSelectionModel().select(b);
                return;
            }
        }
        showAlert("Book not found!");
    }

    @FXML
    public void issueBook() {
        Book selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Select a book first!");
            return;
        }
        if (selected.getStatus().equals("Issued")) {
            showAlert("Book already issued!");
        } else {
            selected.issueBook();
            table.refresh();
        }
    }

    @FXML
    public void returnBook() {
        Book selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Select a book first!");
            return;
        }
        selected.returnBook();
        table.refresh();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }
}
