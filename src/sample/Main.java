package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cpt02?useSSL=False","root","omar");
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
        loginButt.setOnAction(val -> screen3());
        runnerButt.setOnAction(val -> screen2());

        window.setScene(new Scene(rootBorderPane,width,height));
        window.show();
    }

    public void screen2(){
        BorderPane rootBorderPane = new BorderPane();
        Label headerLabel = new Label("Marathon Skills 2015");
        Button backButton = new Button("Back");
        HBox topBox = new HBox(backButton,headerLabel);
        HBox bottomBox = new HBox(countDownLabel);
        Button competedBeforeButt = new Button("I have competed before");
        Button newCompButt = new Button("I am a new competitor");
        VBox mainBox = new VBox(competedBeforeButt,newCompButt);

        //=========proprieties==========
        headerLabel.setFont(Font.font("Open Sans", FontWeight.SEMI_BOLD,24));
        rootBorderPane.setTop(topBox);
        rootBorderPane.setBottom(bottomBox);
        rootBorderPane.setCenter(mainBox);
        topBox.setSpacing(20);
        mainBox.setSpacing(20);
        topBox.setAlignment(Pos.CENTER_LEFT);
        mainBox.setAlignment(Pos.CENTER);
        bottomBox.setAlignment(Pos.CENTER);
        competedBeforeButt.setPrefSize(400,50);
        newCompButt.setPrefSize(400,50);
        topBox.setPadding(new Insets(20));
        bottomBox.setPadding(new Insets(15));
        topBox.setStyle("-fx-background-color: #339966");
        bottomBox.setStyle("-fx-background-color: #339966");

        newCompButt.setOnAction(val -> {
            try {
                screen4();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        window.setScene(new Scene(rootBorderPane,width,height));
        window.show();
    }

    public void screen3(){
        BorderPane rootBorderPane = new BorderPane();
        Label headerLabel = new Label("Marathon Skills 2015");
        Button backButton = new Button("Back");
        HBox topBox = new HBox(backButton,headerLabel);
        HBox bottomBox = new HBox(countDownLabel);
        Label titleLabel = new Label("Login Form");
        Text desc = new Text("Login FormLogin FormLogin FormLogin FormLogin FormLogin FormLogin FormLogin Form");
        Label emailLabel = new Label("Email: ");
        Label passLabel = new Label("Password: ");
        TextField emailField = new TextField("a.wenzinger@gmail.com");
        TextField passField = new TextField("u!!CqiDD");
        VBox labelsBox = new VBox(emailLabel,passLabel);
        VBox fieldsBox = new VBox(emailField,passField);
        HBox loginElement = new HBox(labelsBox,fieldsBox);
        Button loginButt = new Button("Login");
        Button cancelButt = new Button("Cancel");
        HBox buttsBox = new HBox(loginButt,cancelButt);
        VBox mainBox = new VBox(titleLabel,desc,loginElement,buttsBox);

        //=========proprieties==========
        headerLabel.setFont(Font.font("Open Sans", FontWeight.SEMI_BOLD,24));
        rootBorderPane.setTop(topBox);
        rootBorderPane.setBottom(bottomBox);
        rootBorderPane.setCenter(mainBox);
        topBox.setSpacing(20);
        mainBox.setSpacing(20);
        buttsBox.setSpacing(15);
        labelsBox.setSpacing(10);
        fieldsBox.setSpacing(5);

        topBox.setAlignment(Pos.CENTER_LEFT);
        mainBox.setAlignment(Pos.CENTER);
        bottomBox.setAlignment(Pos.CENTER);
        loginElement.setAlignment(Pos.CENTER);
        labelsBox.setAlignment(Pos.CENTER_RIGHT);
        fieldsBox.setAlignment(Pos.CENTER_LEFT);
        buttsBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(20));
        bottomBox.setPadding(new Insets(15));
        topBox.setStyle("-fx-background-color: #339966");
        bottomBox.setStyle("-fx-background-color: #339966");


        loginButt.setOnAction(val -> {
            try {
                ResultSet user = sqlExe("SELECT * FROM user WHERE email ='"+emailField.getText()+"' AND password = '"+passField.getText()+"';");
                user.next();
                String roleId = user.getString("roleId");
                switch (roleId){
                    case "R":
                        screen9();
                        break;
                    case "C":
                        screen19();
                        break;
                    case "A":
                        screen20();
                        break;


                }



            } catch (SQLException e) {
                e.printStackTrace();
            }


        });


        window.setScene(new Scene(rootBorderPane,width,height));
        window.show();
    }

    public void screen4() throws SQLException {
        BorderPane rootBorderPane = new BorderPane();
        Label headerLabel = new Label("Marathon Skills 2015");
        Button backButton = new Button("Back");
        HBox topBox = new HBox(backButton,headerLabel);
        HBox bottomBox = new HBox(countDownLabel);
        Label titleLabel = new Label("Register as a runner");
        Text descText = new Text("Please fill outPlease fill outPlease fill outPlease fill outPlease fill outPlease fill outPlease fill out");

        //left side
        Label emailLabel = new Label("Email: ");
        Label pwLabel = new Label("Password: ");
        Label pwALabel = new Label("Password Again: ");
        Label firstNLabel = new Label("First Name: ");
        Label lastNLabel = new Label("Last Name: ");
        VBox leftLabelsBox = new VBox(emailLabel,pwLabel,pwALabel,firstNLabel,lastNLabel);
        TextField emailField = new TextField();
        TextField pwField = new TextField();
        TextField pwAField = new TextField();
        TextField firstNField = new TextField();
        TextField lastNField = new TextField();
        VBox leftFieldsBox = new VBox(emailField,pwField,pwAField,firstNField,lastNField);
        HBox leftSide = new HBox(leftLabelsBox,leftFieldsBox);

        //right side
        Label genderLabel = new Label("Gender: ");
        Label DOBLabel = new Label("Date of Birth: ");
        Label countryLabel = new Label("Country: ");
        VBox rightlabelsBox = new VBox(genderLabel,DOBLabel,countryLabel);
        ComboBox genderCombo = new ComboBox();
        DatePicker DOBpicker = new DatePicker();
        ComboBox countryCombo = new ComboBox();
        VBox rightFieldsBox = new VBox(genderCombo,DOBpicker,countryCombo);
        HBox rightSide = new HBox(rightlabelsBox,rightFieldsBox);

        HBox bothSides = new HBox(leftSide,rightSide);
        Button regButt = new Button("Register");
        Button cancelButt = new Button("Cancel");
        HBox buttsBox = new HBox(regButt,cancelButt);
        VBox mainBox = new VBox(titleLabel,descText,bothSides,buttsBox);

        //=========proprieties==========
        headerLabel.setFont(Font.font("Open Sans", FontWeight.SEMI_BOLD,24));
        rootBorderPane.setTop(topBox);
        rootBorderPane.setBottom(bottomBox);
        rootBorderPane.setCenter(mainBox);
        topBox.setSpacing(20);
        mainBox.setSpacing(20);
        leftSide.setSpacing(10);
        rightSide.setSpacing(10);
        buttsBox.setSpacing(20);
        leftLabelsBox.setSpacing(12);
        leftFieldsBox.setSpacing(5);
        rightlabelsBox.setSpacing(12);
        rightFieldsBox.setSpacing(5);
        bothSides.setSpacing(40);
        topBox.setAlignment(Pos.CENTER_LEFT);
        mainBox.setAlignment(Pos.CENTER);
        bottomBox.setAlignment(Pos.CENTER);
        leftFieldsBox.setAlignment(Pos.CENTER_LEFT);
        leftLabelsBox.setAlignment(Pos.CENTER_RIGHT);
        leftSide.setAlignment(Pos.CENTER);
        rightFieldsBox.setAlignment(Pos.CENTER_LEFT);
        rightlabelsBox.setAlignment(Pos.CENTER_RIGHT);
        rightSide.setAlignment(Pos.CENTER);
        buttsBox.setAlignment(Pos.CENTER);
        bothSides.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(20));
        bottomBox.setPadding(new Insets(15));
        topBox.setStyle("-fx-background-color: #339966");
        bottomBox.setStyle("-fx-background-color: #339966");


        //----------sql-data-filling-----------
        ResultSet genders = sqlExe("SELECT * FROM gender");
        while (genders.next()) genderCombo.getItems().add(genders.getString("gender"));
        ResultSet countries = sqlExe("SELECT countryName FROM country");
        while (countries.next()) countryCombo.getItems().add(countries.getString("countryName"));

        //----------code-------------
        regButt.setOnAction(val -> {
            boolean allFieldsFilled = !emailField.getText().isEmpty() && !pwField.getText().isEmpty()
                    && !pwAField.getText().isEmpty()  && !firstNField.getText().isEmpty()
                    && !lastNField.getText().isEmpty()  && !genderCombo.getSelectionModel().isEmpty()
                    && !countryCombo.getSelectionModel().isEmpty() && DOBpicker.getValue() != null;
            boolean emailMatch = emailField.getText().matches("(.*)[@](.*)[.](.*)");
            boolean pwReq = pwField.getText().equals(pwAField.getText()) && pwField.getText().length()>5
                    && pwField.getText().matches(".[!@#$%^]") && pwField.getText().matches(".[\\d]");
            System.out.println(pwField.getText().matches("[!@#$%^]"));

            Calendar now = Calendar.getInstance();
            now.setTimeInMillis(System.currentTimeMillis());
            Calendar DOB = Calendar.getInstance();
            DOB.set(DOBpicker.getValue().getYear(), DOBpicker.getValue().getMonthValue(), DOBpicker.getValue().getDayOfMonth());
            boolean dateReq = now.get(Calendar.YEAR) - DOB.get(Calendar.YEAR) >= 10;
        });
        cancelButt.setOnAction(val -> {System.out.println(DOBpicker.getValue());});




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
//RUNNER
    public void screen9(){
        BorderPane rootBorderPane = new BorderPane();
        Label headerLabel = new Label("Marathon Skills 2015");
        Button backButton = new Button("Back");
        HBox topBox = new HBox(backButton,headerLabel);
        HBox bottomBox = new HBox(countDownLabel);
        Label titleLabel = new Label("Runner menu");
        Button[] butts = new Button[]{new Button("Register for an event"),
                new Button("My race results"),
                new Button("Edit your profile"),
                new Button("My sponsorship"),
                new Button("Contact information")};
        VBox leftButts = new VBox(butts[0],butts[2],butts[4]);
        VBox rightButts = new VBox(butts[1],butts[3]);
        HBox allButts = new HBox(leftButts,rightButts);
        VBox mainBox = new VBox(titleLabel,allButts);

        //=========proprieties==========
        headerLabel.setFont(Font.font("Open Sans", FontWeight.SEMI_BOLD,24));
        rootBorderPane.setTop(topBox);
        rootBorderPane.setBottom(bottomBox);
        rootBorderPane.setCenter(mainBox);
        topBox.setSpacing(20);
        mainBox.setSpacing(40);
        leftButts.setSpacing(20);
        rightButts.setSpacing(20);
        allButts.setSpacing(20);
        for (Button b:butts) b.setPrefSize(250,50);
        topBox.setAlignment(Pos.CENTER_LEFT);
        mainBox.setAlignment(Pos.CENTER);
        bottomBox.setAlignment(Pos.CENTER);
        leftButts.setAlignment(Pos.TOP_CENTER);
        rightButts.setAlignment(Pos.TOP_CENTER);
        allButts.setAlignment(Pos.TOP_CENTER);
        topBox.setPadding(new Insets(20));
        bottomBox.setPadding(new Insets(15));
        topBox.setStyle("-fx-background-color: #339966");
        bottomBox.setStyle("-fx-background-color: #339966");


        butts[4].setOnAction(val -> {
            Label contactTitleLabel = new Label("Contact Information");
            Text descText = new Text("For more info please contact blah blah blah blah blah blah blah blah blah blah blah ");
            Label phoneBoldLabel = new Label("Phone: ");
            Label emailBoldLabel = new Label("Email: ");
            Label phoneLabel = new Label("+966 65 8475 3887");
            Label emailLabel = new Label("coordinators@marathonskills.org");
            HBox phoneBox = new HBox(phoneBoldLabel,phoneLabel);
            HBox emailBox = new HBox(emailBoldLabel,emailLabel);
            VBox mainInfoBox = new VBox(contactTitleLabel,descText,phoneBox,emailBox);

            phoneBoldLabel.setFont(Font.font("",FontWeight.BOLD,12));
            emailBoldLabel.setFont(Font.font("",FontWeight.BOLD,12));
            mainInfoBox.setAlignment(Pos.CENTER);
            emailBox.setAlignment(Pos.CENTER);
            phoneBox.setAlignment(Pos.CENTER);
            mainInfoBox.setSpacing(20);

            Stage contactInfoStage = new Stage();
            contactInfoStage.setScene(new Scene(mainInfoBox,400,250));
            contactInfoStage.show();
        });

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

        butts[4].setOnAction(val -> {
            try {
                screen13();
            } catch (SQLException | FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        window.setScene(new Scene(rootBorderPane,width,height));
        window.show();
    }

    public void screen13() throws SQLException, FileNotFoundException {
        BorderPane rootBorderPane = new BorderPane();
        Label headerLabel = new Label("Marathon Skills 2015");
        Button backButton = new Button("Back");
        HBox topBox = new HBox(backButton,headerLabel);
        HBox bottomBox = new HBox(countDownLabel);
        Label titleLabel = new Label("List of charities");
        Text desc = new Text("List of charitiesList of charitiesList of charitiesList of charitiesList of charitiesList of charitiesList of charitiesList of charitiesList of charities");
        ScrollPane mainPane = new ScrollPane();
        VBox mainBox = new VBox(titleLabel,desc);

        //=========proprieties==========
        headerLabel.setFont(Font.font("Open Sans", FontWeight.SEMI_BOLD,24));
        mainPane.setContent(mainBox);
        rootBorderPane.setTop(topBox);
        rootBorderPane.setBottom(bottomBox);
        rootBorderPane.setCenter(mainPane);
        topBox.setSpacing(20);
        mainBox.setSpacing(20);
        topBox.setAlignment(Pos.CENTER_LEFT);
        mainBox.setAlignment(Pos.CENTER);
        bottomBox.setAlignment(Pos.CENTER);
        desc.setWrappingWidth(600);
        topBox.setPadding(new Insets(20));
        bottomBox.setPadding(new Insets(15));
        topBox.setStyle("-fx-background-color: #339966");
        bottomBox.setStyle("-fx-background-color: #339966");

        //------------code-------------------------
        ResultSet charities = sqlExe("SELECT * FROM charity");
        while (charities.next()){
            Label currName = new Label(charities.getString("charityName"));
            Text currDesc = new Text(charities.getString("charityDescription"));
            String currImagePath = charities.getString("charityLogo");
            ImageView currLogo = new ImageView(new Image(new FileInputStream("src/Images/"+currImagePath)));
            VBox descAndTitle = new VBox(currName,currDesc);
            HBox charityElement = new HBox(currLogo,descAndTitle);

            currLogo.setPreserveRatio(true);
            currLogo.setFitWidth(200);
            currName.setFont(new Font(15));
            currDesc.setWrappingWidth(500);
            charityElement.setSpacing(15);
            charityElement.setPadding(new Insets(5));
            charityElement.setAlignment(Pos.CENTER);
            charityElement.setStyle("-fx-border-color: black; -fx-border-width: 2;");
            descAndTitle.setSpacing(10);
            mainBox.getChildren().add(charityElement);
        }


        backButton.setOnAction(val -> screen10());

        window.setScene(new Scene(rootBorderPane,width,height));
        window.show();
    }
//COORD
    public void screen19(){
    BorderPane rootBorderPane = new BorderPane();
    Label headerLabel = new Label("Marathon Skills 2015");
    Button backButton = new Button("Back");
    HBox topBox = new HBox(backButton,headerLabel);
    HBox bottomBox = new HBox(countDownLabel);
    Label titleLabel = new Label("Coordinator menu");
    Button[] butts = new Button[]{new Button("Runners"),
            new Button("Sponsorship")};
    VBox leftButts = new VBox(butts[0]);
    VBox rightButts = new VBox(butts[1]);
    HBox allButts = new HBox(leftButts,rightButts);
    VBox mainBox = new VBox(titleLabel,allButts);

    //=========proprieties==========
    headerLabel.setFont(Font.font("Open Sans", FontWeight.SEMI_BOLD,24));
    rootBorderPane.setTop(topBox);
    rootBorderPane.setBottom(bottomBox);
    rootBorderPane.setCenter(mainBox);
    topBox.setSpacing(20);
    mainBox.setSpacing(40);
    leftButts.setSpacing(20);
    rightButts.setSpacing(20);
    allButts.setSpacing(20);
    for (Button b:butts) b.setPrefSize(250,50);
    topBox.setAlignment(Pos.CENTER_LEFT);
    mainBox.setAlignment(Pos.CENTER);
    bottomBox.setAlignment(Pos.CENTER);
    leftButts.setAlignment(Pos.TOP_CENTER);
    rightButts.setAlignment(Pos.TOP_CENTER);
    allButts.setAlignment(Pos.TOP_CENTER);
    topBox.setPadding(new Insets(20));
    bottomBox.setPadding(new Insets(15));
    topBox.setStyle("-fx-background-color: #339966");
    bottomBox.setStyle("-fx-background-color: #339966");

    window.setScene(new Scene(rootBorderPane,width,height));
    window.show();
}
//ADMIN
    public void screen20(){
    BorderPane rootBorderPane = new BorderPane();
    Label headerLabel = new Label("Marathon Skills 2015");
    Button backButton = new Button("Back");
    HBox topBox = new HBox(backButton,headerLabel);
    HBox bottomBox = new HBox(countDownLabel);
    Label titleLabel = new Label("Administrator menu");
    Button[] butts = new Button[]{new Button("Users"),
            new Button("Volunteers"),
            new Button("Charities"),
            new Button("Inventory")};
    VBox leftButts = new VBox(butts[0],butts[2]);
    VBox rightButts = new VBox(butts[1],butts[3]);
    HBox allButts = new HBox(leftButts,rightButts);
    VBox mainBox = new VBox(titleLabel,allButts);

    //=========proprieties==========
    headerLabel.setFont(Font.font("Open Sans", FontWeight.SEMI_BOLD,24));
    rootBorderPane.setTop(topBox);
    rootBorderPane.setBottom(bottomBox);
    rootBorderPane.setCenter(mainBox);
    topBox.setSpacing(20);
    mainBox.setSpacing(40);
    leftButts.setSpacing(20);
    rightButts.setSpacing(20);
    allButts.setSpacing(20);
    for (Button b:butts) b.setPrefSize(250,50);
    topBox.setAlignment(Pos.CENTER_LEFT);
    mainBox.setAlignment(Pos.CENTER);
    bottomBox.setAlignment(Pos.CENTER);
    leftButts.setAlignment(Pos.TOP_CENTER);
    rightButts.setAlignment(Pos.TOP_CENTER);
    allButts.setAlignment(Pos.TOP_CENTER);
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
