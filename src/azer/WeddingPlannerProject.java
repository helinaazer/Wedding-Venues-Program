package azer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Toggle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;
import numberlist.IndexRangeException;
import numberlist.objectlist.Date;
import numberlist.objectlist.NumericObjectArrayList;
import numberlist.primitivelist.IntegerArrayList;
import numberlist.primitivelist.RealArrayList;

/**
 * The GUI class
 *
 * @author Nardin Eshak & Helina Azer & Lydia Soliman
 * @version 3/19/20
 */
public class WeddingPlannerProject extends Application {

    //create global images
    private ImageView titleCoverImage;
    private ImageView signUpCoverImage;
    private ImageView planCoverImage;
    private ImageView upcomingWeddingsCoverImage;
    private ImageView weddingDetailsCoverImage;
    private Button btnSignUp;
    private Button btnUpcomingWeddings;
    private Button btnPlan;
    private Button btnCancel;
    Button btnNewWedding;
    private Scene mainScene;
    private Scene signUpScene;
    private Scene planScene;
    private Scene upcomingWeddingScene;
    private ScrollPane scrollPane;
    private VBox vBox;
    private LocalDate ld;
    private Stage window;
    private String brideName;
    private String groomName;
    private int numberOfGuests;
    private int year;
    private int day;
    private int month;
    private Date date;
    private Label lblTotalOutput;
    private double menuCost = 1500.0;
    private double cakeCost = 200.0;
    private double musicCost = 400.0;
    private double totalCost;
    private RadioButton rbDj;
    private RadioButton rbSinger;
    private RadioButton rbDancer;
    private RadioButton rbBasic;
    private RadioButton rbFancy;
    private RadioButton rbSuperFancy;
    private RadioButton rbChoco;
    private RadioButton rbVanilla;
    private Weddings list;
    private ToggleGroup musicGroup;
    private ToggleGroup menuGroup;
    private ToggleGroup cakeGroup;
    private Group rootUpcomingWedding;
    private String musicType = "";
    private String menuType = "";
    private String cakeType = "";
    private Label lblCouplesName2;
    private Label lblWeddingDate2;
    private Label lblNumberOfGuests2;
    private Label lblCake2;
    private Label lblMusic2;
    private Label lblMenu2;
    private Button btnDelete;
    private Button btnEdit;
    private Button btnUpdate;
    private Group rootPlanScene;
    private Button btnSubmit;
    private int selected = -1;
    private String path = "weddings.txt";

    /**
     * This method starts the GUI program.
     *
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            readList();
        } catch (IndexRangeException ex) {
            Logger.getLogger(WeddingPlannerProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        window = primaryStage;
        Image titleCover = new Image(getClass().getResourceAsStream("/images/Title.jpg"));
        titleCoverImage = new ImageView(titleCover);
        titleCoverImage.setFitHeight(700);
        titleCoverImage.setFitWidth(900);
        Image signUpCover = new Image(getClass().getResourceAsStream("/images/SignUpCover.jpg"));
        signUpCoverImage = new ImageView(signUpCover);
        signUpCoverImage.setFitHeight(700);
        signUpCoverImage.setFitWidth(900);
        Image planCover = new Image(getClass().getResourceAsStream("/images/PlanCover.jpg"));
        planCoverImage = new ImageView(planCover);
        planCoverImage.setFitHeight(700);
        planCoverImage.setFitWidth(900);
        Image upcomingWeddingsCover = new Image(getClass().getResourceAsStream("/images/UpcomingWeddings.jpg"));
        upcomingWeddingsCoverImage = new ImageView(upcomingWeddingsCover);
        upcomingWeddingsCoverImage.setFitHeight(700);
        upcomingWeddingsCoverImage.setFitWidth(900);
        Image weddingDetailsCover = new Image(getClass().getResourceAsStream("/images/WeddingDetails.jpg"));
        weddingDetailsCoverImage = new ImageView(weddingDetailsCover);
        weddingDetailsCoverImage.setFitHeight(700);
        weddingDetailsCoverImage.setFitWidth(900);
        window.setScene(createMainScene());
        window.setTitle("A Day To Remember Wedding Planners");
        window.setResizable(false);
        window.setWidth(900);
        window.setHeight(700);
        window.setX(200);
        window.setY(200);
        window.show();
    }

    /**
     * This method writes the list to the Wedding file.
     *
     */
    @Override
    public void stop() {
        writeList();
    }

    /**
     * This method creates the main scene of our user interface.
     *
     * @return mainScene the main scene of our GUI
     */
    Scene createMainScene() {
        Group root = new Group();
        root.getChildren().addAll(titleCoverImage);
        btnSignUp = new Button("Congrats! Let Us Plan Your Wedding!");
        btnSignUp.setMaxSize(400.0, 400.0);
        btnSignUp.setWrapText(true);
        btnSignUp.setMaxHeight(350);
        btnSignUp.setMaxWidth(450);
        btnSignUp.setStyle("-fx-text-size: 10em;");
        btnSignUp.setLayoutX(200);
        btnSignUp.setLayoutY(400);
        btnSignUp.setStyle("-fx-font-size: 1.1em;");
        btnSignUp.setOnAction(e -> window.setScene(createSignUpScene()));
        btnUpcomingWeddings = new Button("Upcoming Wedding");
        btnUpcomingWeddings.setMaxSize(400.0, 400.0);
        btnUpcomingWeddings.setStyle("-fx-font-size: 1.1em;");
        btnUpcomingWeddings.setLayoutX(550);
        btnUpcomingWeddings.setLayoutY(400);
        btnUpcomingWeddings.setOnAction(e -> {
            try {
                window.setScene(createUpcomingWeddingScene());
                addBtns();
            } catch (IndexRangeException ex) {
                return;
            }
        });
        root.getChildren().add(btnSignUp);
        root.getChildren().add(btnUpcomingWeddings);
        mainScene = new Scene(root, 900, 700);
        return mainScene;
    }

    /**
     * This method creates the sign up scene of our user interface.
     *
     * @return signUpScene the sign up scene of our GUI
     */
    Scene createSignUpScene() {
        Group root = new Group();
        root.getChildren().addAll(signUpCoverImage);
        Label lblBrideName = new Label("Name of Bride: ");
        lblBrideName.setLayoutX(100);
        lblBrideName.setLayoutY(265);
        lblBrideName.setFont(new Font("Arial", 24));
        lblBrideName.setTextFill(Color.GRAY);
        Label lblGroomName = new Label("Name of Groom: ");
        lblGroomName.setLayoutX(460);
        lblGroomName.setLayoutY(265);
        lblGroomName.setFont(new Font("Arial", 24));
        lblGroomName.setTextFill(Color.GRAY);
        Label lblDate = new Label("Wedding Date & Time: ");
        lblDate.setFont(new Font("Arial", 24));
        lblDate.setTextFill(Color.GRAY);
        lblDate.setLayoutX(200);
        lblDate.setLayoutY(340);
        Label lblGuests = new Label("Number of Guests Attending: ");
        lblGuests.setFont(new Font("Arial", 24));
        lblGuests.setTextFill(Color.GRAY);
        lblGuests.setLayoutX(185);
        lblGuests.setLayoutY(410);
        //create calendar 
        DatePicker dpWeddingDate = new DatePicker();
        root.getChildren().add(dpWeddingDate);
        dpWeddingDate.getEditor().setDisable(true);
        dpWeddingDate.setDayCellFactory(d
                -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(item.isBefore(LocalDate.now().plusWeeks(1)));
            }
        });
        dpWeddingDate.setLayoutX(455);
        dpWeddingDate.setLayoutY(340);
        dpWeddingDate.setOnAction(e -> {
            ld = dpWeddingDate.getValue();
            year = ld.getYear();
            month = ld.getMonthValue();
            day = ld.getDayOfMonth();
            date = new Date(year, month, day);
        });
        // create text boxes
        brideName = "";
        TextField txtBrideName = new TextField();
        txtBrideName.setLayoutX(270);
        txtBrideName.setLayoutY(265);
        groomName = "";
        TextField txtGroomName = new TextField();
        txtGroomName.setLayoutX(640);
        txtGroomName.setLayoutY(265);
        numberOfGuests = 0;
        TextField txtNumberOfGuests = new TextField();
        txtNumberOfGuests.setLayoutX(500);
        txtNumberOfGuests.setLayoutY(410);
        btnPlan = new Button("Let's Plan!");
        btnPlan.setLayoutX(330);
        btnPlan.setLayoutY(480);
        btnPlan.setOnAction(e -> {
            try {
                if (txtBrideName.getText().equals("")) {
                    displayErrorMessage("The bride's name is invalid");
                }

                if (txtGroomName.getText().equals("")) {
                    displayErrorMessage("The groom's name is invalid");
                }

                brideName = txtBrideName.getText();
                groomName = txtGroomName.getText();
                try {
                    numberOfGuests = Integer.parseInt(txtNumberOfGuests.getText());
                } catch (NumberFormatException ex) {
                    displayErrorMessage("Number of guests has to be more than 0 and less than 2000.");
                }
                if (numberOfGuests < 0 || numberOfGuests > 2000) {
                    displayErrorMessage("Number of guests has to be more than 0 and less than 2000.");
                }
                if (dpWeddingDate.getValue() == null) {
                    displayErrorMessage("Please choose a wedding date.");

                }
                window.setScene(createPlanScene());
            } catch (IndexRangeException ex) {
                return;
            }
        });
        btnCancel = new Button("Cancel");
        btnCancel.setLayoutX(500);
        btnCancel.setLayoutY(480);
        btnCancel.setOnAction(e -> window.setScene(createMainScene()));
        root.getChildren().add(lblBrideName);
        root.getChildren().add(txtBrideName);
        root.getChildren().add(lblGroomName);
        root.getChildren().add(txtGroomName);
        root.getChildren().add(lblDate);
        root.getChildren().add(lblGuests);
        root.getChildren().add(txtNumberOfGuests);
        root.getChildren().add(btnPlan);
        root.getChildren().add(btnCancel);
        signUpScene = new Scene(root, 900, 700);
        return signUpScene;
    }

    /**
     * This method creates the plan scene of our user interface.
     *
     * @return planScene the plan scene of our GUI
     */
    Scene createPlanScene() throws IndexRangeException {
        rootPlanScene = new Group();
        rootPlanScene.getChildren().addAll(planCoverImage);
        //create labels 
        Label lblMusic = new Label("Music Options");
        lblMusic.setLayoutX(130);
        lblMusic.setLayoutY(250);
        rootPlanScene.getChildren().add(lblMusic);
        Label lblMenu = new Label("Menu Options");
        lblMenu.setLayoutX(350);
        lblMenu.setLayoutY(250);
        rootPlanScene.getChildren().add(lblMenu);
        Label lblCake = new Label("Cake Options");
        lblCake.setLayoutX(580);
        lblCake.setLayoutY(250);
        rootPlanScene.getChildren().add(lblCake);
        //create the music radio button group
        musicGroup = new ToggleGroup();
        rbDj = new RadioButton("Dj $400");
        rbDj.setToggleGroup(musicGroup);
        rbDj.setSelected(true);
        rbDj.setLayoutX(130);
        rbDj.setLayoutY(275);
        rootPlanScene.getChildren().add(rbDj);
        rbDancer = new RadioButton("Dancer $1,000");
        rbDancer.setToggleGroup(musicGroup);
        rbDancer.setLayoutX(130);
        rbDancer.setLayoutY(300);
        rootPlanScene.getChildren().add(rbDancer);
        rbSinger = new RadioButton("Live Singer $4,000");
        rbSinger.setToggleGroup(musicGroup);
        rbSinger.setLayoutX(130);
        rbSinger.setLayoutY(325);
        rootPlanScene.getChildren().add(rbSinger);
        //create the menu radio button group
        menuGroup = new ToggleGroup();
        rbBasic = new RadioButton("Basic Menu $1,500");
        rbBasic.setToggleGroup(menuGroup);
        rbBasic.setSelected(true);
        rbBasic.setLayoutX(350);
        rbBasic.setLayoutY(275);
        rootPlanScene.getChildren().add(rbBasic);
        rbFancy = new RadioButton("Fancy Menu $3,000");
        rbFancy.setToggleGroup(menuGroup);
        rbFancy.setLayoutX(350);
        rbFancy.setLayoutY(300);
        rootPlanScene.getChildren().add(rbFancy);
        rbSuperFancy = new RadioButton("Super Fancy Menu $5,000");
        rbSuperFancy.setToggleGroup(menuGroup);
        rbSuperFancy.setLayoutX(350);
        rbSuperFancy.setLayoutY(325);
        rootPlanScene.getChildren().add(rbSuperFancy);
        //create the cake radio button group 
        cakeGroup = new ToggleGroup();
        rbChoco = new RadioButton("3 Layer Chocolate Cake $200");
        rbChoco.setToggleGroup(cakeGroup);
        rbChoco.setSelected(true);
        rbChoco.setLayoutX(580);
        rbChoco.setLayoutY(275);
        rootPlanScene.getChildren().add(rbChoco);
        rbVanilla = new RadioButton("3 Layer Vanilla Cake $200");
        rbVanilla.setToggleGroup(cakeGroup);
        rbVanilla.setLayoutX(580);
        rbVanilla.setLayoutY(300);
        rootPlanScene.getChildren().add(rbVanilla);
        RadioButton rbStrawberry = new RadioButton("3 Layer Cake with Strawberry Filling $350");
        rbStrawberry.setToggleGroup(cakeGroup);
        rbStrawberry.setLayoutX(580);
        rbStrawberry.setLayoutY(325);
        rootPlanScene.getChildren().add(rbStrawberry);
        Label lblTotal = new Label("CurrentTotal: ");
        lblTotal.setLayoutX(580);
        lblTotal.setLayoutY(425);
        lblTotalOutput = new Label("$" + Double.toString(getTotalCost()));
        lblTotalOutput.setLayoutX(660);
        lblTotalOutput.setLayoutY(425);
        rootPlanScene.getChildren().addAll(lblTotal, lblTotalOutput);
        //create buttons
        btnSubmit = new Button("Submit Order");
        if (btnEdit != null) {
            btnSubmit.setVisible(false);
        }
        btnSubmit.setLayoutX(200);
        btnSubmit.setLayoutY(425);
        rootPlanScene.getChildren().add(btnSubmit);
        btnSubmit.setOnAction(e -> {
            try {
                list.addWeddings(brideName, groomName, date, numberOfGuests, getTotalCost());
                getSelection();
                window.setScene(createUpcomingWeddingScene());
                addBtns();
            } catch (IndexRangeException ire) {
                return;
            }
        });
        btnCancel = new Button("Cancel Order");
        btnCancel.setLayoutX(400);
        btnCancel.setLayoutY(425);
        btnCancel.setOnAction(e -> {
            window.setScene(createMainScene());
            brideName = "";
            groomName = "";
            numberOfGuests = 0;
            date = null;
            totalCost = 0;
        });
        rootPlanScene.getChildren().add(btnCancel);
        //create scene
        planScene = new Scene(rootPlanScene, 900, 700);
        return planScene;
    }

    /**
     * This method gets the users choices for the menu, music, cake.
     *
     */
    void getSelection() {
        if (rbBasic.isSelected()) {
            menuType = "Basic Menu";
        } else if (rbFancy.isSelected()) {
            menuType = "Fancy Menu";
        } else {
            menuType = "Super Fancy Menu";
        }
        if (rbChoco.isSelected()) {
            cakeType = "3-Layer Chocolate Cake";
        } else if (rbVanilla.isSelected()) {
            cakeType = "3-Layer Vanilla Cake";
        } else {
            cakeType = "3-Layer Strawberry Filled Cake";
        }
        if (rbDj.isSelected()) {
            musicType = "DJ";
        } else if (rbDancer.isSelected()) {
            musicType = "Dancer";
        } else {
            musicType = "Live Singer";
        }
    }

    /**
     *
     * This method gets the users choices for the menu, music, cake and
     * calculates the cost.
     *
     */
    double getTotalCost() {
        musicGroup.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) -> {
            RadioButton rb = (RadioButton) musicGroup.getSelectedToggle();
            if (rbDj.isSelected()) {
                musicCost = 0;
                musicCost = 400.0;
            } else if (rbDancer.isSelected()) {
                musicCost = 0;
                musicCost = 1000.0;
            } else {
                musicCost = 0;
                musicCost = 4000.0;
            }
            lblTotalOutput.setText("$" + Double.toString(musicCost + menuCost + cakeCost));
        });
        menuGroup.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) -> {
            RadioButton rb = (RadioButton) menuGroup.getSelectedToggle();
            if (rbBasic.isSelected()) {
                menuCost = 0;
                menuCost = 1500;
            } else if (rbFancy.isSelected()) {
                menuCost = 0;
                menuCost = 3000.0;
            } else {
                menuCost = 0;
                menuCost = 5000.0;
            }

            lblTotalOutput.setText("$" + Double.toString(musicCost + menuCost + cakeCost));
        });
        cakeGroup.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) -> {
            RadioButton rb = (RadioButton) cakeGroup.getSelectedToggle();
            if (rbChoco.isSelected()) {
                cakeCost = 0;
                cakeCost = 200.0;
            } else if (rbVanilla.isSelected()) {
                cakeCost = 0;
                cakeCost = 200.0;
            } else {
                cakeCost = 0;
                cakeCost = 350.0;
            }
            lblTotalOutput.setText("$" + Double.toString(musicCost + menuCost + cakeCost));
        });
        totalCost = musicCost + menuCost + cakeCost;
        return totalCost;
    }

    /**
     * This method creates the upcoming wedding scene of our user interface.
     *
     * @return upcomingWeddingScene the upcoming wedding scene of our GUI
     */
    Scene createUpcomingWeddingScene() throws IndexRangeException {
        rootUpcomingWedding = new Group();
        rootUpcomingWedding.getChildren().addAll(upcomingWeddingsCoverImage);
        scrollPane = new ScrollPane();
        scrollPane.setPrefViewportWidth(430);
        scrollPane.setPrefViewportHeight(310);
        scrollPane.setLayoutX(50);
        scrollPane.setLayoutY(245);
        rootUpcomingWedding.getChildren().add(scrollPane);
        vBox = new VBox();
        scrollPane.setContent(vBox);
        //create drop down box 
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "Name",
                        "Cost",
                        "Number of Guests",
                        "Date"
                );
        ComboBox comboBox = new ComboBox(options);
        comboBox.setLayoutX(360);
        comboBox.setLayoutY(210);
        comboBox.setOnAction(e -> {
            try {
                if (comboBox.getValue() == "Name") {
                    sortName();
                } else if (comboBox.getValue() == "Cost") {
                    sortCost();
                } else if (comboBox.getValue() == "Number of Guests") {
                    sortNumberOfGuests();
                } else {
                    sortDate();
                }
            } catch (IndexRangeException ire) {
                return;
            }
        });
        Label lblCouplesName = new Label("NAME OF BRIDE AND GROOM: ");
        lblCouplesName.setFont(new Font("Arial", 18));
        lblCouplesName.setTextFill(Color.GRAY);
        lblCouplesName.setLayoutX(560);
        lblCouplesName.setLayoutY(220);
        Label lblWeddingDate = new Label("DATE OF WEDDING: ");
        lblWeddingDate.setFont(new Font("Arial", 18));
        lblWeddingDate.setTextFill(Color.GRAY);
        lblWeddingDate.setLayoutX(615);
        lblWeddingDate.setLayoutY(285);
        Label lblNumberOfGuests = new Label("NUMBER OF GUESTS: ");
        lblNumberOfGuests.setFont(new Font("Arial", 18));
        lblNumberOfGuests.setTextFill(Color.GRAY);
        lblNumberOfGuests.setLayoutX(610);
        lblNumberOfGuests.setLayoutY(340);
        Label lblCake = new Label("TYPE OF CAKE: ");
        lblCake.setFont(new Font("Arial", 18));
        lblCake.setTextFill(Color.GRAY);
        lblCake.setLayoutX(633);
        lblCake.setLayoutY(390);
        Label lblMusic = new Label("TYPE OF MUSIC: ");
        lblMusic.setFont(new Font("Arial", 18));
        lblMusic.setTextFill(Color.GRAY);
        lblMusic.setLayoutX(635);
        lblMusic.setLayoutY(453);
        Label lblMenu = new Label("TYPE OF MENU: ");
        lblMenu.setFont(new Font("Arial", 18));
        lblMenu.setTextFill(Color.GRAY);
        lblMenu.setLayoutX(633);
        lblMenu.setLayoutY(498);
        lblCouplesName2 = new Label();
        lblWeddingDate2 = new Label();
        lblNumberOfGuests2 = new Label();
        lblCake2 = new Label();
        lblMenu2 = new Label();
        lblMusic2 = new Label();
        //create buttons
        Button btnGoBack = new Button("Go Back");
        btnGoBack.setLayoutX(790);
        btnGoBack.setLayoutY(585);
        btnGoBack.setOnAction(e -> {
            window.setScene(createMainScene());
            btnEdit = null;
        });
        btnDelete = new Button("Cancel Wedding");
        btnDelete.setLayoutX(650);
        btnDelete.setLayoutY(585);
        btnEdit = new Button("Edit Wedding");
        btnEdit.setLayoutX(550);
        btnEdit.setLayoutY(585);
        rootUpcomingWedding.getChildren().addAll(comboBox, lblCouplesName,
                lblWeddingDate, lblNumberOfGuests, lblCake, lblMusic, lblMenu,
                lblCouplesName2, lblWeddingDate2, lblNumberOfGuests2, lblMenu2,
                lblMusic2, lblCake2, btnGoBack, btnDelete, btnEdit);
        upcomingWeddingScene = new Scene(rootUpcomingWedding, 900, 700);
        return upcomingWeddingScene;
    }

    /**
     * This method adds all of the programs wedding buttons.
     *
     */
    void addBtns() throws IndexRangeException {
        selected = -1;
        ArrayList<String> brideNames = list.getBrideNames();
        vBox.getChildren().clear();
        for (int i = 0; i < brideNames.size(); i++) {
            final int index = i;
            Button btnWedding = new Button("Wedding of:   " + list.getBrideName(i)
                    + " & " + list.getGroomName(i) + "        " + list.getDate(i)
                    + "        " + list.getGuestNum(i) + "        $" + list.getMoney(i));
            vBox.getChildren().add(btnWedding);
            btnWedding.setOnAction(e -> {
                try {
                    selected = index;
                    lblCouplesName2.setText("");
                    lblWeddingDate2.setText("");
                    lblNumberOfGuests2.setText("");
                    lblCake2.setText("");
                    lblMenu2.setText("");
                    lblMusic2.setText("");
                    showWeddingDetails(index);
                } catch (IndexRangeException ex) {
                    Logger.getLogger(WeddingPlannerProject.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            btnEdit.setOnAction(e -> {
                try {
                    window.setScene(createPlanScene());
                    musicCost = 400.0;
                    menuCost = 1500.0;
                    cakeCost = 200.0;
                    lblTotalOutput.setText("$" + Double.toString(musicCost + menuCost + cakeCost));
                } catch (IndexRangeException ex) {
                    return;
                }

                btnUpdate = new Button("Update Order");
                rootPlanScene.getChildren().addAll(btnUpdate);
                btnUpdate.setLayoutX(370);
                btnUpdate.setLayoutY(500);
                btnUpdate.setOnAction(b -> {
                    try {
                        list.replaceWeddings(selected, getTotalCost());
                        getSelection();
                        window.setScene(createUpcomingWeddingScene());
                        addBtns();
                    } catch (IndexRangeException ex) {
                        Logger.getLogger(WeddingPlannerProject.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            });
        }
        btnDelete.setOnAction(e -> {
            try {
                list.deleteWeddings(selected);
                window.setScene(createUpcomingWeddingScene());
                addBtns();
            } catch (IndexRangeException ex) {
                return;
            }
        });

    }

    /**
     * This method shows the wedding details for each wedding.
     *
     */
    void showWeddingDetails(int index) throws IndexRangeException {
        lblCouplesName2.setText(list.getBrideName(index) + " & " + list.getGroomName(index));
        lblCouplesName2.setFont(new Font("Arial", 18));
        lblCouplesName2.setTextFill(Color.GRAY);
        lblCouplesName2.setLayoutX(588);
        lblCouplesName2.setLayoutY(250);
        lblWeddingDate2.setText(((Date) list.getDate(index)).toString());
        lblWeddingDate2.setFont(new Font("Arial", 18));
        lblWeddingDate2.setTextFill(Color.GRAY);
        lblWeddingDate2.setLayoutX(655);
        lblWeddingDate2.setLayoutY(310);
        lblNumberOfGuests2.setText((Long.toString(list.getGuestNum(index))));
        lblNumberOfGuests2.setFont(new Font("Arial", 18));
        lblNumberOfGuests2.setTextFill(Color.GRAY);
        lblNumberOfGuests2.setLayoutX(680);
        lblNumberOfGuests2.setLayoutY(360);
        lblCake2.setText(cakeType);
        lblCake2.setFont(new Font("Arial", 18));
        lblCake2.setTextFill(Color.GRAY);
        lblCake2.setLayoutX(608);
        lblCake2.setLayoutY(418);
        lblMusic2.setText(musicType);
        lblMusic2.setFont(new Font("Arial", 18));
        lblMusic2.setTextFill(Color.GRAY);
        lblMusic2.setLayoutX(680);
        lblMusic2.setLayoutY(475);
        lblMenu2.setText(menuType);
        lblMenu2.setFont(new Font("Arial", 18));
        lblMenu2.setTextFill(Color.GRAY);
        lblMenu2.setLayoutX(650);
        lblMenu2.setLayoutY(520);
        rootUpcomingWedding.getChildren().addAll(lblCouplesName2, lblWeddingDate2,
                lblNumberOfGuests2, lblCake2, lblMusic2, lblMenu2);
        btnDelete.setOnAction(e -> {
            try {
                list.deleteWeddings(index);
                addBtns();
            } catch (IndexRangeException ex) {
                Logger.getLogger(WeddingPlannerProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /**
     * This method sorts the list depending on the name.
     *
     */
    void sortName() throws IndexRangeException {
        ArrayList<String> listBrideName = list.getBrideNames();
        list.sort(listBrideName);
        addBtns();
    }

    /**
     * This method sorts the list depending on the money.
     *
     */
    void sortCost() throws IndexRangeException {
        RealArrayList listMoney = list.getMoneys();
        list.sortMoney(listMoney);
        addBtns();
    }

    /**
     * This method sorts the list depending on the number of guests .
     *
     */
    void sortNumberOfGuests() throws IndexRangeException {
        IntegerArrayList listGuestNum = list.getGuestNums();
        list.sort(listGuestNum);
        addBtns();
    }

    /**
     * This method sorts the list depending on the dates.
     *
     */
    void sortDate() throws IndexRangeException {
        NumericObjectArrayList listDates = list.getDates();
        list.sort(listDates);
        addBtns();
    }

    /**
     *
     * This method writes the items from the file.
     *
     */
    public void writeList() {
        File file = new File(path);
        try (PrintWriter output = new PrintWriter(file)) {
            output.print(list.toString());
            // System.out.println(list);
        } catch (IOException ex) {
            System.out.println("Cannot write to file.");
        }
    }

    /**
     *
     * This method reads the items from the file.
     *
     */
    public void readList() throws IndexRangeException {
        File file = new File(path);
        try (Scanner input = new Scanner(new FileInputStream(file))) {
            list = new Weddings();
            while (input.hasNext()) {
                String s = input.nextLine();
                // System.out.println(s);
                if (s.equals("")) {
                    return;
                }
                String[] args = s.split(",");
                // String brideName, String groomName, Date date, int guestNum, double money
                String[] date = args[2].split("/");
                int month = Integer.parseInt(date[0]);
                int day = Integer.parseInt(date[1]);
                int year = Integer.parseInt(date[2]);
                list.addWeddings(args[0], args[1], new Date(year, month, day), Long.parseLong(args[3]), Double.parseDouble(args[4]));
            }
            if (list == null) {
                list = new Weddings();
            }
        } catch (IOException ex) {
            list = new Weddings();
            System.out.println("Cannot read from file.");
        }
    }

    private boolean verifyName(String name) {
        if (name.length() == 0) {
            return false;
        }

        for (int i = 0; i < name.length(); i++) {
            if (!Character.isAlphabetic(name.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    void displayErrorMessage(String error) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Warning!");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.show();
        alert.showAndWait();
    }

}
