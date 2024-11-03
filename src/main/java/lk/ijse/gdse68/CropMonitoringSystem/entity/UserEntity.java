//package lk.ijse.gdse68.CropMonitoringSystem.entity;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Table(name = "user")
//@Entity
//public class UserEntity implements SuperEntity {
//    @Id
//    @Column(unique = true)
//    private String email;
//    private String password;
//    private String role;
//}
package lk.ijse.gdse68.CropMonitoringSystem.entity;

import jakarta.persistence.*;
import lk.ijse.gdse68.CropMonitoringSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
@Entity
public class UserEntity implements SuperEntity {
    @Id
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
