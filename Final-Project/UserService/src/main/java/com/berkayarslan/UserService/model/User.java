package com.berkayarslan.UserService.model;


import com.berkayarslan.UserService.general.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
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


    @Column(name = "LATITUDE", nullable = false)
    private Double latitude;


    @Column(name = "LONGITUDE", nullable = false)
    private Double longitude;


    public User(String firstName, String lastName, String telephone, String e_mail, String password, Double latitude, Double longitude) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.e_mail = e_mail;
        this.password = password;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
