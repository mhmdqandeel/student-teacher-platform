package com.qnadeel.springdemo.studentteacherpatform.entities.user;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@AttributeOverride(name = "userId",
        column = @Column(name = "admin_id")
)
public class Admin extends User{
}