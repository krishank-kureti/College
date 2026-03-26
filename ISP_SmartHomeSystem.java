interface SmartDeviceBad {
    void turnOn();
    void turnOff();
    void setTemperature(double temperature);
    void playMusic(String song);
    void adjustBrightness(int level);
}

class SmartLightBad implements SmartDeviceBad {
    private boolean isOn;
    private int brightness;
    
    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Light is ON");
    }
    
    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Light is OFF");
    }
    
    @Override
    public void setTemperature(double temperature) {
        throw new UnsupportedOperationException("Light cannot set temperature");
    }
    
    @Override
    public void playMusic(String song) {
        throw new UnsupportedOperationException("Light cannot play music");
    }
    
    @Override
    public void adjustBrightness(int level) {
        brightness = level;
        System.out.println("Brightness set to: " + level + "%");
    }
}

class ThermostatBad implements SmartDeviceBad {
    private double currentTemp;
    
    @Override
    public void turnOn() {
        System.out.println("Thermostat is ON");
    }
    
    @Override
    public void turnOff() {
        System.out.println("Thermostat is OFF");
    }
    
    @Override
    public void setTemperature(double temperature) {
        currentTemp = temperature;
        System.out.println("Temperature set to: " + temperature + "°C");
    }
    
    @Override
    public void playMusic(String song) {
        throw new UnsupportedOperationException("Thermostat cannot play music");
    }
    
    @Override
    public void adjustBrightness(int level) {
        throw new UnsupportedOperationException("Thermostat has no brightness control");
    }
}

interface Switchable {
    void turnOn();
    void turnOff();
}

interface TemperatureControl {
    void setTemperature(double temperature);
    double getTemperature();
}

interface AudioPlayback {
    void playMusic(String song);
    void stopMusic();
}

interface BrightnessControl {
    void adjustBrightness(int level);
    int getBrightness();
}

class SmartLight implements Switchable, BrightnessControl {
    private boolean isOn;
    private int brightness;
    
    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Light is ON");
    }
    
    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Light is OFF");
    }
    
    @Override
    public void adjustBrightness(int level) {
        brightness = level;
        System.out.println("Brightness set to: " + level + "%");
    }
    
    @Override
    public int getBrightness() {
        return brightness;
    }
}

class Thermostat implements Switchable, TemperatureControl {
    private boolean isOn;
    private double currentTemp;
    
    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Thermostat is ON");
    }
    
    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Thermostat is OFF");
    }
    
    @Override
    public void setTemperature(double temperature) {
        currentTemp = temperature;
        System.out.println("Temperature set to: " + temperature + "°C");
    }
    
    @Override
    public double getTemperature() {
        return currentTemp;
    }
}

class SmartSpeaker implements Switchable, AudioPlayback, BrightnessControl {
    private boolean isOn;
    private String currentSong;
    private int displayBrightness;
    
    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Speaker is ON");
    }
    
    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Speaker is OFF");
    }
    
    @Override
    public void playMusic(String song) {
        currentSong = song;
        System.out.println("Playing: " + song);
    }
    
    @Override
    public void stopMusic() {
        System.out.println("Music stopped");
    }
    
    @Override
    public void adjustBrightness(int level) {
        displayBrightness = level;
        System.out.println("Display brightness set to: " + level + "%");
    }
    
    @Override
    public int getBrightness() {
        return displayBrightness;
    }
}

class SmartHub implements Switchable, TemperatureControl, AudioPlayback, BrightnessControl {
    private boolean isOn;
    private double temperature;
    private String currentSong;
    private int brightness;
    
    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("SmartHub is ON");
    }
    
    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("SmartHub is OFF");
    }
    
    @Override
    public void setTemperature(double temperature) {
        this.temperature = temperature;
        System.out.println("Hub temperature control: " + temperature + "°C");
    }
    
    @Override
    public double getTemperature() {
        return temperature;
    }
    
    @Override
    public void playMusic(String song) {
        currentSong = song;
        System.out.println("Hub playing: " + song);
    }
    
    @Override
    public void stopMusic() {
        System.out.println("Hub music stopped");
    }
    
    @Override
    public void adjustBrightness(int level) {
        brightness = level;
        System.out.println("Hub brightness: " + level + "%");
    }
    
    @Override
    public int getBrightness() {
        return brightness;
    }
}

public class ISP_SmartHomeSystem {
    public static void main(String[] args) {
        System.out.println("===== Interface Segregation Principle (ISP) =====\n");
        
        System.out.println("--- Smart Light ---");
        SmartLight light = new SmartLight();
        light.turnOn();
        light.adjustBrightness(75);
        light.getBrightness();
        
        System.out.println("\n--- Thermostat ---");
        Thermostat thermostat = new Thermostat();
        thermostat.turnOn();
        thermostat.setTemperature(22.5);
        System.out.println("Current temperature: " + thermostat.getTemperature() + "°C");
        
        System.out.println("\n--- Smart Speaker ---");
        SmartSpeaker speaker = new SmartSpeaker();
        speaker.turnOn();
        speaker.playMusic("Bohemian Rhapsody");
        speaker.adjustBrightness(50);
        
        System.out.println("\n--- Smart Hub (Full Featured) ---");
        SmartHub hub = new SmartHub();
        hub.turnOn();
        hub.setTemperature(20.0);
        hub.playMusic("Imagine");
        hub.adjustBrightness(100);
    }
}
