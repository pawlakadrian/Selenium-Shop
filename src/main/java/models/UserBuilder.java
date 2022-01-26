package models;

public final class UserBuilder {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public User build() {
        if (firstName.isEmpty()) {
            throw new IllegalStateException("Name cannot be empty");
        }
        if (lastName.isEmpty()) {
            throw new IllegalStateException("Last name cannot be empty");
        }
        if (email.isEmpty()) {
            throw new IllegalStateException("Email cannot be empty");
        }
        if (password.isEmpty()) {
            throw new IllegalStateException("Password cannot be empty");
        }

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}

