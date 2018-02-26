package app.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance
@DiscriminatorColumn(name = "person_type")
@JsonIgnoreProperties(value = {"created", "lastModified"}, allowGetters = true)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "PERSON")
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "field name is required")
    @Size(min = 4, max = 255, message = "field name must be between 4 and 255 characters")
    private String name;
    @NotNull(message = "field cpf is required")
    @CPF(message = "field cpf invalid")
    @Column(unique = true)
    private String cpf;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "field birth is required")
    private Date birth;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PERSON_HAS_ADDRESS",
               joinColumns = {@JoinColumn(name = "person_id")},
               inverseJoinColumns = {@JoinColumn(name = "address_id")})
    private Set<Address> address = new HashSet<>();
    @NotNull
    private char gender;
    @NotNull(message = "field email is required")
    @Email
    private String email;
    @NotNull(message = "field phone is required")
    @NotEmpty
    private String phone;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date created;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastModified;

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birth);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        this.birth = calendar.getTime();
        return birth;
    }

    public void setBirth(Date birth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birth);
        calendar.add(Calendar.DATE, 1);
        this.birth = calendar.getTime();
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

}
