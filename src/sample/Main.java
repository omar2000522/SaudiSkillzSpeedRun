package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.omg.PortableInterceptor.INACTIVE;

import java.sql.*;
import java.util.Calendar;

public class Main extends Application {
    Connection conn = null;
    Stage window = null;
    Calendar raceDate = Calendar.getInstance();
    Label countDownLabel = new Label("");
    double height = 600;
    double width = 800;
    Runnable timer = () -> {
        while (true){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    long timeTillRaceMilli = raceDate.getTimeInMillis() - System.currentTimeMillis();
                    long days = timeTillRaceMilli/86400000;
                    long hours = (timeTillRaceMilli%86400000)/3600000;
                    long mins = ((timeTillRaceMilli%86400000)%3600000)/60000;
                    long secs = (((timeTillRaceMilli%86400000)%3600000)%60000)/1000;
                    countDownLabel.setText(days+" Days "+hours+" Hours "+mins+" Mins "+secs+" Seconds untill marathon start!");
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    Thread countDownThrd = new Thread(timer);


    @Override
    public void start(Stage primaryStage) throws Exception{
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cpt01?useSSL=False","root","omar");
        window = primaryStage;
        raceDate.set(2019,8,5,6,0);
        countDownThrd.start();
        countDownLabel.setFont(Font.font("Open Sans",FontWeight.SEMI_BOLD,18));

        screen1();
    }

    public void screen0(){
        BorderPane rootBorderPane = new BorderPane();
        Label headerLabel = new Label("Marathon Skills 2015");
        Button backButton = new Button("Back");
        HBox topBox = new HBox(backButton,headerLabel);
        HBox bottomBox = new HBox(countDownLabel);
        VBox mainBox = new VBox();

        //=========proprieties==========
        headerLabel.setFont(Font.font("Open Sans", FontWeight.SEMI_BOLD,24));
        rootBorderPane.setTop(topBox);
        rootBorderPane.setBottom(bottomBox);
        rootBorderPane.setCenter(mainBox);
        topBox.setSpacing(20);
        topBox.setAlignment(Pos.CENTER_LEFT);
        mainBox.setAlignment(Pos.CENTER);
        bottomBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(20));
        bottomBox.setPadding(new Insets(15));
        topBox.setStyle("-fx-background-color: #339966");
        bottomBox.setStyle("-fx-background-color: #339966");

        window.setScene(new Scene(rootBorderPane,width,height));
        window.show();
    }

    public void screen1(){
        BorderPane rootBorderPane = new BorderPane();
        Label headerLabel = new Label("Marathon Skills 2015");
        HBox topBox = new HBox(headerLabel);
        HBox bottomBox = new HBox(countDownLabel);
        Button runnerButt = new Button("I want to be a runner");
        Button sponsorButt = new Button("I want to sponsor a runner");
        Button infoButt = new Button("I want to find out more");
        VBox buttonsBox = new VBox(runnerButt,sponsorButt,infoButt);
        Button loginButt = new Button("Login");
        HBox loginBox = new HBox(loginButt);
        VBox mainBox = new VBox(buttonsBox,loginBox);

        //=========proprieties==========
        headerLabel.setFont(Font.font("Open Sans", FontWeight.BOLD,40));
        rootBorderPane.setTop(topBox);
        rootBorderPane.setBottom(bottomBox);
        rootBorderPane.setCenter(mainBox);
        buttonsBox.setSpacing(40);
        infoButt.setMinSize(300,50);
        runnerButt.setMinSize(300,50);
        sponsorButt.setMinSize(300,50);
        topBox.setAlignment(Pos.CENTER);
        mainBox.setAlignment(Pos.CENTER);
        bottomBox.setAlignment(Pos.CENTER);
        buttonsBox.setAlignment(Pos.CENTER);
        loginBox.setAlignment(Pos.TOP_RIGHT);
        buttonsBox.setPadding(new Insets(40));
        loginBox.setPadding(new Insets(0,40,0,0));
        topBox.setPadding(new Insets(20));
        bottomBox.setPadding(new Insets(15));
        topBox.setStyle("-fx-background-color: #339966");
        bottomBox.setStyle("-fx-background-color: #339966");

        sponsorButt.setOnAction(val -> {
            try {
                screen6();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        infoButt.setOnAction(val -> screen10());

        window.setScene(new Scene(rootBorderPane,width,height));
        window.show();
    }

    public void screen6() throws SQLException {
        BorderPane rootBorderPane = new BorderPane();
        Label headerLabel = new Label("Marathon Skills 2015");
        Button backButton = new Button("Back");
        HBox topBox = new HBox(backButton,headerLabel);
        HBox bottomBox = new HBox(countDownLabel);
        Label titleLabel = new Label("Sponsor a runner");
        Text desc = new Text("Sponsor a runnerSponsor a runnerSponsor a runnerSponsor a runnerSponsor a runnerSponsor a runnerSponsor a runnerSponsor a runnerSponsor a runnerSponsor a runnerSponsor a runnerSponsor a runnerSponsor a runnerSponsor a runnerSponsor a runnerSponsor a runnerSponsor a runner");
        Label detDesc = new Label("Sponsor details");

        //left side--------------------------------
        //labels
        Label nameLabel = new Label("Your Name: ");
        Label runnerLabel = new Label("Runner: ");
        Label nameOnCardLabel = new Label("Name on Card: ");
        Label cardNumLabel = new Label("Credit Card #: ");
        Label expLabel = new Label("Expiry Date: ");
        Label cvcLabel = new Label("CVC: ");
        //fields
        TextField nameField = new TextField();
        ComboBox runnerCombo = new ComboBox();
        TextField nameOnCardField = new TextField();
        TextField cardNumField = new TextField();
        TextField expMField = new TextField();
        TextField expYField = new TextField();
        HBox expElement = new HBox(expMField,expYField);
        TextField CVCField = new TextField();
        nameField.setMinWidth(200);
        runnerCombo.setMinWidth(200);
        nameOnCardField.setMinWidth(200);
        cardNumField.setMinWidth(200);
        expMField.setMaxWidth(30);
        nameField.setMaxWidth(50);
        CVCField.setMaxWidth(50);

        //boxes
        VBox labelsBox = new VBox(nameLabel,runnerLabel,nameOnCardLabel,cardNumLabel,expLabel,cvcLabel);
        VBox fieldsBox = new VBox(nameField,runnerCombo,nameOnCardField,cardNumField,expElement,CVCField);
        HBox leftBothSides = new HBox(labelsBox,fieldsBox);
        leftBothSides.setSpacing(10);
        VBox leftSide = new VBox(detDesc,leftBothSides);
        leftSide.setAlignment(Pos.CENTER);
        //-----------------

        //right side--------------------------
        Label charityLabel = new Label("Charity");
        Label charityName = new Label();
        Button infoButt = new Button(" i ");
        HBox charityElement = new HBox(charityName,infoButt);
        Label amountToDonate = new Label("Amount to donate");
        Label amountLabel = new Label("$0");
        Button minusButt = new Button(" - ");
        Button plusButt = new Button("+");
        TextField amountField = new TextField("0");
        HBox amountElement = new HBox(minusButt,amountField,plusButt);
        Button payButt = new Button("pay now");
        Button cancelButt = new Button("Cancel");
        HBox buttonsBox = new HBox(payButt,cancelButt);
        VBox rightSide = new VBox(charityLabel,charityElement,amountToDonate,amountLabel,amountElement,buttonsBox);
        rightSide.setSpacing(20);
        buttonsBox.setSpacing(10);
        amountElement.setSpacing(5);
        rightSide.setAlignment(Pos.CENTER);
        amountElement.setAlignment(Pos.CENTER);
        buttonsBox.setAlignment(Pos.CENTER);
        charityElement.setAlignment(Pos.CENTER);
        infoButt.setVisible(false);
        //---------------------------------------------------
        HBox bothSides = new HBox(leftSide,rightSide);
        VBox mainBox = new VBox(titleLabel,desc,bothSides);

        //=========proprieties==========
        headerLabel.setFont(Font.font("Open Sans", FontWeight.SEMI_BOLD,24));
        rootBorderPane.setTop(topBox);
        rootBorderPane.setBottom(bottomBox);
        rootBorderPane.setCenter(mainBox);
        topBox.setSpacing(20);
        labelsBox.setSpacing(20);
        fieldsBox.setSpacing(10);
        charityElement.setSpacing(10);
        desc.setWrappingWidth(width-200);
        topBox.setAlignment(Pos.CENTER_LEFT);
        mainBox.setAlignment(Pos.TOP_CENTER);
        bottomBox.setAlignment(Pos.CENTER);
        bothSides.setAlignment(Pos.CENTER);
        labelsBox.setAlignment(Pos.CENTER_RIGHT);
        fieldsBox.setAlignment(Pos.CENTER_LEFT);
        topBox.setPadding(new Insets(20));
        mainBox.setPadding(new Insets(30));
        leftSide.setPadding(new Insets(30));
        topBox.setPadding(new Insets(20));
        bottomBox.setPadding(new Insets(15));
        topBox.setStyle("-fx-background-color: #339966");
        bottomBox.setStyle("-fx-background-color: #339966");


        //----------code----------------
        ResultSet runners = sqlExe("SELECT firstName, lastName,bibNumber,countryCode FROM ((runner INNER JOIN user ON user.email = runner.email) INNER JOIN registration ON registration.runnerId = runner.runnerId) INNER JOIN registrationEvent ON registrationEvent.registrationId = registration.registrationId");
        while (runners.next()) runnerCombo.getItems().add(runners.getString("lastName")+", "+runners.getString("firstName")+" - "+runners.getString("bibNumber")+" ("+runners.getString("countryCode")+")");

        runnerCombo.setOnAction(val -> {
            String bibNum = runnerCombo.getSelectionModel().getSelectedItem().toString();
            bibNum = bibNum.substring(bibNum.indexOf('-')+1,bibNum.indexOf('('));
            System.out.println(bibNum);

            try {
                ResultSet charity = sqlExe("SELECT charityName FROM (((registrationEvent INNER JOIN registration ON registrationEvent.registrationId = registration.registrationId ) INNER JOIN charity ON registration.charityId = charity.charityId)) WHERE bibNumber ="+bibNum+";");
                charity.next();
                charityName.setText(charity.getString("charityName"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            infoButt.setVisible(true);
        });
        payButt.setOnAction(val -> {
            Calendar expDate = Calendar.getInstance();
            expDate.set(Integer.parseInt(expYField.getText()),Integer.parseInt(expMField.getText()),0);

            boolean required = !nameField.getText().isEmpty() &&
                    !runnerCombo.getSelectionModel().isEmpty() &&
                    cardNumField.getText().length() == 16 &&
                    CVCField.getText().length() == 3 &&
                    expDate.getTimeInMillis() - System.currentTimeMillis() > 0;

            if (required){
                screen7(runnerCombo.getSelectionModel().getSelectedItem().toString(),charityName.getText(),amountLabel.getText());
            }
        });
        plusButt.setOnAction(val -> {
            Integer amount = Integer.parseInt(amountLabel.getText().substring(1));
            amount+=10;
            amountLabel.setText("$"+amount.toString());
            amountField.setText(amount.toString());

        });
        minusButt.setOnAction(val -> {
            Integer amount = Integer.parseInt(amountLabel.getText().substring(1));
            if (amount>=10)amount-=10;
            amountLabel.setText("$"+amount.toString());
            amountField.setText(amount.toString());

        });
        amountField.setOnAction(val -> {
            if(Integer.parseInt(amountField.getText()) > 0){
                Integer amount = Integer.parseInt(amountField.getText());
                amountLabel.setText("$"+amount.toString());
            }
        });
        infoButt.setOnAction(val -> {
            try {
                Stage charityInfo = new Stage();
                String charityString = charityName.getText();
                ResultSet charityInfoRS = sqlExe("SELECT charityDescription FROM charity WHERE charityName = '"+charityString+"'");
                charityInfoRS.next();
                Label charityInfoHeader = new Label(charityString);
                Text charityInfoText = new Text(charityInfoRS.getString("charityDescription"));
                VBox mainDialogBox = new VBox(charityInfoHeader,charityInfoText);

                mainDialogBox.setSpacing(15);
                charityInfoText.setWrappingWidth(300);

                charityInfo.setScene(new Scene(mainDialogBox,400,300));
                charityInfo.show();

            } catch (SQLException e) {
                e.printStackTrace();
            }


        });

        backButton.setOnAction(val -> screen1());
        cancelButt.setOnAction(val -> screen1());

        window.setScene(new Scene(rootBorderPane,width,height));
        window.show();
    }

    public void screen7(String info,String charityName,String amount){
        BorderPane rootBorderPane = new BorderPane();
        Label headerLabel = new Label("Marathon Skills 2015");
        Button backButton = new Button("Back");
        HBox topBox = new HBox(backButton,headerLabel);
        HBox bottomBox = new HBox(countDownLabel);
        Label thxLabel = new Label("Thank you for your sponsorship!");
        Text thxText = new Text("Thank you for your sponsorship again in Marathon Skillz 2019 Thank you for your sponsorship again in Marathon  Thank you for your sponsorship again in Marathon ");
        Label sponInfoLabel = new Label(info);
        Label charityNameLabel = new Label(charityName);
        Label amountLabel = new Label(amount);
        Button backButton2 = new Button("    Back    ");

        VBox mainBox = new VBox(thxLabel,thxText,sponInfoLabel,charityNameLabel,amountLabel,backButton2);

        //=========proprieties==========
        headerLabel.setFont(Font.font("Open Sans", FontWeight.SEMI_BOLD,24));
        rootBorderPane.setTop(topBox);
        rootBorderPane.setBottom(bottomBox);
        rootBorderPane.setCenter(mainBox);
        topBox.setSpacing(20);
        mainBox.setSpacing(30);
        thxText.setWrappingWidth(width-200);
        topBox.setAlignment(Pos.CENTER_LEFT);
        mainBox.setAlignment(Pos.CENTER);
        bottomBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(20));
        bottomBox.setPadding(new Insets(15));
        topBox.setStyle("-fx-background-color: #339966");
        bottomBox.setStyle("-fx-background-color: #339966");

        //-----

        backButton.setOnAction(val -> screen1());
        backButton2.setOnAction(val -> screen1());

        window.setScene(new Scene(rootBorderPane,width,height));
        window.show();
    }

    public void screen10(){
        BorderPane rootBorderPane = new BorderPane();
        Label headerLabel = new Label("Marathon Skills 2015");
        Button backButton = new Button("Back");
        HBox topBox = new HBox(backButton,headerLabel);
        HBox bottomBox = new HBox(countDownLabel);
        Label titleLabel = new Label("Find out more information");
        Button[] butts = {
                new Button("Marathon Skills 2019"),
        new Button("Previous race results"),
        new Button("BMI calculator"),
        new Button("How long is a marathon?"),
        new Button("List of charities"),
        new Button("BMR calculator")
        };

        VBox leftButts = new VBox(butts[0],butts[1],butts[2]);
        VBox rightButts = new VBox(butts[3],butts[4],butts[5]);
        HBox boffSides = new HBox(leftButts,rightButts);
        VBox mainBox = new VBox(titleLabel,boffSides);

        //=========proprieties==========
        headerLabel.setFont(Font.font("Open Sans", FontWeight.SEMI_BOLD,24));
        rootBorderPane.setTop(topBox);
        rootBorderPane.setBottom(bottomBox);
        rootBorderPane.setCenter(mainBox);
        topBox.setSpacing(20);
        mainBox.setSpacing(40);
        boffSides.setSpacing(30);
        rightButts.setSpacing(20);
        leftButts.setSpacing(20);
        topBox.setAlignment(Pos.CENTER_LEFT);
        mainBox.setAlignment(Pos.CENTER);
        bottomBox.setAlignment(Pos.CENTER);
        boffSides.setAlignment(Pos.CENTER);
        leftButts.setAlignment(Pos.CENTER);
        rightButts.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(20));
        bottomBox.setPadding(new Insets(15));
        topBox.setStyle("-fx-background-color: #339966");
        bottomBox.setStyle("-fx-background-color: #339966");
        for (Button b:butts) {
            b.setMinSize(250,50);
        }

        backButton.setOnAction(val -> screen1());

        butts[4].setOnAction(val -> screen13());

        window.setScene(new Scene(rootBorderPane,width,height));
        window.show();
    }

    public void screen13(){
        BorderPane rootBorderPane = new BorderPane();
        Label headerLabel = new Label("Marathon Skills 2015");
        Button backButton = new Button("Back");
        HBox topBox = new HBox(backButton,headerLabel);
        HBox bottomBox = new HBox(countDownLabel);
        VBox mainBox = new VBox();

        //=========proprieties==========
        headerLabel.setFont(Font.font("Open Sans", FontWeight.SEMI_BOLD,24));
        rootBorderPane.setTop(topBox);
        rootBorderPane.setBottom(bottomBox);
        rootBorderPane.setCenter(mainBox);
        topBox.setSpacing(20);
        topBox.setAlignment(Pos.CENTER_LEFT);
        mainBox.setAlignment(Pos.CENTER);
        bottomBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(20));
        bottomBox.setPadding(new Insets(15));
        topBox.setStyle("-fx-background-color: #339966");
        bottomBox.setStyle("-fx-background-color: #339966");

        window.setScene(new Scene(rootBorderPane,width,height));
        window.show();
    }

    public ResultSet sqlExe(String query) throws SQLException {
        Statement stmnt = conn.createStatement();
        System.out.println("Executing query...");
        return stmnt.executeQuery(query);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
