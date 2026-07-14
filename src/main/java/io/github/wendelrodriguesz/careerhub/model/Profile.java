package io.github.wendelrodriguesz.careerhub.model;

public class Profile {
    private String name;
    private String professionalTitle;
    private String summary;
    private String email;
    private String city;

    public Profile(
            String name,
            String professionalTitle,
            String summary,
            String email,
            String city
    ) {
        this.name = name;
        this.professionalTitle = professionalTitle;
        this.summary = summary;
        this.email = email;
        this.city = city;
    }

    public String getName() {
        return this.name;
    }

    public String getProfessionalTitle() {
        return this.professionalTitle;
    }

    public String getSummary() {
        return this.summary;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCity() {
        return this.city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
