package lk.ijse.gdse68.CropMonitoringSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "field")
@Entity
public class FieldEntity implements SuperEntity {
    @Id
    private String fieldCode;
    private String fieldName;
    private Double extentSize;
    private String fieldLocation;

    @OneToMany(mappedBy = "field",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CropEntity> crops;

    @ManyToMany
    @JoinTable(name = "field_staff",
            joinColumns = @JoinColumn(name = "fieldCode"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<StaffEntity> allocatedStaff;
}
