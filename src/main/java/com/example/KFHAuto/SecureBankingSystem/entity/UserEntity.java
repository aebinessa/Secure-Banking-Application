package com.example.KFHAuto.SecureBankingSystem.entity;

import javax.persistence.*;

    @Entity
    @Table(name = "bank_users")
    public class UserEntity {

        @Id
        @Column(name = "id", nullable = false)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "user_name", nullable = false)
        private String username;

        @Column(name = "phone_number", nullable = false)
        private String phoneNumber;

        @Column(name = "email", nullable = false)
        private String email;


        @Column(name = "password",nullable = false)
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Column(name = "address",nullable = false)
        private String address;

        @OneToOne
        @JoinColumn(name = "role_id")
        private RoleEntity roles;
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return username;
        }

        public void setName(String username) {
            this.username = username;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }





        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public RoleEntity getRoles() {
            return roles;
        }

        public void setRoles(RoleEntity roles) {
            this.roles = roles;
        }
}
