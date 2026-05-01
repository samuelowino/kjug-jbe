package com.kjug.boottask;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true,
            columnDefinition =
            "VARCHAR(50)",
            check = @CheckConstraint(
                    name = "username_constraint",
                    constraint = "length(username) >= 3 AND length(username) <= 50")
    ) // schema
    @Size(min = 3, max = 50) // validation
    @NotNull
    private String username;
    @Column(nullable = false,
            check = @CheckConstraint(
                    name = "password_constraint",
                    constraint = "length(password) >= 6"
            ))
    @NotNull
    @NotBlank
    private String password;
    @Column(nullable = false, unique = true,
        check = @CheckConstraint(
                name = "email_constraint",
                constraint = "email ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'"
        )
    )
    @NotBlank
    @Email
    @Pattern(regexp = "^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$")
    private String email;
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdated;
    public User(String name, String password, String email){
        this.username = name;
        this.password = password;
        this.email = email;
    }
}
