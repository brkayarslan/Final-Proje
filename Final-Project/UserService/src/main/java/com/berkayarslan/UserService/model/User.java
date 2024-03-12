package com.berkayarslan.UserService.model;


import com.berkayarslan.UserService.general.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
@Entity
@Table(name = "USER_TABLE")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "FIRST_NAME", length = 100, nullable = false)
    private String firstName;

    @NotBlank
    @Column(name = "LAST_NAME", length = 100, nullable = false)
    private String lastName;

    @NotBlank
    @Column(name = "TELEPHONE")
    private String telephone;

    @Email
    @NotBlank
    @Column(name = "E_MAIL")
    private String e_mail;

    @NotBlank(message = "PASSWORD CANNOT BE BLANK!")
    @Column(name = "PASSWORD", length = 400, nullable = false)
    private String password;

    @NotBlank
    @Column(name = "LATITUDE", nullable = false)
    private Double latitude;

    @NotBlank
    @Column(name = "LONGITUDE", nullable = false)
    private Double longitude;

}
