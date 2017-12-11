package io.github.mapper.excel;

import java.util.Date;
import java.util.Objects;

public class UnAnnotizedModel {

    private String fistName;
    private String lastName;
    private Integer age;
    private String birthdate;
    private String fatherName;
    private Boolean iq;

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setIq(Boolean iq) {
        this.iq = iq;
    }

    public String getFistName() {
        return fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Boolean getIq() {
        return iq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnAnnotizedModel that = (UnAnnotizedModel) o;
        return Objects.equals(fistName, that.fistName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(age, that.age) &&
                Objects.equals(birthdate, that.birthdate) &&
                Objects.equals(fatherName, that.fatherName) &&
                Objects.equals(iq, that.iq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fistName, lastName, age, birthdate, fatherName, iq);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UnAnnotizedModel{");
        sb.append("fistName='").append(fistName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", age=").append(age);
        sb.append(", birthdate='").append(birthdate).append('\'');
        sb.append(", fatherName='").append(fatherName).append('\'');
        sb.append(", iq=").append(iq);
        sb.append('}');
        return sb.toString();
    }
}
