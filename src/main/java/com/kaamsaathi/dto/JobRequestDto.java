package com.kaamsaathi.dto;

public class JobRequestDto {

    private String title;
    private String description;
    private String city;
    private String salary;
    private String phone;
    private Long createdBy;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Long getCreatedBy() { return createdBy; }
    public void setCreatedBy(Long createdBy) { this.createdBy = createdBy; }
}
