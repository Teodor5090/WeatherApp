package com.bwap.weatherapp.WeatherApp.view;

import com.bwap.weatherapp.WeatherApp.controller.WeatherService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringUI(path = "")
public class MainView<cityName> extends UI {
    @Autowired
    private WeatherService weatherService;
    private VerticalLayout mainLayout;
    private NativeSelect<String> unitSelect;
    private NativeSelect<String> cityTextField;
    private Button searchButton;
    private Label location;
    private Label currentTemp;
    private Label weatherDescription;
    private Label weatherMin;
    private Label weatherMax;
    private Label pressureLabel;
    private Label humidityLabel;
    private Label windSpeedLabel;
    private Label feelsLike;
    private Image iconImg;
    private HorizontalLayout Dashboard;
    private HorizontalLayout mainDescriptionLayout;
    private Image logo;
    private HorizontalLayout footer;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setUpLayout();
        setHeader();
        setLogo();
        setForm();
        dashboardTitle();
        dashboardDetails();

        footer();
        searchButton.addClickListener(clickEvent -> {
            if (!cityTextField.getValue().equals("")) {
                try {
                    updateUI();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else
                Notification.show("Please Enter The City");
        });

    }

    public void setUpLayout() {
        logo = new Image();
        iconImg = new Image();
        iconImg.setWidth("200px");
        iconImg.setHeight("200px");
        mainLayout = new VerticalLayout();
        mainLayout.setWidth("100%");
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        mainLayout.setStyleName("BackColorGrey");
        setContent(mainLayout);
    }

    private void setHeader() {
        HorizontalLayout headerlayout = new HorizontalLayout();
        headerlayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Label Title = new Label("Weather APP");
        Title.addStyleName(ValoTheme.LABEL_H1);
        Title.addStyleName(ValoTheme.LABEL_BOLD);
        Title.addStyleName(ValoTheme.LABEL_COLORED);

        headerlayout.addComponents(Title);
        mainLayout.addComponents(headerlayout);

    }

    private void setLogo() {
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);

        logo.setSource(new ExternalResource(
                "data:image/png;base64,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"));
        logo.setWidth("300px");
        logo.setHeight("150px");
        logo.setVisible(true);

        logoLayout.addComponents(logo);
        mainLayout.addComponents(logoLayout);
    }

    private void setForm() {
    	String url = "jdbc:postgresql://localhost:5432/WeatherDB";
    	String username = "postgres";
    	String password = "a";
    	Connection connection = null;
    	try {
    	    connection = DriverManager.getConnection(url, username, password);
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}
    	
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        formLayout.setSpacing(true);
        formLayout.setMargin(true);

        unitSelect = new NativeSelect<>();
        unitSelect.setWidth("70px");
        ArrayList<String> items = new ArrayList<>();
        items.add("C");
        items.add("F");

        unitSelect.setItems(items);
        unitSelect.setValue(items.get(0));
        formLayout.addComponents(unitSelect);

       
     // cityTextField
        cityTextField = new NativeSelect<>();
        cityTextField.setWidth("80%");
        ArrayList<String> cities = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from public.\"Locations\" ;");

            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                // ... access other columns similarly
                cities.add(name);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        cityTextField.setItems(cities);
        cityTextField.setValue(cities.get(0));
        formLayout.addComponents(cityTextField);

        searchButton = new Button();
        searchButton.setIcon(VaadinIcons.SEARCH);
        formLayout.addComponent(searchButton);

        mainLayout.addComponents(formLayout);
    }

    private void dashboardTitle() {
        Dashboard = new HorizontalLayout();
        Dashboard.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        
        location = new Label("Currently in Bhakkar");
        location.addStyleName(ValoTheme.LABEL_H2);
        location.addStyleName(ValoTheme.LABEL_LIGHT);

      
        currentTemp = new Label("10F");
        currentTemp.setStyleName(ValoTheme.LABEL_BOLD);
        currentTemp.setStyleName(ValoTheme.LABEL_H1);
        Dashboard.addComponents(location, iconImg, currentTemp);

    }

    private void dashboardDetails() {
        mainDescriptionLayout = new HorizontalLayout();
        mainDescriptionLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

       
        VerticalLayout descriptionLayout = new VerticalLayout();
        descriptionLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

      
        weatherDescription = new Label("Description: Clear Skies");
        weatherDescription.setStyleName(ValoTheme.LABEL_SUCCESS);
        descriptionLayout.addComponents(weatherDescription);

        weatherMin = new Label("Min:53");
        descriptionLayout.addComponents(weatherMin);
    
        weatherMax = new Label("Max:22");
        descriptionLayout.addComponents(weatherMax);

      

        VerticalLayout pressureLayout = new VerticalLayout();
        pressureLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        pressureLabel = new Label("Pressure:123Pa");
        pressureLayout.addComponents(pressureLabel);

        humidityLabel = new Label("Humidity:34");
        pressureLayout.addComponents(humidityLabel);

        windSpeedLabel = new Label("124/hr");
        pressureLayout.addComponents(windSpeedLabel);

        feelsLike = new Label("FeelsLike:");
        pressureLayout.addComponents(feelsLike);

        mainDescriptionLayout.addComponents(descriptionLayout, pressureLayout);

    }



    private void footer() {
        footer = new HorizontalLayout();
        footer.setDefaultComponentAlignment(Alignment.BOTTOM_CENTER);
        footer.setSpacing(true);
        footer.setMargin(true);
        footer.setWidth("100%");
        footer.setHeight("40px");
        Label description = new Label();
        description.setValue(" ");
        footer.addComponents(description);
        mainLayout.addComponents(footer);
    }

    private void updateUI() throws JSONException {
      
        String city = cityTextField.getValue();
        String defaultUnit;
        weatherService.setCityName(city);

       
        if (unitSelect.getValue().equals("F")) {
            weatherService.setUnit("imperials");
            unitSelect.setValue("F");
            defaultUnit = "\u00b0" + "F";
        } else {
            weatherService.setUnit("metric");
            defaultUnit = "\u00b0" + "C";
            unitSelect.setValue("C");
        }

     
        location.setValue("Currently in " + city);
        JSONObject mainObject = weatherService.returnMainObject();
        int temp = mainObject.getInt("temp");
        currentTemp.setValue(temp + defaultUnit);

     
        String iconCode = null;
        String weatherDescriptionNew = null;
        JSONArray jsonArray = weatherService.returnWeatherArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject weatherObject = jsonArray.getJSONObject(i);
            iconCode = weatherObject.getString("icon");
            weatherDescriptionNew = weatherObject.getString("description");
            System.out.println(iconCode);
        }
        
        iconImg.setSource(new ExternalResource("http://openweathermap.org/img/wn/" + iconCode + "@2x.png"));
       
        logo.setSource(new ExternalResource("http://openweathermap.org/img/wn/" + iconCode + "@2x.png"));

      
        weatherDescription.setValue("Description: " + weatherDescriptionNew);

      
        weatherMax.setValue(
                "Max Temp: " + weatherService.returnMainObject().getInt("temp_max") + "\u00b0" + unitSelect.getValue());
        
        weatherMin.setValue(
                "Min Temp: " + weatherService.returnMainObject().getInt("temp_min") + "\u00b0" + unitSelect.getValue());
       
        pressureLabel.setValue("Pressure: " + weatherService.returnMainObject().getInt("pressure"));
       
        humidityLabel.setValue("Humidity: " + weatherService.returnMainObject().getInt("humidity"));

       
        windSpeedLabel.setValue("Wind: " + weatherService.returnWindObject().getInt("speed") + "m/s");
        
        feelsLike.setValue("Feelslike: " + weatherService.returnMainObject().getDouble("feels_like"));

        
    
        mainLayout.addComponents(Dashboard, mainDescriptionLayout, footer);
    }

}