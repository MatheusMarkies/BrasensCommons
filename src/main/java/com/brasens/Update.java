package com.brasens;

public class Update {
    String version = "";
    String URL = "";

    @Override
    public String toString() {
        return "Update{" +
                "version='" + version + '\'' +
                ", URL='" + URL + '\'' +
                '}';
    }

    public Update() {
    }

    public Update(String version, String URL) {
        this.version = version;
        this.URL = URL;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}