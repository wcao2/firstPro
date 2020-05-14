package com.ascendingdc.training.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name=" images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",columnDefinition = "SERIAL")
    private Long id;

    @Column(name="file_name")
    private String fileName;

    @Column(name="s3Key")
    private String s3Key;

    @Column(name="creation_time")
    private LocalDateTime createTime;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    public Image() {
    }

    public Image(String fileName, String s3Key, LocalDateTime createTime, Employee employee) {
        this.fileName = fileName;
        this.s3Key = s3Key;
        this.createTime = createTime;
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", s3Key='" + s3Key + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getS3Key() {
        return s3Key;
    }

    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
