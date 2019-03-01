package com.example.o;

import java.util.List;

public class WeatherWeather {
    private Main main;
    private List<Weather> weather;
    private String name;

    public WeatherWeather(Main main, List<Weather> weather, String name) {
        this.main = main;
        this.weather = weather;
        this.name = name;
    }

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getName() {
        return name;
    }

    class Main {
        double temp;

        public Main(double temp) {
            this.temp = temp;
        }

        public double getTemp() {
            return temp;
        }
    }


}

